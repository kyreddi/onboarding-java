package com.jdbank.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jdbank.entity.Invoice;
import com.jdbank.repository.InvoiceRepository;
import com.jdbank.service.OnboardingServiceSingleUser;

@Service
public class OnboardingServiceSingleUserImpl implements OnboardingServiceSingleUser {
	
	@Autowired
	InvoiceRepository invoiceRepository;

	

	@Override
	public Invoice addInvoice(Invoice invoice) {
		return invoiceRepository.save(invoice);
	}


	@Override
	public Invoice updateInvoiceById(String id, Invoice invoice) {
		if (!invoiceRepository.existsById(id))
//			throw new EmployeePayrollException();
			System.out.println(" user id not exiest");
		invoice.setId(id);
		//log.info("Employee Payroll data updated with empId: " + empId);
		return invoiceRepository.save(invoice);
	}
	
	@Override
	public String deleteInvoiceById(String id) {
		if (invoiceRepository.existsById(id)) {
			invoiceRepository.deleteById(id);
			 
			 return "Deleted Role";
		}else {
			return "id not found";
		}
		
		
	}
	
	public List<Invoice> getAllSellers() {
		return invoiceRepository.findAll();
		}


//	@Override
//	public List<Invoice> getAllUsers(Invoice invoice) {
//		// TODO Auto-generated method stub
//		return invoiceRepository.findAll();
//	}
	
	@Override
	public Optional<Invoice> getSellersById(String id) {
		return this.invoiceRepository.findById(id);
	}


}
