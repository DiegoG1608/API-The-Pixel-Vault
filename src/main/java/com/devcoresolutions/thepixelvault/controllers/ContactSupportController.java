package com.devcoresolutions.thepixelvault.controllers;


import com.devcoresolutions.thepixelvault.dtos.ContactSupportRequest;
import com.devcoresolutions.thepixelvault.models.ContactSupport;
import com.devcoresolutions.thepixelvault.services.ContactSupportService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@CrossOrigin
@RestController
@RequestMapping(path = "/api/contactSupport")
public class ContactSupportController {
    private final ContactSupportService contactSupportService;

    @Autowired
    public ContactSupportController(ContactSupportService contactSupportService) {
        this.contactSupportService = contactSupportService;
    }

    @PostMapping
    public ResponseEntity<ContactSupport> createContactSupport(@Valid @RequestBody ContactSupportRequest contactSupportRequest) {
        ContactSupport contactSupport = contactSupportService.createContactSupport(contactSupportRequest);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(contactSupport.getId())
                .toUri();
        return ResponseEntity.created(location).body(contactSupport);
    }


    @DeleteMapping(path = "{supportId}")
    public ResponseEntity<ContactSupport> deleteContactSupport(@PathVariable("supportId") Long supportId) {
        ContactSupport  contactSupport = contactSupportService.deleteContactSupport(supportId);
        return ResponseEntity.ok(contactSupport);
    }
}
