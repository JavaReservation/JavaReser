package group42.hotel.business;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
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

		List<Room> list = this.resDAO.getFreeRooms(checkIn, checkOut, roomType);
		// helper listArray
		List<Room> dubs = new ArrayList<Room>();
		// helper variables
		int count = 1, tempCount;
		Room temp;

		// assume the first index is the bes sutted
		Room room = list.get(0);

		for (int i = 0; i < list.size() - 1; i++) {
			tempCount = 0;
			temp = list.get(i);

			for (int j = 1; j < list.size(); j++) {
				if (list.get(i).getFloor() == list.get(j).getFloor()) {
					tempCount++;
				}
			} // end of j loop
			if (tempCount > count) {

				if (count == tempCount - 1) {
					dubs.add(temp);
					dubs.add(room);
				}

				room = temp;
				// System.out.println(room + " a " + temp);
				count = tempCount;
			}
		} // end of i loop

		Room result = room;
		Room tempResult;

		if (dubs.size() != 0) {
			result = dubs.get(0);
			for (int i = 0; i < dubs.size(); i++) {
				tempResult = dubs.get(i);
				if (result.getFloor() > tempResult.getFloor())
					room = dubs.get(i);

			}
		}

		return room;
	}
}
