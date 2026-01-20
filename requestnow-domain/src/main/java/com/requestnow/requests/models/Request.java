package com.requestnow.requests.models;

import com.requestnow.requests.constants.Constants;
import com.requestnow.requests.exceptions.DomainException;
import com.requestnow.requests.models.entities.Assignment;
import com.requestnow.requests.models.entities.StatusHistoryItem;
import com.requestnow.requests.models.events.DomainEvent;
import com.requestnow.requests.models.events.RequestAssigned;
import com.requestnow.requests.models.shared.UserId;
import com.requestnow.requests.models.vo.*;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public final class Request {

    private final RequestId id;

    private final UserId requestedBy;
    private final UserId createdBy;

    private final RequestTypeId typeId;
    private final RequestSubTypeId subTypeId;

    private RequestSubject subject;
    private RequestObservation observation;

    private RequestStatus currentStatus;
    private final List<StatusHistoryItem> statusHistory;

    private final List<Assignment> assignments;

    private Rating rating;
    private String ratingComment;

    private final Instant createdAt;
    private Instant updatedAt;

    private final List<DomainEvent> domainEvents;

    private Request(RequestId id, UserId requestedBy, UserId createdBy, RequestTypeId typeId, RequestSubTypeId subTypeId, RequestSubject subject, RequestObservation observation, RequestStatus currentStatus, List<StatusHistoryItem> statusHistory, List<Assignment> assignments, Rating rating, String ratingComment, Instant createdAt, Instant updatedAt) {

        this.id = Objects.requireNonNull(id);
        this.requestedBy = Objects.requireNonNull(requestedBy);
        this.createdBy = Objects.requireNonNull(createdBy);
        this.typeId = Objects.requireNonNull(typeId);
        this.subTypeId = Objects.requireNonNull(subTypeId);
        this.subject = Objects.requireNonNull(subject);
        this.observation = observation;

        this.currentStatus = Objects.requireNonNull(currentStatus);
        this.statusHistory = new ArrayList<>(Objects.requireNonNull(statusHistory));

        this.assignments = new ArrayList<>(Objects.requireNonNull(assignments));

        this.rating = rating;
        this.ratingComment = ratingComment;

        this.createdAt = Objects.requireNonNull(createdAt);
        this.updatedAt = Objects.requireNonNull(updatedAt);

        this.domainEvents = new ArrayList<>();
    }

    public static Request create(RequestId id, UserId requestedBy, UserId createdBy, RequestTypeId typeId, RequestSubTypeId subTypeId, RequestSubject subject, RequestObservation observation, Instant now) {
        RequestStatus initialStatus = RequestStatus.NEW;
        List<StatusHistoryItem> history = new ArrayList<>();
        history.add(StatusHistoryItem.initial(id, initialStatus, createdBy, now));

        Request request = new Request(id, requestedBy, createdBy, typeId, subTypeId, subject, observation, initialStatus, history,
                new ArrayList<>(), null, null, now, now);

        return request;
    }

    public void changeStatus(RequestStatus newStatus, UserId changedBy, Instant now) {
        if (this.currentStatus == newStatus) return;

        if (this.currentStatus != RequestStatus.NEW && newStatus == RequestStatus.NEW) {
            throw new DomainException(Constants.OPERATION_NOT_ALLOWED);
        }
    }

    public void assignTo(UserId assignee, UserId assignedBy, Instant now) {
        this.assignments.forEach(Assignment::deactivate);

        Assignment newAssignment = Assignment.active(this.id, assignee, now);
        this.assignments.add(newAssignment);
        this.updatedAt = now;

        addDomainEvent(new RequestAssigned(this.id, assignee, assignedBy, now));
    }

    public void addAttachmentToCurrentStatus(AttachmentId attachmentId, String fileName, FileUrl url, FileType type, FileSize size, UserId userId, Instant now) {
        if(this.statusHistory.isEmpty()){
            throw new DomainException(Constants.OPERATION_NOT_ALLOWED);
        }

        StatusHistoryItem currentStatus = this.statusHistory.get(statusHistory.size() - 1);

        if(!currentStatus.isActive()){
            throw new DomainException(Constants.CONTENT_NOT_FOUND);
        }

        this.updatedAt = now;
    }

    public void rate(Rating rating, String comment, Instant now) {
        if (this.currentStatus != RequestStatus.RESOLVED) {
            throw new DomainException(Constants.OPERATION_NOT_ALLOWED);
        }

        this.rating = rating;
        this.ratingComment = comment;
        this.updatedAt = now;
    }

    private void addDomainEvent(DomainEvent event) {
        this.domainEvents.add(event);
    }

    public List<DomainEvent> pullDomainEvents() {
        List<DomainEvent> copy = List.copyOf(this.domainEvents);
        this.domainEvents.clear();
        return copy;
    }
}