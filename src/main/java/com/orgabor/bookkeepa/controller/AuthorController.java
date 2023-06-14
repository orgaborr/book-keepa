package com.orgabor.bookkeepa.controller;

import com.orgabor.bookkeepa.data.entity.Author;
import com.orgabor.bookkeepa.data.service.AuthorService;
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
public class AuthorController {
    private static final String NOT_FOUND_MESSAGE = "Author not found with id %s!";
    private AuthorService authorService;

    @GetMapping("/authors/all")
    public ResponseEntity<Collection<Author>> getAll() {
        try {
            return new ResponseEntity<>(authorService.findAll(), HttpStatus.OK);
        } catch (DataAccessException e) {
            throw new ResponseStatusException(HttpStatus.FAILED_DEPENDENCY, e.getMessage());
        }
    }

    @GetMapping("/authors/{id}")
    public ResponseEntity<Author> getAuthor(@PathVariable Long id) {
        try {
            Author author = authorService.findById(id);
            if (author == null) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format(NOT_FOUND_MESSAGE, id));
            }
            return new ResponseEntity<>(author, HttpStatus.OK);
        } catch (DataAccessException e) {
            throw new ResponseStatusException(HttpStatus.FAILED_DEPENDENCY, e.getMessage());
        }
    }

    @PostMapping("/authors/add")
    public ResponseEntity<Author> addAuthor(@RequestBody Author newAuthor) {
        try {
            if (newAuthor.getAuthorId() != null) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "New author can not have a predefined ID!");
            }
            if (StringUtils.isBlank(newAuthor.getName())) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "New author has no name!");
            }
            newAuthor = authorService.save(newAuthor);
            return new ResponseEntity<>(newAuthor, HttpStatus.CREATED);
        } catch (DataAccessException e) {
            throw new ResponseStatusException(HttpStatus.FAILED_DEPENDENCY, e.getMessage());
        }
    }

    @PutMapping("/authors/{id}/edit")
    public ResponseEntity<Author> editAuthor(@PathVariable Long id, @RequestBody Author author) {
        try {
            Author existingAuthor = authorService.findById(id);
            if (existingAuthor == null) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format(NOT_FOUND_MESSAGE, id));
            }
            if (!existingAuthor.getAuthorId().equals(author.getAuthorId())) {
                throw new ResponseStatusException(HttpStatus.CONFLICT, "Author ID can not be modified!");
            }
            if (StringUtils.isBlank(author.getName())) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Author has no title!");
            }
            author = authorService.save(author);
            return new ResponseEntity<>(author, HttpStatus.OK);
        } catch (DataAccessException e) {
            throw new ResponseStatusException(HttpStatus.FAILED_DEPENDENCY, e.getMessage());
        }
    }

    @DeleteMapping("/authors/{id}/delete")
    public ResponseEntity<Author> deleteAuthor(@PathVariable Long id) {
        try {
            Author author = authorService.findById(id);
            if (author == null) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format(NOT_FOUND_MESSAGE, id));
            }
            authorService.delete(author);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (DataAccessException e) {
            throw new ResponseStatusException(HttpStatus.FAILED_DEPENDENCY, e.getMessage());
        }
    }
}
