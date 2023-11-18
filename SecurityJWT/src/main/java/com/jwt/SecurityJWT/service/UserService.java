package com.jwt.SecurityJWT.service;

import org.springframework.http.ResponseEntity;

import com.jwt.SecurityJWT.DTO.UserRequest;
import com.jwt.SecurityJWT.DTO.logindto;

public interface UserService 
{
	public ResponseEntity<?> addUser(UserRequest dto);
	public boolean checkemailpassword(logindto ldto);

}
