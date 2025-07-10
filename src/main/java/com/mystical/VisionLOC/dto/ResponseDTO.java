package com.mystical.VisionLOC.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.mystical.VisionLOC.model.Signup;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseDTO {

	private Long id;
	private User user;

	public ResponseDTO(Long id) {
		this.id = id;
	}

	public ResponseDTO(Signup signup) {
		this.id = signup.getId();
		this.user = new User(signup.getId(), signup.getName(), signup.getEmail());
	}

	@Data
	@NoArgsConstructor
	@AllArgsConstructor
	private class User {
		private Long id;
		private String name;
		private String email;
	}
}
