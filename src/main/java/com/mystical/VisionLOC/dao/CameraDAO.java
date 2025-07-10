package com.mystical.VisionLOC.dao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.mystical.VisionLOC.model.Camera;
import com.mystical.VisionLOC.repository.CameraRepository;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class CameraDAO {

	private CameraRepository repository;

	public void save(Camera camera) {
		repository.save(camera);
	}

	public Camera getCameraByID(Long id) {
		return repository.findById(id).orElse(null);
	}

	public List<Camera> getAllCameras(Long signUpID) {
		return repository.findAllByCreatedBy(signUpID);
	}

	public void delete(Camera camera) {
		repository.delete(camera);
	}

}
