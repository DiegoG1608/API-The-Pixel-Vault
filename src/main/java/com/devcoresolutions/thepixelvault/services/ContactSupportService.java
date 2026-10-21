package com.devcoresolutions.thepixelvault.services;


import com.devcoresolutions.thepixelvault.dtos.ContactSupportRequest;
import com.devcoresolutions.thepixelvault.exceptions.ResourceNotFoundException;
import com.devcoresolutions.thepixelvault.models.ContactSupport;
import com.devcoresolutions.thepixelvault.repository.ContactSupportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ContactSupportService {
    private final ContactSupportRepository contactSupportRepository;

    @Autowired
    public ContactSupportService(
            ContactSupportRepository contactSupportRepository) {
        this.contactSupportRepository = contactSupportRepository;
    }


    public ContactSupport createContactSupport(ContactSupportRequest newContactSupport) {
        ContactSupport contactSupport = new ContactSupport();
        contactSupport.setMessage(newContactSupport.message());
        contactSupport.setFullName(newContactSupport.fullName());
        contactSupport.setEmail(newContactSupport.email());
        contactSupport.setNoUserPhone(newContactSupport.noUserPhone());
        contactSupportRepository.save(contactSupport);
        return contactSupport;

    }

    public ContactSupport deleteContactSupport(Long id){
        ContactSupport contactSupport = contactSupportRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Mensaje de soporte con el "+id+" no encontrado")
        );
        contactSupportRepository.delete(contactSupport);
        return contactSupport;
    }
}
