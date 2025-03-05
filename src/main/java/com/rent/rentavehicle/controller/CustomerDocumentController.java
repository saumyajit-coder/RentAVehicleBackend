package com.rent.rentavehicle.controller;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.rent.rentavehicle.entity.CustomerDocument;
import com.rent.rentavehicle.service.CustomerDocumentService;
import com.rent.rentavehicle.service.S3StorageService;

@RestController
@RequestMapping("/api/customers/customer-documents")
public class CustomerDocumentController {

    private final CustomerDocumentService documentService;
    private final S3StorageService s3StorageService;
    
    public CustomerDocumentController(CustomerDocumentService documentService,S3StorageService s3StorageService ) {
        this.documentService = documentService;
        this.s3StorageService = s3StorageService;
    }

    // ✅ Upload a new document
    @PostMapping("create")
    public ResponseEntity<CustomerDocument> uploadDocument(
            @RequestParam("file") MultipartFile file,
            @RequestParam("customerId") String customerId,
            @RequestParam("bookingId") Long bookingId,
            @RequestParam("documentType") CustomerDocument.DocumentType documentType) {

        try {
            String fileUrl = s3StorageService.uploadFile(file); // Upload file to S3
            CustomerDocument savedDocument = documentService.uploadDocument(customerId, bookingId, documentType, fileUrl);
            return ResponseEntity.ok(savedDocument);
        } catch (IOException e) {
            return ResponseEntity.badRequest().body(null);
        }
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