package group42.hotel.business;

import dw317.hotel.business.RoomType;
import dw317.hotel.business.interfaces.Room;

/**
 * This class implements Room
 * 
 * @author Werner,Aidan, Keylen
 */
public class DawsonRoom implements Room {

	private int roomNumber;
	private RoomType roomType;
	private static final long serialVersionUID = 42031768871L;

	public DawsonRoom(int roomNumber, RoomType type) {

		try {
			this.roomNumber = validRoom(roomNumber);
			this.roomType = type;
		} catch (Exception e) {

			throw new IllegalArgumentException(e.getMessage());
		}
	}

	/**
	 * This method checks to see if the int roomNumber is a valid room number
	 * 
	 * @throws IllegalArgumentException
	 *             if the floor does not exist
	 * @throws IllegalArgumentException
	 *             if the floor does not have that specific room number
	 * @throws IllegalArgumentException
	 *             if the floor does not have that specific room number
	 * @param roomNumber
	 * @return roomNumber
	 */
	public int validRoom(int roomNumber) {
		if (roomNumber / 100 != 1 && roomNumber / 100 != 2 && roomNumber / 100 != 3 && roomNumber / 100 != 4
				&& roomNumber / 100 != 5 && roomNumber / 100 != 6 && roomNumber / 100 != 7 && roomNumber / 100 != 8) {
			throw new IllegalArgumentException("That floor does not excist ");

		}

		if (roomNumber / 100 == 1 && roomNumber / 100 == 2 && roomNumber / 100 == 3 && roomNumber / 100 == 4
				&& roomNumber / 100 == 5) {

			if (roomNumber % 100 != 1 && roomNumber % 100 != 2 && roomNumber % 100 != 3 && roomNumber % 100 != 4
					&& roomNumber % 100 != 5 && roomNumber % 100 != 6 && roomNumber % 100 != 7
					&& roomNumber % 100 != 8) {

				throw new IllegalArgumentException(
						"That floor entered does not have that room number pleas try again  ");

			}
		}
		if (roomNumber / 100 == 6 && roomNumber / 100 == 7) {
			if (roomNumber % 100 != 1 && roomNumber % 100 != 2 && roomNumber % 100 != 3 && roomNumber % 100 != 4) {

				throw new IllegalArgumentException(
						"That floor entered does not have that room number pleas try again  ");

			}

		}

		if (roomNumber / 100 == 8) {

			if (roomNumber % 100 != 1) {
				throw new IllegalArgumentException(
						"That floor entered does not have that room number please try again  ");
			}

		}

		return roomNumber;
	}

	/**
	 * The hashcode must be oveerriden if the equals method was overridden
	 * 
	 * @return result
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + roomNumber;
		result = prime * result + ((roomType == null) ? 0 : roomType.hashCode());
		return result;
	}

	/**
	 * This method overrides the equals method so it is now able to compare room
	 * objects
	 * 
	 * @return boolean
	 */
	@Override
	public boolean equals(Object obj) {

		if (this == obj)
			return true;

		if (obj == null)
			return false;

		if (!(obj instanceof Room)) {

			return false;
		}
		Room room = (Room) obj;

		if (!this.equals(room.getRoomNumber()))
			return false;

		if (!this.equals(room.getRoomType()))
			return false;

		return true;

	}

	/**
	 * This is a toString override
	 * 
	 * @return String
	 */
	@Override
	public String toString() {
		return this.roomNumber + "*" + this.roomType;
	}

	/**
	 * This method compares room numbers
	 * 
	 * @return roomNumber
	 */
	@Override
	public int compareTo(Room o) {

		if (this.getRoomNumber() > o.getRoomNumber()) {
			return 1;
		} else if (this.getRoomNumber() == o.getRoomNumber()) {
			return 0;
		} else {
			return -1;
		}

	}

	/**
	 * This method gets the room floor
	 * 
	 * @return roomNumber
	 */
	@Override
	public int getFloor() {
		return this.roomNumber / 100;
	}

	/**
	 * This method gets the room number
	 * 
	 * @return roomNumber
	 */
	@Override
	public int getNumber() {

		return this.roomNumber % 100;
	}

	/**
	 * This method gets the roomType
	 * 
	 * @return roomType
	 */
	@Override
	public RoomType getRoomType() {

		return this.roomType;
	}

	/**
	 * This method gets the digit in the ones position
	 * 
	 * @return roomNumber
	 */
	@Override
	public int getRoomNumber() {

		return this.roomNumber;
	}

}
