package com.mitrais.bootcamp.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mitrais.bootcamp.model.User;
import com.mitrais.bootcamp.service.UserService;
import com.mitrais.bootcamp.service.impl.UserServiceImpl;
import com.mitrais.bootcamp.util.SessionUtility;

@WebServlet(name = "user-servlet", displayName = "user-servlet", urlPatterns = "/user")
public class UserServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		UserService service = new UserServiceImpl();
		if(checkSession(req)){
			Cookie newCookie = new Cookie("session", SessionUtility.generateSession());
			resp.addCookie(newCookie);
			try {
				List<User> listUser = service.listUserService();
				req.setAttribute("listUser", listUser);
				getServletContext().getRequestDispatcher("/userList.jsp").include(req, resp);
			} catch (Exception e) {
				req.setAttribute("listUser", null );
				PrintWriter out = resp.getWriter();
				out.println("<p class =\"w3-text-red\">Sorry There's Somethin wrong :"+e.toString()+"</p>");
				getServletContext().getRequestDispatcher("/userList.jsp").include(req, resp);
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

			switch (action) {
			case 1: {
				try {
					String fullName =req.getParameter("fullname");
					String address =req.getParameter("address");
					String user =req.getParameter("user");
					String pass =req.getParameter("pass");
					String dobString = req.getParameter("dob");
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					Date dob = new Date();
					try {
						dob= sdf.parse(dobString);
					} catch (ParseException e) {
						e.printStackTrace();	
					}
					User newUser = new User(fullName, address, user, pass, dob);
					addUser(newUser);
					resp.sendRedirect("user?");
				} catch (Exception e) {
					e.printStackTrace();
					PrintWriter out = resp.getWriter();
					out.println("<p class =\"w3-text-red\">Failed to add caused by: "+e.toString()+"</p>");
				}
				break;
			}
			
			case 2 : {
				try {
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					String username = req.getParameter("user");
					String fullname = req.getParameter("fullname");
					String address = req.getParameter("address");
					String password = req.getParameter("pass");
					int pin_try = Integer.parseInt(req.getParameter("pin_try"));
					String login =req.getParameter("login_status");
					String block =req.getParameter("block_status");
					int login_status = Integer.parseInt(req.getParameter("login_status"));
					int block_status = Integer.parseInt(req.getParameter("block_status"));
					Date birthDate = new Date();
					try {
						birthDate = sdf.parse(req.getParameter("dob"));
					} catch (ParseException e) {
						e.printStackTrace();
					}
					
					User updatedUser = new User(fullname, address, username, password, birthDate);
					updatedUser.setPin_try(pin_try);
					updatedUser.setBlock_status(block_status);
					updatedUser.setLogin_status(login_status);
					UserService service = new UserServiceImpl();
					service.updateUser(updatedUser);
//					getServletContext().getRequestDispatcher("/user?").forward(req, resp);
					resp.sendRedirect("user?");
				} catch (Exception e) {
					e.printStackTrace();
					PrintWriter out = resp.getWriter();
					out.println("<p class =\"w3-text-red\">Failed to update caused by: "+e.toString()+"</p>");
				}
				break;
			}
			
			case 3 : {
				String username = req.getParameter("user");
				UserService service = new UserServiceImpl();
				try {
					service.deleteUser(username);
//					getServletContext().getRequestDispatcher("/user?").forward(req, resp);
					resp.sendRedirect("user?");
				} catch (Exception e) {
					e.printStackTrace();
					PrintWriter out = resp.getWriter();
					out.println("<p class =\"w3-text-red\">Failed to delete caused by: "+e.toString()+"</p>");
					getServletContext().getRequestDispatcher("user?").include(req, resp);
//					resp.sendRedirect("user?");
				}
				break;
			}

			default:
				break;
			}
		}else{
			PrintWriter out = resp.getWriter();
			out.println("<p class =\"w3-text-red\">Session already expired please log in again</p>");
			getServletContext().getRequestDispatcher("/login.jsp").include(req, resp);
		}
	}

	public void addUser(User newUser) {
		UserService service = new UserServiceImpl();
		try {
			service.addNewUser(newUser);
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
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
