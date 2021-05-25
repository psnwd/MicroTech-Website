package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

import javax.servlet.GenericServlet;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import constants.IMicroTechConstants;
import sql.IUserConstants;
import sql.IPaymentConstants;

@WebServlet("/SavePayements")
public class SavePayements extends GenericServlet {

    public SavePayements() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException
	{
		PrintWriter pw = res.getWriter();
		
		res.setContentType(IMicroTechConstants.CONTENT_TYPE_TEXT_HTML);
		
		// ---------------------------------------------------------------------------------------------------------------- //
		
		String Fname = req.getParameter(IUserConstants.COLUMN_FNAME);
		String Lname = req.getParameter(IUserConstants.COLUMN_LNAME);
		String Uname = req.getParameter(IUserConstants.COLUMN_UNAME);
		String Email = req.getParameter(IUserConstants.COLUMN_EMAIL);
		String Address = req.getParameter(IUserConstants.COLUMN_ADDRESS);
		
		try {
			Connection con = DBConnection.getCon();
			PreparedStatement ps = con.prepareStatement("INSERT INTO " + IUserConstants.TABLE_USER + "  values(?,?,?,?,?)");
			ps.setString(1, Fname);
			ps.setString(2, Lname);
			ps.setString(3, Uname);
			ps.setString(4, Email);
			ps.setString(5, Address);
			
			int k = ps.executeUpdate();
			if(k==1)
			{
				RequestDispatcher rd = req.getRequestDispatcher("checkout.html");
				rd.include(req, res);
				//pw.println("<div class=\"tab\">Book Detail Updated Successfully!<br/>Add More Books</div>");
			}
			else
			{
				RequestDispatcher rd = req.getRequestDispatcher("checkout.html");
				//pw.println("<div class=\"tab\">Failed to Add Books! Fill up CareFully</div>");
				rd.include(req, res);
			}
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		// ------------------------------------------------------------------------------------------------------------------------ //
		
		String Cardtype = req.getParameter(IPaymentConstants.COLUMN_TYPE);
		String Cardname = req.getParameter(IPaymentConstants.COLUMN_CARDNAME);
		String Cardno = req.getParameter(IPaymentConstants.COLUMN_CARDNO);
		String Cardexpdate = req.getParameter(IPaymentConstants.COLUMN_EXPDATE);
		
		try {
			Connection con = DBConnection.getCon();
			PreparedStatement ps2 = con.prepareStatement("INSERT INTO " + IPaymentConstants.TABLE_PAYMENT + "  values(?,?,?,?)");
			ps2.setString(1, Cardtype);
			ps2.setString(2, Cardname);
			ps2.setString(3, Cardno);
			ps2.setString(4, Cardexpdate);
			
			int k = ps2.executeUpdate();
			if(k==1)
			{
				RequestDispatcher rd = req.getRequestDispatcher("checkout.html");
				//pw.println("<div class=\"tab\">Book Detail Updated Successfully!<br/>Add More Books</div>");
				rd.include(req, res);
				
			}
			else
			{
				RequestDispatcher rd = req.getRequestDispatcher("checkout.html");
				//pw.println("<div class=\"tab\">Failed to Add Books! Fill up CareFully</div>");
				rd.include(req, res);
				
			}
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		// ------------------------------------------------------------------------------------------------------------------------ //
		
		
		
	}

}


