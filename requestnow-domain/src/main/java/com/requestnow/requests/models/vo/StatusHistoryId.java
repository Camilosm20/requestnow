package com.requestnow.requests.models.vo;

import com.requestnow.requests.constants.Constants;
import com.requestnow.requests.exceptions.DomainException;

import java.util.UUID;

public record StatusHistoryId(UUID value) {

    public StatusHistoryId{
        if(value == null){
            throw new DomainException(Constants.REQUIRED);
        }
    }

    public static StatusHistoryId create(){
        return new StatusHistoryId(UUID.randomUUID());
    }

    public static StatusHistoryId of(UUID value) {
        return new StatusHistoryId(value);
    }
}
