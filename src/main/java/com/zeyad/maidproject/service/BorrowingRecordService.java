package com.zeyad.maidproject.service;

import com.zeyad.maidproject.DAO.BookRepository;
import com.zeyad.maidproject.DAO.BorrowingRecordRepository;
import com.zeyad.maidproject.DAO.PatronRepository;
import com.zeyad.maidproject.entity.Book;
import com.zeyad.maidproject.entity.BorrowingRecord;
import com.zeyad.maidproject.entity.Patron;
import com.zeyad.maidproject.exception.BookBorrowedException;
import com.zeyad.maidproject.exception.BookNotFoundException;
import com.zeyad.maidproject.exception.BorrowingRecordNotFoundException;
import com.zeyad.maidproject.exception.PatronNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class BorrowingRecordService {

    private BorrowingRecordRepository borrowingRecordRepository;

    @Autowired
    public BorrowingRecordService(BorrowingRecordRepository borrowingRecordRepository) {
        this.borrowingRecordRepository = borrowingRecordRepository;
    }

    private BookRepository bookRepository;

    @Autowired
    public void setBookRepository(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }


    private PatronRepository patronRepository;

    @Autowired
    public void setPatronRepository(PatronRepository patronRepository) {
        this.patronRepository = patronRepository;
    }

    public BorrowingRecord borrowBook(Integer bookId, Long patronId) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new BookNotFoundException("Book with ID " + bookId + " not found"));
        Patron patron = patronRepository.findById(patronId)
                .orElseThrow(() -> new PatronNotFoundException("Patron with ID " + patronId + " not found"));

        if (book.getBorrowed()) {
            throw new BookBorrowedException("Book is already borrowed");
        }

        BorrowingRecord borrowingRecord = new BorrowingRecord(book, patron, new Date());
        book.setBorrowed(true);
        bookRepository.save(book);
        return borrowingRecordRepository.save(borrowingRecord);
    }

    public BorrowingRecord returnBook(Integer bookId, Long patronId) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new BookNotFoundException("Book with ID " + bookId + " not found"));
        Patron patron = patronRepository.findById(patronId)
                .orElseThrow(() -> new PatronNotFoundException("Patron with ID " + patronId + " not found"));

        BorrowingRecord borrowingRecord = borrowingRecordRepository.findByBookAndPatronAndReturnDateIsNull(book, patron)
                .orElseThrow(() -> new BorrowingRecordNotFoundException("Borrowing record not found"));

        borrowingRecord.setReturnDate(new Date());
        book.setBorrowed(false);
        bookRepository.save(book);
        return borrowingRecordRepository.save(borrowingRecord);
    }
}
