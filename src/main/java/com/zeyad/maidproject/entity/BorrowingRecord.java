package com.zeyad.maidproject.entity;

import jakarta.persistence.*;

import java.util.Date;

@Entity
public class BorrowingRecord {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @ManyToOne
        @JoinColumn(name = "book_id")
        private Book book;

        @ManyToOne
        @JoinColumn(name = "patron_id")
        private Patron patron;

        @Temporal(TemporalType.DATE)
        private Date borrowingDate;

        @Temporal(TemporalType.DATE)
        private Date returnDate;

        // Constructors
        public BorrowingRecord() {}

        public BorrowingRecord(Book book, Patron patron, Date borrowingDate) {
            this.book = book;
            this.patron = patron;
            this.borrowingDate = borrowingDate;
        }

        // Getters and Setters
        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public Book getBook() {
            return book;
        }

        public void setBook(Book book) {
            this.book = book;
        }

        public Patron getPatron() {
            return patron;
        }

        public void setPatron(Patron patron) {
            this.patron = patron;
        }

        public Date getBorrowingDate() {
            return borrowingDate;
        }

        public void setBorrowingDate(Date borrowingDate) {
            this.borrowingDate = borrowingDate;
        }

        public Date getReturnDate() {
            return returnDate;
        }

        public void setReturnDate(Date returnDate) {
            this.returnDate = returnDate;
        }
    }


