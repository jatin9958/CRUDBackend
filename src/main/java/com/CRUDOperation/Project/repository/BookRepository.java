package com.CRUDOperation.Project.repository;

import com.CRUDOperation.Project.entity.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<BookEntity, Integer> {

     List<BookEntity>findByName(String name);

     List<BookEntity>findByAuthor(String author);
}
