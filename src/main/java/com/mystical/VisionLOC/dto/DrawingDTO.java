package com.mystical.VisionLOC.dto;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.mystical.VisionLOC.model.Drawing;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DrawingDTO {

	private Long id;
	private String shapeName;
	private String label;
	private Double area;
	private Double radius;
	private List<LatlngOfDrawingDTO> latlng;
	private List<BearingAndBackbearingDTO> bearAndbackBearing;

	public DrawingDTO(Drawing drawing) {
		this.id = drawing.getId();
		this.shapeName = drawing.getShapeName();
		this.label = drawing.getLabel();
		this.area = drawing.getArea();
		this.radius = drawing.getRadius();
		this.latlng = Optional.ofNullable(drawing.getLatlng()).orElse(Collections.emptyList()).stream()
				.map(latlng -> new LatlngOfDrawingDTO(latlng.getId(), latlng.getLat(), latlng.getLng()))
				.collect(Collectors.toList());

		this.bearAndbackBearing = Optional.ofNullable(drawing.getBearAndbackBearing()).orElse(Collections.emptyList())
				.stream().map(bAndBb -> new BearingAndBackbearingDTO(bAndBb.getId(), bAndBb.getBearing(),
						bAndBb.getBackBearing()))
				.collect(Collectors.toList());
	}
}
