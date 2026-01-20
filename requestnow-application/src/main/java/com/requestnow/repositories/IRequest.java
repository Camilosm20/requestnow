package com.requestnow.repositories;

import com.requestnow.requests.models.Request;
import com.requestnow.requests.models.vo.RequestId;

import java.util.Optional;

public interface IRequest {
    void save(Request request);
    Optional<Request> findById(RequestId id);
    boolean existsById(RequestId id);
}
