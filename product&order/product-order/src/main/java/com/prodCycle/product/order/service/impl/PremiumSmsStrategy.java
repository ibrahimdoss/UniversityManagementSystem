package com.prodCycle.product.order.service.impl;

import com.prodCycle.product.order.service.SmsStrategy;
import org.springframework.stereotype.Service;

@Service
public class PremiumSmsStrategy implements SmsStrategy {
    @Override
    public void sendSms(String phoneNumber, String message) {

        System.out.println("Premium SMS Provider: SMS sent to " + phoneNumber + ": " + message);

    }
}
