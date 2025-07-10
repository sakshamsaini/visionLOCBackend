package com.mystical.VisionLOC.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.mystical.VisionLOC.dto.DrawingDTO;
import com.mystical.VisionLOC.service.CustomMarkerService;

@RestController
@CrossOrigin
public class CustomMarkerController {

	@Autowired
	private CustomMarkerService service;

	@PostMapping("/marker")
	public ResponseEntity<?> createMarker(@RequestHeader Long signUpID,
			@RequestPart(value = "filePayload", required = false) MultipartFile file,
			@RequestPart(value = "payload") String data) throws IOException {

		return this.service.createMarker(signUpID, file, data);
	}

	@GetMapping("/markers")
	public ResponseEntity<?> getMarkerList(@RequestHeader Long signUpID) {
		return ResponseEntity.ok(service.getMarkerList(signUpID));
	}

	@DeleteMapping("/marker/{id}")
	public ResponseEntity<?> deleteMarker(@RequestHeader Long signUpID, @PathVariable Long id) {
		return this.service.deleteMarker(signUpID, id);
	}

	@PostMapping("/drawing")
	public ResponseEntity<?> createDrawing(@RequestHeader Long signUpID, @RequestBody DrawingDTO drawingDTO) {
		return this.service.createDrawing(signUpID, drawingDTO);
	}

	@GetMapping("/drawings")
	public ResponseEntity<?> getDrawingList(@RequestHeader Long signUpID) {
		return ResponseEntity.ok(service.getDrawingList(signUpID));
	}

	@DeleteMapping("/drawing/{id}")
	public ResponseEntity<?> deleteDrawing(@RequestHeader Long signUpID, @PathVariable Long id) {
		return this.service.deleteDrawing(signUpID, id);
	}

}
