package com.orgabor.bookkeepa.data.service;

import com.orgabor.bookkeepa.data.entity.Book;

import java.util.List;

public interface BookService {

    Book findById(Long id);

    List<Book> findAll();

    Book save(Book book);

    void delete(Book book);
}
