import java.sql.*;
import java.util.*;


class DatabaseManager {
    
    private Connection connection;

    public DatabaseManager() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/hotel_booking", "root", "Gajendran@04");
            System.out.println("Database connection established successfully.");
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void addRoom(Room room) {
        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO Rooms (room_number, room_type, is_available) VALUES (?, ?, ?)");
            statement.setString(1, room.getRoomNumber());
            statement.setString(2, room.getRoomType());
            statement.setBoolean(3, room.isAvailable());

            statement.executeUpdate();
            System.out.println("Room added to the database: " + room.getRoomNumber());

            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateRoom(Room room) {
        try {
            PreparedStatement statement = connection.prepareStatement("UPDATE Rooms SET room_number = ?, room_type = ?, is_available = ? WHERE room_id = ?");
            statement.setString(1, room.getRoomNumber());
            statement.setString(2, room.getRoomType());
            statement.setBoolean(3, room.isAvailable());
            statement.setInt(4, room.getRoomId());

            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Room updated in the database: " + room.getRoomNumber());
            } else {
                System.out.println("Room not found with ID: " + room.getRoomId());
            }

            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteRoom(int roomId) {
        try {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM Rooms WHERE room_id = ?");
            statement.setInt(1, roomId);

            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Room deleted from the database: Room ID - " + roomId);
            } else {
                System.out.println("Room not found with ID: " + roomId);
            }

            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Room> listRooms() {
        List<Room> rooms = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM Rooms");

            while (resultSet.next()) {
                int roomId = resultSet.getInt("room_id");
                String roomNumber = resultSet.getString("room_number");
                String roomType = resultSet.getString("room_type");
                boolean isAvailable = resultSet.getBoolean("is_available");

                Room room = new Room(roomId, roomNumber, roomType, isAvailable);
                rooms.add(room);
            }

            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rooms;
    }

    public void bookRoom(Booking booking) {
        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO Bookings (room_id, guest_id, check_in_date, check_out_date) VALUES (?, ?, ?, ?)");
            statement.setInt(1, booking.getRoomId());
            statement.setInt(2, booking.getGuestId());
            statement.setDate(3, new java.sql.Date(booking.getCheckInDate().getTime()));
            statement.setDate(4, new java.sql.Date(booking.getCheckOutDate().getTime()));

            statement.executeUpdate();
            System.out.println("Room booked: Room ID - " + booking.getRoomId() + ", Check-in Date - " + booking.getCheckInDate() + ", Check-out Date - " + booking.getCheckOutDate());

            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void checkIn(int roomId) {
        try {
            PreparedStatement statement = connection.prepareStatement("UPDATE Rooms SET is_available = false WHERE room_id = ?");
            statement.setInt(1, roomId);

            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Guest checked in to room: Room ID - " + roomId);
            } else {
                System.out.println("Room not found with ID: " + roomId);
            }

            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void checkOut(int roomId) {
        try {
            PreparedStatement statement = connection.prepareStatement("UPDATE Rooms SET is_available = true WHERE room_id = ?");
            statement.setInt(1, roomId);

            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Guest checked out from room: Room ID - " + roomId);
            } else {
                System.out.println("Room not found with ID: " + roomId);
            }

            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}