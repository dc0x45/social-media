//TODO: Fix the viewEditAccount function (probably). Fix the updateProfile function in the ProfileDAO file. Also fix up any lingering JSP page issues.

package application;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map.Entry;

import org.apache.tomcat.jakartaee.commons.compress.utils.IOUtils;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import interactive.Profile;
import interactive.ProfileDAO;

@SuppressWarnings("serial")
public class Controller extends HttpServlet {
	
	private ProfileDAO dao;
	private Profile uProfile;

	public void init() {
		final String url = getServletContext().getInitParameter("JDBC-URL");
		final String username = getServletContext().getInitParameter("JDBC-USERNAME");
		final String password = getServletContext().getInitParameter("JDBC-PASSWORD");
		
		dao = new ProfileDAO(url, username, password);
	}
  
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doPost");
		doGet(request, response);
	}
  
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		final String action = request.getServletPath();
		System.out.println("doGet");
		try {
			switch(action) {
				case "/entity": viewAccount(request, response); break; 
				case "/viewEdit": viewEditAccount(request, response); break;
				case "/edit": actualEditor(request, response); break;
				case "/logout": viewLogout(request, response); break;
				case "/login": viewLogin(request, response); break; 
				case "/upload": uploadHandler(request, response); break;
				default: viewLogin(request, response); break;
			}
		} catch (SQLException e) {
			throw new ServletException(e);
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void viewLogout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Cookie cookie = new Cookie("authUser", null);
		cookie.setMaxAge(0);
		response.addCookie(cookie);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
		dispatcher.forward(request, response);
	}
	
	private void viewAccount(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException, NoSuchAlgorithmException {
		System.out.println("found viewAccount");
		final String userProf = request.getServletPath();
		Cookie[] cookies = request.getCookies(); 
		boolean failedLogin = false;

		for (Cookie userCookie : cookies) {
		    if(userCookie.getName().equals("authUser")) {
	    		System.out.println("found cookie");
		    	uProfile = dao.getProfile(userCookie.getValue()); 
		    	if(uProfile != null) {
		    		System.out.println("sending profile");
		    		for(Entry<String, String> set : uProfile.details.entrySet()) {
						request.setAttribute(set.getKey(), set.getValue());
					}
		    	}
		    } else {
	    		System.out.println("no cookie");
		    	String logUsername = request.getParameter("username");
				String logPassword = request.getParameter("password");
				
				boolean authed = false;
				if(logUsername != null) {
					System.out.println(logUsername);
					Profile checkProfile = dao.getProfile(logUsername);
					if(checkProfile != null) {
						System.out.println("profile found");
						authed = checkProfile.loginUser(logUsername, logPassword);
						System.out.println(authed);
						if(authed) {
							System.out.println("auth cookie");
							Cookie cookie = new Cookie("authUser", logUsername);
							response.addCookie(cookie);
							String dateString = DateFormat.getDateInstance().format(new Date());
							checkProfile.setAttr("lastLogin", dateString);
							dao.updateProfile(checkProfile);
							for(Entry<String, String> set : checkProfile.details.entrySet()) {
								request.setAttribute(set.getKey(), set.getValue());
							}
						} else {
							failedLogin = true;
							viewLogin(request, response);
						}
					}
				}
		    }
		}
		if(!failedLogin) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("entity.jsp");
			dispatcher.forward(request, response);
		}
	}

	private void viewEditAccount(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		Cookie[] cookies = request.getCookies(); 
		for (Cookie userCookie : cookies) {
		    if(userCookie.getName().equals("authUser")) {
		    	Profile user = dao.getProfile(userCookie.getValue()); 
		    	for(Entry<String, String> set : user.details.entrySet()) {
					request.setAttribute(set.getKey(), set.getValue());
		    	}
		    }
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("edit.jsp");
		dispatcher.forward(request, response);

	}
	
	private void actualEditor(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException, NoSuchAlgorithmException {
		Cookie[] cookies = request.getCookies(); 
		for (Cookie userCookie : cookies) {
		    if(userCookie.getName().equals("authUser")) {
		    	Profile user = dao.getProfile(userCookie.getValue()); 
		    	for(Entry<String, String> set : user.details.entrySet()) {
					request.setAttribute(set.getKey(), set.getValue());
				}
		    	HashMap<String, String> params = new HashMap<String, String>();
		    	if(user != null) {				
					params.put("hashedPassword", request.getParameter("hashedPassword"));
					params.put("first", request.getParameter("first")); 
					params.put("last", request.getParameter("last")); 
					params.put("college", request.getParameter("college")); 
					params.put("highschool", request.getParameter("highschool")); 
					params.put("dob", request.getParameter("dob"));
					params.put("email", request.getParameter("email")); 
					params.put("phone_number", request.getParameter("phone_number"));
					params.put("hometown", request.getParameter("hometown"));
					
					for(Entry<String, String> set : params.entrySet()) {
						if(set.getValue() != null) {
							user.setAttr(set.getKey(), set.getValue());
						}
					}
					dao.updateProfile(user);
					request.setAttribute("profile", user);
		    	}
		    }
		}
		viewAccount(request, response);
	}
	
	private void uploadHandler(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException, NoSuchAlgorithmException {
		Cookie[] cookies = request.getCookies(); 
		for (Cookie userCookie : cookies) {
		    if(userCookie.getName().equals("authUser")) {
		    	Profile user = dao.getProfile(userCookie.getValue()); 
		    	byte[] imageBytes = IOUtils.toByteArray(request.getPart("basedPic").getInputStream());
				String base64 = Base64.getEncoder().encodeToString(imageBytes);
				user.setAttr("basedPic", base64);
				dao.updateProfile(user);
		    }
		}
		viewAccount(request, response);
	}
	
	private void viewLogin(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
		dispatcher.forward(request, response);
	}
}