package com.nidaff.entity.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import java.util.List;

@Entity
@Table(name = "department")
@Getter
@Setter
@NoArgsConstructor
public class Department extends AEntity {

    @Column(name = "department_name")
    private String departmentName;

    @ManyToMany(mappedBy = "departments")
    private List<Book> books;

}
