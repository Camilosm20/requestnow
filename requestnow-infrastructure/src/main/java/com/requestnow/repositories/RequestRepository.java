package com.requestnow.repositories;

import com.requestnow.requests.models.Request;
import com.requestnow.requests.models.vo.RequestId;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class RequestRepository implements IRequest{
    @Override
    public void save(Request request) {

    }

    @Override
    public Optional<Request> findById(RequestId id) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(RequestId id) {
        return false;
    }
}
