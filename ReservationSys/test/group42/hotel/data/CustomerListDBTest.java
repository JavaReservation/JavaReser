/**
 * 
 */
package group42.hotel.data;

import java.io.File;
import java.io.IOException;

import dw317.hotel.business.interfaces.Customer;
import dw317.hotel.data.DuplicateCustomerException;
import dw317.hotel.data.NonExistingCustomerException;
import group42.hotel.business.DawsonCustomer;
import group42.hotel.business.DawsonHotelFactory;
import group42.util.ListUtilities;

/**
 * @author Keylen
 *
 */
public class CustomerListDBTest {
		public static void main(String[] args) {
			testAddMethod();
			testDisconnect();
			testGetCustomer();
			testUpdateMethod();
			testToString();
		}
		
		private static void setup()
		{
			String[] rooms = new String[4];
			rooms[0] = "101*normal";
			rooms[1] = "102*normal";
			rooms[2] = "301*suite";
			rooms[3] = "401*penthouse";
			
			String[] custs = new String[7];
			custs [0] = "raj@aing.ru*Raj*Wong*visa*4556737586899855";
			custs [1] = "hateraid@gmail.com*Erica*Hoppes**";
			custs [2] = "joe.mancini@mail.me*Joe*Mancini**";
			custs [3] = "donut@yahoo.com*Donnie*Nut**";
			custs [4] = "Leon@yahoo.com*Lynel*Homatoses**";
			custs [5] = "mike.antso@yahoo.com*Mike*antso**";
			


			String[] reservs = new String[8];
			reservs [0] = "raj@aing.ru*2016*9*10*2016*9*15*101";
			reservs [1] = "joe.mancini@mail.me*2016*10*10*2016*10*20*401";
			//...
			reservs [7] = "d@zzz.com*2016*10*12*2016*10*15*102";
			SequentialTextFileList file = new SequentialTextFileList("testfiles/testRooms.txt",
																	 "testfiles/testCustomers.txt",
																	 "testfiles/testReservations.txt");
			File dir = new File("testfiles");
			try{
				if (!dir.exists()){  
					dir.mkdirs();
				}
				ListUtilities.saveListToTextFile(rooms,"testfiles/testRooms.txt");
				ListUtilities.serializeObject(rooms,"testfiles/testRooms.ser");
				
				ListUtilities.saveListToTextFile(custs,"testfiles/testCustomers.txt");
				ListUtilities.serializeObject(custs,"testfiles/testCustomers.ser");
				
				ListUtilities.saveListToTextFile(reservs,"testfiles/testReservations.txt");
				ListUtilities.serializeObject(reservs, "testfiles/testReservations.ser"); 
			}
			catch(IOException io){
				System.out.println
				("Error creating file in setUp()");
			}
		}	
		
		private static void teardown() {
			File theFile = new File("testfiles/testRooms.txt");
			if (theFile.exists()) {
				theFile.delete();
			}
			theFile = new File("testfiles/testCustomers.txt");
			if (theFile.exists()) {
				theFile.delete();
			}
			theFile = new File("testfiles/testReservations.txt");
			if (theFile.exists()) {
				theFile.delete();
			}
		}
		private static void teardownSerialization(){
			File theFile = new File("testfiles/testRooms.ser");
			if (theFile.exists()) {
				theFile.delete();
			}
			theFile = new File("testfiles/testCustomers.ser");
			if (theFile.exists()) {
				theFile.delete();
			}
			theFile = new File("testfiles/testReservations.ser");
			if (theFile.exists()) {
				theFile.delete();
			}
		}
	
		private static void testAddMethod(){
			setup();
			Customer [] customerArray = new DawsonCustomer[5];
			ObjectSerializedList file = new ObjectSerializedList
					("testfiles/testRooms.ser", "testfiles/testCustomers.ser",
							"testfiles/testReservations.ser");
			CustomerListDB db = new CustomerListDB(file);
			customerArray[1] = new DawsonCustomer("Aaron", "Blakeh", "AaRon@yahoo.com");
			try{
				db.add(customerArray[1]);
			}
			catch(DuplicateCustomerException dce) {
				System.out.print("That customer already exists");
				//continue;
			}catch(Exception e){
				System.out.print("General Exception");
				//continue;
			}
			System.out.println("\n"+db.toString());
			
			teardownSerialization();	
		}
		
		
		private static void testDisconnect(){
			setup();
			Customer [] customerArray = new DawsonCustomer[5];
			ObjectSerializedList file = new ObjectSerializedList
					("testfiles/testRooms.txt", "testfiles/testCustomers.txt",
							"testfiles/testReservations.txt");
			CustomerListDB db = new CustomerListDB(file);
			customerArray[1] = new DawsonCustomer("Aaron", "Blakeh", "AaRon@yahoo.com");
			try{
				db.add(customerArray[1]);
				testToString();
				db.disconnect();
				CustomerListDB database = new CustomerListDB(file);
				System.out.println(database.toString());
			}
			catch(DuplicateCustomerException dce) {
				System.out.print("That customer already exists");
				//continue;
			}
			catch (NullPointerException e) {
				System.out.print("Not pointing in the right direction");
			}catch(Exception e){
				System.out.print("General Exception");
				//continue;
			}
			
			

			
			teardownSerialization();	
		}

		private static void testGetCustomer(){
			setup();
			Customer [] customerArray = new DawsonCustomer[5];
			ObjectSerializedList file = new ObjectSerializedList
					("testfiles/testRooms.ser", "testfiles/testCustomers.ser",
							"testfiles/testReservations.ser");
			CustomerListDB db = new CustomerListDB(file);
			customerArray[0] = new DawsonCustomer("Donnie", "Nut", "donut@yahoo.com");
			try{
				db.getCustomer(customerArray[0].getEmail());
			}
			catch(NonExistingCustomerException nec) {
				System.out.print("That customer does not exist" + nec.getMessage());
				//continue;
			}catch(Exception e){
				System.out.print("General Exception");
				//continue;
			}
			
			
			teardownSerialization();	
		}
		
		private static void testUpdateMethod(){
			setup();
			Customer [] customerArray = new DawsonCustomer[5];
			ObjectSerializedList file = new ObjectSerializedList
					("testfiles/testRooms.ser", "testfiles/testCustomers.ser",
							"testfiles/testReservations.ser");
			CustomerListDB db = new CustomerListDB(file);
			customerArray[0] = new DawsonCustomer("Rose", "Hilty", "hateraid@gmail.com");
			try{
				System.out.println(db.getCustomer(customerArray[0].getEmail()));
				db.update(customerArray[0].getEmail(), DawsonHotelFactory.DAWSON.getCard("AMEX","344931899529540"));
				System.out.println(db.getCustomer(customerArray[0].getEmail()));
			}
			catch(NonExistingCustomerException nec) {
				System.out.print("That customer does not exist" + nec.getMessage());
			}catch(Exception e){
				System.out.print("General Exception");
				//continue;
			}
			teardownSerialization();
		
		}
		
		private static void testToString(){
			setup();
			ObjectSerializedList file = new ObjectSerializedList
					("testfiles/testRooms.txt", "testfiles/testCustomers.txt",
							"testfiles/testReservations.txt");
			CustomerListDB db = new CustomerListDB(file);
			
			System.out.println(db.toString());

			teardownSerialization();
		}

	}




