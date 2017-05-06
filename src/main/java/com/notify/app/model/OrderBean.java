package com.notify.app.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class OrderBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer orderId;//AUTO GENERATION
	@Column
	private String username;//AUTO POPULATION
	@Column
	private Integer tokenId;//NOT REQUIRED FROM UI-HTM
	@Column
	private Integer customerId;//NOT REQUIRED FROM UI-HTM
	@Column
	private String orderDetails;
	@Column
	private String createdDate;
	@Column
	private String modifiedDate;
	@Column
	private Long restaurantId;//AUTO POPULATION
	@Column
	private String restaurantName;//AUTO POPULATION
	@Column
	private String orderDate;//NOT REQUIRED FROM UI-HTM
	@Column
	private Boolean orderAccept;
	@Column
	private Boolean activeOrder;

	public String getRestaurantName() {
		return restaurantName;
	}

	public void setRestaurantName(String restaurantName) {
		this.restaurantName = restaurantName;
	}

	public void setRestaurantId(Long restaurantId) {
		this.restaurantId = restaurantId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public Integer getTokenId() {
		return tokenId;
	}

	public void setTokenId(Integer tokenId) {
		this.tokenId = tokenId;
	}

	public Integer getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}

	public String getOrderDetails() {
		return orderDetails;
	}

	public void setOrderDetails(String orderDetails) {
		this.orderDetails = orderDetails;
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

	public Boolean getActiveOrder() {
		return activeOrder;
	}

	public void setActiveOrder(Boolean activeOrder) {
		this.activeOrder = activeOrder;
	}

	public String getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}

	public Boolean getOrderAccept() {
		return orderAccept;
	}

	public void setOrderAccept(Boolean orderAccept) {
		this.orderAccept = orderAccept;
	}

}
