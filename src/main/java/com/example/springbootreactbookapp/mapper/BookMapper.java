package com.example.springbootreactbookapp.mapper;

import com.example.springbootreactbookapp.dto.BookDto;
import com.example.springbootreactbookapp.model.Book;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper(componentModel = "spring")
public interface BookMapper {
    Book dtoToModel(BookDto bookDto);

    BookDto modelToDto(Book book);

    List<BookDto> toListDto(List<Book> books);
}
