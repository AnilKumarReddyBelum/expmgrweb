package com.notify.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.notify.app.model.OrderBean;
import com.notify.app.model.User;
import com.notify.app.repo.OrderRep;
import com.notify.app.repo.UserRepository;

@RestController
public class OrderRestController {

	@Autowired
	private OrderRep orderRep;

	@Autowired
	private UserRepository userRepository;

	@GetMapping(value = "/order/getAllOrders")
	public List<OrderBean> getAllOrders() {
		return orderRep.findByUsername(getLoggedInUserName());
	}

	@PostMapping(value = "/order/createOrder")
	public OrderBean saveMenu(@RequestBody OrderBean orderBean) {
		final User user = userRepository.findByUsername(getLoggedInUserName());
		orderBean.setRestaurantId(user.getRestaurantId());
		orderBean.setRestaurantName(user.getProfile().getRestaurantName());
		return orderRep.save(orderBean);
	}

	@PostMapping(value = "/order/deleteOrder")
	public void deleteMenu(@RequestBody OrderBean orderBean) {
		orderRep.delete(orderBean);
	}

	public final String getLoggedInUserName() {
		org.springframework.security.core.userdetails.User user = (org.springframework.security.core.userdetails.User) SecurityContextHolder
				.getContext().getAuthentication().getPrincipal();
		final String username = user.getUsername();
		return username;

	}

}
