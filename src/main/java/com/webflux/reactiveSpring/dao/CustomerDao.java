package com.webflux.reactiveSpring.dao;

import com.webflux.reactiveSpring.dto.Customer;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.List;
import java.util.stream.IntStream;

@Component
public class CustomerDao {

    private static void sleepexecution(int i){
        try{
            Thread.sleep(1000);
        }catch(InterruptedException e){
            e.printStackTrace();
        }
    }

    // method will return a list of objects
    //list and IntStream are standard java imports that are used to create a list of customers and generate a stream of integers.
    public List<Customer> getCustomers()  {
        // return 50 customer objects
        return IntStream.rangeClosed(1, 50).peek(CustomerDao::sleepexecution).peek(i -> System.out.println("Processing Customer count " + i)).mapToObj(i -> new Customer(i, "Customer " + i)).toList();
    }
    // the method above returns a list of customer objects, this method will generate 50 customers with IDs and names.
    // intstream generates a stream of integers 1 to 50, which will be used to create customer objects.
    // peek is an intermediate ops on the stream that allows you to perform an action for each element of the stream without modifiying the stream.
    // here, it logs the message for each customer being processed where i is the current number in the stream.
    // mapToObj converts each integer i in the stream into a customer object.
    public Flux<Customer> getCustomersStream()  {
        // return 50 customer objects
        return Flux.range(1, 50).delayElements(Duration.ofSeconds(1)).doOnNext(i -> System.out.println("Processing Customer count Stream flow " + i)).map(i-> new Customer(i, "customer" +i));
    }


    public Flux<Customer> getCustomerList()  {
        // return 50 customer objects
        return Flux.range(1, 50).doOnNext(i -> System.out.println("Processing Customer count Stream flow " + i)).map(i-> new Customer(i, "customer" +i));
    }
}
