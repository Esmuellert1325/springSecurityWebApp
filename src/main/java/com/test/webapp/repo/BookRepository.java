package com.test.webapp.repo;

import com.test.webapp.entity.Book;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BookRepository extends CrudRepository<Book, Long> {

    @Query(value = "select * from books", nativeQuery = true)
    List<Book> findAllBooks();
}
