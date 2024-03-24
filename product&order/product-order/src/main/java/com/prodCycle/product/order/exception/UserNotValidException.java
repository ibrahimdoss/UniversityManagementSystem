package com.prodCycle.product.order.exception;

public class UserNotValidException extends BusinessException{

    public UserNotValidException(Long userId) {
        super(ErrorCode.valueOf("User is not valid, ID: " + userId));
    }
}
