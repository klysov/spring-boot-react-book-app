package com.example.springbootreactbookapp.service;


import com.example.springbootreactbookapp.dto.BookDto;

import java.util.List;

public interface BookService {
    List<BookDto> findAll();

    BookDto findById(Long id);

    BookDto save(BookDto bookDto);

    void deleteById(Long id);
}
