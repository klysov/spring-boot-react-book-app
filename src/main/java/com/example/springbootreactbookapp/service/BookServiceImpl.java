package com.example.springbootreactbookapp.service;

import com.example.springbootreactbookapp.dto.BookDto;
import com.example.springbootreactbookapp.mapper.BookMapper;
import com.example.springbootreactbookapp.model.Book;
import com.example.springbootreactbookapp.repository.BookRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final BookMapper bookMapper;

    @Override
    public List<BookDto> findAll() {
        return bookMapper.toListDto(bookRepository.findAll());
    }

    @Override
    public BookDto findById(Long id) {
        return Optional.of(getById(id)).map(bookMapper::modelToDto).get();
    }

    @Override
    @Transactional
    public BookDto save(BookDto bookDto) {
        return bookMapper.modelToDto(
                bookRepository.save(bookMapper.dtoToModel(bookDto))
        );
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        bookRepository.delete(getById(id));
    }

    private Book getById(Long id) {
        return bookRepository.findById(id).orElseThrow(
                () -> new RuntimeException(
                        "Book with id: " + id + " not found")
        );
    }
}
