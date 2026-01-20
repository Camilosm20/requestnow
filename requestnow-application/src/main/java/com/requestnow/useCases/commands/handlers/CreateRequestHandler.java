package com.requestnow.useCases.commands.handlers;

import an.awesome.pipelinr.Command;
import com.requestnow.models.ApiResponse;
import com.requestnow.useCases.commands.CreateRequest;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Component
public class CreateRequestHandler implements Command.Handler<CreateRequest, ApiResponse<UUID>> {

    @Override
    @Transactional
    public ApiResponse<UUID> handle(CreateRequest request) {
        UUID id = UUID.randomUUID();
//        return ApiResponse.failure(List.of("ERROR, errores de pruebas"));
        return ApiResponse.success(id);
    }
}
