package com.orgabor.bookkeepa.controllers;

import com.orgabor.bookkeepa.data.Book;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@RestController
@AllArgsConstructor
public class BookController {

    private static final Map<Long, Book> books = new HashMap<>();
    private static final String NOT_FOUND_MESSAGE = "Book not found with id %s!";
    private static Long bookId = 0L;

    @GetMapping("/books/all")
    public ResponseEntity<Collection<Book>> getAll() {
        return new ResponseEntity<>(books.values(), HttpStatus.OK);
    }

    @GetMapping("/books/{id}")
    public ResponseEntity<Book> getBook(@PathVariable Long id) {
        Book book = books.get(id);
        if (book == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format(NOT_FOUND_MESSAGE, id));
        } else {
            return new ResponseEntity<>(book, HttpStatus.OK);
        }
    }

    @PostMapping("/books/add")
    public ResponseEntity<Book> addBook(@RequestBody Book newBook) {
        bookId++;
        newBook.setBookId(bookId);
        books.put(newBook.getBookId(), newBook);
        return new ResponseEntity<>(newBook, HttpStatus.CREATED);
    }

    @PutMapping("/books/{id}/edit")
    public ResponseEntity<Book> editBook(@PathVariable Long id, @RequestBody Book book) {
        if (books.get(id) == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format(NOT_FOUND_MESSAGE, id));
        }
        books.put(id, book);
        return new ResponseEntity<>(book, HttpStatus.OK);
    }

    @DeleteMapping("/books/{id}/delete")
    public ResponseEntity<Book> deleteBook(@PathVariable Long id) {
        if (books.get(id) == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format(NOT_FOUND_MESSAGE, id));
        } else {
            books.remove(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }
}
