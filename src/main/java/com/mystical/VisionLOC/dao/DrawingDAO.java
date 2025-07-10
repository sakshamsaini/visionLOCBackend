package com.mystical.VisionLOC.dao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.mystical.VisionLOC.model.Drawing;
import com.mystical.VisionLOC.repository.DrawingRepository;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class DrawingDAO {

	private DrawingRepository repository;

	public Drawing save(Drawing drawing) {
		return repository.save(drawing);
	}

	public List<Drawing> getAllDrawings(Long signUpID) {
		return repository.findAllByCreatedBy(signUpID);
	}

	public Drawing getDrawingByID(Long id) {
		return repository.findById(id).orElse(null);
	}

	public void delete(Drawing drawing) {
		repository.delete(drawing);
	}

}
