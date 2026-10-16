package com.devcoresolutions.thepixelvault.repository;

import com.devcoresolutions.thepixelvault.models.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRoleRepository extends JpaRepository<UserRole,Long> {
}
