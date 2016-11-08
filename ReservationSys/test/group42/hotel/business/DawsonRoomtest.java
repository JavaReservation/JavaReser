package group42.hotel.business;

public class DawsonRoomtest{


	public static void main(String[] args) {

		testDawsonRoom("Case 1  - valid data entered", 401, RoomType.NORMAL, true);
		testDawsonRoom("Case 2  - RoomType too high", 900, RoomType.PENTHOUSE, false);
		testDawsonRoom("Case 3  - RoomType too low", 69, RoomType.NORMAL, false);
		DawsonRoom testRoom1 = new DawsonRoom(450, RoomType.NORMAL);
		DawsonRoom testRoom2 = new DawsonRoom(201, RoomType.SUITE);
		testGetNumber("case 4  - testRoom1.getNumber()", testRoom1, 50);
		testGetFloor("case 5  - testRoom1.getFloor()", testRoom1, 4);
		testCompareTo("case 6  - compareTo larger instance", testRoom1, testRoom2, 249);
		testCompareTo("case 7  - compareTo smaller instance", testRoom2, testRoom1, -249);
		DawsonRoom test1Room1 = new DawsonRoom(101, RoomType.NORMAL);
		DawsonRoom test2Room2 = new DawsonRoom(101, RoomType.SUITE);
		DawsonRoom test2Room3 = new DawsonRoom(102, RoomType.NORMAL);
		testEquals("case 8  - equals objects are equal", test1Room1, test2Room2, true);
		testEquals("case 9  - equals objects are not equal", test1Room1, test2Room3, false);
	}

	private static void testDawsonRoom(String testCase, int roomNumber, RoomType roomType, boolean expectedResult) {

		System.out.println(testCase);

		try {
			DawsonRoom test = new DawsonRoom(roomNumber, roomType);
			if (expectedResult == true) {
				System.out.println("valid DawsonRoom -->  " + test.toString());
			}
		} catch (IllegalArgumentException iea) {
			System.out.println(iea.getMessage());
		}
		System.out.println("END OF THAT TEST CASE " + testCase.substring(0, 8) + "\n");

	}

	private static void testGetNumber(String testCase, DawsonRoom room1, int expectedResult) {

		System.out.println(testCase);

		int test = room1.getNumber();

		if (test == expectedResult) {
			System.out.println("good return -->" + test);
		} else {
			System.out.println("bad return -->" + test);
		}

		System.out.println("END OF THAT TEST CASE " + testCase.substring(0, 8) + "\n");

	}

	private static void testGetFloor(String testCase, DawsonRoom room1, int expectedResult) {

		System.out.println(testCase);

		int test = room1.getFloor();

		if (test == expectedResult) {
			System.out.println("good return -->" + test);
		} else {
			System.out.println("bad return -->" + test);
		}

		System.out.println("END OF THAT TEST CASE " + testCase.substring(0, 8) + "\n");

	}

	private static void testCompareTo(String testCase, DawsonRoom room1, DawsonRoom room2, int expectedResult) {

		System.out.println(testCase);

		int test = room1.compareTo(room2);

		if (test == expectedResult) {
			System.out.println("good compareTo result --> " + test);
		} else {
			System.out.println("bad compareTo result -->" + test);
		}

		System.out.println("END OF THAT TEST CASE " + testCase.substring(0, 8) + "\n");

	}

	private static void testEquals(String testCase, DawsonRoom room1, DawsonRoom room2, boolean expectedResult) {

		System.out.println(testCase);

		boolean test = room1.equals(room2);

		if (test == expectedResult) {
			System.out.println("DawsonRooms are equal --> " + test);
		} else {
			System.out.println("DawsonRooms are not equal-->" + test);
		}

		System.out.println("END OF THAT TEST CASE " + testCase.substring(0, 8) + "\n");

	}

}
