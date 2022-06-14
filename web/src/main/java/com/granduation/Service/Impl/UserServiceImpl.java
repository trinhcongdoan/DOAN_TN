package com.granduation.Service.Impl;

import java.util.List;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.granduation.Entity.MyUserDetails;
import com.granduation.Entity.ProviderAuth;
import com.granduation.Entity.Users;
import com.granduation.JPARepository.AuthorizationReponsitory;
import com.granduation.JPARepository.UserReponsitory;
import com.granduation.Service.IUserService;


@Service
public class UserServiceImpl implements IUserService{

	@Autowired
	private UserReponsitory userReponsitory;
	
	@Autowired
	private AuthorizationReponsitory auth_repo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Users user = userReponsitory.getUserByUserName(username);
		if(user == null) {
			throw new UsernameNotFoundException("Khong tim thay " + username);
		}
		return new MyUserDetails(user);
	}

	@Override
	public void processOAuthPostLogin(String username,String clientName) {
		Users existUser = userReponsitory.getUserByUserName(username);
        
        if (existUser == null) {
            Users newUser = new Users();
            newUser.setUsername(username);
            if(clientName.toUpperCase().equals("FACEBOOK")) {
            	newUser.setProvider(ProviderAuth.FACEBOOK);
            }
            else if(clientName.toUpperCase().equals("GOOGLE")) {
            	newUser.setProvider(ProviderAuth.GOOGLE);
            }
            newUser.setEmail(username);
            newUser.setEnable(true);
            newUser.setFirstname(randomAlphabetic(8));
            newUser.setLastname(randomAlphabetic(8));
            newUser.setPhone('0' + randomNumber(9));
            newUser.setAddress(randomAlphabetic(10));
            newUser.setWard(randomAlphabetic(8));
            newUser.setDistrict(randomAlphabetic(8));
            newUser.setProvince(randomAlphabetic(8));
            newUser.setPassword(randomAlphabetic(16));
            newUser.setAuthorization(auth_repo.findByRole("USER"));
            
            userReponsitory.save(newUser);     
        }
		
	}
	private String randomAlphabetic(int i) {
		return RandomStringUtils.randomAlphanumeric(8);
	}
	private String randomNumber(int i) {
		return RandomStringUtils.randomNumeric(i);
	}

	@Override
	public void updateResetPasswordToken(String token, String email) {
		Users user = userReponsitory.findByEmail(email);
        if (user != null) {
        	user.setResetPasswordToken(token);
        	userReponsitory.save(user);
        } else {
            throw new UsernameNotFoundException("Could not find any user with the email " + email);
        }
	}

	@Override
	public Users getByResetPasswordToken(String token) {
		return userReponsitory.findByResetPasswordToken(token);
	}

	@Override
	public void updatePassword(Users user, String newPassword) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(newPassword);
        user.setPassword(encodedPassword);
         
        user.setResetPasswordToken(null);
        userReponsitory.save(user);
	}

	@Override
	public Users findByUserName(String username) {
		return userReponsitory.getUserByUserName(username);
	}

	@Override
	public List<Users> findAll() {
		return userReponsitory.findAll();
	}

	@Override
	public void save(Users user) {
		userReponsitory.save(user);
	}

	@Override
	public void delete(int id) {
		userReponsitory.deleteById((long) id);
	}

	@Override
	public Users getById(int id) {
		return userReponsitory.getById((long) id);
	}

}
