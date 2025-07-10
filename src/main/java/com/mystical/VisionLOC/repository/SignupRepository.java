package com.mystical.VisionLOC.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mystical.VisionLOC.model.Signup;

@Repository
public interface SignupRepository extends JpaRepository<Signup, Long> {

	Signup findByEmailAndPassword(String email, String password);

	Boolean existsByEmail(String email);
}
