package com.granduation.Config.Security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.web.servlet.oauth2.client.OAuth2ClientSecurityMarker;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.granduation.Config.oauth.CustomOAuth2User;
import com.granduation.Config.oauth.CustomOAuth2UserService;
import com.granduation.Service.IUserService;

@OAuth2ClientSecurityMarker
@Configuration
@EnableWebSecurity
@Order(2)
@ComponentScan(basePackageClasses = SecurityConfig.class)
public class SecurityConfig2 extends WebSecurityConfigurerAdapter{

	@Autowired
    private CustomOAuth2UserService oauth2UserService;
	
	@Autowired
	private IUserService userService;
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		.authorizeRequests()
		.antMatchers("/thanh-toan","/gio-hang","pay/success","pay/cancel")
		.hasAnyAuthority("USER", "ADMIN")

		.and()
		.formLogin()
		.loginPage("/dang-nhap")
		.usernameParameter("username")
		.passwordParameter("password")
		.loginProcessingUrl("/login")
		.failureUrl("/dang-nhap?error=failed")
		.permitAll()
		
		.and()
        .oauth2Login()
            .loginPage("/login")
            .userInfoEndpoint()
                .userService(oauth2UserService)
                .and().successHandler(new AuthenticationSuccessHandler() {
                	 
                    @Override
                    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                            Authentication authentication) throws IOException, ServletException {
                    	
                        CustomOAuth2User oauthUser = (CustomOAuth2User) authentication.getPrincipal();
                        String clientName = oauthUser.getClientName();
                        userService.processOAuthPostLogin(oauthUser.getEmail(),clientName);
                        response.sendRedirect("/thanh-toan");
                    }
                }).permitAll()
		.and()
		.logout()
		.logoutUrl("/logout")
		.logoutSuccessUrl("/dang-nhap")
		.deleteCookies("JSESSIONID")

		.and()
		.rememberMe()
		.userDetailsService(userDetailsService())
        .tokenValiditySeconds(24*60*60)
        .rememberMeParameter("remember-me")
        .rememberMeCookieName("rememberUser")
		
		.and()
		.exceptionHandling()
		.accessDeniedPage("/403")
		
		.and().csrf().disable();
	}
}
