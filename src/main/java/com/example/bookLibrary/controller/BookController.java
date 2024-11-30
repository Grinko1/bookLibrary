package com.example.bookLibrary.controller;

import com.example.bookLibrary.model.Book;
import com.example.bookLibrary.service.BookService;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping("/api/books")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @GetMapping
    @JsonView(Book.BookSummary.class)
    public ResponseEntity<Map<String, Object>> getAllBooks(@RequestParam(defaultValue = "0",required = false) int page,
                                                                     @RequestParam(defaultValue = "10",required = false) int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Book> booksPage = bookService.getAllBooks(pageable);

        Map<String, Object> response = new HashMap<>();
        response.put("content", booksPage.getContent());
        response.put("totalPages", booksPage.getTotalPages());
        response.put("totalElements", booksPage.getTotalElements());
        response.put("currentPage", booksPage.getNumber());
        response.put("pageSize", booksPage.getSize());

        return ResponseEntity.ok(response);
    }


    @GetMapping("/{id}")
    @JsonView(Book.BookDetails.class)
    public ResponseEntity<Book> getBookById(@PathVariable Long id) {
        return new ResponseEntity<>(bookService.getBookById(id), HttpStatus.OK);
    }


    @PostMapping
    @JsonView(Book.BookSummary.class)
    public ResponseEntity<Book> createBook(@RequestBody Book book) {
        Book createdBook = bookService.createBook(book);
        return new ResponseEntity<>(createdBook, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @JsonView(Book.BookSummary.class)
    public ResponseEntity<Book> updateBook(@PathVariable Long id, @RequestBody Book book) {
        Book updatedBook = bookService.updateBook(id, book);
        return  new ResponseEntity<>(updatedBook, HttpStatus.OK);

    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        return bookService.deleteBook(id) ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // why doesnt work?
//    @GetMapping
//    @JsonView(Book.BookSummary.class)
//    public ResponseEntity<Page<Book>> getAllBooks(@RequestParam(defaultValue = "0", required = false) int page,
//                                                  @RequestParam(defaultValue = "10", required = false) int size) {
//        Pageable pageable = PageRequest.of(page, size);
//        Page<Book> books = bookService.getAllBooks(pageable);
//        return ResponseEntity.ok(books);
//    }
}