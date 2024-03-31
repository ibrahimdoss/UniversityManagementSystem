package com.prodCycle.product.order.service.impl;

import com.prodCycle.product.order.domain.OrderEntity;
import com.prodCycle.product.order.domain.OrderProductEntity;
import com.prodCycle.product.order.domain.ProductEntity;
import com.prodCycle.product.order.exception.BusinessException;
import com.prodCycle.product.order.repository.OrderProductRepository;
import com.prodCycle.product.order.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderProductService {

    private final OrderProductRepository orderProductRepository;
    private final ProductService productService;

    private static final Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);


    public OrderProductService(OrderProductRepository orderProductRepository , ProductService productService) {
        this.orderProductRepository = orderProductRepository;
        this.productService = productService;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void saveOrderProduct(List<Long> productIdList, OrderEntity orderEntity) {
         productIdList.forEach(productId -> processProduct(productId, orderEntity));
    }

    private void processProduct(Long productId, OrderEntity orderEntity) {
        ProductEntity product = productService.findProductById(productId);
        if (product == null) {
            throw new BusinessException("Product not found with id: " + productId);
        }

        updateProductStock(product);

        OrderProductEntity orderProduct = new OrderProductEntity();
        orderEntity.setProduct(product);
        orderEntity.setTotalAmount(product.getPrice());
        orderProduct.setOrder(orderEntity);
        orderProduct.setProduct(product);
        orderProductRepository.save(orderProduct);
        logger.info("Order product saved for order: {} and product: {}", orderEntity.getOrderNumber(), product.getId());
    }

    //TODO synchronized thread safe (multi customer durumda race condition olmaması için)
    private synchronized void updateProductStock(ProductEntity product) {
        if (product.getNumberOfProduct() > 0) {
            product.setNumberOfProduct(product.getNumberOfProduct() - 1);
            productService.saveProduct(product);
        } else {
            throw new BusinessException("Product is out of stock for productId: " + product.getId());
        }
    }

    public List<ProductEntity> getProductsByOrderId(Long orderId) {
        List<OrderProductEntity> orderProducts = orderProductRepository.findAllByOrder_Id(orderId);
        if (orderProducts.isEmpty()) {
            return Collections.emptyList();
        }

        return orderProducts.stream()
                .map(OrderProductEntity::getProduct)
                .collect(Collectors.toList());
    }
}
