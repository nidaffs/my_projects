package com.nidaff.api.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class BookDto extends ADto {

    private BookDetailsDto bookDetails;

    private Integer quantity;

    private String avgRating;

    private List<DepartmentDto> departmentsDto;

    private List<HistoryDto> histories = new ArrayList<>();
  
}
