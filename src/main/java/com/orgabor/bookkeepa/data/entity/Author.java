package com.orgabor.bookkeepa.data.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
@Table(name = "AUTHORS")
public class Author implements Serializable {
    @Serial
    private static final long serialVersionUID = -4452017086726936070L;

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "AUTHOR_ID", nullable = false)
    private Long authorId;

    @Column(name = "NAME", length = 100, nullable = false)
    private String name;

    @ManyToMany(mappedBy = "authors")
    @EqualsAndHashCode.Exclude
    @JsonIgnoreProperties({"authors"})
    private Set<Book> books = new HashSet<>();
}
