package servlets;

import java.io.*;
import java.sql.*;
import javax.servlet.*;

import constants.IMicroTechConstants;
import sql.ILoginUserConstants;

public class RegisterServlet extends GenericServlet {
	public void service(ServletRequest req, ServletResponse res) throws IOException, ServletException {
		
		PrintWriter pw = res.getWriter();
		res.setContentType(IMicroTechConstants.CONTENT_TYPE_TEXT_HTML);

		String Name = req.getParameter(ILoginUserConstants.COLUMN_NAME);
		String uName = req.getParameter(ILoginUserConstants.COLUMN_USERNAME);
		String Email = req.getParameter(ILoginUserConstants.COLUMN_EMAIL);
		String pWord = req.getParameter(ILoginUserConstants.COLUMN_PASSWORD);
		String phNo = req.getParameter(ILoginUserConstants.COLUMN_PHONE);
	
		try {
			Connection con = DBConnection.getCon();
			PreparedStatement ps = con.prepareStatement("insert into " + ILoginUserConstants.TABLE_USERS + "  values(?,?,?,?,?)");
			
			ps.setString(1, Name);
			ps.setString(2, uName);
			ps.setString(2, Email);
			ps.setString(2, pWord);
			ps.setString(2, phNo);
			
			int k = ps.executeUpdate();
			
			if (k == 1) {
				RequestDispatcher rd = req.getRequestDispatcher("login.html");
				rd.include(req, res);
				pw.println("<h3 class='tab'>User Registered Successfully</h3>");
				
			}
			else {
				RequestDispatcher rd = req.getRequestDispatcher("userreg");
				rd.include(req, res);
				pw.println("Sorry for interruption! Register again");
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		
	}
	
}
