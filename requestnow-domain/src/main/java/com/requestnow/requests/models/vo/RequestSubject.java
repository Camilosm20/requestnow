package com.requestnow.requests.models.vo;

import com.requestnow.requests.constants.Constants;
import com.requestnow.requests.exceptions.DomainException;

import java.text.MessageFormat;
import java.util.Objects;

public record RequestSubject(String value) {

    public static final int MAX_LENGTH = 100;
    public static final int MIN_LENGTH = 3;

    public RequestSubject {

        if(Objects.isNull(value) || value.isEmpty()){
            throw new DomainException(MessageFormat.format(Constants.CONTENT_NOT_VALID, "RequestSubject"));
        }

        String normalized = normalize(value);

        if (normalized.isBlank()) {
            throw new DomainException(MessageFormat.format(Constants.CONTENT_NOT_VALID, "RequestSubject"));
        }
        if (normalized.length() > MAX_LENGTH) {
            throw new DomainException(MessageFormat.format(Constants.TOO_LONG, "RequestSubject", MAX_LENGTH));
        }
        if (normalized.length() < MIN_LENGTH) {
            throw new DomainException(MessageFormat.format(Constants.TOO_SHORT, MIN_LENGTH));
        }

        value = normalized;
    }

    public static RequestSubject of(String value) {
        return new RequestSubject(value);
    }

    private static String normalize(String input) {
        return input.trim().replaceAll("\\s+", " ");
    }

}