package com.example.openapi.api;

import com.example.openapi.model.Book;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

public interface BookApi {
    @Operation(summary = "Get a Book by it's id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Book found for id", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Book.class))}),
            @ApiResponse(responseCode = "404", description = "Book not found for id", content = @Content)
    })
    @GetMapping("/book/id/{id}")
    ResponseEntity<Book> getBookById(@Parameter(description = "id for book to be searched") @PathVariable Long id);


    @Operation(summary = "Get a book by it's Title")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Book found by Title", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Book.class))}),
            @ApiResponse(responseCode = "404", description = "Book not found by title", content = @Content)
    })
    @GetMapping("/book/title/{title}")
    ResponseEntity<Book> getBookByTitle(@Parameter(description = "Title for book to be searched") @PathVariable String title);
}
