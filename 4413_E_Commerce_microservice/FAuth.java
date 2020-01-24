package service;

import java.io.IOException;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import model.FAuthEngine;


/**
 * Servlet implementation class FAuth
 */
@WebServlet("/FAuth")
public class FAuth extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FAuth() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		FAuthEngine engine = FAuthEngine.getInstance();
		response.setContentType("text/plain");
		PrintStream out = new PrintStream(response.getOutputStream(), true);

		String resp = "";
		 String authHeader = request.getHeader("Authorization");
	        if (authHeader == null) {
	        	response.setStatus(401);
	            response.setHeader("WWW-Authenticate", "BASIC realm=\"Some realm name\"");
	            response.sendError(response.SC_UNAUTHORIZED);
	           
	        } else {
	        	 String base64 = authHeader.substring("Basic".length()).trim();
	        	    byte[] credDecoded = Base64.getDecoder().decode(base64);
	        	    String credentials = new String(credDecoded, StandardCharsets.UTF_8);
	        	    final String[] values = credentials.split(":", 2);
	        	    String username = values[0];
	        	    String password = values[1];
	        	 
		try
		{		
			resp = engine.fAuthCompute(username, password);
			if(resp.equals("OK"))
			{
				response.setStatus(200);
//				out.println("HTTP/1.1 200 OK");
//				out.println("Content-Type: text/plain");
				out.println();	
			}
			if(resp.equals("FAILURE"))
			{
				//response.setStatus(403); 
				response.sendError(403);
			
				
			}
		}
		catch (Exception e)
		{
			resp = e.toString();
		}
	        }
		out.print(resp);
	
		}
	//}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	
	
}
