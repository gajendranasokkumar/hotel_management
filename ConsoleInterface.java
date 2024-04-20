import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.*;


class ConsoleInterface {
    private HotelManager hotelManager;
    private Scanner scanner;

    public ConsoleInterface(HotelManager hotelManager) {
        this.hotelManager = hotelManager;
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        boolean running = true;

        while (running) {
            System.out.println("Hotel Booking System");
            System.out.println("1. Add Room");
            System.out.println("2. Update Room");
            System.out.println("3. Delete Room");
            System.out.println("4. List Rooms");
            System.out.println("5. Book Room");
            System.out.println("6. Check-in");
            System.out.println("7. Check-out");
            System.out.println("8. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    addRoom();
                    break;
                case 2:
                    updateRoom();
                    break;
                case 3:
                    deleteRoom();
                    break;
                case 4:
                    listRooms();
                    break;
                case 5:
                    bookRoom();
                    break;
                case 6:
                    checkIn();
                    break;
                case 7:
                    checkOut();
                    break;
                case 8:
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }

        scanner.close();
    }

    private void addRoom() {
        System.out.print("Enter room number: ");
        String roomNumber = scanner.next();
        System.out.print("Enter room type: ");
        String roomType = scanner.next();

        Room room = new Room(0, roomNumber, roomType, true);
        hotelManager.addRoom(room);
    }

    private void updateRoom() {
        System.out.print("Enter room ID to update: ");
        int roomId = scanner.nextInt();
        System.out.print("Enter new room number: ");
        String roomNumber = scanner.next();
        System.out.print("Enter new room type: ");
        String roomType = scanner.next();
        System.out.print("Is the room available? (true/false): ");
        boolean isAvailable = scanner.nextBoolean();

        Room room = new Room(roomId, roomNumber, roomType, isAvailable);
        hotelManager.updateRoom(room);
    }

    private void deleteRoom() {
        System.out.print("Enter room ID to delete: ");
        int roomId = scanner.nextInt();
        hotelManager.deleteRoom(roomId);
    }

    private void listRooms() {
        List<Room> rooms = hotelManager.listRooms();
        System.out.println("Rooms:");
        for (Room room : rooms) {
            String availability = room.isAvailable() ? "Available" : "Occupied";
            System.out.println("Room ID: " + room.getRoomId() + ", Room Number: " + room.getRoomNumber() + ", Room Type: " + room.getRoomType() + ", Availability: " + availability);
        }
    }

    private void bookRoom() {
        System.out.print("Enter room ID to book: ");
        int roomId = scanner.nextInt();
        System.out.print("Enter guest ID: ");
        int guestId = scanner.nextInt();
        System.out.print("Enter check-in date (yyyy-mm-dd): ");
        String checkInDateString = scanner.next();
        System.out.print("Enter check-out date (yyyy-mm-dd): ");
        String checkOutDateString = scanner.next();

        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date checkInDate = (Date) sdf.parse(checkInDateString);
            Date checkOutDate = (Date) sdf.parse(checkOutDateString);

            Booking booking = new Booking(0, roomId, guestId, checkInDate, checkOutDate);
            hotelManager.bookRoom(booking);
        } catch (Exception e) {
            System.out.println("Invalid date format. Please enter dates in yyyy-mm-dd format.");
        }
    }

    private void checkIn() {
        System.out.print("Enter room ID to check-in: ");
        int roomId = scanner.nextInt();
        hotelManager.checkIn(roomId);
    }

    private void checkOut() {
        System.out.print("Enter room ID to check-out: ");
        int roomId = scanner.nextInt();
        hotelManager.checkOut(roomId);
    }
}
