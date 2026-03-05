package com.example.BookStoreRest.controller;

import com.example.BookStoreRest.dto.BookDto;
import com.example.BookStoreRest.dto.PageResponse;
import com.example.BookStoreRest.service.BookService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/books")
public class BookController {

    @Autowired
    private BookService bookService;

    // CREATE BOOK
    @PostMapping("/addBook")
    @Operation(summary = "Create Book", description = "Creates a new book")
    @ApiResponse(responseCode = "201", description = "Book created successfully")
    public ResponseEntity<BookDto> createBook(@Valid @RequestBody BookDto bookDto){
        BookDto createBook = bookService.createBook(bookDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createBook);
    }

    // GET BOOK BY ID
    @GetMapping("/getBook/{id}")
    @Operation(summary = "Get Book By ID", description = "Fetches a book by its ID")
    @ApiResponse(responseCode = "200", description = "Book retrieved successfully")
    public ResponseEntity<BookDto> getBookById(@PathVariable Long id){
        BookDto bookDto = bookService.getBookById(id);
        return ResponseEntity.ok(bookDto);
    }

    // UPDATE BOOK
    @PutMapping("/updateBook/{id}")
    @Operation(summary = "Update Book", description = "Updates an existing book by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Book updated successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input data"),
            @ApiResponse(responseCode = "404", description = "Book not found")
    })
    public ResponseEntity<BookDto> updateBook(@PathVariable Long id,
                                              @Valid @RequestBody BookDto bookDto){
        BookDto updateBook = bookService.updateBookById(id, bookDto);
        return ResponseEntity.ok(updateBook);
    }

    // DELETE BOOK
    @DeleteMapping("/deleteBook/{id}")
    @Operation(summary = "Delete Book", description = "Deletes a book by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Book deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Book not found")
    })
    public ResponseEntity<BookDto> deleteBook(@PathVariable Long id){
        BookDto deletedBook = bookService.deleteById(id);
        return ResponseEntity.ok(deletedBook);
    }

    // GET ALL BOOKS
    @GetMapping("/getAllBooks")
    @Operation(summary = "Get All Books", description = "Retrieves all books")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Books retrieved successfully")
    })
    public ResponseEntity<List<BookDto>> getAllBooks(){
        return ResponseEntity.ok(bookService.getAllBooks());
    }

    // PAGINATION + SORTING
    @GetMapping("/page")
    @Operation(summary = "Get Books With Pagination",
            description = "Retrieves books with pagination and sorting")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Books retrieved successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid pagination parameters")
    })
    public ResponseEntity<PageResponse<BookDto>> getBooks(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String direction
    ){
        PageResponse<BookDto> books =
                bookService.getBooks(page, size, sortBy, direction);
        return ResponseEntity.ok(books);
    }
}