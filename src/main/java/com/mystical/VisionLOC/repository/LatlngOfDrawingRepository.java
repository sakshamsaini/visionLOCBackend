package com.mystical.VisionLOC.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mystical.VisionLOC.model.LatlngOfDrawing;

@Repository
public interface LatlngOfDrawingRepository extends JpaRepository<LatlngOfDrawing, Long> {

}
