
public class HotelBookingSystem {
    public static void main(String[] args) {
        DatabaseManager dbManager = new DatabaseManager();
        HotelManager hotelManager = new HotelManager(dbManager);
        ConsoleInterface console = new ConsoleInterface(hotelManager);

        console.start();
    }
}
