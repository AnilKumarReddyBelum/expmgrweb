package com.notify.app.controller;

import java.util.List;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.notify.app.model.Expense;


import com.twilio.sdk.TwilioRestClient;
import com.twilio.sdk.TwilioRestException;
import com.twilio.sdk.resource.factory.SmsFactory;
import com.twilio.sdk.resource.instance.Account;

@RestController
public class ExpenseControler {

	private static Logger EXPENSE_CONTROLLER_LOGGER = Logger.getLogger(ExpenseControler.class);
	
	
	

	@Autowired
	private com.notify.app.repo.ExpenseRepository expenseRepository;
	
	
	public static final String ACCOUNT_SID = "AC766b60408feb07e32867e683fbb600e9";
	public static final String AUTH_TOKEN = "fd8667ebac199f98c9948e2aff971f72";

	@RequestMapping("/sendSMSoverTwilio")
	public String sendSMSoverTwilio() {
		String messageSentStaus = null;
		TwilioRestClient twilioRestClient = new TwilioRestClient(ACCOUNT_SID, AUTH_TOKEN);
		Account account = twilioRestClient.getAccount();
		SmsFactory factory = account.getSmsFactory();
		Map<String, String> messageMap = new HashMap<String, String>();
		messageMap.put("To", "+918884631730");
		messageMap.put("From", "+919502106030");
		messageMap.put("Body", "Hello! This is Anil from Twilio & Thanks Twilio!");
		try {
			factory.create(messageMap);
			messageSentStaus = "Success!";
		} catch (TwilioRestException e) {
			messageSentStaus = e.getErrorMessage();
		}

		return messageSentStaus;

	}

	
	@RequestMapping("/helloCache/{name}")
	@Cacheable(value = { "expmgrwebcache" },key = "#p0")
	public String helloCache(@PathVariable final String name) {
		EXPENSE_CONTROLLER_LOGGER.info("Enter into helloCache : " +name);
		EXPENSE_CONTROLLER_LOGGER.info("Exit From helloCache : " + name);
		return "Hello Mr . " + name;
	}

	@RequestMapping("/loadExpTemplate")
	public Expense loadExpTemplate() {
		EXPENSE_CONTROLLER_LOGGER.info("Enter into loadExpTemplate");
		Expense e = new Expense(getLoggedInUserName());
		EXPENSE_CONTROLLER_LOGGER.info("Exit from loadExpTemplate by user :" + e.getUsername());
		return e;
	}

	@RequestMapping(value = "/addNewExpense", method = RequestMethod.PUT)
	Expense addNewExpense(@RequestBody Expense expense) {
		return expenseRepository.save(expense);
	}

	@RequestMapping("/expenses")
	List<Expense> expenses() {
		return expenseRepository.findByUsername(getLoggedInUserName());
	}

	@Cacheable(value = { "expmgrwebcache" })
	public final String getLoggedInUserName() {
		EXPENSE_CONTROLLER_LOGGER.info("Enter into getLoggedInUserName");
		org.springframework.security.core.userdetails.User user = (org.springframework.security.core.userdetails.User) SecurityContextHolder
				.getContext().getAuthentication().getPrincipal();
		final String username = user.getUsername();
		EXPENSE_CONTROLLER_LOGGER.info("Exit from getLoggedInUserName by user :" + username);
		return username;
	}

}
