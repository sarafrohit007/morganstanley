package com.example.morganstanley.controller;

import javax.validation.Valid;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.morganstanley.dto.request.CurrencyConversionRequest;
import com.example.morganstanley.service.MorganStanleyService;

@Controller
@RequestMapping("/api")
public class WebController {
	
	@Autowired
	MorganStanleyService morganStanleyService;
	
	@RequestMapping(method = RequestMethod.GET)
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseEntity<String> verifyRESTService() {
		//logger.info("Inside verifyRESTService... of APIEkoPayServlet..");
		String response = "You have reached Eko's Distributor GST Services.";
		return ResponseEntity.ok().body(response);
	}
	
	@RequestMapping(method = RequestMethod.POST,value="/getcurrencyquantity")
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseEntity<String> getConversionRate(@Valid @RequestBody CurrencyConversionRequest currencyRequest) {
		//logger.info("Inside verifyRESTService... of APIEkoPayServlet..");
		//int quantityy = morganStanleyService.getChangedCurrencyAmount(currencyRequest);
		String message = morganStanleyService.getChangedCurrencyAmount(currencyRequest);
		return ResponseEntity.ok().body(message);
	}

}
