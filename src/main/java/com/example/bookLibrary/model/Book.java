package com.example.bookLibrary.model;

import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Book {
    public interface BookSummary {}
    public interface BookDetails extends BookSummary {}
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(BookSummary.class)
    private Long id;
    @JsonView(BookSummary.class)
    @NotBlank(message = "Название должно быть заполнено!")
    private String title;
    @JsonView(BookSummary.class)
    @NotBlank(message = "Жанр должен быть заполнен!")
    private String genre;
    @JsonView(BookSummary.class)
    @NotNull(message = "Год публикации должен быть указан!")
    @Max(2024)
    private Integer publishedYear;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id")
    @JsonView(BookDetails.class)
    private Author author;
}
