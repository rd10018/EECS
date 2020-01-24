package service;

import java.io.IOException;
import java.io.PrintStream;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Engine;

/**
 * Servlet implementation class Add
 */
@WebServlet("/Add")
public class Add extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Add()
	{
		super();
		// TODO Auto-generated constructor stub
	}

	
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		Engine engine = Engine.getInstance();
		response.setContentType("text/plain");
		PrintStream out = new PrintStream(response.getOutputStream(), true);
		String x = request.getParameter("x");
		String y = request.getParameter("y");
		String resp;
		try
		{
			resp = engine.add(x,y);
		}
		catch (Exception e)
		{
			resp = e.toString();
		}
		out.print(resp);
		
	
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
