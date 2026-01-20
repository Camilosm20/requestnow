package com.requestnow.requests.models.entities;

import com.requestnow.requests.models.shared.UserId;
import com.requestnow.requests.models.vo.*;
import lombok.Getter;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
public final class StatusHistoryItem {

    private final StatusHistoryId id;
    private final RequestId requestId;
    private final RequestStatus statusId;
    private final UserId userId;
    private boolean isActive;
    private final Instant createdAt;

    private final List<Attachment> attachments = new ArrayList<>();

    private StatusHistoryItem(StatusHistoryId id, RequestId requestId, RequestStatus statusId, UserId userId, boolean isActive, Instant createdAt) {
        this.id = Objects.requireNonNull(id);
        this.requestId = Objects.requireNonNull(requestId);
        this.statusId = Objects.requireNonNull(statusId);
        this.userId = Objects.requireNonNull(userId);
        this.isActive = isActive;
        this.createdAt = Objects.requireNonNull(createdAt);
    }

    public void addAttachment(AttachmentId attachmentId, String fileName, FileUrl url, FileType type, FileSize size, Instant now) {
        Attachment attachment = Attachment.create(attachmentId, this.id, fileName, url, type,size,now);
        this.attachments.add(attachment);
    }

    public static StatusHistoryItem initial(RequestId requestId, RequestStatus statusId, UserId userId, Instant at) {
        return new StatusHistoryItem(StatusHistoryId.create(), requestId, statusId, userId, true, at);
    }

    public static StatusHistoryItem change(RequestId requestId, RequestStatus statusId, UserId userId, Instant at) {
        return new StatusHistoryItem(StatusHistoryId.create(), requestId, statusId, userId, true, at);
    }
}
