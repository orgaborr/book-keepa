package com.orgabor.bookkeepa.controller;

import com.orgabor.bookkeepa.data.entity.Book;
import com.orgabor.bookkeepa.data.service.BookService;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collection;

@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class BookController {

    private static final String NOT_FOUND_MESSAGE = "Book not found with id %s!";
    private BookService bookService;

    @GetMapping("/books/all")
    public ResponseEntity<Collection<Book>> getAll() {
        try {
            return new ResponseEntity<>(bookService.findAll(), HttpStatus.OK);
        } catch (DataAccessException e) {
            throw new ResponseStatusException(HttpStatus.FAILED_DEPENDENCY, e.getMessage());
        }
    }

    @GetMapping("/books/{id}")
    public ResponseEntity<Book> getBook(@PathVariable Long id) {
        try {
            Book book = bookService.findById(id);
            if (book == null) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format(NOT_FOUND_MESSAGE, id));
            }
            return new ResponseEntity<>(book, HttpStatus.OK);
        } catch (DataAccessException e) {
            throw new ResponseStatusException(HttpStatus.FAILED_DEPENDENCY, e.getMessage());
        }
    }

    @PostMapping("/books/add")
    public ResponseEntity<Book> addBook(@RequestBody Book newBook) {
        try {
            if (newBook.getBookId() != null) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "New book can not have a predefined ID!");
            }
            if (StringUtils.isBlank(newBook.getTitle())) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "New book has no title!");
            }
            newBook = bookService.save(newBook);
            return new ResponseEntity<>(newBook, HttpStatus.CREATED);
        } catch (DataAccessException e) {
            throw new ResponseStatusException(HttpStatus.FAILED_DEPENDENCY, e.getMessage());
        }
    }

    @PutMapping("/books/{id}/edit")
    public ResponseEntity<Book> editBook(@PathVariable Long id, @RequestBody Book book) {
        try {
            Book existingBook = bookService.findById(id);
            if (existingBook == null) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format(NOT_FOUND_MESSAGE, id));
            }
            if (!existingBook.getBookId().equals(book.getBookId())) {
                throw new ResponseStatusException(HttpStatus.CONFLICT, "Book ID can not be modified!");
            }
            if (StringUtils.isBlank(book.getTitle())) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Book has no title!");
            }
            book = bookService.save(book);
            return new ResponseEntity<>(book, HttpStatus.OK);
        } catch (DataAccessException e) {
            throw new ResponseStatusException(HttpStatus.FAILED_DEPENDENCY, e.getMessage());
        }
    }

    @DeleteMapping("/books/{id}/delete")
    public ResponseEntity<Book> deleteBook(@PathVariable Long id) {
        try {
            Book book = bookService.findById(id);
            if (book == null) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format(NOT_FOUND_MESSAGE, id));
            }
            bookService.delete(book);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (DataAccessException e) {
            throw new ResponseStatusException(HttpStatus.FAILED_DEPENDENCY, e.getMessage());
        }
    }
}
