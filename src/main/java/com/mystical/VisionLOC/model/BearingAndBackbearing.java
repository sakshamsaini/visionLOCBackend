package com.mystical.VisionLOC.model;

import com.mystical.VisionLOC.dto.BearingAndBackbearingDTO;

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
@Table(name = "bearing_and_backbearing")
public class BearingAndBackbearing {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@ManyToOne
	@JoinColumn(name = "drawing_id", nullable = false)
	private Drawing drawing;
	private Double bearing;
	private Double backBearing;
	
	public BearingAndBackbearing(BearingAndBackbearingDTO bAndBb, Drawing drawing) {
		this.drawing = drawing;
		this.bearing = bAndBb.getBearing();
		this.backBearing = bAndBb.getBackBearing();
	}
}
