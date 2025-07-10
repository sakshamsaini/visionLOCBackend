package com.mystical.VisionLOC.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mystical.VisionLOC.model.Camera;

@Repository
public interface CameraRepository extends JpaRepository<Camera, Long> {

	List<Camera> findAllByCreatedBy(Long signUpID);

}
