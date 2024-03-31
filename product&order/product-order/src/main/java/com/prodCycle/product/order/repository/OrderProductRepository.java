package com.prodCycle.product.order.repository;

import com.prodCycle.product.order.domain.OrderEntity;
import com.prodCycle.product.order.domain.OrderProductEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderProductRepository extends CrudRepository<OrderProductEntity, Long> {


    @Query("select po from OrderProductEntity po where po.order.id = :orderId")
    List<OrderProductEntity> findProductOrdersByOrderId(Long orderId);


    @Query("SELECT op FROM OrderProductEntity op WHERE op.order.id = :orderId")
    List<OrderProductEntity> findAllByOrder_Id(@Param("orderId") Long orderId);
}
