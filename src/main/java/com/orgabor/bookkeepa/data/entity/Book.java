package com.orgabor.bookkeepa.data.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.orgabor.bookkeepa.data.enumeration.Genre;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
@Entity
@Table(name = "BOOKS")
public class Book implements Serializable {
    @Serial
    private static final long serialVersionUID = -4964703387565746372L;

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "BOOK_ID", nullable = false)
    private Long bookId;

    @Column(name = "TITLE", nullable = false, length = 100)
    private String title;

    @Enumerated(EnumType.STRING)
    @Column(name = "GENRE")
    private Genre genre;

    @ManyToMany
    @JoinTable(name = "books_authors",
            joinColumns = {@JoinColumn(name = "book_id", referencedColumnName = "book_id")},
            inverseJoinColumns = {@JoinColumn(name = "author_id", referencedColumnName = "author_id")})
    @EqualsAndHashCode.Exclude
    @JsonIgnoreProperties({"books"})
    private Set<Author> authors = new HashSet<>();
}
