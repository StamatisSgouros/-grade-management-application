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

@WebServlet("/erotima2")
public class erotima2 extends HttpServlet
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
	
	
	public erotima2() {
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
		out.println("<head><link rel=\"stylesheet\" type=\"text/css\" href=\"cssarxio.css\"> <title>�� ���� ������ ��� ��������� ��� ��������� ��� ����</title></head>");
		out.println("<body>");	
		out.println("<h1>�� ���� ������ ��� ��������� ��� ��������� ��� ����</h1><br><br><br>");
		try
		{
			Connection con = datasource.getConnection();
			Statement stmt = con.createStatement();	
			
				// �� \r\n ����� �������� ��� ��� ����� ��� ����� ���� ������� ��� ������� 
			
			ResultSet rs = stmt.executeQuery("select extract (month from date_st ) as mhnas , extract (year from date_st ) as etos ,avg(kostos) as Mesos_Oros\r\n" + 
						"from episkeues\r\n" + 
						"group by mhnas,etos");
			
			out.println("<table border=\"1\">");
			out.println("<tr>");
			out.println("<th>�����  </th>");	
			out.println("<th> ����  </th>");
			out.println("<th> ����� ����  </th>");
			out.println("</tr>");
			
			while(rs.next())
			{
				String mhnas = rs.getString("mhnas");
				out.println("<tr>");
				out.println("<td >" + mhnas + "</td>");	
				
				String etos = rs.getString("etos");
				out.println("<td >" + etos + "</td>");
				
				String mesos_Oros = rs.getString("mesos_Oros");
				out.println("<td >" + mesos_Oros + "</td>");
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

