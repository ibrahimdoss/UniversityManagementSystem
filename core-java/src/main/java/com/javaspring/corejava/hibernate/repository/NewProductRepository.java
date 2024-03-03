package com.javaspring.corejava.hibernate.repository;

import com.javaspring.corejava.hibernate.domain.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NewProductRepository extends JpaRepository<ProductEntity, Long> {
}
