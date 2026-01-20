package com.requestnow.controllers;

import an.awesome.pipelinr.Pipeline;
import com.requestnow.models.ApiResponse;
import com.requestnow.useCases.commands.CreateRequest;
import com.requestnow.useCases.commands.handlers.CreateRequestHandler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/requests")
public class RequestController {

    private final Pipeline pipeline;

    public RequestController(Pipeline pipeline) {
        this.pipeline = pipeline;
    }

    @PostMapping("create")
    public ResponseEntity<ApiResponse<UUID>> create(@RequestBody CreateRequest request){
        var response = pipeline.send(request);
        return response.match(ok -> ResponseEntity.status(HttpStatus.CREATED).body(ok),fail -> ResponseEntity.status(HttpStatus.BAD_REQUEST).body(fail));
    }
}
