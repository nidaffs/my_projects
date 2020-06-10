package com.nidaff.entity.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import java.util.List;

@Entity
@Table(name = "book_details")
@Getter
@Setter
@NoArgsConstructor
public class BookDetails extends AEntity {

    @Column(name = "isbn", length = 13)
    private String isbn;

    @Column(name = "title")
    private String title;

    @Column(name = "author")
    private String author;

    @Column(name = "description")
    private String description;

    @Column(name = "image")
    private String image;

    @OneToMany(mappedBy = "bookDetails", fetch = FetchType.LAZY)
    private List<Book> books;

}
