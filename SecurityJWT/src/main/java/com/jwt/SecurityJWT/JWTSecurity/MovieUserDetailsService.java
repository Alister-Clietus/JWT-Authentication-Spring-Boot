package com.jwt.SecurityJWT.JWTSecurity;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.jwt.SecurityJWT.entity.UserEntity;
import com.jwt.SecurityJWT.repository.UserRepositiry;
	
	public class MovieUserDetailsService implements UserDetailsService 
	{
	
		@Autowired
		UserRepositiry repo;
		
		@Override
		public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException 
		{
			
	        if (usernameExistsInYourSystem(username)) 
	        {

	            return buildUserDetails(username);
	        } 
	        else 
	        {
	            
	            throw new UsernameNotFoundException("Username not found: " + username);
	        }
		}
		
	    private boolean usernameExistsInYourSystem(String username) 
	    {
	        Optional<UserEntity> opt = repo.findIdByEmail(username);
	        if(opt!=null)
	        {
	        	UserEntity details=opt.get();
	        	return true;
	        }
	        else
	        {
	        	return false;
	        }
	        
	    }
	    
	    private UserDetails buildUserDetails(String username) 
	    {
	    	Optional<UserEntity> opt = repo.findIdByEmail(username);
	    	UserEntity details=opt.get();
	    	return (UserDetails) details;
	    }
	}
