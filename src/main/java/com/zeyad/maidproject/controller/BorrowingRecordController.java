package com.zeyad.maidproject.controller;

import com.zeyad.maidproject.entity.BorrowingRecord;
import com.zeyad.maidproject.service.BorrowingRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/borrowing-records")
public class BorrowingRecordController {

    private BorrowingRecordService borrowingRecordService;
    @Autowired
    public BorrowingRecordController(BorrowingRecordService borrowingRecordService) {
    this.borrowingRecordService = borrowingRecordService;
}
    @PostMapping("/borrow/{bookId}/patron/{patronId}")
    public ResponseEntity<BorrowingRecord> borrowBook(@PathVariable Integer bookId, @PathVariable Long patronId) {
        BorrowingRecord borrowingRecord = borrowingRecordService.borrowBook(bookId, patronId);
        return ResponseEntity.ok(borrowingRecord);
    }

    @PutMapping("/return/{bookId}/patron/{patronId}")
    public ResponseEntity<BorrowingRecord> returnBook(@PathVariable Integer bookId, @PathVariable Long patronId) {
        BorrowingRecord borrowingRecord = borrowingRecordService.returnBook(bookId, patronId);
        return ResponseEntity.ok(borrowingRecord);
    }

}
