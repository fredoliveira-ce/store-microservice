package com.fredoliveira.domain.exception;

import com.fredoliveira.util.MessageKey;
import com.fredoliveira.util.Messages;
import com.fredoliveira.web.dto.ErrorDTO;
import lombok.extern.java.Log;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

import static com.fredoliveira.util.MessageKey.ERROR_VALIDATION_DESCRIPTION;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@Log
@ControllerAdvice
public class ExceptionTranslatorController {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(BAD_REQUEST)
    @ResponseBody
    public ErrorDTO processValidationError(MethodArgumentNotValidException ex) {

        BindingResult result = ex.getBindingResult();
        List<FieldError> fieldErrors = result.getFieldErrors();

        return processFieldErrors(fieldErrors);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    @ResponseStatus(INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ErrorDTO processDataIntegrityViolation(DataIntegrityViolationException ex) {
        ErrorDTO dto = ErrorDTO.builder()
                .errorCode(ErrorDTO.ErrorCodes.ERR_DATA_INTEGRITY)
                .message(ex.getMessage())
                .build();

        if (ex.getCause() instanceof ConstraintViolationException) {
            ConstraintViolationException constraintViolation = (ConstraintViolationException) ex.getCause();
            String constraintName = constraintViolation.getCause().getMessage();

            if (constraintName.contains("payment_key")) {
                dto = ErrorDTO.builder()
                        .errorCode(ErrorDTO.ErrorCodes.ERR_DATA_INTEGRITY)
                        .message(Messages.getString(MessageKey.PAYMENT_ALREADY_EXISTS))
                        .build();
            } else {
                dto = ErrorDTO.builder()
                        .errorCode(ErrorDTO.ErrorCodes.ERR_DATA_INTEGRITY)
                        .message(constraintName)
                        .build();
            }

        }

        return dto;
    }

    private ErrorDTO processFieldErrors(List<FieldError> fieldErrors) {
        ErrorDTO dto = ErrorDTO.builder()
                .errorCode(ErrorDTO.ErrorCodes.ERR_VALIDATION)
                .message(Messages.getString(ERROR_VALIDATION_DESCRIPTION))
                .build();

        fieldErrors.forEach(fieldError -> dto.add(fieldError.getObjectName(), fieldError.getField(), fieldError.getDefaultMessage()));

        return dto;
    }

}
