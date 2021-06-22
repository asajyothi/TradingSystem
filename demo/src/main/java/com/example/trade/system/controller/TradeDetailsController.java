package com.example.trade.system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.trade.system.model.TradeInformation;
import com.example.trade.system.service.TradingService;

@RestController
public class TradeDetailsController {
	
	@Autowired
	private TradingService tradeService;
	
	@PostMapping(path = "/tradesystem", consumes ="application/json" )
	public ResponseEntity<String> saveOrUpdateTradeDetails(@RequestBody TradeInformation data){
		
		return tradeService.saveTradingInformation(data);
	}
	

}
