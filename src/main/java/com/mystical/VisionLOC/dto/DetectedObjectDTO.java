package com.mystical.VisionLOC.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DetectedObjectDTO {

	private Long id;
	private String label;
	private Double latitude;
	private Double longitude;
	private String type;
	private String imageType;
	private byte[] imageBase64;

}
