package com.library_management.controller;

import com.library_management.dto.ResponseObject;
import com.library_management.dto.request.book.CreateBookRequest;
import com.library_management.dto.request.book.UpdateBookRequest;
import com.library_management.entity.Book;
import com.library_management.service.impl.BookServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(path = "/api/book")
@RequiredArgsConstructor
public class BookController {
    private final BookServiceImpl bookService;

    @PostMapping(value = "")
    public ResponseEntity<?> create(@RequestBody CreateBookRequest request) {
        Book book = bookService.create(request);
        return ResponseEntity.ok(new ResponseObject<>(true, "SUCCESS", "Thêm sách thành công", book));
    }

    @PostMapping(value = "/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody UpdateBookRequest request) {
        Book book = bookService.update(id, request);
        if (book == null) {
            return ResponseEntity.ok(new ResponseObject<>(false, "NOT_FOUND", "Không tìm thấy sách"));
        } else {
            return ResponseEntity.ok(new ResponseObject<>(true, "SUCCESS", "Cập nhật sách thành công"));
        }
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        Book book = bookService.delete(id);
        if (book == null) {
            return ResponseEntity.ok(new ResponseObject<>(false, "NOT_FOUND", "Không tìm thấy sách"));
        } else {
            return ResponseEntity.ok(new ResponseObject<>(true, "SUCCESS", "Xóa sách thành công"));
        }
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> get(@PathVariable Long id) {
        Book book = bookService.get(id);
        if (book == null) {
            return ResponseEntity.ok(new ResponseObject<>(false, "NOT_FOUND", "Không tìm thấy sách"));
        } else {
            return ResponseEntity.ok(new ResponseObject<>(true, "SUCCESS", "Lấy thông tin sách thành công", book));
        }
    }

    @GetMapping(value = "")
    public ResponseEntity<?> list() {
        List<Book> bookList = bookService.list();
        return ResponseEntity.ok(new ResponseObject<>(true, "SUCCESS", "Lấy danh sách sách thành công", bookList));
    }


}
