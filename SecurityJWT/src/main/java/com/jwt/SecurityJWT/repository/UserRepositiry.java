package com.jwt.SecurityJWT.repository;

import java.util.Optional;

import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import com.jwt.SecurityJWT.DTO.UserRequest;
import com.jwt.SecurityJWT.entity.UserEntity;


@Repository
public interface UserRepositiry extends JpaRepository<UserEntity, Long>
{

	Optional<UserEntity> findIdByEmail(String email);

//	 Optional<UserDetails> findByEmail(String email);
    Optional<UserEntity> findByPhoneNumber(String phoneNumber);

	
	
}
