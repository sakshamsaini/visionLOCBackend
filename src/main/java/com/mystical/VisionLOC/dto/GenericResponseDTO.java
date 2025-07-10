package com.mystical.VisionLOC.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GenericResponseDTO {

	private String title;
	private String message;
	private Object response;

	public GenericResponseDTO(String title, String message) {
		this.title = title;
		this.message = message;
	}

}
