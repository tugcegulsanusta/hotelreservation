package model;

import java.util.Objects;

public class Room implements IRoom {

    private String roomNumber;
    private Double price;
    private RoomType roomType;


    public Room(String roomNumber,Double price,RoomType roomtype){
        this.roomNumber=roomNumber;
        this.price=price;
        this.roomType=roomtype;
    }

    @Override
    public String getRoomNumber() {
        return roomNumber;
    }

    @Override
    public Double getRoomPrice() {
        return price;
    }

    @Override
    public RoomType getRoomType() {
        return roomType;
    }

    @Override
    public boolean isFree() {
        return false;
    }
    @Override
    public String toString(){
        return "Roomnumber: "+ roomNumber+ " Price: " + price +" Room Type: "+ roomType;
    }
    @Override
    public boolean equals(Object obj){
        if(obj == this){
            return true;
        }if(!(obj instanceof Room)) {
            return false;
        }final Room room = (Room) obj;
        return Objects.equals(this.roomNumber,room.roomNumber);
    }
    @Override
    public int hashCode(){
        return Objects.hash(roomNumber);
    }
}
