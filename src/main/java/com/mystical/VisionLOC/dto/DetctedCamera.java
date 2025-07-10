package com.mystical.VisionLOC.dto;

import java.util.List;

import lombok.Data;

@Data
public class DetctedCamera {

	private String camera_id;
	private Double latitude;
	private Double longitude;
	private int image_width;
	private int image_height;
	private Double fov_deg;
	private Double focal_length_px;
	private List<DetectionCamera> detections;
}
