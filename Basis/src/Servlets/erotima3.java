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

@WebServlet("/erotima3")
public class erotima3 extends HttpServlet
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
	
	
	public erotima3() {
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
		out.println("<head><link rel=\"stylesheet\" type=\"text/css\" href=\"cssarxio.css\"> <title>�� ���������� ��� ����� ����� ��� �������� ���� ��� 1 ���� ��� ��������� �����</title></head>");
		out.println("<body>");	
		out.println("<h1>Ο πωλητής με το μέγιστο τζίρο</h1><br><br><br>");
		try
		{
			Connection con = datasource.getConnection();
			Statement stmt = con.createStatement();	
			
				// �� \r\n ����� �������� ��� ��� ����� ��� ����� ���� ������� ��� ������� 
			
			ResultSet rs = stmt.executeQuery("select x.onoma,x.epitheto ,(case when y.xrh isnull then x.xr when x.xr isnull then 0-y.xrh else x.xr-y.xrh end)as megistos_tziros\r\n" + 
					"from (select pwl_id, pwlites.name as onoma ,pwlites.surname as epitheto ,sum(kostos)as xr\r\n" + 
					"	 from istoriko natural join pwlites\r\n" + 
					"	 where pwlish=true\r\n" + 
					"	 group by pwl_id,pwlites.name,pwlites.surname )   as x   full join\r\n" + 
					"	 (Select pwl_id,sum(kostos) as xrh\r\n" + 
					"	  from istoriko natural join pwlites\r\n" + 
					"	  where pwlish=false\r\n" + 
					"	  group by pwl_id ) as y  on x.pwl_id=y.pwl_id\r\n" + 
					"order by megistos_tziros desc\r\n" + 
					"limit(1)");
			
			out.println("<table border=\"1\">");
			out.println("<tr>");
			out.println("<th>�����  </th>");	
			out.println("<th> �������  </th>");
			out.println("<th> �������� ������  </th>");
			out.println("</tr>");
		
			while(rs.next())
			{
				String onoma = rs.getString("onoma");
				out.println("<tr>");
				out.println("<td >" + onoma + "</td>");	
				
				String epitheto = rs.getString("epitheto");
				out.println("<td >" + epitheto + "</td>");
				
				String megistos_tziros = rs.getString("megistos_tziros");
				out.println("<td >" + megistos_tziros + "</td>");
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

