package com.example.trade.system.util;

import java.util.Date;

public class TradingUtility {

	public static boolean validateMaturityDate(Date maturityDate) {
		
		Date currentDate = new Date();
	
		return maturityDate.before(currentDate);
		
	}
}
