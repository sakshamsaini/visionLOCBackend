package com.mystical.VisionLOC.dao;

import org.springframework.stereotype.Component;

import com.mystical.VisionLOC.dto.LoginDTO;
import com.mystical.VisionLOC.model.Signup;
import com.mystical.VisionLOC.repository.SignupRepository;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class LoginDAO {

	private SignupRepository repository;

	public void save(Signup signup) {
		repository.save(signup);
	}

	public Signup getByEmailAndPassword(LoginDTO loginDTO) {
		return repository.findByEmailAndPassword(loginDTO.getUserName(), loginDTO.getPassword());
	}

	public Boolean isEmailExists(String userName) {
		return repository.existsByEmail(userName);
	}

}
