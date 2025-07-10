package com.mystical.VisionLOC.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@Table(name = "drawing")
public class Drawing {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(nullable = false)
	private String shapeName;
	@Column(nullable = false)
	private String label;
	@Column(nullable = true)
	private Double area;
	@Column(nullable = true)
	private Double radius;
	@Column(nullable = false)
	private Long createdBy;

	@OneToMany(mappedBy = "drawing", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<LatlngOfDrawing> latlng;

	@OneToMany(mappedBy = "drawing", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<BearingAndBackbearing> bearAndbackBearing;

	public Drawing(Long signUpID, String shapeName, String label, Double area, Double radius) {
		super();
		this.shapeName = shapeName;
		this.label = label;
		this.area = area;
		this.radius = radius;
		this.createdBy = signUpID;
	}

}
