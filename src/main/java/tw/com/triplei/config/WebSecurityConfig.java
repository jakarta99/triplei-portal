package tw.com.triplei.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.social.security.SocialUserDetailsService;
import org.springframework.social.security.SpringSocialConfigurer;

import tw.com.triplei.service.UserDetailsSocialService;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	
	@Override
	//@Order(1)
    protected void configure(HttpSecurity http) throws Exception {
		// spring social 登入
		springSocialConfigurer(http);  
		
		// 一般登入
		http.authorizeRequests().antMatchers("/product/buyProduct/**")
		.hasAnyRole("ADMIN","USER","NORMAL")
		.and().formLogin().loginPage("/login").permitAll();
		
        http.authenticationProvider(daoAuthenticationProvider())
        	.authorizeRequests()
        	.antMatchers(
                		"/", 
                		"/resources/**",
                		"/userfiles/**",
                		"/insurer/**", 
                		"/product/**", 
                		"/gift/**", 
                		"/article/**",
                		"/jolokia/**",
                		"/registered/**",
                		"/signin/**",
                		"/signup/**"
                		).permitAll().anyRequest().authenticated()
            .and().headers().frameOptions().sameOrigin()
            .and().formLogin().loginPage("/login").permitAll()
            .and().logout().permitAll();
        
        http.authorizeRequests().antMatchers("/fckeditor/**").hasRole("ADMIN").and().csrf().disable();

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
		
		provider.setPasswordEncoder(passwordEncoder());
		return provider;
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public SocialUserDetailsService socialUserDetailsService() {
		return new UserDetailsSocialService();
	}
	
	private void springSocialConfigurer(HttpSecurity http) throws Exception {
		http.apply(new SpringSocialConfigurer().defaultFailureUrl("/login?error"));
	}
	
	
}
