/**
 * 
 */
package group42.util;

/**
 * @author pepe
 *
 */
public class ListUtilitiesTesting {

	/**
	 * @param args
	 */
	@SuppressWarnings("rawtypes")
	public static void main(String[] args) {
		// Selection sort testing is self containd inside it's own method
		
		//These will serve as test data input for the sort method	
		String[] strArr = { "S", "O", "R", "T", "E", "X", "A", "M", "P", "L", "E" };
		Integer[] intArr = { 9, 8, 7, 6, 6, 5, 4, 3, 2, 2, 1 };
		Comparable[] anArray1 = new Comparable[2];
		anArray1[0] = "random";
		anArray1[1] = null;
		Comparable[] anArray2 = null;

		System.out.println("------------This is the Sorted method testing------------\n");

		selectionSortTesting("Test case-1 The array is filled in with strings", strArr, true);
		System.out.println("\n");
		selectionSortTesting("Test case-2 The array is filled in with integers", intArr, true);
		System.out.println("\n");
		selectionSortTesting("Test case-3 The array is filled in with strings", strArr, false);
		System.out.println("\n");
		;
		selectionSortTesting("Test case-4 The array is null", anArray2, false);

		System.out.println("\n \n ");
		// this will test the merge method and also will write the duplicates
		// into the duplicates.txt
		System.out.println("\n --------------- this is the merge array ---------------");

		Integer[] l1 = { 1, 2, 55, 8 }; // 6
		Comparable[] l2 = { 5, 6, 2, 8, 7, 9, 10, 55 };
		String[] str1 = { "z", "S", "d", "G" };
		String[] str2 = { "z", "S", "d", "G" };
		String dirLocation = "ReservationSys\\ListUtilitiesTestingFiles\\";
		testMerge("Test case-1 The two arrays are set with integers", l1, l2, dirLocation + "testCase1.txt", true);
		System.out.println("\n");
		testMerge("Test case-2 The two arrays are set with Strings", str1, str2, dirLocation + "testCase2.txt", true);
		System.out.println("\n");
		testMerge("Test case-3 The text file does not exist ", l1, l2, dirLocation + "invalid.txt", false);
		System.out.println("\n");
		testMerge("Test case-4 The second array is null, since it is null the testCase4.txt will be empty ", str1, null,
				dirLocation + "testCase4.txt", false);
		System.out.println("\n");
		testMerge("Test case-5 The empty string given as text file name", l1, l2, dirLocation + "", false);

	}

	// testing sorting of arrays
	@SuppressWarnings("rawtypes")
	public static void selectionSortTesting(String testCase, Comparable[] arrSort, boolean validation) {
		try {
			ListUtilities.sort(arrSort);

			System.out.println("\nAfter The Sort = ");

			for (int i = 0; i < arrSort.length; i++) {

				System.out.print(arrSort[i] + " ");

			}
		} catch (NullPointerException a) {
			System.out.println(a.getMessage());
		} catch (IllegalArgumentException ai) {
			System.out.println(ai.getMessage());
		}
	}

	@SuppressWarnings("rawtypes")
	public static void testMerge(String testCase, Comparable[] l1, Comparable[] l2, String fileLocation,
			boolean validation) {

		System.out.println(testCase);

		try {
			ListUtilities.sort(l1);
			ListUtilities.sort(l2);
			Comparable[] l3;
			l3 = ListUtilities.merge(l1, l2, fileLocation);
			if (validation) {

				System.out.println("\n successfully merge");

				for (int i = 0; i < l3.length; i++)

					System.out.print(l3[i] + " ");
			}

			else {

				System.out.println("invalid data entered");

			}
		}

		catch (NullPointerException a) {
			System.out.println(a.getMessage());
		} catch (IllegalArgumentException ai) {

			System.out.println(ai.getMessage());

		} catch (Exception e) {

			System.out.println(e.getMessage());

		}

	}
}
