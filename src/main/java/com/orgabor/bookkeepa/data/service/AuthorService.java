package com.orgabor.bookkeepa.data.service;

import com.orgabor.bookkeepa.data.entity.Author;
import com.orgabor.bookkeepa.data.entity.Book;

import java.util.List;

public interface AuthorService {
    Author findById(Long id);

    List<Author> findAll();

    Author save(Author author);

    void delete(Author author);
}
