package com.example.bookLibrary.service;

import com.example.bookLibrary.model.Book;
import com.example.bookLibrary.repository.BookRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;



@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepository bookRepository;

    public Page<Book> getAllBooks(Pageable pageable) {
             return   bookRepository.findAll(pageable);
    }
    public Book getBookById(Long id) {
        return bookRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    public Book createBook(Book book) {
        return bookRepository.save(book);
    }

    public Book updateBook(Long id, Book bookDetails) {
      Book existingBook = getBookById(id);
        if (bookDetails.getTitle() != null) {
            existingBook.setTitle(bookDetails.getTitle());
        }
        if (bookDetails.getGenre() != null) {
            existingBook.setGenre(bookDetails.getGenre());
        }
        if (bookDetails.getPublishedYear() != null) {
            existingBook.setPublishedYear(bookDetails.getPublishedYear());
        }

        return bookRepository.save(existingBook);
    }

    public boolean deleteBook(Long id) {
        if (bookRepository.existsById(id)) {
            bookRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
