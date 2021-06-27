package com.example.JBDL.databaseconnection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.*;

@RestController
public class BookController{

    @Autowired
    BookService bookService;

    @GetMapping("/books")
    @ResponseBody
    public List<Book> getBooks() throws SQLException {
       return bookService.getAllBooks();
    }

    @GetMapping("/books/{id}")
    @ResponseBody
    public Book getBook(@PathVariable("id") int id) throws SQLException {
        return bookService.GetBookById(id);
    }

    @PostMapping("/book")
    public void createBook(@RequestBody Book book) throws SQLException {
        bookService.createBook(book);
    }

    @PutMapping("/book/{id}")
    public void updateBook(@PathVariable("id") int id,@RequestBody Book book) throws SQLException {
        bookService.UpdateBook(id,book);
    }

    @DeleteMapping("/book/delete")
    public void deleteBook(@RequestParam int id) throws SQLException {
        bookService.deleteBookById(id);
    }
}
