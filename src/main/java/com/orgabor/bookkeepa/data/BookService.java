package com.orgabor.bookkeepa.data;

import java.util.List;

public interface BookService {

    Book findById(Long id);

    List<Book> findAll();

    Book save(Book book);

    void delete(Book book);
}
