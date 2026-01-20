package com.requestnow.requests.models.entities;

import com.requestnow.requests.constants.Constants;
import com.requestnow.requests.exceptions.DomainException;
import com.requestnow.requests.models.vo.*;
import lombok.Getter;

import java.time.Instant;
import java.util.Objects;

@Getter
public final class Attachment {

    private final AttachmentId id;
    private final StatusHistoryId statusHistoryId;

    private final String fileName;
    private final FileUrl url;
    private final FileType type;
    private final FileSize size;

    private final Instant createdAt;

    private Attachment(AttachmentId id, StatusHistoryId statusHistoryId, String fileName, FileUrl url, FileType type, FileSize size,Instant createdAt) {
        this.id = Objects.requireNonNull(id);
        this.statusHistoryId = Objects.requireNonNull(statusHistoryId);

        if (fileName == null || fileName.trim().isEmpty()) {
            throw new DomainException(Constants.CONTENT_NOT_VALID);
        }
        this.fileName = fileName.trim();

        this.url = Objects.requireNonNull(url);
        this.type = Objects.requireNonNull(type);
        this.size = Objects.requireNonNull(size);
        this.createdAt = Objects.requireNonNull(createdAt);
    }

    public static Attachment create(AttachmentId id, StatusHistoryId statusHistoryId, String fileName, FileUrl url, FileType type, FileSize size,Instant createdAt) {
        return new Attachment(id, statusHistoryId, fileName, url, type, size, createdAt);
    }
}