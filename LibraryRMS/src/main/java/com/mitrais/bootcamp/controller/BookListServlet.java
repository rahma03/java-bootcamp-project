package com.mitrais.bootcamp.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mitrais.bootcamp.dao.JdbcConnection;
import com.mitrais.bootcamp.model.Book;

public class BookListServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		getListBook(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
	}
	
	public void getListBook(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		List<Book> listOfBook;
		try {
			JdbcConnection jdbc = new JdbcConnection();
			listOfBook = jdbc.selectAllBook();
			jdbc.closeAll();
		} catch (Exception e) {
			listOfBook = new ArrayList<Book>();
		}
		
		req.setAttribute("listBook", listOfBook);
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/bookList.jsp");
		dispatcher.forward(req, resp);
	}
	
}
