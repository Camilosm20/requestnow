package com.requestnow.requests.models.vo;

import com.requestnow.requests.constants.Constants;
import com.requestnow.requests.exceptions.DomainException;

import java.net.URI;
import java.text.MessageFormat;

public record FileUrl(String value) {

    public FileUrl {
        if (value == null || value.isBlank()) {
            throw new DomainException(Constants.REQUIRED);
        }

        String normalized = value.trim();

        URI uri;
        try {
            uri = URI.create(normalized);
        } catch (Exception e) {
            throw new DomainException(MessageFormat.format(Constants.CONTENT_NOT_VALID, "FileUrl"), e);
        }

        String scheme = uri.getScheme();
        if (scheme == null || scheme.isBlank()) {
            throw new DomainException(MessageFormat.format(Constants.CONTENT_NOT_VALID, "FileUrl"));
        }

        if (!(scheme.equalsIgnoreCase("https") || scheme.equalsIgnoreCase("http") || scheme.equalsIgnoreCase("s3"))) {
            throw new DomainException(MessageFormat.format(Constants.CONTENT_NOT_VALID, "FileUrl"));
        }

        if ((scheme.equalsIgnoreCase("https") || scheme.equalsIgnoreCase("http"))
                && (uri.getHost() == null || uri.getHost().isBlank())) {
            throw new DomainException(MessageFormat.format(Constants.CONTENT_NOT_VALID, "FileUrl"));
        }

        value = normalized;
    }

    public static FileUrl of(String value) {
        return new FileUrl(value);
    }
}
