package com.requestnow.config;

import an.awesome.pipelinr.Command;
import an.awesome.pipelinr.Pipeline;
import an.awesome.pipelinr.Pipelinr;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PipelineConfig {

    @Bean
    public Pipeline pipeline(ObjectProvider<Command.Handler> handlers, ObjectProvider<Command.Middleware> middlewares) {
        return new Pipelinr().with(handlers::stream).with(middlewares::stream);
    }
}
