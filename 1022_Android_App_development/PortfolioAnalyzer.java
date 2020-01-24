package jr.eecs1022.finpro;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import jba.roumani.lib.Stock;

/**
 * This class encapsulates a portfolio of investments and
 * provides tools for performing portfolio analysis.
 *
 * @author H. Roumani
 * @since Winter 2016
 */
public class PortfolioAnalyzer
{
    private String title;
    private List<Equity> portfolio;
    public static final long MS_PER_DAY = 24L * 3600 * 1000;
    public static final int DAY_PER_YEAR = 365;
    double y = 0.0;

    Equity e1;


    public PortfolioAnalyzer(String title, String[] rows)
    {
        this.title = title;
        this.portfolio = new ArrayList<Equity>();
        for (String row : rows)
        {
            Equity equity;
            String[] fields = row.split(",");
            try
            {
                String symbol = fields[0];
                int qty = Integer.parseInt(fields[1]);
                double bookValue = Double.parseDouble(fields[2]);
                Date acquired = new SimpleDateFormat("d/MM/y").parse(fields[3]);
                double marketValue = this.getInvestmentMarketValue(symbol);
                double yield = this.getInvestmentYield(bookValue, marketValue, acquired);
                equity = new Equity(symbol, qty, bookValue, acquired, marketValue, yield);
            } catch (Exception e)
            {
                equity = new Equity("?", 0, 0.0, null, 0.0, 0.0);
            }
            this.portfolio.add(equity);
        }
    }

    public List<Equity> getPortfolio()
    {
        return this.portfolio;
    }

    public int getPortfolioSize()
    {
        return this.portfolio.size();
    }

    public String toString()
    {
        return "This portfolio has " + this.getPortfolioSize() + " equities and: \n" +
                "-A yield of " + String.format("%,.1f%% ", 100 * this.getPortfolioYield()) + "\n"
                + "-And a highest Yield of " + String.format("%,.1f%%",100* this.maxYield()) ;
    }

    private double getInvestmentMarketValue(String symbol)
    {
       Stock stock = new Stock(symbol);
       return stock.getPrice();

    }

    private double getInvestmentYield(double bookValue, double marketValue, Date acquired)
    {
       Date current = new Date();// provides me with the current date
        long days = (current.getTime() - acquired.getTime())/MS_PER_DAY;//gettime() returns date in milliseconds
        double yield1 = ((marketValue - bookValue)/bookValue);
        double yield2 = ((double)DAY_PER_YEAR/days);
          y = yield1 * yield2;
        return   y;


    }
 // calculating the maximum yield by taking a double variable named max and comparing it with individual yields of the portfolio
    double maxYield()
    {
       double max = 0.0;
        for(Equity equity : this.portfolio)
        {
           if(equity.getYield() > max)//comparison goes here
               max = equity.getYield();
        }
     return max;

    }

    public double getPortfolioMarketValue()
    {
        double marketValueTotal = 0.0;
        for(Equity equity : this.portfolio)
        {
            marketValueTotal += equity.getMarketValue() * equity.getQty();
        }
        return marketValueTotal;


    }

    public double getPortfolioYield()
    {
        double weightedYieldSum = 0;
        double weightSum = 0;
        for (Equity equity : this.portfolio)
        {
            weightedYieldSum += equity.getQty() * equity.getBookValue() * equity.getYield();
            weightSum += equity.getQty() * equity.getBookValue();
        }
        return weightedYieldSum / weightSum;
    }

}