package com.devcoresolutions.thepixelvault.repository;


import com.devcoresolutions.thepixelvault.models.ContactSupport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactSupportRepository extends JpaRepository<ContactSupport, Long> {
}
