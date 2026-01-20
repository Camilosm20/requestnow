package com.requestnow.requests.models.vo;

import com.requestnow.requests.constants.Constants;
import com.requestnow.requests.exceptions.DomainException;

import java.text.MessageFormat;
import java.util.Objects;

public record RequestObservation(String value) {

    public static final int MAX_LENGTH = 255;
    public static final int MIN_LENGTH = 3;

    public RequestObservation{
        if(Objects.isNull(value) || value.isEmpty()){
            throw new DomainException(MessageFormat.format(Constants.CONTENT_NOT_VALID, "RequestObservation"));
        }

        String normalized = normalize(value);

        if (normalized.isBlank()) {
            throw new DomainException(MessageFormat.format(Constants.CONTENT_NOT_VALID, "RequestObservation"));
        }
        if (normalized.length() > MAX_LENGTH) {
            throw new DomainException(MessageFormat.format(Constants.TOO_LONG, "RequestObservation", MAX_LENGTH));
        }
        if (normalized.length() < MIN_LENGTH) {
            throw new DomainException(MessageFormat.format(Constants.TOO_SHORT, MIN_LENGTH));
        }

        value = normalized;
    }

    public static RequestObservation of(String value) {
        return new RequestObservation(value);
    }

    private static String normalize(String input) {
        return input.trim().replaceAll("\\s+", " ");
    }
}