package com.example.trade.system.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "TRADE_INFORMATION")
public class TradeInformation {
	
	@EmbeddedId
	private TradingKey tradingkey;
	
	@Column(name = "COUNTER_PARTY_ID")
	private String counterPartyID;
	
	@Column(name = "BOOKID")
	private String bookID;
	
	@JsonFormat(pattern="yyyy-MM-dd")
	@Column(name = "MATURITY_DATE")
	private Date maturityDate;
	
	@JsonFormat(pattern="yyyy-MM-dd")
	@Column(name = "CREATED_DATE")
	private Date createdDate;
	
	@Column(name = "EXPIRED")
	private String expired;
	
	

	public TradingKey getTradingkey() {
		return tradingkey;
	}
	public void setTradingkey(TradingKey tradingkey) {
		this.tradingkey = tradingkey;
	}
	public String getCounterPartyID() {
		return counterPartyID;
	}
	public void setCounterPartyID(String counterPartyID) {
		this.counterPartyID = counterPartyID;
	}
	public String getBookID() {
		return bookID;
	}
	public void setBookID(String bookID) {
		this.bookID = bookID;
	}
	public Date getMaturityDate() {
		return maturityDate;
	}
	public void setMaturityDate(Date maturityDate) {
		this.maturityDate = maturityDate;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	public String getExpired() {
		return expired;
	}
	public void setExpired(String expired) {
		this.expired = expired;
	}
	

	
	
	

}
