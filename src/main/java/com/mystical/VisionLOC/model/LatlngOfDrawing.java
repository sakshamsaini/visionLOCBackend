package com.mystical.VisionLOC.model;

import com.mystical.VisionLOC.dto.LatlngOfDrawingDTO;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@Table(name = "latlng_of_drawing")
public class LatlngOfDrawing {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@ManyToOne
	@JoinColumn(name = "drawing_id", nullable = false)
	private Drawing drawing;
	private Double lat;
	private Double lng;

	public LatlngOfDrawing(LatlngOfDrawingDTO latlng, Drawing drawing) {
		this.drawing = drawing;
		this.lat = latlng.getLat();
		this.lng = latlng.getLng();
	}

}
