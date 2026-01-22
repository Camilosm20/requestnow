package com.requestnow.controllers;


import an.awesome.pipelinr.Pipeline;
import com.requestnow.models.ApiResponse;

public abstract class ApiBaseController {

    protected final Pipeline pipeline;

    protected ApiBaseController(Pipeline pipeline) {
        this.pipeline = pipeline;
    }
}
