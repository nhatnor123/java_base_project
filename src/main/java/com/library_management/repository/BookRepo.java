package com.library_management.repository;

import com.library_management.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepo extends JpaRepository<Book, Long> {
    Book findFirstByIdAndIsActiveIsTrue(Long id);

    List<Book> findAllByIsActiveIsTrueOrderById();
}
