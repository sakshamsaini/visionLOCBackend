package com.mystical.VisionLOC.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class SignupDTO {

	@NotBlank
	private String fullName;
	@NotBlank
	private String email;
	@NotBlank
	private String password;
}
