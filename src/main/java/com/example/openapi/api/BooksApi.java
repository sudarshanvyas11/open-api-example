package com.example.openapi.api;

import com.example.openapi.model.Author;
import com.example.openapi.model.Book;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface BooksApi {

    @Operation(description = "List all books available")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List all books available in the repository", content = {@Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Book.class)))}),
            @ApiResponse(responseCode = "404", description = "No books found in the repository", content = {@Content})

    })
    @GetMapping("/books")
    ResponseEntity<List<Book>> listBooks();

    @Operation(description = "List all books for an Author")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List all books for the author", content = {@Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Book.class)))}),
            @ApiResponse(responseCode = "404", description = "No books found for the author", content = {@Content})
    })
    @PostMapping("/books/author/")
    ResponseEntity<List<Book>> listBooksByAuthor(@RequestBody Author author);

    @Operation(description = "List all books for a Genre")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List all books for the genre", content = {@Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Book.class)))}),
            @ApiResponse(responseCode = "404", description = "No books found for the genre", content = {@Content})
    })
    @GetMapping("/books/genre/{genre}")
    ResponseEntity<List<Book>> listBooksByGenre(@Parameter(description = "genre for books to be listed") @PathVariable String genre);
}
