package com.mystical.VisionLOC.service;

import org.springframework.stereotype.Component;

import com.mystical.VisionLOC.dto.GenericResponseDTO;

@Component
public class GenericResponseService {

	public static final String TITLE_SUCCESS = "Success";
	public static final String TITLE_ERROR = "Error";

	public static GenericResponseDTO getSuccessResponse(String message) {
		GenericResponseDTO dto = new GenericResponseDTO(TITLE_SUCCESS, message);
		return dto;
	}

	public static GenericResponseDTO getSuccessResponse(String message, Object response) {
		GenericResponseDTO dto = new GenericResponseDTO(TITLE_SUCCESS, message, response);
		return dto;
	}

	public static GenericResponseDTO getFailureResponse(String message) {
		GenericResponseDTO dto = new GenericResponseDTO(TITLE_ERROR, message);
		return dto;
	}

}
