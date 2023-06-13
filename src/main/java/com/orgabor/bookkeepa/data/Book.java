package com.orgabor.bookkeepa.data;

import lombok.*;

@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Book {
    private Long bookId;
    private String title;
    private Genre genre;
    private Author author;
}
