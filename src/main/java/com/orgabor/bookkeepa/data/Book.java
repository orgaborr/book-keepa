package com.orgabor.bookkeepa.data;

import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
@Entity
@Table(name = "BOOKS")
public class Book {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "BOOK_ID", nullable = false)
    private Long bookId;
    @Column(name = "TITLE", nullable = false)
    private String title;
    @Enumerated(EnumType.STRING)
    private Genre genre;

//    private Author author;
}
