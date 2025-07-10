package com.mystical.VisionLOC.model;

import java.util.List;

import com.mystical.VisionLOC.dto.CameraDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "camera")
public class Camera {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(nullable = false)
	private String label;
	@Column(nullable = false)
	private Double latitude;
	@Column(nullable = false)
	private Double longitude;
	@Column(nullable = false)
	private Double fovDeg;
	@Column(nullable = false)
	private Double focalLengthPx;
	@Column(nullable = false)
	private String cameraLink;
	@Column(nullable = false)
	private Long createdBy;

	@OneToMany(mappedBy = "camera")
	private List<Detections> detectionList;

	public Camera(Long signUpID, CameraDTO cameraDTO) {
		this.label = cameraDTO.getLabel();
		this.latitude = cameraDTO.getLatitude();
		this.longitude = cameraDTO.getLongitude();
		this.fovDeg = cameraDTO.getFovDeg();
		this.focalLengthPx = cameraDTO.getFocalLengthPx();
		this.cameraLink = cameraDTO.getCameraLink();
		this.createdBy = signUpID;
	}

	public Camera(Long signUpID, Long id, CameraDTO cameraDTO) {
		this.id = id;
		this.label = cameraDTO.getLabel();
		this.latitude = cameraDTO.getLatitude();
		this.longitude = cameraDTO.getLongitude();
		this.fovDeg = cameraDTO.getFovDeg();
		this.focalLengthPx = cameraDTO.getFocalLengthPx();
		this.cameraLink = cameraDTO.getCameraLink();
		this.createdBy = signUpID;
	}

}
