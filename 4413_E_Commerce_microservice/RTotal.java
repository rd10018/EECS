package service;

import java.io.IOException;
import java.io.PrintStream;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Engine;

/**
 * Servlet implementation class RTotal
 */
@WebServlet("/RT")
public class RTotal extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		Engine engine = Engine.getInstance();
		
		HttpSession session = request.getSession(true);
		if (session.getAttribute("total") == null) session.setAttribute("total", "0");
		String old = (String) session.getAttribute("total");
		
		ServletContext context = this.getServletContext();
		if (context.getAttribute("grand") == null) context.setAttribute("grand", "0");
		String oldGrand = (String) context.getAttribute("grand");
		
		response.setContentType("text/plain");
		PrintStream out = new PrintStream(response.getOutputStream(), true);
		
		out.println("Your ip:port: " + request.getRemoteAddr() + ":" + request.getRemotePort());
		out.println("You asked for URL: " + request.getRequestURL());
		out.println("Your query string: " + request.getQueryString());
		out.println("You sent me header Host = " + request.getHeader("host"));
		out.println("You sent me cookie = " + request.getHeader("cookie"));
		out.println("Your session started at " + session.getCreationTime());
		
		
		try
		{
			String num = request.getParameter("num");
			
			String now = engine.add(old,  num);
			session.setAttribute("total",  now);
			
			String grandNow = engine.add(oldGrand,  num);
			context.setAttribute("grand", grandNow);
			
			out.println("Total is: " + now);
			out.println("Grand Total is: " + grandNow);
		}
		catch (Exception e)
		{
			out.println(e);
		}
	}





	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		doGet(request, response);
	}

}
