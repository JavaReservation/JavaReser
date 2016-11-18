package group42.hotel.business;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;

import dw317.hotel.business.RoomType;
import dw317.hotel.business.interfaces.Room;
import dw317.hotel.data.interfaces.ListPersistenceObject;
import dw317.hotel.data.interfaces.ReservationDAO;
import group42.hotel.data.ReservationListDB;
import group42.hotel.data.SequentialTextFileList;
import group42.util.ListUtilities;

public class DawsonHotelAllocationPolicyTest {

	public static void main(String[] args) throws FileNotFoundException, ClassNotFoundException, IOException {

		

	}
	
	
	
	
	
	public static void setFilesToTest (){
		String[] rooms = new String[4];
		rooms[0] = "101*normal";
		rooms[1] = "102*normal";
		rooms[2] = "201*normal";
		rooms[3] = "301*normal";
		rooms[4] = "202*normal";
		rooms[5] = "102*normal";
		rooms[6] = "301*suite";
		rooms[7] = "401*penthouse";

		String[] reservs = new String[8];
		reservs[0] = "raj@aing.ru*2016*9*10*2016*9*15*101";
		reservs[1] = "joe.mancini@mail.me*2016*10*10*2016*10*20*401";
		reservs[2] = "raj@aing.ru*2016*9*10*2016*9*15*101";
		reservs[3] = "joe.mancini@mail.me*2016*10*10*2016*10*20*401";
		reservs[4] = "raj@aing.ru*2016*9*10*2016*9*15*101";
		reservs[5] = "joe.mancini@mail.me*2016*10*10*2016*10*20*401";
		reservs[6] = "raj@aing.ru*2016*9*10*2016*9*15*101";
		reservs[7] = "joe.mancini@mail.me*2016*10*10*2016*10*20*401";
		
		File dir = new File("testfiles");

		try {
			if (!dir.exists()) {
				dir.mkdirs();
			}
			ListUtilities.saveListToTextFile(rooms, "testfiles/testRooms.txt");
			ListUtilities.saveListToTextFile(reservs, "testfiles/testReservations.txt");
		} catch (IOException io) {
			System.out.println("Error creating file in setUp()");
		}
	}

}
