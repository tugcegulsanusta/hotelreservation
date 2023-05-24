package api;

import model.Customer;
import model.IRoom;

import service.CustomerService;
import service.ReservationService;

import java.util.Collection;

import java.util.List;

public class AdminResource {
    private static final AdminResource SINGLETON = new AdminResource();
    public Customer getCustomer(String email){
        return CustomerService.SINGLETON.getCustomer(email);
    }
    public static AdminResource getSingleton(){
        return SINGLETON;
    }
    public void addRoom(List<IRoom> rooms){
        for (IRoom room:rooms) {
            ReservationService.getSingleton().addRoom(room);
        }
    }
    public Collection<IRoom> getAllRooms(){
        return ReservationService.getSingleton().getAllRooms();
    }
    public Collection<Customer> getAllCustomer(){
        return CustomerService.SINGLETON.getAllCustomers();
    }
    public void displayAllReservations(){
         ReservationService.getSingleton().printAllReservation();
    }


}
