package com.notify.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.notify.app.model.NotificationBean;
import com.notify.app.repo.NotificationRep;

@RestController
public class NotificationRestContrller {

	@Autowired
	private NotificationRep notificationRep;

	@GetMapping(value = "/notifications/getNotifications")
	public List<NotificationBean> getNotifications() {
		return notificationRep.findByUsername(getLoggedInUserName());
	}

	@PostMapping(value = "/notifications/saveNotification")
	public NotificationBean saveNotification(@RequestBody NotificationBean notificationBean) {
		return notificationRep.save(notificationBean);
	}

	@PostMapping(value = "/notifications/deleteNotification")
	public void deleteNotification(@RequestBody NotificationBean notificationBean) {
		notificationRep.delete(notificationBean);
	}

	public final String getLoggedInUserName() {
		org.springframework.security.core.userdetails.User user = (org.springframework.security.core.userdetails.User) SecurityContextHolder
				.getContext().getAuthentication().getPrincipal();
		final String username = user.getUsername();
		return username;

	}

}
