package com.jdbank.service;

import java.util.List;
import java.util.Optional;

import com.jdbank.entity.Invoice;

public interface OnboardingServiceSingleUser {
	public Invoice addInvoice(Invoice invoice);
	public String deleteInvoiceById(String id);
//	public Invoice updateInvoice(Invoice invoice);
	public List<Invoice> getAllSellers();
	
//	public String updateInvoiceById(Invoice invoice);
	Optional<Invoice> getSellersById(String id);

	Invoice updateInvoiceById(String id, Invoice invoice);
}
