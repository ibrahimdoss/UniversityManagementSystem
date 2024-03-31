package com.prodCycle.product.order.service.impl;

import com.prodCycle.product.order.service.SmsStrategy;
import org.springframework.stereotype.Service;

@Service
public class StandardSmsStrategy implements SmsStrategy {
    @Override
    public void sendSms(String phoneNumber, String message) {
            System.out.println("Standard SMS Provider: SMS sent to " + phoneNumber + ": " + message);
        }

}
