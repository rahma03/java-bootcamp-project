package com.mitrais.bootcamp.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mitrais.bootcamp.dao.JdbcConnection;
import com.mitrais.bootcamp.model.Book;



public class BookEditServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		long id = 0;
		id= Long.parseLong(req.getParameter("id"));
		String author = req.getParameter("author");
		String title = req.getParameter("title");
		
		int stock =  Integer.parseInt(req.getParameter("stock"));
		boolean avalaible = Boolean.valueOf(req.getParameter("avalaible").equalsIgnoreCase("AVALAIBLE"));
		Book updatedBook = new Book(id, author, title, stock);
		String action = req.getParameter("action");
		try{
			JdbcConnection jdbc = new JdbcConnection();
			if(action.equalsIgnoreCase("edit")){
			if(jdbc.editBook(updatedBook)){
					resp.sendRedirect(req.getContextPath() + "/bookListController");
				} else {
					req.setAttribute("book", updatedBook);
					PrintWriter out = resp.getWriter();
					out.print("Sorry can't update this data");
					RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/bookEdit.jsp");
					dispatcher.include(req, resp);
				}
			}else{
				if(jdbc.addBook(updatedBook)){
					resp.sendRedirect(req.getContextPath() + "/bookListController");
				}else{
					req.setAttribute("book", updatedBook);
					PrintWriter out = resp.getWriter();
					out.print("Sorry can't add this data");
					RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/bookAdd.jsp");
					dispatcher.include(req, resp);
				}
			}
			jdbc.closeAll();
		}catch (Exception e) {
			req.setAttribute("book", updatedBook);
			PrintWriter out = resp.getWriter();
			out.println("<p>Sorry can't update/add this data caused by :"+e.toString()+"</p>");
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/bookEdit.jsp");
			dispatcher.include(req, resp);
		}
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		String action = req.getParameter("do");
		long id = 0;
		id = Long.parseLong(req.getParameter("id"));
		try{
			JdbcConnection jdbc = new JdbcConnection();
			if(action.equals("add")){
				
			}else if(action.equals("edit")){
				Book foundBook = jdbc.selectBookById(id);
				jdbc.closeAll();
				req.setAttribute("book", foundBook);
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/bookEdit.jsp");
				dispatcher.forward(req, resp);
			}else if(action.equals("delete")){
				
				if(jdbc.deleteBookById(id)){
					resp.sendRedirect(req.getContextPath() + "/bookListController");
				} else {
					PrintWriter out = resp.getWriter();
					out.print("Failed Delete this data");
					RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/bookListController");
					dispatcher.include(req, resp);
				}
			}
		}catch (Exception e) {
			req.setAttribute("error message", e.toString());
		}
		
	}

}
