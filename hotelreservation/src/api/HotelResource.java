package api;

import model.Customer;
import model.IRoom;
import model.Reservation;
import service.CustomerService;
import service.ReservationService;

import java.util.Collection;
import java.util.Date;

public class HotelResource {
    private static final HotelResource SINGLETON = new HotelResource();
    public static HotelResource getSingleton(){
        return SINGLETON;
    }

    public Customer getCustomer(String email){
        return CustomerService.SINGLETON.getCustomer(email);
    }
    public void createACustomer(String email,String firstName,String lastName){
        CustomerService.SINGLETON.addCustomer(email,firstName,lastName);
    }
    public IRoom getRoom(String roomNumber){
        return ReservationService.getSingleton().getARoom(roomNumber);
    }
    public Reservation bookARoom(Customer customerEmail, IRoom room, Date checkInDate,Date checkOutDate){
        return ReservationService.getSingleton().reserveARoom(customerEmail,room,checkInDate,checkOutDate);
    }
    public Collection <Reservation> getCustomersReservations(String customerEmail){
        return ReservationService.getSingleton().getCustomersReservation(customerEmail);
    }
    public Collection<IRoom> findARoom (Date checkIn,Date checkOut){
        return ReservationService.getSingleton().findRooms(checkIn,checkOut);
    }






}
