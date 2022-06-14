package com.granduation.Service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.granduation.Entity.Users;


@Service
public interface IUserService extends UserDetailsService {

	public void processOAuthPostLogin(String email,String clientName);
	
	public void updateResetPasswordToken(String token, String email);
	public Users getByResetPasswordToken(String token);

	public void updatePassword(Users user, String newPassword);
	
	public Users findByUserName(String username);
	
	public List<Users> findAll();

	public void save(Users user);

	public void delete(int id);

	public Users getById(int id);
}
