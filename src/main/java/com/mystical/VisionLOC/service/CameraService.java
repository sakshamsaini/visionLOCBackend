package com.mystical.VisionLOC.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mystical.VisionLOC.dao.CameraDAO;
import com.mystical.VisionLOC.dto.CameraDTO;
import com.mystical.VisionLOC.dto.DetectedObjectDTO;
import com.mystical.VisionLOC.model.BoundingBox;
import com.mystical.VisionLOC.model.Camera;
import com.mystical.VisionLOC.model.Detections;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CameraService {

	private CameraDAO dao;
	private PythonDetectionService pythonScript;

	static final double EARTH_RADIUS_METERS = 6371000; // Earth's radius in meters

	@Transactional
	public ResponseEntity<?> createCamera(Long signUpID, CameraDTO cameraDTO) {
		Camera camera = new Camera(signUpID, cameraDTO);
		dao.save(camera);

		// Run detection in background
		pythonScript.runDetectionAsync(signUpID);

		return ResponseEntity.ok(GenericResponseService.getSuccessResponse("Camera add successfully"));
	}

	public ResponseEntity<?> getCameraByID(Long signUpID, Long id) {
		Camera camera = dao.getCameraByID(id);
		CameraDTO response = new CameraDTO(camera);
		return ResponseEntity.ok(response);
	}

	public ResponseEntity<?> updateCamera(Long signUpID, Long id, CameraDTO cameraDTO) {
		Camera camera = dao.getCameraByID(id);
		if (camera != null) {
			Camera newCamera = new Camera(signUpID, id, cameraDTO);
			dao.save(newCamera);
			return ResponseEntity.ok(GenericResponseService.getSuccessResponse("Camera updated successfully"));
		} else {
			return ResponseEntity.ok(GenericResponseService.getFailureResponse("Camera not found at id: " + id));
		}
	}

	@Transactional
	public List<CameraDTO> getCameraList(Long signUpID) {
		List<Camera> cameraList = dao.getAllCameras(signUpID);
		List<CameraDTO> responseList = new ArrayList<CameraDTO>();
		for (Camera cam : cameraList) {
			responseList.add(new CameraDTO(cam));
		}
		return responseList;
	}

	@Transactional
	public ResponseEntity<?> deleteCamera(Long signUpID, Long id) {
		Camera camera = dao.getCameraByID(id);
		if (camera != null) {
			dao.delete(camera);
			return ResponseEntity.ok(GenericResponseService.getSuccessResponse("Camera deleted successfully"));
		} else {
			return ResponseEntity.ok(GenericResponseService.getFailureResponse("Camera not found at id: " + id));
		}
	}

	public List<DetectedObjectDTO> getDetectedObjectList(Long signUpID) {
		List<DetectedObjectDTO> list = new ArrayList<DetectedObjectDTO>();
		List<Camera> cameraList = dao.getAllCameras(signUpID);
		for (Camera camera : cameraList) {
			System.out.println("Camera ID: " + camera.getId() + camera.getLatitude() + camera.getLongitude());

			DetectedObjectDTO cameraRes = new DetectedObjectDTO(camera.getId(), camera.getLabel(), camera.getLatitude(),
					camera.getLongitude(), "camera", null, null);
			list.add(cameraRes);

			// detected objects
			List<DetectedObjectDTO> detectedObjectList = calculateLatLong(camera);
			list.addAll(detectedObjectList);
		}

		return list;
	}

	public List<DetectedObjectDTO> calculateLatLong(Camera camera) {
		List<Detections> detections = camera.getDetectionList();
		List<DetectedObjectDTO> list = new ArrayList<DetectedObjectDTO>();

		Long imageHeight = 640l;
		Long imageWidth = 2l;
		for (Detections detection : detections) {
			BoundingBox bbox = detection.getBoundingBox();

//			int cameraHeight = camera.getImage_height() / 1000;
			// Step 1: Estimate Distance (Z-axis)
			double estimatedDistance = (imageHeight * camera.getFocalLengthPx()) / bbox.getH();

			// Step 2: Get Bounding Box Center in Image
			double xCenter = bbox.getX() + (bbox.getW() / 2);
//			double yCenter = bbox.getY() + (bbox.getH() / 2);

			// Step 3: Calculate pixel offset from the image center
			double dx = xCenter - (imageWidth / 2.0);

			// Step 4: Estimate the angle offset from the camera's view
			double angleOffsetDeg = (dx / imageWidth) * camera.getFocalLengthPx();

			// Step 5: Calculate the bearing of the object relative to the camera's heading
			double cameraHeading = camera.getFovDeg(); // (optional: camera orientation, default 0 = North
			double bearingDeg = (cameraHeading + angleOffsetDeg) % 360;

			// Step 6: Convert camera lat/lon to radians for calculation
			double lat1Rad = Math.toRadians(camera.getLatitude());
			double lon1Rad = Math.toRadians(camera.getLongitude());
			double bearingRad = Math.toRadians(bearingDeg);

			// Step 7: Calculate the destination latitude (lat2) and longitude (lon2) using
			// spherical law of cosines
			double lat2Rad = Math.asin(Math.sin(lat1Rad) * Math.cos(estimatedDistance / EARTH_RADIUS_METERS)
					+ Math.cos(lat1Rad) * Math.sin(estimatedDistance / EARTH_RADIUS_METERS) * Math.cos(bearingRad));
			double lon2Rad = lon1Rad + Math.atan2(
					Math.sin(bearingRad) * Math.sin(estimatedDistance / EARTH_RADIUS_METERS) * Math.cos(lat1Rad),
					Math.cos(estimatedDistance / EARTH_RADIUS_METERS) - Math.sin(lat1Rad) * Math.sin(lat2Rad));

			// Step 8: Convert the resulting lat2 and lon2 from radians back to degrees
			double objectLat = Math.toDegrees(lat2Rad);
			String formattedValue = String.format("%.7f", objectLat);
			double roundedValueLat = Double.parseDouble(formattedValue);

			double objectLon = Math.toDegrees(lon2Rad);
			String formattedValue1 = String.format("%.7f", objectLon);
			double roundedValueLong = Double.parseDouble(formattedValue1);

			// Read bytes
			byte[] imageBytes = null;
			String imageType = null;
			try {
				File file = new File(detection.getImagePath());
				imageType = Files.probeContentType(file.toPath());
				imageBytes = Files.readAllBytes(file.toPath());
			} catch (IOException e) {
				e.printStackTrace();
			}

			DetectedObjectDTO response = new DetectedObjectDTO(detection.getId(), detection.getLabel(), roundedValueLat,
					roundedValueLong, detection.getLabel(), imageType, imageBytes);
			list.add(response);

		}
		return list;
	}

}
