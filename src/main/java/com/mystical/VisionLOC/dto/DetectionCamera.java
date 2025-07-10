package com.mystical.VisionLOC.dto;

import com.mystical.VisionLOC.model.BoundingBox;

import lombok.Data;

@Data
public class DetectionCamera {

	private String label;
	private BoundingBox bbox;
	private String timestamp;
	private String type;
	private String imagePath;

}
