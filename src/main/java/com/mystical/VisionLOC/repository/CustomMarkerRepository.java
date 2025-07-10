package com.mystical.VisionLOC.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mystical.VisionLOC.model.CustomMarker;

@Repository
public interface CustomMarkerRepository extends JpaRepository<CustomMarker, Long> {

	List<CustomMarker> findAllByCreatedBy(Long signUpID);

}
