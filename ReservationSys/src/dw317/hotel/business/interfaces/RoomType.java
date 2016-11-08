/**
 * 
 */
package dw317.hotel.business.interfaces;

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
