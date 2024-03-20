package com.prodCycle.product.order.service;

import com.prodCycle.product.order.domain.OrderEntity;
import com.prodCycle.product.order.domain.UserEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class SmsService {

    @Async
    public void sendSmsToUser(OrderEntity order, UserEntity users) {
        ReplaceFunction replaceFunction = (template, name, orderNumber) -> template.replace("NAME", name).replace("ORDERNUMBER", orderNumber);

        String orderNumber = order.getOrderNumber();
        String name = users.getName();
        String phoneNumber = users.getPhoneNumber();

        if (StringUtils.hasText(orderNumber)) {
            String smsBody = "Merhaba NAME, siparişiniz alındı. Sipariş numarası: ORDERNUMBER. Detaylar kısa süre içinde telefonunuza gönderilecektir.";
            smsBody = replaceFunction.replace(smsBody, name, orderNumber);

            sendSms(phoneNumber, smsBody);
        }
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private void sendSms(String phoneNumber, String message) {
        System.out.println("SMS sent to " + phoneNumber + ": " + message);
    }

}
