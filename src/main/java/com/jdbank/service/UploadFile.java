package com.jdbank.service;

import org.springframework.web.multipart.MultipartFile;

public interface UploadFile {
	
	public String uploadDocument(MultipartFile file);

}
