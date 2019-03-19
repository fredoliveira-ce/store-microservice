package com.fredoliveira.domain.exception;

import com.fredoliveira.util.MessageKey;
import com.fredoliveira.util.Messages;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class PaymentNotFoundException extends PaymentException {

    public PaymentNotFoundException() {
        super(Messages.getString(MessageKey.PAYMENT_NOT_FOUND));
    }

}
