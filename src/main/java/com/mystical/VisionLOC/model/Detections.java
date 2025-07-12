package com.mystical.VisionLOC.model;

import java.time.LocalDateTime;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@Table(name = "detections")
public class Detections {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@ManyToOne
	@JoinColumn(name = "camera_id", referencedColumnName = "id", nullable = false)
	private Camera camera;
	@Column(nullable = false)
	private String label;
	@Column(nullable = false)
	private String imagePath;
	@Column(nullable = false)
	private Long confidence;
	@OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "bounding_box_id	", referencedColumnName = "id")
	private BoundingBox boundingBox;
	@Column(nullable = false)
	private LocalDateTime timeStamp;

}
