package sda.backend.server.error;

import java.util.Date;

public class ErrorDetails {
    private String message;
    private String details;
    private String validationType;
    private Date timestamp;

    public String getValidationType() {
        return validationType;
    }

    public ErrorDetails setValidationType(String validationType) {
        this.validationType = validationType;
        return this;
    }

    public ErrorDetails() {
    }

    public String getMessage() {
        return message;
    }

    public ErrorDetails setMessage(String message) {
        this.message = message;
        return this;
    }

    public String getDetails() {
        return details;
    }

    public ErrorDetails setDetails(String details) {
        this.details = details;
        return this;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public ErrorDetails setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
        return this;
    }

    public ErrorDetails(String message, String details, Date timestamp) {
        this.message = message;
        this.details = details;
        this.timestamp = timestamp;
    }
}
