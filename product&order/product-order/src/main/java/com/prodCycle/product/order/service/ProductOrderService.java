package com.prodCycle.product.order.service;

import com.prodCycle.product.order.domain.OrderEntity;
import com.prodCycle.product.order.domain.OrderProductEntity;
import com.prodCycle.product.order.domain.ProductEntity;
import com.prodCycle.product.order.exception.BusinessException;
import com.prodCycle.product.order.mapper.ProductMapper;
import com.prodCycle.product.order.repository.OrderProductRepository;
import com.prodCycle.product.order.repository.OrderRepository;
import com.prodCycle.product.order.repository.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductOrderService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final OrderRepository orderRepository;
    private final OrderProductRepository orderProductEntityRepository;

    private static final Logger logger = LoggerFactory.getLogger(ProductService.class);


    public ProductOrderService(ProductRepository productRepository, ProductMapper productMapper, OrderRepository orderRepository, OrderProductRepository orderProductEntityRepository) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
        this.orderRepository = orderRepository;
        this.orderProductEntityRepository = orderProductEntityRepository;
    }


    public void getProductByOrderId(Long orderId){
        OrderEntity orderEntity = orderRepository.findById(orderId).get();

        List<OrderProductEntity> allByOrder = orderProductEntityRepository.findAllByOrder(orderEntity);
        for (OrderProductEntity orderProductEntity : allByOrder){
            ProductEntity productEntity = orderProductEntity.getProduct();
            logger.info("product");
        }
    }


    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void saveOrderProduct(List<Long> productIdList, OrderEntity orderEntity) {
        productIdList.stream()
                .map(productId -> productRepository.findById(productId)
                        .orElseThrow(() -> new BusinessException("Product not found for ID: " + productId)))
                .forEach(productEntity -> {
                    if (productEntity.getNumberOfProduct() <= 0) {
                        throw new BusinessException("Product is out of stock for ID: " + productEntity.getId());
                    }

                    try {
                        OrderProductEntity orderProductEntity = new OrderProductEntity();
                        orderProductEntity.setOrder(orderEntity);
                        orderProductEntity.setProduct(productEntity);
                        int numberOfProduct = productEntity.getNumberOfProduct();
                        productEntity.setNumberOfProduct(--numberOfProduct);

                        orderProductEntityRepository.save(orderProductEntity);
                        productRepository.save(productEntity);
                    } catch (Exception e) {
                        throw new BusinessException("Error processing order for product ID: " + productEntity.getId());
                    }
                });
    }
}
