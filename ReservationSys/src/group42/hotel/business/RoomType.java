/**
 * 
 */
package group42.hotel.business;

/**
 * @author RoanWC
 *
 */
public enum RoomType {
	NORMAL, SUITE, PENTHOUSE;
	public String toString() {
		return super.toString().toLowerCase();
	}

}
