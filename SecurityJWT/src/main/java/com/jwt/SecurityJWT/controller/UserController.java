package com.jwt.SecurityJWT.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jwt.SecurityJWT.DTO.JwtResponse;
import com.jwt.SecurityJWT.DTO.UserRequest;
import com.jwt.SecurityJWT.DTO.logindto;
import com.jwt.SecurityJWT.JWTSecurity.JwtUtil;
import com.jwt.SecurityJWT.serviceImp.UserServiceImplementation;

@RestController
@RequestMapping("/api")
public class UserController 
{
    @Autowired
    private JwtUtil jwtUtil;
	
	@Autowired
	UserServiceImplementation service;
	
	@PostMapping("/createUser")
	public ResponseEntity<?> addUser(@RequestBody UserRequest dto)
	{

		return new ResponseEntity<>(service.addUser(dto),HttpStatus.OK);
	}
		
    @PostMapping("/login")
    public ResponseEntity<?> LoginandGenerate(@RequestBody logindto dto) 
    {
    	if(service.checkemailpassword(dto))
    	{
            final UserDetails userDetails = service.loadUserByUsername(dto.getEmail());
            String token = jwtUtil.generateToken(userDetails);
            JwtResponse jwt=new JwtResponse();
            jwt.setToken(token);
            jwt.setUsername(dto.getName());
            return new ResponseEntity<>(jwt,HttpStatus.OK);
    	}
    	else
    	{
            return new ResponseEntity<>("UserName or Password is Incorrect",HttpStatus.OK);

    	}

    }
}
