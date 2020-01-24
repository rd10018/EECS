package service;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class StatefulDb {

	private static Map< Integer, double[]> user_db= new HashMap<>();
	
	private static Set<Integer> cookie_used = new HashSet<Integer>();
	private int cookie = 0; 
	private double[] values = new double[2];
	
	
	
	public StatefulDb() {
		
	}

	public int getCookie()
	{
		return cookie;
	}
	
	public void setCookie(int cookie)
	{
		this.cookie = cookie;
	}
	
	
	
	public boolean cookie_check(int cookie)
	{
		return cookie_used.contains(cookie);
	}
	
	
	public void addCookie(int cookie)
	{
		cookie_used.add(cookie);
	}
	public void setMap(int cookie, double latitude, double longitude)
	{
		user_db.put(cookie, new double[] {latitude, longitude});
	}
	
	public Map<Integer, double[]> getMap()
	{
		return user_db;
	}
	
	public Set<Integer> getCookieSet()
	{
		return cookie_used;
	}
}
