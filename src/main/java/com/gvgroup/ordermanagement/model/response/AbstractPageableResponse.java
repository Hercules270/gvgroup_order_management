package com.gvgroup.ordermanagement.model.response;


import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Data
@SuperBuilder
public abstract class AbstractPageableResponse<T> {

    private int totalPages;
    private long totalElements;
    private List<T> elements;

}
