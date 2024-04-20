import java.util.List;

class HotelManager {
    private DatabaseManager dbManager;

    public HotelManager(DatabaseManager dbManager) {
        this.dbManager = dbManager;
    }

    public void addRoom(Room room) {
        dbManager.addRoom(room);
    }

    public void updateRoom(Room room) {
        dbManager.updateRoom(room);
    }

    public void deleteRoom(int roomId) {
        dbManager.deleteRoom(roomId);
    }

    public List<Room> listRooms() {
        return dbManager.listRooms();
    }

    public void bookRoom(Booking booking) {
        dbManager.bookRoom(booking);
    }

    public void checkIn(int roomId) {
        dbManager.checkIn(roomId);
    }

    public void checkOut(int roomId) {
        dbManager.checkOut(roomId);
    }

}