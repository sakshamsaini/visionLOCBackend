package com.mystical.VisionLOC.dto;

import com.mystical.VisionLOC.model.CustomMarker;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateMarkerDTO {

	private Long id;
	private String label;
	private Double latitude;
	private Double longitude;
	private byte[] image;
	private String imageType;

	public CreateMarkerDTO(CustomMarker marker) {
		this.id = marker.getId();
		this.label = marker.getLabel();
		this.latitude = marker.getLatitude();
		this.longitude = marker.getLongitude();
		this.image = marker.getImage();
		this.imageType = marker.getImageType();
	}

}
