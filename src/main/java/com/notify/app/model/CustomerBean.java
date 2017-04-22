package com.notify.app.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;

import com.fasterxml.jackson.annotation.JsonRootName;

@Entity
@JsonRootName("Account")
public class CustomerBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer customerId;
	@Column(nullable=false,length=15)
	@NotNull
	private String customerName;
	@Column
	@Size(max=10)
	@NotNull
	private String password;
	@Column(unique=true,nullable=false)
	@Email
	@NotNull
	@Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\."
	        +"[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@"
	        +"(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?",
	             message="{invalid.email}")
	private String customerEmail;
	@Column
	private Boolean verifyEmail;
	@Column
	private String createdDate;
	@Column
	private String modifiedDate;
	@Column
	private Boolean active;
	@Column
//	@Pattern(regexp="^\\(?(\\d{3})\\)?[- ]?(\\d{3})[- ]?(\\d{4})$",
//     message="{invalid.phonenumber}")
	private String mobile;
	@Column
	private Boolean verifyMobile;
	
	public Integer getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getCustomerEmail() {
		return customerEmail;
	}
	public void setCustomerEmail(String customerEmail) {
		this.customerEmail = customerEmail;
	}
	public String getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}
	public String getModifiedDate() {
		return modifiedDate;
	}
	public void setModifiedDate(String modifiedDate) {
		this.modifiedDate = modifiedDate;
	}
	public Boolean getActive() {
		return active;
	}
	public void setActive(Boolean active) {
		this.active = active;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public Boolean getVerifyEmail() {
		return verifyEmail;
	}
	public void setVerifyEmail(Boolean verifyEmail) {
		this.verifyEmail = verifyEmail;
	}
	public Boolean getVerifyMobile() {
		return verifyMobile;
	}
	public void setVerifyMobile(Boolean verifyMobile) {
		this.verifyMobile = verifyMobile;
	}
	@Override
	public String toString() {
		return "CustomerBean [customerId=" + customerId + ", customerName="
				+ customerName + ", password=" + password + ", customerEmail="
				+ customerEmail + ", verifyEmail=" + verifyEmail
				+ ", createdDate=" + createdDate + ", modifiedDate="
				+ modifiedDate + ", active=" + active + ", mobile=" + mobile
				+ ", verifyMobile=" + verifyMobile + "]";
	}

}
