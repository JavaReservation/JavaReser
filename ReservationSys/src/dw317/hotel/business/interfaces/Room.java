package dw317.hotel.business.interfaces;

import java.io.Serializable;

import dw317.hotel.business.RoomType;

/**
 * @author RoanWC
 *
 */
public interface Room extends Comparable<Room>, Serializable {

	RoomType getRoomType();

	int getRoomNumber();

	int getFloor();

	int getNumber();

}
