package com.rent.rentavehicle.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

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

    @Column(name = "file_url", nullable = false)
    private String fileUrl;

    @Column(nullable = false)
    private LocalDateTime uploadedAt = LocalDateTime.now();

    public enum DocumentType {
        DRIVING_LICENSE, RC, INSURANCE_POLICY, PUC_CERTIFICATE, ID_PROOF, VEHICLE_IMAGE
    }

    public VehicleDocument() {
    }

    public VehicleDocument(Long documentId, Vehicle vehicle, DocumentType documentType, String fileUrl, LocalDateTime uploadedAt) {
        this.documentId = documentId;
        this.vehicle = vehicle;
        this.documentType = documentType;
        this.fileUrl = fileUrl;
        this.uploadedAt = uploadedAt;
    }

    public Long getDocumentId() { return documentId; }
    public void setDocumentId(Long documentId) { this.documentId = documentId; }

    public Vehicle getVehicle() { return vehicle; }
    public void setVehicle(Vehicle vehicle) { this.vehicle = vehicle; }

    public DocumentType getDocumentType() { return documentType; }
    public void setDocumentType(DocumentType documentType) { this.documentType = documentType; }

    public String getFilePath() { return fileUrl; }
    public void setFilePath(String fileUrl) { this.fileUrl = fileUrl; }

    public LocalDateTime getUploadedAt() { return uploadedAt; }
    public void setUploadedAt(LocalDateTime uploadedAt) { this.uploadedAt = uploadedAt; }
}