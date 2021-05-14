package com.example.exception;

public class ResponseException extends RuntimeException {
    public ResponseException() {
        super();
    }

    public ResponseException(String message) {
        super(message);
    }

    public ResponseException(Throwable cause) {
        super(cause);
    }

    public ResponseException(String message, Throwable cause) {
        super(message, cause);
    }


}
