package com.rent.rentavehicle.service;

import com.rent.rentavehicle.entity.CustomerDocument;
import java.util.List;

public interface CustomerDocumentService {
    CustomerDocument uploadDocument(CustomerDocument document);

    List<CustomerDocument> getDocumentsByCustomer(String customerId);

    void deleteDocument(Long documentId);

    CustomerDocument getDocumentById(Long documentId); // ✅ Fixed method name
}
