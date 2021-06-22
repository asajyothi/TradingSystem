package com.example.trade.system.service;

import org.springframework.http.ResponseEntity;

import com.example.trade.system.model.TradeInformation;

public interface TradingService {
	
	public ResponseEntity<String> saveTradingInformation(TradeInformation tradeData);
	
	public void updateExpiredTradeInformation();

}
