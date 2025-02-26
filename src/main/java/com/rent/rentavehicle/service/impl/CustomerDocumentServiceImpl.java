package com.rent.rentavehicle.service.impl;

import com.rent.rentavehicle.entity.CustomerDocument;
import com.rent.rentavehicle.repositories.CustomerDocumentRepository;
import com.rent.rentavehicle.service.CustomerDocumentService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerDocumentServiceImpl implements CustomerDocumentService {

    private final CustomerDocumentRepository customerDocumentRepository;

    public CustomerDocumentServiceImpl(CustomerDocumentRepository customerDocumentRepository) {
        this.customerDocumentRepository = customerDocumentRepository;
    }

    @Override
    public CustomerDocument uploadDocument(CustomerDocument document) {
        return customerDocumentRepository.save(document);
    }

    @Override
    public List<CustomerDocument> getDocumentsByCustomer(String customerId) {
        return customerDocumentRepository.findByCustomerCustomerId(customerId);
    }

    @Override
    public void deleteDocument(Long documentId) {
        if (!customerDocumentRepository.existsById(documentId)) {
            throw new RuntimeException("Document with ID " + documentId + " not found.");
        }
        customerDocumentRepository.deleteById(documentId);
    }

    @Override
    public CustomerDocument getDocumentById(Long documentId) {
        return customerDocumentRepository.findById(documentId)
                .orElseThrow(() -> new RuntimeException("Document with ID " + documentId + " not found."));
    }
}
