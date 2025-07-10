package com.mystical.VisionLOC.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.mystical.VisionLOC.dto.CameraDTO;
import com.mystical.VisionLOC.service.CameraService;

@RestController
@CrossOrigin
public class CameraController {

	@Autowired
	private CameraService service;

	@PostMapping("/camera")
	public ResponseEntity<?> createCamera(@RequestHeader Long signUpID, @RequestBody CameraDTO cameraDTO) {
		return this.service.createCamera(signUpID, cameraDTO);
	}

	@GetMapping("/camera/{id}")
	public ResponseEntity<?> getCameraByID(@RequestHeader Long signUpID, @PathVariable Long id) {
		return this.service.getCameraByID(signUpID, id);
	}

	@PatchMapping("/camera/{id}")
	public ResponseEntity<?> updateCamera(@RequestHeader Long signUpID, @PathVariable Long id,
			@RequestBody CameraDTO cameraDTO) {
		return this.service.updateCamera(signUpID, id, cameraDTO);
	}

	@GetMapping("/cameras")
	public ResponseEntity<?> getCameraList(@RequestHeader Long signUpID) {
		return ResponseEntity.ok(service.getCameraList(signUpID));
	}

	@DeleteMapping("/camera/{id}")
	public ResponseEntity<?> deleteCamera(@RequestHeader Long signUpID, @PathVariable Long id) {
		return this.service.deleteCamera(signUpID, id);
	}

	@GetMapping("/detected-objects")
	public ResponseEntity<?> getDetectedObjectList(@RequestHeader Long signUpID) {
		return ResponseEntity.ok(service.getDetectedObjectList(signUpID));
	}

}
