package com.mystical.VisionLOC.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mystical.VisionLOC.dto.LoginDTO;
import com.mystical.VisionLOC.dto.SignupDTO;
import com.mystical.VisionLOC.service.LoginService;

import jakarta.validation.Valid;

@RestController
@CrossOrigin
public class LoginController {

	@Autowired
	private LoginService service;

	@PostMapping("/sign-up")
	public ResponseEntity<?> signup(@RequestBody @Valid SignupDTO signupDTO) {
		return this.service.signup(signupDTO);
	}

	@PostMapping("/login")
	public ResponseEntity<?> loginUser(@RequestBody @Valid LoginDTO loginDTO) {
		return this.service.loginUser(loginDTO);
	}
}
