package com.example.BookStoreRest.controller;

import com.example.BookStoreRest.dto.BookDto;
import com.example.BookStoreRest.dto.PageResponse;
import com.example.BookStoreRest.entity.Book;
import com.example.BookStoreRest.service.BookService;
import jakarta.validation.Valid;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("api/books")
public class BookController {

    @Autowired
    private BookService bookService;

//    public BookController(BookService bookService) {
//        this.bookService = bookService;
//    }

    //    @PostMapping
//    public Book createBook(){
//        Book book = new Book();
//        book.setId(1L);
//        book.setAuthor("babal");
//        book.setPrice(20.0);
//        book.setTitle("bio");
//        return book;
//    }
    @PostMapping("/addBook")
    public ResponseEntity<BookDto> createBook(@Valid @RequestBody BookDto bookDto){

        BookDto createBook = bookService.createBook(bookDto);
        return ResponseEntity.ok(createBook);


    }
    // GET Book by id
    @GetMapping("/getBook/{id}")
    public ResponseEntity<BookDto> getBookById(@PathVariable Long id){
        BookDto bookDto = bookService.getBookById(id);
        return  ResponseEntity.ok(bookDto);
    }

    //update book by id
    @PutMapping("/updateBook/{id}")
    public ResponseEntity<BookDto> updateBook(@PathVariable Long id,@Valid @RequestBody BookDto bookDto){
        BookDto updateBook = bookService.updateBookById(id,bookDto);
        return ResponseEntity.ok(updateBook);
    }

    @DeleteMapping("/deleteBook/{id}")
    public ResponseEntity<BookDto> deleteBook(@PathVariable Long id){
        BookDto deletedBook = bookService.deleteById(id);
        return ResponseEntity.ok(deletedBook);
    }

    @GetMapping("/getAllBooks")
    public List<BookDto> getAllBooks(){
        return bookService.getAllBooks();
    }

    @GetMapping("/page")

    public ResponseEntity<PageResponse<BookDto>> getBooks(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String direction
    ){
        PageResponse<BookDto> books = bookService.getBooks(page,size,sortBy,direction);
        return ResponseEntity.ok(books);
    }


}
