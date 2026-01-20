package com.requestnow.requests.models.events;

import java.time.Instant;

public interface DomainEvent {
    String eventType();
    Instant occurredAt();
}
