package com.jwt.SecurityJWT.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/operation")
public class OperationsController 
{
	@GetMapping("/getdetails")
	ResponseEntity<?> getdetails()
	{
		return new ResponseEntity<>("GET DETAIL FUNCTION",HttpStatus.OK);
	}
	
	@GetMapping("/byid")
	ResponseEntity<?> getdetailsbyid()
	{
		return new ResponseEntity<>("GET DETAIL FUNCTION by id",HttpStatus.OK);
	}

}
