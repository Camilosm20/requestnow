package com.requestnow.useCases.commands;

import an.awesome.pipelinr.Command;
import com.requestnow.models.ApiResponse;

import java.util.UUID;

public record CreateRequest (
        UUID requestedBy,
        UUID createdBy,
        UUID typeId,
        UUID subTypeId,
        String subject,
        String observation
) implements Command<ApiResponse<UUID>> {}
