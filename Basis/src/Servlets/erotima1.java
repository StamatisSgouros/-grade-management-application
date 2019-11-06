package Servlets;


import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.naming.InitialContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

@WebServlet("/erotima1")
public class erotima1 extends HttpServlet
{
	private static final long serialVersionUID = 1L;
	
	private DataSource datasource = null;

	public void init() throws ServletException
	{
		try
		{	
			InitialContext ctx = new InitialContext();
			datasource = (DataSource)ctx.lookup("java:comp/env/jdbc/confluence");
		} 
		catch(Exception e) 
		{
			throw new ServletException(e.toString());
		}

	}
	
	
	public erotima1() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		out.println("<html>");   
		out.println("<head><link rel=\"stylesheet\" type=\"text/css\" href=\"cssarxio.css\"> <title>�� ������� ����������� �� �� ������� ������ ������</title></head>");
		out.println("<body>");	
		out.println("<h1>�� ������� ����������� �� �� ������� ������ ������</h1><br><br><br>");
		try
		{
			Connection con = datasource.getConnection();
			Statement stmt = con.createStatement();	
			
				// �� \r\n ����� �������� ��� ��� ����� ��� ����� ���� ������� ��� ������� 
			
				ResultSet rs = stmt.executeQuery("select modelo\r\n" + 
					"from episkeues natural join autokinita natural join stoixeia_aut\r\n" + 
					"group by modelo\r\n" + 
					"having count(episkeues_id) in \r\n" + 
					"(			\r\n" + 
					"			select max(ddd.dd)\r\n" + 
					"            from 	\r\n" + 
					"				(	\r\n" + 
					"					select count(episkeues_id) as dd\r\n" + 
					"					from episkeues natural join autokinita natural join stoixeia_aut\r\n" + 
					"					group by modelo\r\n" + 
					"				) as ddd\r\n" + 
					");\r\n" + 
					"");
			
			out.println("<table border=\"1\">");
			out.println("<tr>");
			out.println("<th>�������  </th>");					
			out.println("</tr>");
			
			while(rs.next())
			{
				String modelo = rs.getString("modelo");
				out.println("<tr>");
				out.println("<td >" + modelo + "</td>");
				out.println("</tr>");
											
			}
			out.println("</table>");
			rs.close();

			con.close();
		} 
		catch(Exception e)
		{
			out.println("Database connection problem");
		}
		out.println("<br><br><a href=\"/Basis/index.html\">��������� ��� �����</a>");
		out.println("</body></html>");
				
	}
}
