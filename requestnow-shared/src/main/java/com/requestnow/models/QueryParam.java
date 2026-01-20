package com.requestnow.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class QueryParam {

    public String search = "";
    public String orderBy;
    public Boolean isAscending = false;
    public int pageNumber = 1;
    public int pageSize = 10;
    public Instant createdAt;
    public Instant startDate;
    public Instant endDate;
    public Instant lastDate = null;
}