package com.fredoliveira.domain.exception;

import com.fredoliveira.util.MessageKey;
import com.fredoliveira.util.Messages;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class OrderNotFoundException extends OrderException {

    public OrderNotFoundException() {
        super(Messages.getString(MessageKey.ORDER_NOT_FOUND));
    }

}
