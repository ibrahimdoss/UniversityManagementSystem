package com.prodCycle.product.order.service;

@FunctionalInterface
public interface ReplaceFunction {
    String replace (String template, String name, String orderNumber);
}
