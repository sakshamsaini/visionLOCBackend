package com.mystical.VisionLOC.service;

import java.io.File;
import java.io.FileWriter;
import java.nio.file.Files;
import java.util.regex.Pattern;

import org.apache.commons.io.input.Tailer;
import org.apache.commons.io.input.TailerListener;
import org.json.JSONArray;
import org.json.JSONObject;

public class LogTailerListener implements TailerListener {

	private final String outputPath = "/home/mahima/Downloads/logs.json";

	@Override
	public void handle(String line) {
		processLine(line);
//		System.out.println("New log line: " + line); // Process the line
	}

	private void processLine(String line) {
		String delimiter = "{'camera_id':";
		if (line.contains(delimiter)) {
			String newLine = line.split(Pattern.quote(delimiter))[1];

			String fixedJson = newLine.replace("'", "\"");
			String mm = delimiter.replace("'", "\"");
			System.out.println(mm + fixedJson);
//			appendToJsonArrayFile(fixedJson);
		}
	}

	private void appendToJsonArrayFile(String jsonPart) {
		try {
			JSONObject jsonObject = new JSONObject(jsonPart);

			File file = new File(outputPath);
			JSONArray jsonArray;

			if (file.exists() && file.length() > 0) {
				String content = Files.readString(file.toPath());
				jsonArray = new JSONArray(content);
			} else {
				jsonArray = new JSONArray();
			}

			jsonArray.put(jsonObject);

			try (FileWriter writer = new FileWriter(file, false)) {
				writer.write(jsonArray.toString(2)); // Pretty print with 2 spaces
			}
		} catch (Exception e) {
			System.err.println("Error writing to JSON file: " + e.getMessage());
		}
	}

	@Override
	public void init(Tailer tailer) {
	}

	@Override
	public void fileNotFound() {
		System.out.println("Log file not found.");
	}

	@Override
	public void fileRotated() {
	}

	@Override
	public void handle(Exception ex) {
		ex.printStackTrace();
	}

}
