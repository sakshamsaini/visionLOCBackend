package com.mystical.VisionLOC.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BearingAndBackbearingDTO {
	private Long id;
	private Double bearing;
	private Double backBearing;
}
