package com.mystical.VisionLOC.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class PythonDetectionService {

	@Async
	public void runDetectionAsync(Long signUpID) {
		try {
			// Change this to your actual Python file path
			String scriptPath = "/home/mahima/cctv_app/cctv_yolo_logger.py";

			// If using python3, change to "python3"
			ProcessBuilder builder = new ProcessBuilder("python3", scriptPath);

			// Redirect error stream to standard output
			builder.redirectErrorStream(true);
			Process process = builder.start();

			// Read output from the script
			BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
			String line;
			System.out.println("---- Script Output ----");
			while ((line = reader.readLine()) != null) {
				System.out.println(line);
			}

			// Wait for script to finish
			int exitCode = process.waitFor();
			System.out.println("Exited with code: " + exitCode);

		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}
	}
}
