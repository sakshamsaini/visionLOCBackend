package com.mystical.VisionLOC.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mystical.VisionLOC.dao.CustomMarkerDAO;
import com.mystical.VisionLOC.dao.DrawingDAO;
import com.mystical.VisionLOC.dto.BearingAndBackbearingDTO;
import com.mystical.VisionLOC.dto.CreateMarkerDTO;
import com.mystical.VisionLOC.dto.DrawingDTO;
import com.mystical.VisionLOC.dto.LatlngOfDrawingDTO;
import com.mystical.VisionLOC.dto.ResponseDTO;
import com.mystical.VisionLOC.model.BearingAndBackbearing;
import com.mystical.VisionLOC.model.CustomMarker;
import com.mystical.VisionLOC.model.Drawing;
import com.mystical.VisionLOC.model.LatlngOfDrawing;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CustomMarkerService {

	private CustomMarkerDAO customMarkerDAO;
	private DrawingDAO drawingDAO;

	@Transactional
	public ResponseEntity<?> createMarker(Long signUpID, MultipartFile file, String data)
			throws JsonMappingException, JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();

		CreateMarkerDTO markerDTO = mapper.readValue(data, CreateMarkerDTO.class);
		CustomMarker marker = new CustomMarker(signUpID, markerDTO);
		if (file != null && file.getSize() > 0) {
			try {
				marker.setImage(file.getBytes());
				marker.setImageType(file.getContentType());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		customMarkerDAO.save(marker);
		return ResponseEntity.ok(
				GenericResponseService.getSuccessResponse("Marker add successfully", new ResponseDTO(marker.getId())));
	}

	@Transactional(readOnly = true)
	public List<CreateMarkerDTO> getMarkerList(Long signUpID) {
		List<CustomMarker> markerList = customMarkerDAO.getAllMarkers(signUpID);

		List<CreateMarkerDTO> responseList = markerList.stream().map(e -> new CreateMarkerDTO(e))
				.collect(Collectors.toList());

		return responseList;
	}

	public ResponseEntity<?> deleteMarker(Long signUpID, Long id) {
		CustomMarker marker = customMarkerDAO.getMarkerByID(id);
		if (marker != null) {
			customMarkerDAO.delete(marker);
			return ResponseEntity.ok(GenericResponseService.getSuccessResponse("Marker deleted successfully"));
		} else {
			return ResponseEntity.ok(GenericResponseService.getFailureResponse("Marker not found at id: " + id));
		}
	}

	public ResponseEntity<?> createDrawing(Long signUpID, DrawingDTO drawingDTO) {
		Drawing drawing = new Drawing(signUpID, drawingDTO.getShapeName(), drawingDTO.getLabel(), drawingDTO.getArea(),
				drawingDTO.getRadius());
		if (drawingDTO.getLatlng() != null && drawingDTO.getLatlng().size() > 0) {
			List<LatlngOfDrawing> latlngList = new ArrayList<LatlngOfDrawing>();
			for (LatlngOfDrawingDTO latlng : drawingDTO.getLatlng()) {
				latlngList.add(new LatlngOfDrawing(latlng, drawing));
			}
			drawing.setLatlng(latlngList);
		}

		if (drawingDTO.getBearAndbackBearing() != null && drawingDTO.getBearAndbackBearing().size() > 0) {
			List<BearingAndBackbearing> bAndBbList = new ArrayList<BearingAndBackbearing>();
			for (BearingAndBackbearingDTO bAndBb : drawingDTO.getBearAndbackBearing()) {
				bAndBbList.add(new BearingAndBackbearing(bAndBb, drawing));
			}
			drawing.setBearAndbackBearing(bAndBbList);
		}

		drawingDAO.save(drawing);
		return ResponseEntity.ok(GenericResponseService.getSuccessResponse("Drawing add successfully",
				new ResponseDTO(drawing.getId())));
	}

	public List<DrawingDTO> getDrawingList(Long signUpID) {
		List<DrawingDTO> drawingResponeList = new ArrayList<DrawingDTO>();
		List<Drawing> drawingList = drawingDAO.getAllDrawings(signUpID);
		for (Drawing drawing : drawingList) {
			drawingResponeList.add(new DrawingDTO(drawing));
		}
		return drawingResponeList;
	}

	public ResponseEntity<?> deleteDrawing(Long signUpID, Long id) {
		Drawing drawing = drawingDAO.getDrawingByID(id);
		if (drawing != null) {
			drawingDAO.delete(drawing);
			return ResponseEntity.ok(GenericResponseService.getSuccessResponse("Drawing deleted successfully"));
		} else {
			return ResponseEntity.ok(GenericResponseService.getFailureResponse("Drawing not found at id: " + id));
		}
	}

}
