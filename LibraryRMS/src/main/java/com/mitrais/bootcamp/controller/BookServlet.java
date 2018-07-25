package com.mitrais.bootcamp.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mitrais.bootcamp.model.Book;
import com.mitrais.bootcamp.service.BookService;
import com.mitrais.bootcamp.service.UserService;
import com.mitrais.bootcamp.service.impl.BookServiceImpl;
import com.mitrais.bootcamp.service.impl.UserServiceImpl;
import com.mitrais.bootcamp.util.SessionUtility;

@WebServlet(name="book-servlet", displayName="book-servlet", urlPatterns="/book")
public class BookServlet extends HttpServlet {
	private static final int ADDNEWBOOK=1;
	private static final int EDITBOOK=2;
	private static final int DELETEBOOK=3;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if(checkSession(req)){
			Cookie newCookie = new Cookie("session", SessionUtility.generateSession());
			resp.addCookie(newCookie);
			List<Book> result;
			try {
				BookService service = new BookServiceImpl();
				result = service.getAllBook();
				req.setAttribute("listBook", result );
				getServletContext().getRequestDispatcher("/bookList.jsp").include(req, resp);
			} catch (Exception e) {
				req.setAttribute("listBook", null );
				PrintWriter out = resp.getWriter();
				out.println("<p class =\"w3-text-red\">Sorry There's Somethin wrong :"+e.toString()+"</p>");
				getServletContext().getRequestDispatcher("/index.jsp").include(req, resp);
			}
		}else{
			PrintWriter out = resp.getWriter();
			out.println("<p class =\"w3-text-red\">Session already expired please log in again</p>");
			getServletContext().getRequestDispatcher("/login.jsp").include(req, resp);
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if(checkSession(req)){
			Cookie newCookie = new Cookie("session", SessionUtility.generateSession());
			resp.addCookie(newCookie);
			int action = Integer.parseInt(req.getParameter("action"));
			
			switch(action){
			case ADDNEWBOOK: {
				try {
					String title = req.getParameter("title");
					String author = req.getParameter("author");
					int stock = Integer.parseInt(req.getParameter("stock"));
					boolean avalaibility = stock>0;
					Book newBook = new Book(title, author, stock);
					BookService service = new BookServiceImpl();
					service.addNewBook(newBook);
					resp.sendRedirect("book?");
				} catch (Exception e) {
					e.printStackTrace();
					PrintWriter out = resp.getWriter();
					out.println("<p class =\"w3-text-red\">Failed to add caused by: "+e.toString()+"</p>");
				}
				break;
			}
			case EDITBOOK: {
				try {
					String title = req.getParameter("title");
					String author = req.getParameter("author");
					long idBook = Long.parseLong(req.getParameter("id"));
					int stock = Integer.parseInt(req.getParameter("stock"));
					boolean avalaibility = stock>0;
					Book editedBook = new Book(idBook, author, title, stock);
					BookService service = new BookServiceImpl();
					service.updateBook(editedBook);
					resp.sendRedirect("book?");
				} catch (Exception e) {
					e.printStackTrace();
					PrintWriter out = resp.getWriter();
					out.println("<p class =\"w3-text-red\">Failed to update caused by: "+e.toString()+"</p>");
				}
				break;
			}
			case DELETEBOOK: {
				try {
					long idBook = Long.parseLong(req.getParameter("id"));
					BookService service = new BookServiceImpl();
					service.deleteBook(idBook);
					resp.sendRedirect("book?");
				} catch (Exception e) {
					e.printStackTrace();
					PrintWriter out = resp.getWriter();
					out.println("<p class =\"w3-text-red\">Failed to delete caused by: "+e.toString()+"</p>");
//					getServletContext().getRequestDispatcher("user?").include(req, resp);
				}
				break;
			}
			default:
				break;
			}
		}else{
			
		}
	}

	public boolean checkSession(HttpServletRequest req){
		UserService service = new UserServiceImpl();
		Cookie[] allCookie = req.getCookies();
		String user=null;
		String session=null;
		for(Cookie cookie:allCookie){
			if(cookie.getName().equalsIgnoreCase("user"))
				user= cookie.getValue();
			if(cookie.getName().equalsIgnoreCase("session"))
				session = cookie.getValue();
			
		}
		if(!session.isEmpty() && !user.isEmpty()){
			if(SessionUtility.checkSessionExpired(session)){
				return true;
			}else{
				return false;
			}
		}else{
			return false;
		}
	}
}
