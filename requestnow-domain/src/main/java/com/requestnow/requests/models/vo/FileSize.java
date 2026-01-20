package com.requestnow.requests.models.vo;

import com.requestnow.requests.constants.Constants;
import com.requestnow.requests.exceptions.DomainException;

import java.text.MessageFormat;

public record FileSize(long value) {

    public static final long MAX_BYTES = 10L * 1024 * 1024; // 10 MB
    public static final long MIN_BYTES = 1L;

    public FileSize {
        if (value < MIN_BYTES) {
            throw new DomainException(MessageFormat.format(Constants.TOO_SHORT, MIN_BYTES));
        }
        if (value > MAX_BYTES) {
            throw new DomainException(MessageFormat.format(Constants.TOO_LONG, "FileSize(value)", MAX_BYTES));
        }
    }

    public static FileSize of(long value) {
        return new FileSize(value);
    }

    public double toKb() {
        return value / 1024.0;
    }

    public double toMb() {
        return value / (1024.0 * 1024.0);
    }
}