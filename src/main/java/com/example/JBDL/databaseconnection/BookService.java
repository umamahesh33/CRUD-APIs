package com.example.JBDL.databaseconnection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class BookService {

    @Autowired
    BookRepository bookRepository;

    public List<Book> getAllBooks() throws SQLException {
        return bookRepository.getBooks();
    }

    public void createBook(Book book) throws SQLException {
        bookRepository.createBook(book);
    }
    public Book GetBookById(int id) throws SQLException {
        return bookRepository.getBookById(id);
    }
    public void UpdateBook(int id,Book book) throws SQLException {
        bookRepository.updateBook(id,book);
    }
    public void deleteBookById(int id) throws SQLException {
        bookRepository.deleteBook(id);
    }
}
