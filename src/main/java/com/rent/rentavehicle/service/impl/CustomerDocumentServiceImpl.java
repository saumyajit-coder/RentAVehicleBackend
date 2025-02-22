package com.rent.rentavehicle.service.impl;

import com.rent.rentavehicle.entity.CustomerDocument;
import com.rent.rentavehicle.repositories.CustomerDocumentRepository;
import com.rent.rentavehicle.service.CustomerDocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerDocumentServiceImpl implements CustomerDocumentService {

    @Autowired
    private CustomerDocumentRepository customerDocumentRepository;

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
        customerDocumentRepository.deleteById(documentId);
    }
}
