package com.devcoresolutions.thepixelvault.repository;

import com.devcoresolutions.thepixelvault.models.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository<Users, Long> {
}
