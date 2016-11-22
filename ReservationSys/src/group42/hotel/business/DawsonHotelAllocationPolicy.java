package group42.hotel.business;

import java.time.LocalDate;
import java.util.List;
import dw317.hotel.business.RoomType;
import dw317.hotel.business.interfaces.Room;
import dw317.hotel.data.interfaces.ReservationDAO;

public class DawsonHotelAllocationPolicy {

	private static final long serialVersionUID = 42031768871L;
	private ReservationDAO resDAO;

	public DawsonHotelAllocationPolicy(ReservationDAO resDAO) {

		this.resDAO = resDAO;

	}

	/**
	 * 
	 * 
	 * @author Werner
	 * @param checkIn
	 * @param checkOut
	 * @param roomType
	 * @return
	 */
	public Room freeRoom(LocalDate checkIn, LocalDate checkOut, RoomType roomType) {

		List<Room> list = this.resDAO.getFreeRooms(checkIn, checkOut, roomType);

		// helper variables
		int count = 0, tempCount;
		Room temp;
		Room room = list.get(0);

		for (int i = 0; i < list.size() - 1; i++) {
			tempCount = 0;
			temp = list.get(i);
			for (int j = 0; j < list.size(); j++) {
				if (list.get(i).getFloor() == list.get(j).getFloor()) {
					tempCount++;
				}
			} // end of j loop
			if (tempCount > count) {
				room = temp;
				count = tempCount;
			}
		} // end of i loop

		return room;
	}
}
