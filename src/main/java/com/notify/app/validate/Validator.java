package com.notify.app.validate;

import com.notify.app.model.CustomerBean;

public class Validator {
	
	
	public int validCustomer(CustomerBean customerBean){
		
		if (customerBean.getCustomerEmail() != null
				&& customerBean.getCustomerEmail() != ""
				&& customerBean.getCustomerName() != null
				&& customerBean.getCustomerName() != ""
				&& customerBean.getMobile() != null
				&& customerBean.getMobile() != ""
				&& customerBean.getPassword() != null
				&& customerBean.getPassword() != ""
				&& customerBean.getActive() != null){
			return 102;
		}else{
		
			return 103;
		}
	}

}
