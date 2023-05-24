import api.HotelResource;
import model.Customer;
import model.IRoom;
import model.Reservation;
import service.CustomerService;
import service.ReservationService;

import java.sql.SQLOutput;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Scanner;

public class MainMenu {
    public static final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM-dd-yyyy");
    public static void main(String[] args) {
        showMenu();
    }
    public static void showMenu() {
        Scanner scanner = new Scanner(System.in);

        String selectedOption = null;


            do {
                System.out.println("Welcome to the Hotel Reservation Application!\n" +
                        "Please select a number for the menu option\n" +
                        "*************************************\n" +
                        "1. Find and reserve a room\n" +
                        "2. See my reservations\n" +
                        "3. Create an Account\n" +
                        "4. Admin\n" +
                        "5. Exit\n" + "" +
                        "*************************************");
                selectedOption = scanner.nextLine();
                switch (selectedOption) {
                    case "1":
                        findAndReserveARoom();
                        break;
                    case "2":
                        seeMyReservations();
                        break;
                    case "3":
                        createAccount();
                        break;
                    case "4":
                        AdminMenu.showAdminMenu();
                        break;
                    case "5":
                        System.out.println("Exiting program...");
                        break;
                    default:
                        System.out.println("Invalid action");
                }
            } while (!selectedOption.equals("5"));
    }

    private static Date addDays(Date date, int days)
    {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, days); //minus number would decrement the days
        return cal.getTime();
    }
    private static void findAndReserveARoom() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Check-In Date (MM/dd/yyyy): ");
        Date checkIn = getInputDate(scanner);
        checkIn.setHours(13);
        System.out.println("Check-Out Date (MM/dd/yyyy): ");
        Date checkOut = getInputDate(scanner);
        checkOut.setHours(10);

        if (checkIn != null && checkOut != null){
            Collection <IRoom> availableRooms = HotelResource.getSingleton().findARoom(checkIn,checkOut);
            if (availableRooms.isEmpty()) {
                checkIn=addDays(checkIn,7);
                checkOut=addDays(checkOut,7);

                System.out.println("Searching for alternative rooms between "+ simpleDateFormat.format(checkIn) + " and "+ simpleDateFormat.format(checkOut) +": ");
                availableRooms = HotelResource.getSingleton().findARoom(checkIn,checkOut);
            }
            if (availableRooms.isEmpty()){
                System.out.println("No available rooms found,please try with differnt dates: ");
            }else{
                printRooms(availableRooms);
                reserveARoom(scanner, checkIn,checkOut,availableRooms);
            }

        }
    }

    private static void printRooms(final Collection<IRoom> rooms) {
        if(rooms.isEmpty()) {
            System.out.println("No available rooms found,please try with differnt dates: ");
        }else {
            rooms.forEach(System.out::println);
        }
    }

    private static void reserveARoom(Scanner scanner,Date checkInDate,Date checkOutDate,Collection<IRoom>rooms){
        System.out.println("Would you like to book? \n" +
                        "Yes:y \n" +
                        "No:n");
        String answer = scanner.nextLine();
        if (answer.equals("y")){
            System.out.println("Please enter a room number: ");
            String roomNumber = scanner.nextLine();
            if (rooms.stream().anyMatch(room->room.getRoomNumber().equals(roomNumber)));
            IRoom room = HotelResource.getSingleton().getRoom(roomNumber);
            System.out.println("Please enter your email: ");
            String customerEmail = scanner.nextLine();
            Customer customer = CustomerService.SINGLETON.getCustomer(customerEmail);
            if(customer != null&& room != null){
                Reservation reservation = ReservationService.getSingleton().reserveARoom(customer, room, checkInDate, checkOutDate);
                if (reservation != null){
                    System.out.println("Reservation completed!");
                }
            }else{
                System.out.println("Customer or room could not found!");

            }
        }
    }

    private static Date getInputDate(Scanner scanner) {
        String dateFormat = "MM/dd/yyyy";
        try {
            return new SimpleDateFormat(dateFormat).parse(scanner.nextLine());
        }catch(ParseException ex){
            System.out.println("Invalid Date!");
            return null;
        }
    }

    private static void seeMyReservations() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter your email adress:");
        String email = scanner.nextLine();
        printReservations(HotelResource.getSingleton().getCustomersReservations(email));
    }

    private static void printReservations(Collection<Reservation>reservations) {
        if (reservations == null || reservations.isEmpty()) {
            System.out.println("No reservations found.");
        } else {
            reservations.forEach(reservation -> System.out.println("\n" + reservation));
        }
    }

    private static void createAccount() {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter Email: ");
            String email= scanner.nextLine();
            System.out.println("First Name: ");
            String firstName = scanner.nextLine();
            System.out.println("Last Name: ");
            String lastName= scanner.nextLine();
            try{
                HotelResource.getSingleton().createACustomer(email,firstName,lastName);
                System.out.println("Account created");
            }
            catch (IllegalArgumentException ex){
                throw new IllegalArgumentException("Please check your information.");
            }
        }

    }