package com.rent.rentavehicle.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "customer_documents")
public class CustomerDocument {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long documentId;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "booking_id", nullable = false)
    private Booking booking;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private DocumentType documentType;

    @Column(nullable = false)
    private String filePath;

    @Column(nullable = false)
    private LocalDateTime uploadedAt = LocalDateTime.now();

    public enum DocumentType {
        DRIVING_LICENSE, GOVERNMENT_ID, ADDRESS_PROOF, EMERGENCY_CONTACT, INTERNATIONAL_DRIVING_PERMIT, PASSPORT
    }

    public CustomerDocument() {
    }

    public CustomerDocument(Long documentId, Customer customer, Booking booking, DocumentType documentType, String filePath, LocalDateTime uploadedAt) {
        this.documentId = documentId;
        this.customer = customer;
        this.booking = booking;
        this.documentType = documentType;
        this.filePath = filePath;
        this.uploadedAt = uploadedAt;
    }

    public Long getDocumentId() { return documentId; }
    public void setDocumentId(Long documentId) { this.documentId = documentId; }

    public Customer getCustomer() { return customer; }
    public void setCustomer(Customer customer) { this.customer = customer; }

    public Booking getBooking() { return booking; }
    public void setBooking(Booking booking) { this.booking = booking; }

    public DocumentType getDocumentType() { return documentType; }
    public void setDocumentType(DocumentType documentType) { this.documentType = documentType; }

    public String getFilePath() { return filePath; }
    public void setFilePath(String filePath) { this.filePath = filePath; }

    public LocalDateTime getUploadedAt() { return uploadedAt; }
    public void setUploadedAt(LocalDateTime uploadedAt) { this.uploadedAt = uploadedAt; }
}
