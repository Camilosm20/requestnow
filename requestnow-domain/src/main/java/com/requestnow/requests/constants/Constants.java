package com.requestnow.requests.constants;

public final class Constants {

    private Constants(){}


    public static final String CONTENT_NOT_VALID = "Contenido no valido, variable: {0}";
    public static final String TOO_LONG = "la variable {0} no puede exceder {1} caracteres";
    public static final String TOO_SHORT = "la variable debe tener al menos {0} caracteres.";
    public static final String REQUIRED = "Campo requerido: {0}.";

    public static final String NOT_FOUND = "No se encontró el recurso solicitado.";
    public static final String CONTENT_NOT_FOUND = "No se encontró contenido para {0}.";
    public static final String INVALID_STATE = "La operación no es válida para el estado actual: {0}.";
    public static final String OPERATION_NOT_ALLOWED = "La operación no está permitida.";
    public static final String UNAUTHORIZED = "No está autorizado para realizar esta acción.";
    public static final String FORBIDDEN = "Acceso denegado.";
    public static final String UNEXPECTED_ERROR = "Ocurrió un error inesperado.";
}
