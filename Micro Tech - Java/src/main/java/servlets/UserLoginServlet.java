package servlets;

import javax.servlet.*;
import java.io.*;
import java.sql.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;  
import javax.servlet.http.HttpServletResponse;  
import javax.servlet.http.HttpSession;  

import constants.IMicroTechConstants;
import sql.ILoginUserConstants;

public class UserLoginServlet extends GenericServlet {
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {  
		
		res.setContentType("text/html");  
		PrintWriter pw = res.getWriter();  
		
		res.setContentType(IMicroTechConstants.CONTENT_TYPE_TEXT_HTML);
		String uName = req.getParameter(ILoginUserConstants.COLUMN_USERNAME);
		String pWord = req.getParameter(ILoginUserConstants.COLUMN_PASSWORD);
		
		try {
			Connection con = DBConnection.getCon();
			PreparedStatement ps = con.prepareStatement("SELECT * FROM " + ILoginUserConstants.TABLE_USERS + " WHERE "
					+ ILoginUserConstants.COLUMN_USERNAME + "=? AND " + ILoginUserConstants.COLUMN_PASSWORD + "=? AND " + ILoginUserConstants.COLUMN_USERTYPE + "=2");
			
			ps.setString(1, uName);
			ps.setString(2, pWord);
			ResultSet rs = ps.executeQuery();
			
			if (rs.next()) {
				HttpSession session=req.getSession();
		        session.setAttribute("name", uName);
				req.getRequestDispatcher("home.html").include(req, res);
				
			}
			else {

				req.getRequestDispatcher("UserLogin.html").include(req, res);				
				pw.println("<div class=\"tab\">Incorrect UserName or PassWord</div>");
				
			}

		}
		catch (Exception e) {
			e.printStackTrace();
			
		}
		
	}

	@Override
	public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	}
	
}