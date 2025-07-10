package com.mystical.VisionLOC.model;

import com.mystical.VisionLOC.dto.SignupDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@Table(name = "sign_up", uniqueConstraints = @UniqueConstraint(columnNames = { "email" }))
public class Signup {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(nullable = false)
	private String name;
	@Column(nullable = false)
	private String email;
	@Column(nullable = false)
	private String password;

	public Signup(SignupDTO signupDTO) {
		this.name = signupDTO.getFullName();
		this.email = signupDTO.getEmail();
		this.password = signupDTO.getPassword();
	}

}
