package com.mystical.VisionLOC.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LatlngOfDrawingDTO {
	private Long id;
	private Double lat;
	private Double lng;
}
