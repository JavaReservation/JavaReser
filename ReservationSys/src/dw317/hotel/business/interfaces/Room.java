package dw317.hotel.business.interfaces;

import java.io.Serializable;

/**
 * @author RoanWC
 *
 */
public interface Room extends Comparable<Room>, Serializable {

	group42.hotel.business.RoomType getRoomType();

	int getRoomNumber();

	int getFloor();

	int getNumber();

}
