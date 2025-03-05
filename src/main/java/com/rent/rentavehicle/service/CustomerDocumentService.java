package com.rent.rentavehicle.service;

import java.util.List;

import com.rent.rentavehicle.entity.CustomerDocument;

public interface CustomerDocumentService {
	 CustomerDocument uploadDocument(String customerId, Long bookingId, CustomerDocument.DocumentType documentType, String fileUrl);
	    
    List<CustomerDocument> getDocumentsByCustomer(String customerId);

    void deleteDocument(Long documentId);

    CustomerDocument getDocumentById(Long documentId); // ✅ Fixed method name
}