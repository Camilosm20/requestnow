package com.requestnow.requests.models.events;

import com.requestnow.requests.models.shared.UserId;
import com.requestnow.requests.models.vo.RequestId;

import java.time.Instant;

public record RequestAssigned(RequestId requestId, UserId assignedTo, UserId assignedBy, Instant occurredAt) implements DomainEvent {
    @Override public String eventType() { return "RequestAssigned"; }
}
