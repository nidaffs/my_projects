package com.nidaff.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class BookDto extends ADto {

    private BookDetailsDto bookDetails;

    private Integer quantity;

    private String avgRating;

    private List<DepartmentDto> departmentsDto;

    private List<HistoryDto> histories = new ArrayList<>();
  
}
