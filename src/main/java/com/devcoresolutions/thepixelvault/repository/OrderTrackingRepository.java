package com.devcoresolutions.thepixelvault.repository;

import com.devcoresolutions.thepixelvault.models.OrderTracking;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface OrderTrackingRepository extends JpaRepository<OrderTracking, Long> {

    List<OrderTracking> findByOrderStatus(String orderStatus);

}