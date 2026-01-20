package com.requestnow.requests.models.vo;

import com.requestnow.requests.constants.Constants;
import com.requestnow.requests.exceptions.DomainException;

import java.text.MessageFormat;
import java.util.Set;

public enum FileType {

    IMAGE(Set.of(
            "image/png",
            "image/jpeg",
            "image/jpg",
            "image/webp"
    )),

    PDF(Set.of(
            "application/pdf"
    ));

    private final Set<String> allowedTypes;

    FileType(Set<String> allowedTypes) {
        this.allowedTypes = allowedTypes;
    }

    public static FileType fromType(String Type) {
        if (Type == null || Type.isBlank()) {
            throw new DomainException(MessageFormat.format(Constants.CONTENT_NOT_VALID,"FileType"));
        }

        for (FileType type : values()) {
            if (type.allowedTypes.contains(Type.toLowerCase())) {
                return type;
            }
        }

        throw new DomainException(Constants.UNEXPECTED_ERROR);
    }
}
