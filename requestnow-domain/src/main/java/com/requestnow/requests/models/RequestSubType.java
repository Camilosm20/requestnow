package com.requestnow.requests.models;

import com.requestnow.requests.models.vo.RequestSubTypeId;
import com.requestnow.requests.models.vo.RequestTypeId;
import lombok.Getter;

import java.time.Instant;

public class RequestSubType {

    public RequestSubTypeId id;
    public RequestTypeId requestTypeId;
    public String Name;
    public Instant createdAt;
    public Instant updatedAt;
}
