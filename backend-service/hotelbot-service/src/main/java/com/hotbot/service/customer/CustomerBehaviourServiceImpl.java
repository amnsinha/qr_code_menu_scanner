package com.hotbot.service.customer;

import com.hotbot.controller.customer.CustomerBehaviour;
import com.hotbot.repo.customer.CustomerBehaviourRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CustomerBehaviourServiceImpl implements CustomerBehaviourService {


    @Autowired
    CustomerBehaviourRepository customerBehaviourRepository;

    @Override
    public CustomerBehaviour getCustomerServiceBehaviour(String token) {
        return customerBehaviourRepository.getCustomerServiceBehaviour(token);
    }
}
