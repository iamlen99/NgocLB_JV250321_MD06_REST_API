package ra.edu.controller_advice.custom_exception;

public class ResourceConflictException extends RuntimeException {
    public ResourceConflictException(String message) {
        super(message);
    }
}
