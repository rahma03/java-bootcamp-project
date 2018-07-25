package com.mitrais.bootcamp.service;

import java.sql.SQLException;
import java.util.List;

import com.mitrais.bootcamp.model.Book;

public interface BookService {
	public List<Book> getAllBook() throws SQLException,ClassNotFoundException;
	public void addNewBook(Book newBook) throws SQLException,ClassNotFoundException;
	public void deleteBook(long existingBookId)throws SQLException,ClassNotFoundException;
	public void updateBook(Book updatedBook)throws SQLException,ClassNotFoundException;
}
