package com.fredoliveira.domain.exception;

import com.fredoliveira.util.MessageKey;
import com.fredoliveira.util.Messages;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class StoreNotFoundException extends StoreException {

    public StoreNotFoundException() {
        super(Messages.getString(MessageKey.STORE_NOT_FOUND));
    }

}
