package br.com.davicabeleireiro.davicabeleireiro.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

public class ExceptionResponse implements Serializable {

    @DateTimeFormat(pattern = "dd-MM-yyyy hh:mm:ss")
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss", timezone = "GMT-3")
    private Date timestamp;
    private String message;
    private String details;

    public ExceptionResponse(Date timestamp, String message, String details) {
        this.timestamp = timestamp;
        this.message = message;
        this.details = details;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public String getMessage() {
        return message;
    }

    public String getDetails() {
        return details;
    }
}
