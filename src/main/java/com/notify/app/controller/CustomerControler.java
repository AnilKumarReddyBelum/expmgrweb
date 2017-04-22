package com.notify.app.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.notify.app.exception.CustomException;
import com.notify.app.exception.ExceptionThrower;
import com.notify.app.model.CustomerBean;
import com.notify.app.repo.CustomerRep;

/**
 *
 * @author charan kandula
 * @version 1.0
 * 
 * 
 */
@RestController
@RequestMapping("/customer/v1")
public class CustomerControler {

	@Autowired
	CustomerRep customerRep;
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@SuppressWarnings("rawtypes")
	@PostMapping("/saveCustomer")
	ResponseEntity saveCustomer(
			@RequestBody CustomerBean customerBean) {
				
	      logger.info("staring saveCustomer");
			if (customerBean.getCustomerEmail() != null
					&& customerBean.getCustomerEmail() != ""
					&& customerBean.getCustomerName() != null
					&& customerBean.getCustomerName() != ""
					&& customerBean.getMobile() != null
					&& customerBean.getMobile() != ""
					&& customerBean.getPassword() != null
					&& customerBean.getPassword() != ""
					&& customerBean.getActive() != null) {
				customerRep.save(customerBean);
		 logger.info("ending saveCustomer");
				return new ResponseEntity<CustomerBean>(customerBean,
						HttpStatus.OK);
		} else {
			String errorMsg = "{\"code\": 6328, \"message\": \"An error occurred!\"}";
			
		//	ResponseEntity<CustomerBean> responseEntity = new ResponseEntity<CustomerBean>(customerBean,HttpStatus.PRECONDITION_REQUIRED);
//				return new ResponseEntity(customerBean,
//					HttpStatus.PRECONDITION_REQUIRED);
			
				return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMsg);
		}
	}

	@GetMapping("/getAllCustomer")
	ResponseEntity<List<CustomerBean>> getAllCustomer(
			@RequestHeader("userAgent") String userAgent) {

		if (userAgent != null && !userAgent.isEmpty())
			return new ResponseEntity<List<CustomerBean>>(
					HttpStatus.UNAUTHORIZED);
		return new ResponseEntity<List<CustomerBean>>(customerRep.findAll(),
				HttpStatus.OK);
		// return null;
	}
	
	@GetMapping("/error")
	 ResponseEntity<CustomerBean> errorCustomer() {
		return new ResponseEntity("customerId is mandatry..",
				HttpStatus.UNAUTHORIZED);
		//return new ResponseEntity<List<CustomerBean>>(customerRep.findAll(),HttpStatus.OK);
	}
	
	@GetMapping("/getCustomer/{CustomerId}")
	ResponseEntity<CustomerBean> getCustomer(
			@PathVariable("CustomerId") Integer customerId) {
		logger.info("Started getCustomer");
		if (customerId != null && customerId != 0) {
		logger.info("Ended getCustomer");
			return new ResponseEntity<CustomerBean>(
					(CustomerBean) customerRep.findOne(customerId),
					HttpStatus.OK);
		} else {
			return new ResponseEntity("customerId is mandatry..",
					HttpStatus.PRECONDITION_REQUIRED);
		}
	}

	@GetMapping("/deleteCustomer/{CustomerId}")
	boolean delete(@PathVariable("CustomerId") Integer CustomerId) {
		logger.info("Started delete");
		customerRep.delete(CustomerId);
		logger.info("Ended delete");
		return customerRep.exists(CustomerId);
	}

	@GetMapping("/getPageCustomer/{start}/{size}")
	ResponseEntity<List<CustomerBean>> getPageCustomer(
			@PathVariable("start") Integer start,
			@PathVariable("size") Integer size) {
		logger.info("Started getPageCustomer");

		if (start != null && size != null && size != 0) {
			Iterable<CustomerBean> bean = customerRep.findAll(new PageRequest(
					start, size));
			ArrayList<CustomerBean> customerList = new ArrayList<CustomerBean>();
			if (bean != null) {
				for (CustomerBean customerBean : bean) {
					customerList.add(customerBean);
				}
		logger.info("Ended getPageCustomer");
				return new ResponseEntity<List<CustomerBean>>(customerList,
						HttpStatus.OK);
			} else {
				return new ResponseEntity<List<CustomerBean>>(customerList,
						HttpStatus.OK);
			}
		} else {
			return new ResponseEntity("customerId is mandatry..",
					HttpStatus.PRECONDITION_REQUIRED);
		}
	}
	
	@GetMapping("/getPageCustomer/{start}/{size}/{field}/{dir}")
	ResponseEntity<List<CustomerBean>> getSortPageCustomer(
			@PathVariable("start") Integer start,
			@PathVariable("size") Integer size,
			@PathVariable("dir") String direction,
			@PathVariable("field") String field) throws CustomException {
		logger.info("Started getPageCustomer");

		if (start != null && size != null && size != 0 && !direction.isEmpty() && direction != null && !field.isEmpty() && field != null) {
			Direction sort = null;
			if(direction.equalsIgnoreCase("ASC"))
				 sort = Sort.Direction.ASC;
			else if(direction.equalsIgnoreCase("DESC")){
				 sort = Sort.Direction.DESC;
			    }
			Iterable<CustomerBean> bean = customerRep.findAll(new PageRequest(
					start, size,sort,field));
			ArrayList<CustomerBean> customerList = new ArrayList<CustomerBean>();
			if (bean != null) {
				for (CustomerBean customerBean : bean) {
					customerList.add(customerBean);
				}
		logger.info("Ended getPageCustomer");
				return new ResponseEntity<List<CustomerBean>>(customerList,
						HttpStatus.OK);
			} else {
				return new ResponseEntity<List<CustomerBean>>(customerList,
						HttpStatus.OK);
			}
		} else {
			  ExceptionThrower eT= new ExceptionThrower();
			  //eT.throwGeneralException();
			  eT.throwCustomException();
			  //eT.throwNullPointerException();
			return new ResponseEntity("customerId is mandatry..",
					HttpStatus.PRECONDITION_REQUIRED);
		}
	}
}
