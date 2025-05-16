package com.telu.schoolmanagement.common.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GeneralCreatedUpdatedBy {
    private Long id;
    private String name;
}
