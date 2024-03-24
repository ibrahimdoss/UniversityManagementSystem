package com.prodCycle.product.order.domain.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserSaveRequestDto {

    private String name;
    private String lastName;
    private String email;
    private String phoneNumber;
    private Boolean isActive;
}
