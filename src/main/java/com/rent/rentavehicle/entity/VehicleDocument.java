package com.rent.rentavehicle.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "vehicle_documents")
public class VehicleDocument {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long documentId;

    @ManyToOne
    @JoinColumn(name = "vehicle_id", nullable = false)
    private Vehicle vehicle;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private DocumentType documentType;

    @Column(nullable = false)
    private String filePath;

    @Column(nullable = false)
    private LocalDateTime uploadedAt = LocalDateTime.now();

    public enum DocumentType {
        DRIVING_LICENSE, RC, INSURANCE_POLICY, PUC_CERTIFICATE, ID_PROOF
    }

    public VehicleDocument() {
    }

    public VehicleDocument(Long documentId, Vehicle vehicle, DocumentType documentType, String filePath, LocalDateTime uploadedAt) {
        this.documentId = documentId;
        this.vehicle = vehicle;
        this.documentType = documentType;
        this.filePath = filePath;
        this.uploadedAt = uploadedAt;
    }

    public Long getDocumentId() { return documentId; }
    public void setDocumentId(Long documentId) { this.documentId = documentId; }

    public Vehicle getVehicle() { return vehicle; }
    public void setVehicle(Vehicle vehicle) { this.vehicle = vehicle; }

    public DocumentType getDocumentType() { return documentType; }
    public void setDocumentType(DocumentType documentType) { this.documentType = documentType; }

    public String getFilePath() { return filePath; }
    public void setFilePath(String filePath) { this.filePath = filePath; }

    public LocalDateTime getUploadedAt() { return uploadedAt; }
    public void setUploadedAt(LocalDateTime uploadedAt) { this.uploadedAt = uploadedAt; }
}
