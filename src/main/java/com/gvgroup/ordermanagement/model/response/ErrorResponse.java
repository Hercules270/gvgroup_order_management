package com.gvgroup.ordermanagement.model.response;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorResponse {

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private String errorMessage;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer errorCode;
}
