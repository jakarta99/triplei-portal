package tw.com.triplei.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.social.UserIdSource;
import org.springframework.social.config.annotation.ConnectionFactoryConfigurer;
import org.springframework.social.config.annotation.EnableSocial;
import org.springframework.social.config.annotation.SocialConfigurer;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.ConnectionSignUp;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.connect.jdbc.JdbcUsersConnectionRepository;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.User;
import org.springframework.social.facebook.connect.FacebookConnectionFactory;
import org.springframework.social.security.AuthenticationNameUserIdSource;

import lombok.extern.slf4j.Slf4j;
import tw.com.triplei.entity.UserEntity;
import tw.com.triplei.service.AdminUserService;

/**
 * 
 * 	連到FB
 *
 */
@Slf4j
@EnableSocial
@Configuration
//@PropertySource("classpath:FBUserConnection.sql")
public class SocialConfiguration implements SocialConfigurer{
	
	@Autowired
	private DataSource dataSource;
	
	@Autowired
	private AdminUserService adminUserService;
	

	@Override
	public void addConnectionFactories(ConnectionFactoryConfigurer cfConfig, Environment env) {
		// 連結至FB、設置在properties檔
		//cfConfig.addConnectionFactory(new FacebookConnectionFactory("697474003783661","71a699c151bdb40341bfcebb0758510e"));
	}

	@Override
	public UserIdSource getUserIdSource() {
		// 找到FB的UserId那個人，pom要有spring-social-security
		return new AuthenticationNameUserIdSource();  
	}

	@Override
	public UsersConnectionRepository getUsersConnectionRepository(ConnectionFactoryLocator connectionFactoryLocator) {
		// 使用dataSource連結DB、建立Facebook Table
		JdbcUsersConnectionRepository repository = new JdbcUsersConnectionRepository(
		        dataSource,
		        connectionFactoryLocator,
		        Encryptors.noOpText()
		    );
		repository.setConnectionSignUp(connectionSignUp());
		repository.setTablePrefix("FB"); // 預設DB名稱為UserConnection，若Table名字有改，則要加 Prefix
	    return repository;
	}

	public ConnectionSignUp connectionSignUp() {
		//第一次FB進來時的處理
		return new ConnectionSignUp() {
			@Override
			public String execute(Connection<?> connection) {
				UserEntity entity = new UserEntity(connection); 
				
				final Facebook api = (Facebook) connection.getApi();
				String [] fields = { "id", "email", "first_name", "gender", "last_name" };
				User userProfile = api.fetchObject("me", User.class, fields);
				
				//entity.setAccountNumber(userProfile.getEmail()); // 不能用 用email 做accounNumber，有些人的email抓不到
				entity.setName(userProfile.getLastName() + userProfile.getFirstName());
				entity.setEmail(userProfile.getEmail());
				entity.setGender(userProfile.getGender());
				log.debug("userProfile.getEmail {}", userProfile.getEmail());
				adminUserService.insert(entity);
		        log.info("New social user signin: {} - {}", entity.getName(), entity.getAccountNumber());
				return connection.getKey().getProviderUserId();
			}
		};
	};
}
