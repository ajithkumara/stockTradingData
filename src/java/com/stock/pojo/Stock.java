/**
 * 
 */
package com.stock.pojo;

import com.google.gson.Gson;

/**
 * @author Ajith kumara
 *
 */
public class Stock {
	private String date;
	private String stockName;
	private double open;
	private double high;
	private double low;
	private double close;
	private double adjClose;
	private int volume;
	
	
	public Stock(
			 String date,
			 String stockName,
			 double open,
			 double high,
			 double low,
			 double close,
			 double adjClose,
			 int volume 
			) {
		 this.date = date;
		 this.stockName = stockName;
		 this.open = open;
		 this.high = high;
		 this.low = low;
		 this.close = close;
		 this.adjClose = adjClose;
		 this.volume = volume;
	}
	
	
	




	public Stock( String json ) {
		
		Gson gson = new Gson();
		
		Stock jsonStock = gson.fromJson(json,Stock.class);
		 this.date  = jsonStock.date;
		 this.stockName = jsonStock.stockName;
		 this.open = jsonStock.open;
		 this.high = jsonStock.high;
		 this.low = jsonStock.low;
		 this.close = jsonStock.close;
		 this.adjClose = jsonStock.adjClose;
		 this.volume = jsonStock.volume;
	}
	
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getStockName() {
		return stockName;
	}
	public void setStockName(String stockName) {
		this.stockName = stockName;
	}
	public double getOpen() {
		return open;
	}
	public void setOpen(double open) {
		this.open = open;
	}
	public double getHigh() {
		return high;
	}
	public void setHigh(double high) {
		this.high = high;
	}
	public double getLow() {
		return low;
	}
	public void setLow(double low) {
		this.low = low;
	}
	public double getClose() {
		return close;
	}
	public void setClose(double close) {
		this.close = close;
	}
	public double getAdjClose() {
		return adjClose;
	}
	public void setAdjClose(double adjClose) {
		this.adjClose = adjClose;
	}
	public int getVolume() {
		return volume;
	}
	public void setVolume(int volume) {
		this.volume = volume;
	}

	public String toString() {
		return new Gson().toJson(this);
	}
	
}
