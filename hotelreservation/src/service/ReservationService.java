package service;

import model.Customer;
import model.IRoom;
import model.Reservation;

import java.util.*;

public class ReservationService {
    private static final ReservationService SINGLETON = new ReservationService();
    private final Map<String, IRoom> rooms = new HashMap<>();
    private final Map<String, Collection<Reservation>> reservationMap = new HashMap<>();
    private ReservationService(){}
    public static ReservationService getSingleton(){
        return SINGLETON;
    }
    public void addRoom(IRoom room) {
        rooms.put(room.getRoomNumber(),room);
    }
    public IRoom getARoom(String roomNumber) {
        return rooms.get(roomNumber);
    }
    public Reservation reserveARoom(Customer customer, IRoom room,Date checkInDate,Date checkOutDate){
        Reservation reservation = new Reservation(customer,room,checkInDate,checkOutDate);
        if (!reservationMap.containsKey(customer.getEmail())) {
            reservationMap.put(customer.getEmail(),new ArrayList<>());
        }
        reservationMap.get(customer.getEmail()).add(reservation);
        return reservation;
    }
    public Collection<IRoom> findRooms(Date checkInDate,Date checkOutDate){
        Collection<IRoom> allRooms = new ArrayList<IRoom>(rooms.values());
        Collection<IRoom> reservedRooms = reservedRooms(checkInDate,checkOutDate);
        allRooms.removeAll(reservedRooms);
        return allRooms;
    }

    Collection<IRoom> reservedRooms(Date checkIn,Date checkOut) {
        Collection<IRoom> reservedRooms = new ArrayList<>();
        for (Collection<Reservation> reservations : reservationMap.values()) {
            for (Reservation res : reservations) {
                if ((res.getCheckInDate().before(checkIn) && res.getCheckOutDate().before(checkIn))
                        || (res.getCheckInDate().after(checkOut) && res.getCheckOutDate().after(checkOut))) {
                    // available room
                } else {
                    //not available room
                    reservedRooms.add(res.getRoom());
                }
            }
        }
        return reservedRooms;
    }
    public Collection<Reservation>getCustomersReservation(String customerEmail){
        return reservationMap.get(customerEmail);
    }
    public void printAllReservation(){
        for (Collection<Reservation> reservations : reservationMap.values()) {
            for (Iterator iterator = reservations.iterator(); iterator.hasNext();) {
                Reservation res  = (Reservation) iterator.next();
                System.out.println(res);
            }
        }
    }

    public Collection<IRoom> getAllRooms() {
        return  rooms.values();
    }
}

