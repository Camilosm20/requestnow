package com.requestnow.requests.models.vo;

import com.requestnow.requests.constants.Constants;
import com.requestnow.requests.exceptions.DomainException;

import java.text.MessageFormat;
import java.util.UUID;

public record AttachmentId(UUID value) {

    public AttachmentId{
        if (value == null){
            throw new DomainException(MessageFormat.format(Constants.REQUIRED, "AttachmentId"));
        }
    }

    public static AttachmentId create(){
        return new AttachmentId(UUID.randomUUID());
    }

    public static AttachmentId of(UUID id){
        return new AttachmentId(id);
    }

    @Override
    public String toString() {
        return value.toString();
    }
}
