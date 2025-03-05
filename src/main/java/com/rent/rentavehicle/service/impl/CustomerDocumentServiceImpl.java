package com.rent.rentavehicle.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.rent.rentavehicle.entity.Booking;
import com.rent.rentavehicle.entity.Customer;
import com.rent.rentavehicle.entity.CustomerDocument;
import com.rent.rentavehicle.repositories.CustomerDocumentRepository;
import com.rent.rentavehicle.service.BookingService;
import com.rent.rentavehicle.service.CustomerDocumentService;
import com.rent.rentavehicle.service.CustomerService;

@Service
public class CustomerDocumentServiceImpl implements CustomerDocumentService {

    private final CustomerDocumentRepository customerDocumentRepository;
    private final CustomerService customerService;
    private final BookingService bookingService;
    public CustomerDocumentServiceImpl(CustomerDocumentRepository customerDocumentRepository,CustomerService customerService,
            BookingService bookingService) {
        this.customerDocumentRepository = customerDocumentRepository;
        this.customerService = customerService;
        this.bookingService = bookingService;
    }

    @Override
    public CustomerDocument uploadDocument(String customerId, Long bookingId, CustomerDocument.DocumentType documentType, String fileUrl) {
        Customer customer = customerService.getCustomerById(customerId);
        Booking booking = bookingService.getBookingById(bookingId);

        CustomerDocument document = new CustomerDocument();
        document.setCustomer(customer);
        document.setBooking(booking);
        document.setDocumentType(documentType);
        document.setFilePath(fileUrl);
        document.setUploadedAt(LocalDateTime.now());

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