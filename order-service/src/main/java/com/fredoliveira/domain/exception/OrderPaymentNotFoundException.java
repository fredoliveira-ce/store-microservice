package com.fredoliveira.domain.exception;

import com.fredoliveira.util.MessageKey;
import com.fredoliveira.util.Messages;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class OrderPaymentNotFoundException extends OrderException {

    public OrderPaymentNotFoundException() {
        super(Messages.getString(MessageKey.ORDER_PAYMENT_NOT_FOUND));
    }

}
