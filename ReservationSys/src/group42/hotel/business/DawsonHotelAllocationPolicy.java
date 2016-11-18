package group42.hotel.business;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import javax.sql.rowset.serial.SerialBlob;

import dw317.hotel.business.RoomType;
import dw317.hotel.business.interfaces.Room;
import dw317.hotel.data.interfaces.ReservationDAO;

public class DawsonHotelAllocationPolicy {

	private static final long serialVersionUID = 42031768871L;
	private ReservationDAO resDAO;

	public DawsonHotelAllocationPolicy(ReservationDAO resDAO) {

		this.resDAO = resDAO;

	}

	public Room freeRoom(LocalDate checkIn, LocalDate checkOut, RoomType roomType) {

		List<Room> availableRooms = this.resDAO.getFreeRooms(checkIn, checkOut, roomType);

		int count;
		int max = 0;
		Room roomNumber = null;

		for (int i = 0; i < availableRooms.size(); i++) {
			count = 0;
			for (int j = i; j < availableRooms.size(); j++) {
				if (availableRooms.get(i).getFloor() == availableRooms.get(j).getFloor()) {
					count++;
				}
			} // end of j loop

			if (max < count) {
				max = count;
				roomNumber = availableRooms.get(i);
			}
		} // end of i loop

		return roomNumber;
	}

}
