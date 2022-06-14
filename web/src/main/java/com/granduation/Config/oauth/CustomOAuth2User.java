package com.granduation.Config.oauth;

import java.util.Arrays;
import java.util.Collection;
import java.util.Map;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;

public class CustomOAuth2User implements OAuth2User {
	
	private String clientName;
    private OAuth2User oauth2User;
     
    public CustomOAuth2User(OAuth2User oauth2User,String clientName) {
        this.oauth2User = oauth2User;
        this.clientName = clientName;
        System.out.println("ấc" + clientName);
    }
 
    @Override
    public Map<String, Object> getAttributes() {
        return oauth2User.getAttributes();
    }
 
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Arrays.asList(new SimpleGrantedAuthority("USER"));
    }
 
    @Override
    public String getName() {  
        return oauth2User.<String>getAttribute("email");
    }
 
    public String getEmail() {
        return oauth2User.<String>getAttribute("email");     
    }
    
    public String getClientName() {
        return this.clientName;    
    }

	public String getUsername() {
		return oauth2User.<String>getAttribute("name");
	}

}
