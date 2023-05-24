import api.AdminResource;
import model.Customer;
import model.IRoom;
import model.Room;
import model.RoomType;

import java.util.Collection;
import java.util.Collections;
import java.util.Scanner;

public class AdminMenu {
    public static void main(String[]args){
        showAdminMenu();
    }
    public static void showAdminMenu() {
        Scanner scanner = new Scanner(System.in);

        String selectedOption = null;

        do {
            System.out.println("Welcome to the Admin Menu!\n" +
                    "Please select a number for the menu option\n" +
                    "*************************************\n" +
                    "1. See all Customers\n" +
                    "2. See all Rooms\n" +
                    "3. See all Reservations\n" +
                    "4. Add a Room\n" +
                    "5. Back to Main Menu\n" + "" +
                    "*************************************");
            selectedOption = scanner.nextLine();
            switch (selectedOption) {
                case "1":
                    seeAllCustomer();
                    break;
                case "2":
                    seeAllRooms();
                    break;
                case "3":
                    seeAllReservations();
                    break;
                case "4":
                    addARoom();
                    break;
                case "5":
                    MainMenu.showMenu();
                    break;
                default:
                    System.out.println("Invalid action");
            }
        }while (!selectedOption.equals("5"));

    }
    public static void seeAllCustomer(){
        Collection<Customer> customers = AdminResource.getSingleton().getAllCustomer();
        if (customers.isEmpty()){
            System.out.println("Empty list!");
        }else {
            AdminResource.getSingleton().getAllCustomer().forEach(System.out::println);
        }
    }
    public static void seeAllRooms(){
        Collection<IRoom>rooms =AdminResource.getSingleton().getAllRooms();
        if (rooms.isEmpty()){
            System.out.println("Empty list!");
        }else{
            AdminResource.getSingleton().getAllRooms().forEach(System.out::println);
        }
    }
    public static void seeAllReservations(){
        AdminResource.getSingleton().displayAllReservations();
    }
    public static void addARoom(){
        Scanner scanner =new Scanner(System.in);
        System.out.println("Please enter a room number: ");
        String roomNumber = scanner.nextLine();

        System.out.println("Please enter price per night: ");
        Double price;
        price = scanner.nextDouble();
        scanner.nextLine();
        System.out.println("Enter roomtype: 1 (for single bed)\n" +
                "roomtype: 2 (for double bed): ");
        String roomTypeStr= scanner.nextLine();
        RoomType roomType = null;
        if (roomTypeStr.equals("1")){
           roomType= RoomType.SINGLE;
        } else if (roomTypeStr.equals("2")) {
            roomType= RoomType.DOUBLE;
        }
        Room room = new Room(roomNumber,price, roomType);
        AdminResource.getSingleton().addRoom(Collections.singletonList(room));
        System.out.println("Room added successfully");
    }


}