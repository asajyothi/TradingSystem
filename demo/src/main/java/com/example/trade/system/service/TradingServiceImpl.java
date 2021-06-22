package com.example.trade.system.service;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.example.trade.system.common.Constants;
import com.example.trade.system.exception.BusinessException;
import com.example.trade.system.model.TradeInformation;
import com.example.trade.system.repository.TradingRepository;
import com.example.trade.system.util.TradingUtility;

@Service
public class TradingServiceImpl implements TradingService {

	@Autowired
	private TradingRepository tradeRepository;

	@Override
	public ResponseEntity<String> saveTradingInformation(TradeInformation tradeData) {

		try {

			if (tradeData == null || tradeData.getTradingkey().getTradingID() == null) {
				throw new BusinessException(Constants.INVALID_TRADE, Constants.INVALID_TRADE_CODE);

			} else {

				if (tradeData.getMaturityDate() != null) {

					boolean isValidTrade = TradingUtility.validateMaturityDate(tradeData.getMaturityDate());

					if (isValidTrade) {

						List<TradeInformation> tradeDetails = tradeRepository
								.findByTradingkeyTradingID(tradeData.getTradingkey().getTradingID());

						if (tradeDetails == null || tradeDetails.size() == 0) {

							tradeRepository.save(tradeData);

						} else {
							TradeInformation existingTradeVersion = tradeDetails.stream().filter(trade -> trade
									.getTradingkey().getVersion() == tradeData.getTradingkey().getVersion()).findAny()
									.orElse(null);
							if (existingTradeVersion != null) {
								tradeRepository.save(tradeData);
							} else {

								TradeInformation maxVersionTrade = Collections.max(tradeDetails,
										Comparator.comparing(t -> t.getTradingkey().getVersion()));

								if (tradeData.getTradingkey().getVersion() < maxVersionTrade.getTradingkey()
										.getVersion()) {
									throw new BusinessException(Constants.INVALID_TRADE, Constants.INVALID_TRADE_CODE);
								} else {
									tradeRepository.save(tradeData);

								}
							}
						}

					} else {
						throw new BusinessException(Constants.INVALID_MATURITY_DATE,
								Constants.INVALID_MATURITY_DATE_CODE);
					}
				} else {
					throw new BusinessException(Constants.INVALID_MATURITY_DATE, Constants.INVALID_MATURITY_DATE_CODE);
				}
			}

		} catch (BusinessException ex) {
			return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
		} catch (Exception ex) {
			return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
		}

		return new ResponseEntity<>(Constants.SUCCESS, HttpStatus.OK);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	@Scheduled(cron = "0 0 0 * * ?")
	public void updateExpiredTradeInformation() {

		tradeRepository.updateExpiredTradeData();

	}

}
