package com.webflux.reactiveSpring.handler;

import com.webflux.reactiveSpring.dao.CustomerDao;
import com.webflux.reactiveSpring.dto.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CustomerHandler {

    @Autowired
    private CustomerDao customerDao;

    // server request is for getting all meta data while server response is for mapping request meta data
    // receives a serverRequest and returns a Mono<ServerResponse>
    public Mono<ServerResponse> loadCustomers(ServerRequest request) {
        Flux<Customer> customerLoadData = customerDao.getCustomerList();
        return ServerResponse.ok().body(customerLoadData, Customer.class);
        // the first one is the object being returned while the second one is the class for whatever being returned
    }

}
