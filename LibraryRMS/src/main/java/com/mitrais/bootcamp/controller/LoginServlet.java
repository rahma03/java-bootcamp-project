package com.mitrais.bootcamp.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mitrais.bootcamp.service.UserService;
import com.mitrais.bootcamp.service.impl.UserServiceImpl;
import com.mitrais.bootcamp.util.SessionUtility;

@WebServlet(name="login-servlet",
			displayName="login-servlet",
			urlPatterns="/login"
)
public class LoginServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String user = req.getParameter("user");
		String password = req.getParameter("pass");
		UserService userService = new UserServiceImpl();
		if(userService.loginService(user, password)){
			Cookie userCookie = new Cookie("user", user);
			userCookie.setMaxAge(30*60);
			resp.addCookie(userCookie);
			userCookie= new Cookie("session", SessionUtility.generateSession());
			userCookie.setMaxAge(30*60);
			resp.addCookie(userCookie);
			req.setAttribute("user", user);
			getServletContext().getRequestDispatcher("/index.jsp").include(req, resp);
		}else{
			PrintWriter out = resp.getWriter();
			out.println("<p class =\"w3-text-red\">data input not found, please check again</p>");
			getServletContext().getRequestDispatcher("/login.jsp").include(req, resp);
		}
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.addCookie(new Cookie("user", ""));
		resp.addCookie(new Cookie("session", ""));
		resp.sendRedirect("login.jsp");
	}

}
