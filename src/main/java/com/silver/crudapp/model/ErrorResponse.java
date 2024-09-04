package com.silver.crudapp.model;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponse {
    private int statusCode;
    private String message;

    public ErrorResponse(String message){
        //super();
        this.message=message;
    }

}
