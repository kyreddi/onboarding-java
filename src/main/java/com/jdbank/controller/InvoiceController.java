package com.jdbank.controller;



import java.util.List;

import javax.validation.Valid;
import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.jdbank.entity.Invoice;

import com.jdbank.repository.InvoiceRepository;
import com.jdbank.response.ResponseMessage;


import com.jdbank.service.OnboardingServiceSingleUser;
import com.jdbank.service.UploadFile;




//@Controller
@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class InvoiceController {
	
@Autowired
UploadFile uploadFile;
	

	
	@Autowired
	InvoiceRepository repo;
	
	@Autowired
	OnboardingServiceSingleUser onboardingServiceSingleUser;
	
	@GetMapping("/")
    public String index() {
        return "uploadPage";
    }

    @PostMapping("/uploadFile")
    public String uploadFile(@PathParam
    		("file") MultipartFile file) {
        return uploadFile.uploadDocument(file);
    }
    
  
    
    @PostMapping("/addInvoice")
    @ResponseStatus(code = HttpStatus.OK , reason= "Record created successfully")
    public Invoice addInvoice(@RequestBody Invoice invoice) {
    	return onboardingServiceSingleUser.addInvoice(invoice);
    	
    }
    
    @PutMapping("/updateInvoiceByid/{id}")
    @ResponseStatus(code = HttpStatus.OK , reason= "Record updated successfully")
    public Invoice updateInvoiceById(@PathVariable("id") String id,
    		@Valid @RequestBody Invoice invoice) {
//    	log.info("Requesting to UPDATE seller data with Id {}", id);
		return onboardingServiceSingleUser.updateInvoiceById(id,invoice);

	}
    
//    @DeleteMapping("/deleteInvoiceById/{id}")
//    @ResponseStatus(code = HttpStatus.OK , reason= "Record deleted successfully")
//	 public String deleteInvoiceById(long id) {
//    	
//    	return onboardingServiceSingleUser.deleteInvoiceById(id);
//	}
    
    @DeleteMapping("/deleteInvoiceById/{id}")
    @ResponseStatus(code = HttpStatus.OK , reason= "Record deleted successfully")
	public String deleteInvoiceById(@PathVariable("id") String id) {
		//log.info("Requesting to DELETE employee payroll data with Id {}", empId);
    	
		return onboardingServiceSingleUser.deleteInvoiceById(id);
	}
    
    @GetMapping("/getAllSellers")
//    @ResponseStatus(code = HttpStatus.OK , reason= "Record showed successfully")
	public ResponseEntity<List<Invoice>>  getAllSellers() {

	return new ResponseEntity<>(onboardingServiceSingleUser.getAllSellers(),HttpStatus.OK);
	}
    
 

    


}
