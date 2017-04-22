package com.notify.app.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.notify.app.model.CustomerBean;
@Repository
public interface DeploymentCustomerRep extends CrudRepository<CustomerBean, Integer>{

}
