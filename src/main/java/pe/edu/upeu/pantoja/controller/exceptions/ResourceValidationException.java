package pe.edu.upeu.pantoja.controller.exceptions;

public class ResourceValidationException extends RuntimeException{
    public ResourceValidationException(String message) {
        super(message);
    }

    public ResourceValidationException(String message, Throwable cause) {
        super(message, cause);
    }
}