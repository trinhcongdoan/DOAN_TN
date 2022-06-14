package com.granduation.JPARepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.granduation.Entity.Users;

@Repository
public interface UserReponsitory extends JpaRepository<Users, Long> {

	@Query(value = "Select * from users where username = :username",nativeQuery = true)
	public Users getUserByUserName(@Param("username") String username);
	
	@Query(value = "SELECT * FROM users WHERE email = ?1", nativeQuery = true)
    public Users findByEmail(String email); 
	
	public Users findByResetPasswordToken(String token);
}
