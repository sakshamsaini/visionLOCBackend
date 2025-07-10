package com.mystical.VisionLOC.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.mystical.VisionLOC.dao.LoginDAO;
import com.mystical.VisionLOC.dto.LoginDTO;
import com.mystical.VisionLOC.dto.ResponseDTO;
import com.mystical.VisionLOC.dto.SignupDTO;
import com.mystical.VisionLOC.model.Signup;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class LoginService {

	private LoginDAO dao;

	public ResponseEntity<?> signup(SignupDTO signupDTO) {
		Signup signup = new Signup(signupDTO);
		dao.save(signup);
		return ResponseEntity.ok(GenericResponseService.getSuccessResponse("Signup successfully done"));
	}

	public ResponseEntity<?> loginUser(LoginDTO loginDTO) {
		Signup signup = dao.getByEmailAndPassword(loginDTO);
		if (signup == null) {
			Boolean isEmailExists = dao.isEmailExists(loginDTO.getUserName());
			if (!isEmailExists) {
				return ResponseEntity.ok(GenericResponseService.getFailureResponse("Email does not exists"));
			} else {
				return ResponseEntity.ok(GenericResponseService.getFailureResponse("Password is incorrect"));
			}
		} else {
			return ResponseEntity
					.ok(GenericResponseService.getSuccessResponse("Login successfully", new ResponseDTO(signup)));
		}

	}

}
