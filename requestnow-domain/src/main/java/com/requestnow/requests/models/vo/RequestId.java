package com.requestnow.requests.models.vo;

import com.requestnow.requests.constants.Constants;
import com.requestnow.requests.exceptions.DomainException;

import java.text.MessageFormat;
import java.util.UUID;

public record RequestId(UUID value) {

    public RequestId{
        if(value == null){
            throw new DomainException(MessageFormat.format(Constants.REQUIRED, "Id"));
        }
    }

    public static RequestId create(){
        return new RequestId(UUID.randomUUID());
    }

    public static RequestId of(UUID value) {
        return new RequestId(value);
    }

    @Override
    public String toString() {
        return value.toString();
    }
}