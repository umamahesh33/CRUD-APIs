package com.example.JBDL.databaseconnection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.xml.transform.Result;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.Executor;

@Repository
public class BookRepository{

    private static Connection connection=null;

    public BookRepository() throws SQLException {
        if(connection==null)
        {
            connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/java_course","root","Umamahesh@123");
        }
        createTable();
    }

    private void createTable() throws SQLException {
        Statement statement= connection.createStatement();
        statement.execute("create table if not exists book(id int primary key,bookName VARCHAR(30),authorName VARCHAR(30),cost int)");
    }

    public List<Book> getBooks() throws SQLException {
        // "select * from book"
        List<Book> booksResponse=new ArrayList<>();
        Statement statement= connection.createStatement();
        ResultSet resultSet=statement.executeQuery("select * from book");

        while(resultSet.next()){
            Book responseBook=Book.builder()
                     .id(resultSet.getInt("id"))
                    .bookName(resultSet.getString("bookName"))
                    .authorName(resultSet.getString("authorName"))
                    .cost(resultSet.getInt("cost"))
                    .build();

            booksResponse.add(responseBook);
        }
        return booksResponse;
    }

    public void createBook(Book book) throws SQLException {
        String preparedQuery="insert into book(id,bookName,authorName,cost) VALUES(?,?,?,?)";
        PreparedStatement preparedStatement= connection.prepareStatement(preparedQuery);
        preparedStatement.setInt(1,book.getId());
        preparedStatement.setString(2,book.getBookName());
        preparedStatement.setString(3,book.getAuthorName());
        preparedStatement.setInt(4,book.getCost());
        preparedStatement.execute();
    }

    public Book getBookById(int id) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("select * from book where id = ?");
        statement.setInt(1, id);
        ResultSet resultSet = statement.executeQuery();

        Book bookToReturn = null;
        while(resultSet.next()){

            Book book = Book.builder()
                    .bookName(resultSet.getString(2))
                    .id(resultSet.getInt("id"))
                    .cost(resultSet.getInt(4))
                    .authorName(resultSet.getString(3))
                    .build();

            bookToReturn = book;
        }

        return bookToReturn;

    }

    public void updateBook(int id,Book book) throws SQLException {
        String query="update book set bookName=?,authorName=?,cost=? where id=?";
        PreparedStatement statement=connection.prepareStatement(query);

        statement.setString(1, book.getBookName());
        statement.setString(2, book.getAuthorName());
        statement.setInt(3,book.getCost());
        statement.setInt(4,id);
        statement.execute();
    }

    public void deleteBook(int id) throws SQLException {
        PreparedStatement statement=connection.prepareStatement("delete from book where id=?");
        statement.setInt(1,id);
        statement.execute();
    }


}
