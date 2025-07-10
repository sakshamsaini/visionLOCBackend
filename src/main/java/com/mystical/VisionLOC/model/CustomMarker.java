package com.mystical.VisionLOC.model;

import com.mystical.VisionLOC.dto.CreateMarkerDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "custom_marker")
public class CustomMarker {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(nullable = false)
	private String label;
	@Column(nullable = false)
	private Double latitude;
	@Column(nullable = false)
	private Double longitude;
	@Lob
	private byte[] image;
	private String imageType;
	@Column(nullable = false)
	private Long createdBy;

	public CustomMarker(Long signUpID, CreateMarkerDTO markerDTO) {
		this.label = markerDTO.getLabel();
		this.latitude = markerDTO.getLatitude();
		this.longitude = markerDTO.getLongitude();
		this.createdBy = signUpID;
	}
}
