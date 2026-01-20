package com.requestnow.requests.models.vo;

import com.requestnow.requests.constants.Constants;
import com.requestnow.requests.exceptions.DomainException;

import java.text.MessageFormat;
import java.util.UUID;

public record RequestTypeId(UUID value) {

    public RequestTypeId{
        if (value == null){
            throw new DomainException(MessageFormat.format(Constants.REQUIRED, "UserId"));
        }
    }

    public static RequestTypeId of(UUID value) {
        return new RequestTypeId(value);
    }

    @Override
    public String toString() {
        return value.toString();
    }
}