package com.springboot.exception;

public class StudentException extends Exception {
    public StudentException() {
        super();
    }
    public StudentException(String message, Throwable cause) {
        super(message, cause);
    }
    public StudentException(String message) {
        super(message);
    }
    public StudentException(Throwable cause) {
        super(cause);
    }
}
