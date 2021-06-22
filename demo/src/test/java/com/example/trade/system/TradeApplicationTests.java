package com.example.trade.system;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JsonParseException;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.example.trade.system.model.TradeInformation;
import com.example.trade.system.model.TradingKey;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.MOCK, classes = TradingApplication.class)
@AutoConfigureMockMvc	
@WebAppConfiguration
public class TradeApplicationTests {

	 @Autowired
	protected MockMvc mvc;

	@Autowired
	WebApplicationContext webApplicationContext;
	
		


	protected void setUp() {
		mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}

	protected String mapToJson(Object obj) throws JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.writeValueAsString(obj);
	}

	protected <T> T mapFromJson(String json, Class<T> clazz)
			throws JsonParseException, JsonMappingException, IOException {

		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.readValue(json, clazz);
	}

	@Test
	public void saveOrUpdateTradeDetailsTest() throws Exception {
		String uri = "/tradesystem";
		TradeInformation tradeData = new TradeInformation();
		TradingKey key = new TradingKey();
		key.setTradingID("T5");
		key.setVersion(1);
		
		tradeData.setTradingkey(key);
		tradeData.setBookID("B11");
		tradeData.setCounterPartyID("C111");
		tradeData.setCreatedDate(new Date());
		tradeData.setMaturityDate(new Date());
		tradeData.setExpired("N");
		
		
		String inputJson = mapToJson(tradeData);
		MvcResult mvcResult = mvc.perform(
				MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson))
				.andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		assertEquals(content, "Trade Saved or updated Successfully ");
	}

}
