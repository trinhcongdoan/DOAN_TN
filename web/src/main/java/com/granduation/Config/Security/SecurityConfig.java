package com.granduation.Config.Security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.granduation.Service.Impl.UserServiceImpl;


@Configuration
@EnableWebSecurity
@Order(1)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Bean
	public UserDetailsService userDetailsService() {
		return new UserServiceImpl();
	}

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		authProvider.setUserDetailsService(userDetailsService());
		authProvider.setPasswordEncoder(passwordEncoder());

		return authProvider;
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authenticationProvider());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.antMatcher("/admin**")
        .authorizeRequests()
        .antMatchers("/admin**")
        .hasAuthority("ADMIN")
        
        .and()
        .formLogin()
        .loginPage("/admin-dang-nhap")
        .usernameParameter("username")
        .passwordParameter("password")
        .loginProcessingUrl("/admin-login")
        .failureUrl("/admin-dang-nhap?error=failed")
        .defaultSuccessUrl("/admin")
        .permitAll()
        
        .and()
        .logout()
        .logoutUrl("/admin-logout")
        .logoutSuccessUrl("/admin-dang-nhap")
        .deleteCookies("JSESSIONID")
        
        .and().rememberMe().userDetailsService(userDetailsService())
        .tokenValiditySeconds(3*24*60*60).rememberMeParameter("remember")
        .rememberMeCookieName("rememberAdmin")
        
        .and()
        .exceptionHandling()
        .accessDeniedPage("/403")
        
        .and()
        .csrf().disable();
	}
}