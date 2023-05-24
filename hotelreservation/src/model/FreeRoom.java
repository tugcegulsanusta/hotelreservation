package model;

public class FreeRoom extends Room{
    public FreeRoom(String roomNumber,RoomType roomType){
        super(roomNumber,0.0, roomType);
    }
    public String toString(){
        return "Free room: "+ super.toString();
    }
}
