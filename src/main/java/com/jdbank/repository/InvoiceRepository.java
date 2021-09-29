package com.jdbank.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.jdbank.entity.Invoice;
@Repository
public interface InvoiceRepository extends MongoRepository<Invoice, String> {
	
	Invoice getInvoiceById(String id);
}
