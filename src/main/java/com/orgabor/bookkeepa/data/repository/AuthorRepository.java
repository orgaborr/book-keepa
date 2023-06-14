package com.orgabor.bookkeepa.data.repository;

import com.orgabor.bookkeepa.data.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Long> {
}
