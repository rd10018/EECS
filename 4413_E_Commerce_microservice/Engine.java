package model;

public class Engine
{
	private static Engine engine = null;
	
	private Engine()
	{
	}
	
	public static Engine getInstance()
	{
		if (engine == null) engine = new Engine();
		return engine;
	}
	
	public String add(String x, String y)
	{
		int a = Integer.parseInt(x);
		int b = Integer.parseInt(y);
		int sum = a + b;
		return "" + sum;
	}

}