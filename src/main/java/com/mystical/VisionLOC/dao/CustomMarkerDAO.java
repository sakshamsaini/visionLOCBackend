package com.mystical.VisionLOC.dao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.mystical.VisionLOC.model.CustomMarker;
import com.mystical.VisionLOC.repository.CustomMarkerRepository;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class CustomMarkerDAO {

	private CustomMarkerRepository repository;

	public CustomMarker save(CustomMarker marker) {
		return repository.save(marker);
	}

	public List<CustomMarker> getAllMarkers(Long signUpID) {
		return repository.findAllByCreatedBy(signUpID);
	}

	public CustomMarker getMarkerByID(Long id) {
		return repository.findById(id).orElse(null);
	}

	public void delete(CustomMarker marker) {
		repository.delete(marker);
	}

}
