package com.jwt.SecurityJWT.serviceImp;

import java.util.Collections;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.jwt.SecurityJWT.DTO.UserRequest;
import com.jwt.SecurityJWT.DTO.logindto;
import com.jwt.SecurityJWT.entity.UserEntity;
import com.jwt.SecurityJWT.repository.UserRepositiry;
import com.jwt.SecurityJWT.service.UserService;

@Service
public class UserServiceImplementation implements UserService,UserDetailsService
{
	
	PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    
    
	@Autowired
	UserRepositiry repo;
	
	public ResponseEntity<?> addUser(UserRequest dto)
	{
		UserEntity entity = new UserEntity();
		entity.setEmail(dto.getEmail());
		entity.setName(dto.getName());
		String encodedPassword = passwordEncoder.encode(dto.getPassword());
		entity.setPassword(encodedPassword);
		entity.setPhoneNumber(dto.getPhoneNumber());
		entity.setRoles("USER");
		repo.save(entity);
		return new ResponseEntity<>("Successfully Inserted",HttpStatus.OK);
	}

	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<UserEntity> opt = repo.findIdByEmail(email);
        if (opt.isEmpty()) {
            throw new UsernameNotFoundException("User with email: " + email + " not found!");
        } else {
            UserEntity user = opt.get();
            Set<SimpleGrantedAuthority> authorities = Collections.singleton(new SimpleGrantedAuthority(user.getRoles()));
            return new org.springframework.security.core.userdetails.User(
                    user.getEmail(),
                    user.getPassword(),
                    authorities
            );
        }
    }
	
	public boolean checkemailpassword(logindto ldto)
	{
		Optional<UserEntity> opt = repo.findIdByEmail(ldto.getEmail());
        if (opt.isEmpty()) 
        {
        	return false;
        }
        else
        {
        	UserEntity user = opt.get();
    		if(user.getEmail().equals(ldto.getEmail()) && passwordEncoder.matches(ldto.getPassword(), user.getPassword()))
    		{
    			return true;
    		}
    		return false;
		
        }
	
	

    }
}