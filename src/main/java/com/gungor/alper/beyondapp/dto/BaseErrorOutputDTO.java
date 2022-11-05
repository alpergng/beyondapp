package com.gungor.alper.beyondapp.dto;

import lombok.Data;

import java.time.Instant;

@Data
public class BaseErrorOutputDTO {

    private String timestamp;
    private String status;
    private String error;
    private String path;

    public BaseErrorOutputDTO(String status, String error, String path) {
        this.status = status;
        this.error = error;
        this.path = path;
        this.timestamp = Instant.now().toString();
    }
}
