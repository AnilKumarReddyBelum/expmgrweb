package com.notify.app.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.notify.app.model.CustomerBean;
@Repository
public interface CustomerRep extends JpaRepository<CustomerBean, Integer>{

}
