package com.gvgroup.ordermanagement.repository;

import com.gvgroup.ordermanagement.entity.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;
import java.util.UUID;

public interface OrderRepository extends JpaRepository<Order, Long>, PagingAndSortingRepository<Order, Long> {

    Optional<Order> findByOrderIdAndUserId(UUID orderId, UUID userId);

    Optional<Order> findByOrderId(UUID orderId);

    Page<Order> findAllByUserId(UUID userId, PageRequest pageRequest);

    void deleteByOrderId(UUID orderId);
}
