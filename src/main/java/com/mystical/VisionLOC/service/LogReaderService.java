package com.mystical.VisionLOC.service;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.input.Tailer;
import org.springframework.stereotype.Service;

import jakarta.annotation.PostConstruct;

@Service
public class LogReaderService {

//	@PostConstruct
//	public void init() throws IOException {
//		String logPath = "/home/mahima/Downloads/app.log";
//		startLogMonitoring(logPath);
//	}

	public void startLogMonitoring(String logFilePath) throws IOException {
		System.out.println("Started log tailing. Waiting for new log lines...2");
		File logFile = new File(logFilePath);
		LogTailerListener listener = new LogTailerListener();

		// Tailer: (file, listener, delayMillis, end=true to start at end of file)
		Tailer tailer = new Tailer(logFile, listener, 1000, false);

		Thread thread = new Thread(tailer);
		thread.setDaemon(true); // Run in background
		thread.start();
	}

}
