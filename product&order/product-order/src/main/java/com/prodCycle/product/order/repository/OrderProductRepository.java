package com.prodCycle.product.order.repository;

import com.prodCycle.product.order.domain.OrderEntity;
import com.prodCycle.product.order.domain.OrderProductEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface OrderProductRepository extends CrudRepository<OrderProductEntity, Long> {


    @Query("select po from OrderProductEntity po where po.order.id = :orderId")
    List<OrderProductEntity> findProductOrdersByOrderId(Long orderId);

    List<OrderProductEntity> findAllByOrder(OrderEntity order);
}
