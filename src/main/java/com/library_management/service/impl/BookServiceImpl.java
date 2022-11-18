package com.library_management.service.impl;

import com.library_management.dto.request.book.CreateBookRequest;
import com.library_management.dto.request.book.UpdateBookRequest;
import com.library_management.entity.Book;
import com.library_management.entity.User;
import com.library_management.repository.BookRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookServiceImpl {
    private final UserServiceImpl userService;
    private final BookRepo bookRepo;

    public Book create(CreateBookRequest request) {
        User user = userService.getCurrentUser();

        Book book = Book.builder()
                .title(request.getTitle())
                .author(request.getAuthor())
                .description(request.getDescription())
                .type(request.getType())
                .pageAmount(request.getPageAmount())
                .dateRelease(request.getDateRelease())
                .imageUrl(request.getImageUrl())
                .isActive(true)
                .userId(user.getId())
                .createdAt(new Date())
                .updatedAt(new Date())
                .build();

        book = bookRepo.save(book);
        return book;
    }

    public Book update(Long id, UpdateBookRequest request) {
        Book existedBook = get(id);
        if (existedBook == null) {
            return null;
        }

        existedBook.setTitle(request.getTitle());
        existedBook.setAuthor(request.getAuthor());
        existedBook.setDescription(request.getDescription());
        existedBook.setType(request.getType());
        existedBook.setPageAmount(request.getPageAmount());
        existedBook.setDateRelease(request.getDateRelease());
        existedBook.setImageUrl(request.getImageUrl());
        existedBook.setUserId(null); // todo
        existedBook.setUpdatedAt(new Date());

        return bookRepo.save(existedBook);
    }

    public Book delete(Long id) {
        Book existedBook = get(id);
        if (existedBook == null) {
            return null;
        }

        existedBook.setIsActive(false);

        existedBook.setUpdatedAt(new Date());

        return bookRepo.save(existedBook);
    }

    public Book get(Long id) {
        return bookRepo.findFirstByIdAndIsActiveIsTrue(id);
    }

    public List<Book> list() {
        return bookRepo.findAllByIsActiveIsTrueOrderById();
    }

}
