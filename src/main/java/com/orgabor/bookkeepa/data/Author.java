package com.orgabor.bookkeepa.data;

import lombok.*;

import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
@Data
public class Author {
    private Long authorId;
    private String name;
    private Set<Book> publications = new HashSet<>();
}
