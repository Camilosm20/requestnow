package com.requestnow.requests.models.entities;

import com.requestnow.requests.models.shared.UserId;
import com.requestnow.requests.models.vo.RequestId;
import lombok.Getter;

import java.time.Instant;
import java.util.Objects;
import java.util.UUID;

@Getter
public final class Assignment {

    private final UUID id;
    private final RequestId requestId;
    private final UserId assignedId;
    private boolean active;
    private final Instant createdAt;

    private Assignment(UUID id, RequestId requestId, UserId assignedId, boolean active, Instant createdAt) {
        this.id = Objects.requireNonNull(id);
        this.requestId = Objects.requireNonNull(requestId);
        this.assignedId = Objects.requireNonNull(assignedId);
        this.active = active;
        this.createdAt = Objects.requireNonNull(createdAt);
    }

    public static Assignment active(RequestId requestId, UserId assignedId, Instant createdAt) {
        return new Assignment(UUID.randomUUID(), requestId, assignedId, true, createdAt);
    }

    public void deactivate() {
        this.active = false;
    }
}
