package com.requestnow.requests.models.vo;

import com.requestnow.requests.constants.Constants;
import com.requestnow.requests.exceptions.DomainException;

public record Rating(int value) {

    public static final int MIN = 1;
    public static final int MAX = 5;

    public Rating {
        if (value < MIN || value > MAX) {
            throw new DomainException(Constants.OPERATION_NOT_ALLOWED);
        }
    }

    public static Rating of(int value) {
        return new Rating(value);
    }
}
