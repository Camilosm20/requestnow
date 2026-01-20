package com.requestnow.requests.models.vo;

import com.requestnow.requests.constants.Constants;
import com.requestnow.requests.exceptions.DomainException;

import java.text.MessageFormat;
import java.util.UUID;

public record RequestSubTypeId(UUID value) {

    public RequestSubTypeId{
        if(value == null){
            throw new DomainException(MessageFormat.format(Constants.REQUIRED, "RequestSubTypeId"));
        }
    }

    public static RequestSubTypeId of(UUID value) {
        return new RequestSubTypeId(value);
    }

    @Override
    public String toString() {
        return value.toString();
    }
}