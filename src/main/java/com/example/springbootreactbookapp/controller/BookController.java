package com.example.springbootreactbookapp.controller;

import com.example.springbootreactbookapp.dto.BookDto;
import com.example.springbootreactbookapp.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("api/v1")
@RequiredArgsConstructor
public class BookController {
    private final BookService bookService;

    @GetMapping("/books")
    public List<BookDto> allBooks() {
        return bookService.findAll();
    }

    @GetMapping("/books/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<BookDto> getBook(@PathVariable Long id) {
        return ResponseEntity.ok().body(bookService.findById(id));
    }

    @PostMapping("/books")
    public ResponseEntity<BookDto> createBook(@RequestBody BookDto bookDto) throws URISyntaxException {
        BookDto result = bookService.save(bookDto);
        return ResponseEntity.created(new URI("/api/v1/books/" + result.id()))
                .body(result);
    }

    @PutMapping("/books/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<BookDto> updateBook(@PathVariable Long id, @RequestBody BookDto book) {
        return ResponseEntity.ok().body(bookService.save(book));
    }

    @DeleteMapping("/books/{id}")
    public ResponseEntity<?> deleteBook(@PathVariable Long id) {
        bookService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
