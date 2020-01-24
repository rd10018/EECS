package service;

import java.io.IOException;
import java.io.PrintStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.GeoWebEngine;

/**
 * Servlet implementation class GeoWeb
 */
@WebServlet("/GeoWeb")
public class GeoWeb extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GeoWeb() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	
	@SuppressWarnings("deprecation")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
			
		GeoWebEngine engine = GeoWebEngine.getInstance();
		HttpSession s1 = request.getSession();
		String lat1, lon1;
		String resp = "";
		response.setContentType("text/plain");
		PrintStream out = new PrintStream(response.getOutputStream(), true);
		String lat = request.getParameter("lat");
		String lon = request.getParameter("lon");

		try
		{
			if(s1.getValueNames().length == 0 && !lat.isEmpty() &&!lon.isEmpty() )
			{
				s1.setAttribute("lat", lat);
				s1.setAttribute("lon", lon);
				out.println("RECEIVED");
			}
			else
			{
				lat1 = s1.getAttribute("lat").toString();
				lon1 = s1.getAttribute("lon").toString();				
				s1.setAttribute("lat", lat);
				s1.setAttribute("lon", lon);
				resp = engine.geoCompute(lat1, lon1, lat, lon);
				out.print("The distance from (lat1,lon1) to (lat2,lon2) is ");
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
