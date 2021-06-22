package com.example.trade.system.model;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class TradingKey implements Serializable {

	
	
	private String tradingID;
	private int version;
	
	public TradingKey() {
		
	}
	
	public TradingKey(String tradingID, int version) {
		super();
		this.tradingID = tradingID;
		this.version = version;
	}

	public String getTradingID() {
		return tradingID;
	}

	public void setTradingID(String tradingID) {
		this.tradingID = tradingID;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}
	
	
	
}
