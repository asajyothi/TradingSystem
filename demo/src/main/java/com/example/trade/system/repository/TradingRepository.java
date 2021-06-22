package com.example.trade.system.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.trade.system.model.TradeInformation;
import com.example.trade.system.model.TradingKey;

@Repository
public interface TradingRepository extends CrudRepository<TradeInformation, TradingKey> {
	
	
	List<TradeInformation> findByTradingkeyTradingID(String tradingId);
	
	
	@Modifying
	@Query("update TradeInformation t set t.expired='Y' where t.maturityDate=sysdate")
	void updateExpiredTradeData();
	
	

	
}
