package tw.com.triplei.config;

import java.util.List;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.RegexRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

import com.google.common.collect.Lists;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
   
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authenticationProvider(daoAuthenticationProvider())
        	.authorizeRequests()
        	.antMatchers(
                		"/", 
                		"/resources/**",
                		"/insurer/**", 
                		"/product/**", 
                		"/gift/**", 
                		"/article/**",
                		"/jolokia/**"
                		).permitAll().anyRequest().authenticated()
            .and().headers().frameOptions().sameOrigin()
            .and().formLogin().loginPage("/login").permitAll()
            .and().logout().permitAll();
    }
	
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        // 為特定的使用者綁定相對應的Role​​​
    	auth.inMemoryAuthentication()
        	.withUser("user").password("user1234").roles("USER")
        	.and().withUser("admin").password("admin1234").roles("ADMIN");
    }	
    
	@Bean
	public DaoAuthenticationProvider daoAuthenticationProvider() {
		// 抓到DB的user，使其可以進行登入
		final DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setUserDetailsService(userDetailsService);
		
		// TODO
//		provider.setPasswordEncoder(passwordEncoder());
		return provider;
	}
	
	// TODO　密碼隱碼
//	@Bean
//	public PasswordEncoder passwordEncoder() {
//		return new BCryptPasswordEncoder();
//	}
	

	
	

}
