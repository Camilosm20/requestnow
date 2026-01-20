package com.requestnow.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ApiResponse<T> {

    private boolean isSuccess;
    private T data;
    private String message;
    private List<String> errors;
    private Map<String, String> messageErrors;

    private ApiResponse(T data, boolean isSuccess, String message) {
        this.isSuccess = isSuccess;
        this.data = data;
        this.message = message;
    }

    private ApiResponse(List<String> errors, boolean isSuccess) {
        this.isSuccess = isSuccess;
        this.errors = errors;
    }

    public static <T> ApiResponse<T> success(T data) {
        return new ApiResponse<>(data, true, null);
    }

    public static <T> ApiResponse<T> success(T data, String message) {
        return new ApiResponse<>(data, true, message);
    }

    public static <T> ApiResponse<T> failure(List<String> errors) {
        return new ApiResponse<>(errors, false);
    }

    public static <T> ApiResponse<T> failure(Map<String, String> messageErrors) {
        ApiResponse<T> response = new ApiResponse<>();
        response.isSuccess = false;
        response.messageErrors = messageErrors;
        return response;
    }

    public <R> R match(Function<ApiResponse<T>, R> onSuccess, Function<ApiResponse<T>, R> onFailure) {
        return (isSuccess && data != null) ? onSuccess.apply(this) : onFailure.apply(this);
    }
}
