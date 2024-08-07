package com.zeyad.maidproject.DAO;

import com.zeyad.maidproject.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Integer> {


}
