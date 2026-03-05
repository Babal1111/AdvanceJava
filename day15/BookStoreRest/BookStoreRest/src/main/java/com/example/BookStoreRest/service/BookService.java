package com.example.BookStoreRest.service;

import com.example.BookStoreRest.dto.BookDto;
import com.example.BookStoreRest.dto.PageResponse;
import com.example.BookStoreRest.entity.Book;
import com.example.BookStoreRest.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.SortedMap;


@RequiredArgsConstructor
@Service
public class BookService {
    private final BookRepository bookRepository;
    private final ModelMapper modelMapper;

    @CacheEvict(value = "books",allEntries = true)
    public BookDto createBook(BookDto bookDto){
        Book book = modelMapper.map(bookDto, Book.class);//dto to entity
        Book savedBook = bookRepository.save(book);// save book data
        return modelMapper.map(savedBook,BookDto.class);//entity to dto
    }

    public BookDto updateBookById(Long id,BookDto bookDto){
        Book existingBook = bookRepository.findById(id)
                .orElseThrow(()->new RuntimeException("Book id not found"));
        existingBook.setTitle(bookDto.getTitle());
        existingBook.setAuthor(bookDto.getAuthor());
        existingBook.setPrice(bookDto.getPrice());

        Book updatedBook = bookRepository.save(existingBook);
        return modelMapper.map(updatedBook,BookDto.class);

    }

    public BookDto getBookById(Long id){
        Book book = bookRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Book not found"));
        return modelMapper.map(book,BookDto.class);

    }

    public BookDto deleteById(Long id){
        Optional<Book> book = bookRepository.findById(id);
        bookRepository.deleteById(id);
        return modelMapper.map(book,BookDto.class);
    }

    public List<BookDto> getAllBooks(){
        List<Book> bookList = bookRepository.findAll();

        return bookList.stream().map(book-> modelMapper.map(book,BookDto.class))
                .toList();
    }

    @Cacheable(value = "books", key="#page +'_'+ #size + '_'+ #sortBy+'_'+ #direction")
    public PageResponse<BookDto> getBooks(int page,int size,String sortBy,String direction){

        System.out.println("DB CALL HAPPENING!!!");
        Sort sort = direction.equalsIgnoreCase("desc")
                ?Sort.by(sortBy).descending()
                :Sort.by(sortBy).ascending();
        Pageable pageable = PageRequest.of(page,size,sort);

        Page<Book> bookPage = bookRepository.findAll(pageable);
        List<BookDto>  dtoList = bookPage.getContent()
                .stream()
                .map(book -> modelMapper.map(book,BookDto.class))
                .toList();

        return new PageResponse<>(
                dtoList,
                bookPage.getNumber(),
                bookPage.getSize(),
                bookPage.getTotalElements(),
                bookPage.getTotalPages()
        );

    }




}
