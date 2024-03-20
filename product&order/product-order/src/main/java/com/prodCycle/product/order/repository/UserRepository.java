package com.prodCycle.product.order.repository;

import com.prodCycle.product.order.domain.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
}
