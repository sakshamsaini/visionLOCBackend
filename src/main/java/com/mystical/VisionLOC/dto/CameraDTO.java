package com.mystical.VisionLOC.dto;

import java.util.List;

import com.mystical.VisionLOC.model.Camera;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CameraDTO {

	private Long id;
	private String label;
	private Double latitude;
	private Double longitude;
	private Double fovDeg;
	private Double focalLengthPx;
	private String cameraLink;
	private List<DetectedObjectDTO> detections;

	public CameraDTO(Camera cam) {
		this.id = cam.getId();
		this.label = cam.getLabel();
		this.latitude = cam.getLatitude();
		this.longitude = cam.getLongitude();
		this.fovDeg = cam.getFovDeg();
		this.focalLengthPx = cam.getFocalLengthPx();
		this.cameraLink = cam.getCameraLink();
	}

}
