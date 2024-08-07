package com.zeyad.maidproject.DAO;

import com.zeyad.maidproject.entity.Book;
import com.zeyad.maidproject.entity.BorrowingRecord;
import com.zeyad.maidproject.entity.Patron;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface BorrowingRecordRepository extends JpaRepository<BorrowingRecord, Long> {
    Optional<BorrowingRecord> findByBookAndPatronAndReturnDateIsNull(Book book, Patron patron);
}