package service;

import java.io.IOException;
import java.io.Writer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Toy
 */
@WebServlet("/Toy")
public class Toy extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Toy()
	{
		super();
		// TODO Auto-generated constructor stub
	}

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		//response.sendRedirect("Add");
		
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		response.setContentType("text/plain");
		Writer out = response.getWriter();
		out.write("Hello ... I'm alive!\n");
		out.write("Your IP is: " + request.getRemoteAddr());
		out.write("\nYour port is: " + request.getRemotePort());
		String x = request.getParameter("x");
		out.write("\nYou sent me x = " + x);
		
		HttpSession ses = request.getSession(true);
		ses.setAttribute("x", x);

		
		
	}

	
	
	
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
