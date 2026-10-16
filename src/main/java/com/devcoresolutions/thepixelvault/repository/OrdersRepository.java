package com.devcoresolutions.thepixelvault.repository;

import com.devcoresolutions.thepixelvault.models.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OrdersRepository  extends JpaRepository<Orders, Long> {
    List<Orders> findByUsersId(Long usersId);
    Optional<Orders> findByIdAndUsers_Id(Long orderId, Long usersId);
}
