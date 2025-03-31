package com.webflux.reactiveSpring.handler;

import com.webflux.reactiveSpring.dao.CustomerDao;
import com.webflux.reactiveSpring.dto.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CustomerStreamHandler {

    @Autowired
    private CustomerDao customerDao;

    public Mono<ServerResponse> getCustomers(ServerRequest request) {
        Flux<Customer> customerLoadData = customerDao.getCustomersStream();
        // the content type is set so that data is sent in event/stream wise, and not like a normal json object
        // this enables a continuous stream of data to the client as the data becomes available, rather than sending all data at once like a normal JSON response.
        return ServerResponse.ok().contentType(MediaType.TEXT_EVENT_STREAM).body(customerLoadData, Customer.class);
    }
}
