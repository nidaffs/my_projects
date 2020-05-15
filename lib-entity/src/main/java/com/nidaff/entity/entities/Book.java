package com.nidaff.entity.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import java.util.List;

@Entity
@Table(name = "book")
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class Book extends AEntity {

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "avg_rating")
    private Double avgRating;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_details_id", referencedColumnName = "id")
    private BookDetails bookDetails;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "department_has_book", joinColumns = @JoinColumn(name = "book_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "department_id", referencedColumnName = "id"))
    private List<Department> departments;

    @OneToMany(mappedBy = "book", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<History> histories;

    @OneToMany(mappedBy = "book", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<BookRating> ratings;

}
