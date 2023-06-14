package com.orgabor.bookkeepa.data.repository;

import com.orgabor.bookkeepa.data.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
}
