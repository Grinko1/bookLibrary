package com.example.bookLibrary.controller;

import com.example.bookLibrary.model.Author;
import com.example.bookLibrary.model.Book;
import com.example.bookLibrary.service.BookService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class BookControllerTest {

    @Mock
    private BookService bookService;

    @InjectMocks
    private BookController bookController;

    private MockMvc mockMvc;
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(bookController).build();
        objectMapper = new ObjectMapper();
    }

    @Test
    void getAllBooks_ShouldReturnBooks() throws Exception {
        Book book1 = new Book(1L, "Book 1", "Genre 1", 2020, null);
        Book book2 = new Book(2L, "Book 2", "Genre 2", 2021, null);

        Pageable pageable = PageRequest.of(0, 10);

        Page<Book> page = new PageImpl<>(List.of(book1, book2), pageable, 2);

        when(bookService.getAllBooks(pageable)).thenReturn(page);

        mockMvc.perform(get("/api/books")
                        .param("page", "0")
                        .param("size", "10")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content.length()").value(2))
                .andExpect(jsonPath("$.content[0].id").value(1))
                .andExpect(jsonPath("$.content[0].title").value("Book 1"))
                .andExpect(jsonPath("$.content[1].id").value(2))
                .andExpect(jsonPath("$.content[1].title").value("Book 2"))
                .andExpect(jsonPath("$.content[0].author").doesNotExist())
                .andExpect(jsonPath("$.content[0].publishedYear").value(2020));
    }


    @Test
    void getBookById_ShouldReturnBookDetails() throws Exception {
        Author author = new Author(1L, "Author 1");
        Book book = new Book(1L, "Book 1", "Genre 1", 2020, author);
        when(bookService.getBookById(1L)).thenReturn(book);

        mockMvc.perform(get("/api/books/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.title").value("Book 1"))
                .andExpect(jsonPath("$.genre").value("Genre 1"))
                .andExpect(jsonPath("$.publishedYear").value(2020))
                .andExpect(jsonPath("$.author.name").value("Author 1"));
    }

    @Test
    void createBook_ShouldReturnCreatedBook() throws Exception {
        Book book = new Book(null, "New Book", "New Genre", 2024, null);
        Book createdBook = new Book(1L, "New Book", "New Genre", 2024, null);
        when(bookService.createBook(any(Book.class))).thenReturn(createdBook);


        mockMvc.perform(post("/api/books")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(book)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.title").value("New Book"))
                .andExpect(jsonPath("$.publishedYear").value(2024));
    }

    @Test
    void updateBook_ShouldReturnUpdatedBook() throws Exception {

        Book book = new Book(1L, "Updated Book", "Updated Genre", 2025, null);
        when(bookService.updateBook(eq(1L), any(Book.class))).thenReturn(book);

        mockMvc.perform(put("/api/books/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(book)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.title").value("Updated Book"))
                .andExpect(jsonPath("$.publishedYear").value(2025));
    }

    @Test
    void deleteBook_ShouldReturnStatusOk() throws Exception {
        Long bookId = 1L;
        doNothing().when(bookService).deleteBook(bookId);
        mockMvc.perform(delete("/api/books/{id}", bookId))
                .andExpect(status().isOk());

        verify(bookService, times(1)).deleteBook(bookId);
    }
}