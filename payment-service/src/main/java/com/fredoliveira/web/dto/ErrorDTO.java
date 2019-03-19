package com.fredoliveira.web.dto;

import lombok.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ErrorDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String errorCode;
    private String message;
    private List<FieldErrorDTO> fieldErrors;

    public void add(String objectName, String field, String message) {
        if (fieldErrors == null) {
            fieldErrors = new ArrayList<>();
        }
        fieldErrors.add(new FieldErrorDTO(objectName, field, message));
    }

    public final class ErrorCodes {

        public static final String ERR_VALIDATION = "error.validation";
        public static final String ERR_DATA_INTEGRITY = "error.dataIntegrity";

        private ErrorCodes() {
        }

    }

}