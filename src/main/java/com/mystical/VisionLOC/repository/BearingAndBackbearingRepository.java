package com.mystical.VisionLOC.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mystical.VisionLOC.model.BearingAndBackbearing;

@Repository
public interface BearingAndBackbearingRepository extends JpaRepository<BearingAndBackbearing, Long> {

}
