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
import constants.IMicroTechConstants;

public class AddProduct extends GenericServlet {
	public void service(ServletRequest req,ServletResponse res) throws IOException,ServletException
	{
PrintWriter pw = res.getWriter();
		
		res.setContentType(IMicroTechConstants.CONTENT_TYPE_TEXT_HTML);
		
		String ProName = req.getParameter(IProductConstants.COLUMN_PRODUCTNAME);
		String ProPrice = req.getParameter(IProductConstants.COLUMN_PRODUCTPRICE);
		String ProDis = req.getParameter(IProductConstants.COLUMN_PRODUCTDISCRIPTION);
		String ProImage = req.getParameter(IProductConstants.COLUMN_PRODUCTIMAGE);
		String ProType = req.getParameter(IProductConstants.COLUMN_PRODUCTTYPE);
		
		try {
			Connection con = DBConnection.getCon();
			PreparedStatement ps = con.prepareStatement("INSERT INTO " + IProductConstants.TABLE_PRODUCT + "  values(?,?,?,?,?)");
			
			ps.setString(1, ProName);
			ps.setString(2, ProPrice);
			ps.setString(3, ProDis);
			ps.setString(4, ProImage);
			ps.setString(5, ProType);
			
			int k = ps.executeUpdate();
			
			if(k==1)
			{
				RequestDispatcher rd = req.getRequestDispatcher("AddBook.html");
				rd.include(req, res);
				pw.println("<div class=\"tab\">Book Detail Updated Successfully!<br/>Add More Books</div>");
				
			}
			else
			{
				RequestDispatcher rd = req.getRequestDispatcher("AddBook.html");
				pw.println("<div class=\"tab\">Failed to Add Books! Fill up CareFully</div>");
				rd.include(req, res);
				
			}
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
			
		}
		
	}
	
}
