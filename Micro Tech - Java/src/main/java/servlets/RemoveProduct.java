package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.GenericServlet;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import sql.IProductConstants;

public class RemoveProduct extends GenericServlet {
	public void service(ServletRequest req, ServletResponse res) throws IOException, ServletException {
		PrintWriter pw = res.getWriter();
		res.setContentType("text/html");
		String bkid = req.getParameter("product_id");
		
		try {
			Connection con = DBConnection.getCon();
			PreparedStatement ps = con.prepareStatement("delete from " + IProductConstants.TABLE_PRODUCT + "  where " + IProductConstants.COLUMN_PRODUCTID + "=?");
			ps.setString(1, bkid);
			int k = ps.executeUpdate();
			
			if (k == 1) {
				RequestDispatcher rd = req.getRequestDispatcher("Sample.html");
				rd.include(req, res);
				pw.println("<div class=\"tab\">Book Removed Successfully</div>");
				pw.println("<div class=\"tab\"><a href=\"RemoveBooks.html\">Remove more Books</a></div>");

			} else {
				RequestDispatcher rd = req.getRequestDispatcher("Sample.html");
				rd.include(req, res);
				pw.println("<div class=\"tab\">Book Not Available In The Store</div>");
				pw.println("<div class=\"tab\"><a href=\"RemoveBooks.html\">Remove more Books</a></div>");
				
			}
			
		}
		catch (Exception e) {
			e.printStackTrace();
			
		}
		
	}
}
