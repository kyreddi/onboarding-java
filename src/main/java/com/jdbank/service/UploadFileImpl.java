package com.jdbank.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.hibernate.internal.build.AllowSysOut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.jdbank.entity.Invoice;
import com.jdbank.repository.InvoiceRepository;
import com.sun.jdi.IntegerValue;

@Service
public class UploadFileImpl implements UploadFile {
	
	

	private String bucketName = "jdbankstorage2";

	@Autowired
	private AmazonS3 s3Client;
	
	Workbook workbook;
	
	@Autowired
	InvoiceRepository invoiveRepository;

	@Override
	public String uploadDocument(MultipartFile file) {
		
		
		File document = convertMultipartFileToFile(file);
		String fileName = file.getOriginalFilename();
		
		List<String> list = new ArrayList<String>();
		
		if(!file.isEmpty()) {
			
		
		
		try {
			
			
			s3Client.putObject(new PutObjectRequest(bucketName+"/allfiles", fileName, document));
			
			if((file.getOriginalFilename().split("[.]")[1].equals("xlsx")) 
					|| file.getOriginalFilename().split("[.]")[1].equals("csv")) {
				
				System.out.print(file.getOriginalFilename().split("[.]")[1]);
				DataFormatter dataFormatter = new DataFormatter();

				// Create the Workbook
				try {
					workbook = WorkbookFactory.create(document);
				} catch (EncryptedDocumentException | IOException e) {
					e.printStackTrace();
				}

				// Retrieving the number of sheets in the Workbook
				System.out.println("-------Workbook has '" + workbook.getNumberOfSheets() + "' Sheets-----");

				// Getting the Sheet at index zero
				Sheet sheet = workbook.getSheetAt(0);

				// Getting number of columns in the Sheet
				int noOfColumns = sheet.getRow(0).getLastCellNum();
				System.out.println("-------Sheet has '"+noOfColumns+"' columns------");

				// Using for-each loop to iterate over the rows and columns
				for (Row row : sheet) {
					for (Cell cell : row) {
						String cellValue = dataFormatter.formatCellValue(cell);
						list.add(cellValue);
					}
				}

				// filling excel data and creating list as List<Invoice>
			
					List<Invoice> invList= createList(list, noOfColumns);
					try {
					invoiveRepository.saveAll(invList);
					s3Client.putObject(new PutObjectRequest(bucketName+"/processedfiles", fileName, document));
					
					return "data save in database and file is sent to processed filr";
					}catch(Exception e) {
						
						s3Client.putObject(new PutObjectRequest(bucketName+"/errorfiles", fileName, document));
					
						e.printStackTrace();
						return e.getMessage();
					}
					
				
				
			
			}else {
				try {
					s3Client.putObject(new PutObjectRequest(bucketName+"/errorfiles", fileName, document));
					return "Some error in file";
				}catch(Exception e) {
					e.printStackTrace();
					return e.getMessage().toString();
					
					
				}
				
			}
			
			
			
		}catch(Exception e) {
			e.printStackTrace();
			return e.getMessage();
			
			
		}
		
		
	
		}else {
			return "File is not present";
		}
		
		
		
		
		
	
		
		
	}
	
	
	
	private List<Invoice> createList(List<String> excelData, int noOfColumns) {

		ArrayList<Invoice> invList = new ArrayList<Invoice>();

		int i = noOfColumns;
		do {
			Invoice inv = new Invoice();
			
			inv.setAadharNumber(excelData.get(i));
			inv.setAddress(excelData.get(i+1));
			inv.setAge(Integer.parseInt(excelData.get(i+2)));
			inv.setCertNo(excelData.get(i+3));
			inv.setEmailId(excelData.get(i+4));
			inv.setName(excelData.get(i+5));
			inv.setPanNumber(excelData.get(i+6));
			inv.setPhNo(excelData.get(i+7));
			
			

			invList.add(inv);
			i = i + (noOfColumns);

		} while (i < excelData.size());
		
		return invList;
	}
	
	
	
	private File convertMultipartFileToFile(MultipartFile file) {

		File convertedFile = new File(file.getOriginalFilename());

		try (FileOutputStream fos = new FileOutputStream(convertedFile)) {
			fos.write(file.getBytes());

		} catch (IOException e) {
		
			e.printStackTrace();
		}

		return convertedFile;

	}

}
