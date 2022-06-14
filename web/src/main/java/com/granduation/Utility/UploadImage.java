package com.granduation.Utility;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.web.multipart.MultipartFile;

public class UploadImage {

	public static String uploadDir =System.getProperty("user.dir") + "/src/main/resources/static/imageUploads";
	public static String UploadFile(MultipartFile[] files) {
		new File(uploadDir).mkdir();
		StringBuilder fileNames = new StringBuilder();
		for(MultipartFile file:files) {
			Path fileNameAndPath = Paths.get(uploadDir,file.getOriginalFilename());
			fileNames.append(file.getOriginalFilename());
			try {
				Files.write(fileNameAndPath, file.getBytes());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return "Upload xong";
	}
}
