package com.prodCycle.product.order.service;

public interface SmsStrategy {

    void sendSms(String phoneNumber, String message);

}
