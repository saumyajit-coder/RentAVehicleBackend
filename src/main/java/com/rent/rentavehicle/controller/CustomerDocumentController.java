package com.rent.rentavehicle.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rent.rentavehicle.entity.CustomerDocument;
import com.rent.rentavehicle.service.CustomerDocumentService;

@RestController
@RequestMapping("/customer-documents")
public class CustomerDocumentController {

    private final CustomerDocumentService documentService;

    public CustomerDocumentController(CustomerDocumentService documentService) {
        this.documentService = documentService;
    }

    // Upload a new document
    @PostMapping
    public ResponseEntity<CustomerDocument> uploadDocument(@RequestBody CustomerDocument document) {
        CustomerDocument savedDocument = documentService.uploadDocument(document);
        return ResponseEntity.ok(savedDocument);
    }

    // Get all documents for a customer
    @GetMapping("/customer/{customerId}")
    public ResponseEntity<List<CustomerDocument>> getDocumentsByCustomer(@PathVariable String customerId) {
        List<CustomerDocument> documents = documentService.getDocumentsByCustomer(customerId);
        return ResponseEntity.ok(documents);
    }

    // Delete a document by ID
    @DeleteMapping("/{documentId}")
    public ResponseEntity<String> deleteDocument(@PathVariable Long documentId) {
        documentService.deleteDocument(documentId);
        return ResponseEntity.ok("Document deleted successfully.");
    }
}
