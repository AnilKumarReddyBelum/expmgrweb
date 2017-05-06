package com.notify.app.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.notify.app.model.OrderBean;

@Repository
public interface OrderRep extends JpaRepository<OrderBean, Integer> {

	List<OrderBean> findByUsername(final String username);

}
