package servlets;

import java.io.*;
import java.sql.*;
import javax.servlet.*;

import constants.IMicroTechConstants;
import sql.IProductConstants;

public class ProductLoad extends GenericServlet{
	public void service(ServletRequest req,ServletResponse res) throws IOException,ServletException
	{
		PrintWriter pw = res.getWriter();
		res.setContentType("text/html");
		
		try {
			// Get all product details
			Connection con = DBConnection.getCon();
			PreparedStatement ps = con.prepareStatement("SELECT * FROM " + IProductConstants.TABLE_PRODUCT);
			ResultSet rs = ps.executeQuery();
			RequestDispatcher rd = req.getRequestDispatcher("product.html");
			rd.include(req, res);
			
			while(rs.next())
			{
				int ItemID = rs.getInt(1);
				String Name = rs.getString(2);
				String Price = rs.getString(3);
				String Disciption = rs.getString(4);
				String Image = rs.getString(5);
				
				pw.println("<div class=\"card m-1\" style=\"width: 18rem; user-select: none; padding: 0px;\">\r\n"
						+ "<img src=\""+ Image + "\" style=\"width: 287px; height: 300px;\" class=\"card-img-top\" alt=\"...\">\r\n"
						+ "<div class=\"card-body\">\r\n"
						+ "<h5 class=\"card-title\">" + Name + "</h5>\r\n"
						+ "<p class=\"card-text\">" + Disciption + "<br>$"+ Price +"</p>\r\n"
						+ "<a href=\"#\" class=\"btn btn-primary\">BUY NOW</a>\r\n"
						+ "</div>\r\n"
						+ "</div>");
								
			}
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}
	
}