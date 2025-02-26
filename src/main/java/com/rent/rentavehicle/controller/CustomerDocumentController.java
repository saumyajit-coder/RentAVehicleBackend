package com.rent.rentavehicle.controller;

import java.util.Collections;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.rent.rentavehicle.entity.CustomerDocument;
import com.rent.rentavehicle.service.CustomerDocumentService;

@RestController
@RequestMapping("customers/customer-documents")
public class CustomerDocumentController {

    private final CustomerDocumentService documentService;

    public CustomerDocumentController(CustomerDocumentService documentService) {
        this.documentService = documentService;
    }

    // ✅ Upload a new document
    @PostMapping
    public ResponseEntity<CustomerDocument> uploadDocument(@RequestBody CustomerDocument document) {
        CustomerDocument savedDocument = documentService.uploadDocument(document);
        return ResponseEntity.ok(savedDocument);
    }

    // ✅ Get all documents for a specific customer
    @GetMapping("/customer/{customerId}")
    public ResponseEntity<List<CustomerDocument>> getDocumentsByCustomer(@PathVariable String customerId) {
        List<CustomerDocument> documents = documentService.getDocumentsByCustomer(customerId);
        if (documents == null || documents.isEmpty()) {
            return ResponseEntity.status(404).body(Collections.emptyList()); // Return 404 if no documents found
        }
        return ResponseEntity.ok(documents);
    }

    // ✅ Get a single document by its ID
    @GetMapping("/customer/document/{documentId}")
    public ResponseEntity<CustomerDocument> getDocumentById(@PathVariable Long documentId) {
        CustomerDocument document = documentService.getDocumentById(documentId);
        return ResponseEntity.ok(document);
    }

    // ✅ Delete a document by ID
    @DeleteMapping("/{documentId}")
    public ResponseEntity<String> deleteDocument(@PathVariable Long documentId) {
        documentService.deleteDocument(documentId);
        return ResponseEntity.ok("Document deleted successfully.");
    }
}
