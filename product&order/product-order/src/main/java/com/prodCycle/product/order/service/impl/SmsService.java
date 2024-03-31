package com.prodCycle.product.order.service.impl;

import com.prodCycle.product.order.domain.OrderEntity;
import com.prodCycle.product.order.domain.UserEntity;
import com.prodCycle.product.order.service.ReplaceFunction;
import com.prodCycle.product.order.service.SmsStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
@RequiredArgsConstructor
public class SmsService {

    private final SmsStrategy standardSmsStrategy;
    private final SmsStrategy premiumSmsStrategy;

    @Async
    public void sendSmsToUser(OrderEntity order, UserEntity user) {
        ReplaceFunction replaceFunction = (template, name, orderNumber) -> template.replace("NAME", name).replace("ORDERNUMBER", orderNumber);

        String orderNumber = order.getOrderNumber();
        String name = user.getName();
        String phoneNumber = user.getPhoneNumber();

        if (StringUtils.hasText(orderNumber)) {
            String smsBody = "Merhaba NAME, siparişiniz alındı. Sipariş numarası: ORDERNUMBER. Detaylar kısa süre içinde telefonunuza gönderilecektir.";
            smsBody = replaceFunction.replace(smsBody, name, orderNumber);

            SmsStrategy strategy = user.getIsPremium() ? premiumSmsStrategy : standardSmsStrategy;

            strategy.sendSms(phoneNumber, smsBody);
        }
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Async
    public void sendSmsToUpdateUser(UserEntity user) {
        String name = user.getName();
        String phoneNumber = user.getPhoneNumber();

        if (StringUtils.hasText(phoneNumber)) {
            String smsBody = String.format("Merhaba %s, bilgileriniz güncellenmiştir.", name);

            SmsStrategy strategy = user.getIsPremium() ? premiumSmsStrategy : standardSmsStrategy;
            strategy.sendSms(phoneNumber, smsBody);
        }
    }

}
