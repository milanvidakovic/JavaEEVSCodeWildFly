package services.errors;

public class ErrorMessage {
    private String message;

    public ErrorMessage() {

    }

    public ErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
    @Override
    public String toString() {
        return message;
    }
}
