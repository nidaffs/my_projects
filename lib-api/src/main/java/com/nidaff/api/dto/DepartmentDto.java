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
public class DepartmentDto extends ADto {

    private List<BookDto> books = new ArrayList<>();

    private String departmentName;

}
