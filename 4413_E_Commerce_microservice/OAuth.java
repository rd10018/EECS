package service;

import java.io.IOException;
import java.io.PrintStream;
import java.net.InetAddress;
import java.util.Scanner;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



@WebServlet("/OAuth")
public class OAuth extends HttpServlet {
	
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OAuth() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		 String ipAddress = request.getHeader("X-FORWARDED-FOR");  // for getting the original IP address of the client
	       if (ipAddress == null) {  
	    	   InetAddress inetAddress = InetAddress.getLocalHost();
	    	    ipAddress = inetAddress.getHostAddress();

	   }
		response.setContentType("text/html");
		PrintStream out = new PrintStream(response.getOutputStream(), true);
		String resp = "";
		try
		{	
			String url = request.getScheme() + "://" + ipAddress.toString() + ":" + request.getServerPort() + request.getRequestURI();
				if (request.getQueryString() == null)
				{	
					response.sendRedirect(" https://www.eecs.yorku.ca/~roumani/servers/auth/oauth.cgi?back=" + url);						
				}
				else
				{	
					String username =  request.getParameter("user");
					String fullname = request.getParameter("name");
					out.println("Welcome " + fullname);
				}
			
		}
		catch (Exception e)
		{
			resp = e.toString();
		}
		out.print(resp);
	
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
