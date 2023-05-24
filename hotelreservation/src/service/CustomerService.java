package service;

import model.Customer;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

public class CustomerService {
    public static final CustomerService SINGLETON = new CustomerService();
    private  final List <Customer> customers = new LinkedList<Customer>();
    private CustomerService() {}
    public static CustomerService getSingleton(){
        return SINGLETON;
    }
    public void addCustomer(String email, String firstName, String lastName) {
        customers.add(new Customer(firstName,lastName,email));
    }
    public Customer getCustomer(String email) {
        for(Customer customer: customers){
            if (email.equals(customer.getEmail())){
                return customer;
            }
        }
        return null;
    }
    public Collection<Customer> getAllCustomers(){
        return customers;
    }

}
