package com.telu.schoolmanagement.common.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PaginationResponse {

    /// current page
    private int page;

    /// size per page
    private int size;

    /// total rows ( data ) available
    private long totalRows;

    /// total pages
    private int totalPages;

    /// true, if a next page available
    private boolean hasNext;

    ///  true, if a previous page available
    private boolean hasPrevious;
}
