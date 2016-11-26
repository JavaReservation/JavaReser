package group42.util;

import java.io.BufferedWriter;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Array;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Comparator;
import java.io.IOException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * This class is responsible for creating a merge and sort static methods to be
 * called by the SortMergeApp
 * 
 * @author Werner
 * @version 27/10/2016
 */
@SuppressWarnings("unused")
public class ListUtilities {
	private static final Charset CHARACTER_ENCODING = StandardCharsets.UTF_8;

	protected String saveListToTextFile;

	/**
	 * A no parameter constructor set to private in order for this class not to
	 * be instantiated....
	 */
	private ListUtilities() {

	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void sort(Comparable[] list) throws IllegalArgumentException, NullPointerException {

		if (list == null) {
			throw new NullPointerException("The array is null");
		}
		for (int i = 0; i < list.length; i++) {
			if (list[i] == null) {
				throw new IllegalArgumentException("The array contains a null");
			}
		}

		Comparable<?> minValue;
		int minIndex;

		for (int i = 0; i < list.length - 1; i++) {
			minIndex = i;
			minValue = list[i];

			for (int j = i + 1; j < list.length; j++) {

				if (list[j].compareTo(minValue) < 0) {
					minValue = list[j];
					minIndex = j;
				}

			}
			//if (minIndex != i)
			
			list[minIndex] = list[i];
			list[i] = (Comparable<?>) minValue;
		}

	}

	public static void saveListToTextFile(Object[] objects, String filename)
			throws FileNotFoundException, UnsupportedEncodingException {
		saveListToTextFile(objects, filename, false, CHARACTER_ENCODING);
	}

	public static void saveListToTextFile(Object[] objects, String filename, boolean append)
			throws FileNotFoundException, UnsupportedEncodingException {
		saveListToTextFile(objects, filename, append, CHARACTER_ENCODING);
	}

	public static void saveListToTextFile(Object[] objects, String filename, boolean append, Charset characterEncoding)
			throws FileNotFoundException, UnsupportedEncodingException {

		PrintWriter outputFile = null;

		try {
			FileOutputStream f = new FileOutputStream(filename, append);
			OutputStreamWriter out = new OutputStreamWriter(f, characterEncoding);
			outputFile = new PrintWriter(new BufferedWriter(out));

			for (Object obj : objects)
				if (obj != null)
					outputFile.println(obj);
		} catch (FileNotFoundException e) {
			throw new FileNotFoundException("Error saving list! Unable to access the device " + filename);
		} finally {
			if (outputFile != null) // successfully opened
				outputFile.close(); // flushes buffer and releases resources
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static Comparable[] merge(Comparable[] list1, Comparable[] list2, String duplicateFileName) {
		// ReservationSys\\datafiles\\duplicates
		// validate input
		if (list1 == null || list2 == null) {
			throw new NullPointerException("The array is null");
		}

		for (int i = 0; i < list1.length; i++) {
			if (list1[i] == null) {
				throw new IllegalArgumentException("Index " + i + "is null pleas inisialize");
			}
		}
		for (int i = 0; i < list2.length; i++) {
			if (list2[i] == null) {
				throw new IllegalArgumentException("Index " + i + "is null pleas inisialize");
			}
		}

		// sort the arrays
		sort(list1);
		sort(list2);

		// in order to prevent class type exception
		Comparable[] list3 = (Comparable[]) Array.newInstance(list1.getClass().getComponentType(),
				list1.length + list2.length);

		// helpper variables.
		int index1 = 0, index2 = 0, arrayIndex = 0;

		while (index1 < list1.length && index2 < list2.length) {

			if (list1[index1].compareTo(list2[index2]) < 0) {

				list3[arrayIndex] = list1[index1];
				index1++;

			} else if (list1[index1].compareTo(list2[index2]) == 0) {

				list3[arrayIndex] = list1[index1];
				writeToFile(list3[arrayIndex], duplicateFileName);
				index1++;
				index2++;
			} else {
				list3[arrayIndex] = list2[index2];
				index2++;

			}
			arrayIndex++;
		} // en of while loop

		if (index1 < list1.length) {
			for (int i = index1; i < list1.length; i++) {
				list3[arrayIndex] = list1[i];
				arrayIndex++;
			}
		} else {
			for (int i = index2; i < list2.length; i++) {
				list3[arrayIndex] = list2[i];
				arrayIndex++;
			}
		}

		if (list3.length != arrayIndex) {
			list3 = Arrays.copyOf(list3, arrayIndex);
		}

		return list3;

	}

	private static void writeToFile(Object comparable, String duplicateFileName) {

		PrintWriter outputStream = null;

		try {

			outputStream = new PrintWriter(new BufferedWriter(
					new OutputStreamWriter(new FileOutputStream(duplicateFileName, false), StandardCharsets.UTF_8)));

			outputStream.println(comparable + "(merged)");

		} catch (FileNotFoundException o) {
			throw new IllegalArgumentException("The text file was not found ");
		}

		finally {
			if (outputStream != null)
				outputStream.close();
		}

	}

	/*
	 * Sorts a list of objects in the given order.
	 * 
	 * Precondition: Assumes that the list is not null and that the list's
	 * capacity is equal to the lists size.
	 * 
	 * @param list A list of objects
	 * 
	 * @param sortOrder an object that defines the sort order
	 * 
	 * @throws IllegalArgumentExeption when the parameter is not filled to
	 * capacity.
	 * 
	 * @throws NullPointerExeption if any of the parameters are null
	 */
	@SuppressWarnings({ "rawtypes" })
	public static void sort(Comparable[] list, Comparator sortOrder) {

		if (list == null) {
			throw new NullPointerException("One of your parameter is null");
		}

		for (int i = 0; i < list.length; i++) {
			if (list[i] == null) {
				throw new IllegalArgumentException("The array contains a null");
			}
		}
		sort(list, sortOrder);
	}

	// phase 4
/**
 * serializing out to a file
 * @param object
 * @param fileSpecification
 * @throws IOException
 */
	public static void serializeObject(Object object, String fileSpecification) throws IOException {
		ObjectOutputStream out = null;
		try {
			out = new ObjectOutputStream(new FileOutputStream(fileSpecification));
			out.writeObject(object);
		} catch (IOException e) {
			// normally the exception would be logged to file then thrown
			throw new IOException("Error serializing object to \n" + fileSpecification + " " + e);
		} finally {
			if (out != null)
				out.close();
		}
	}
/**
 * De-serializing into an object
 * @param fileSpecification
 * @return
 * @throws IOException
 * @throws ClassNotFoundException
 */
	public static Object deserializeObject(String fileSpecification) throws IOException, ClassNotFoundException {
		ObjectInputStream in = null;
		try {
			Object obj = null;
			in = new ObjectInputStream(new FileInputStream(fileSpecification));
			if (in != null)
				obj = in.readObject();
			return obj;
		} catch (ClassNotFoundException | IOException e) {
			// normally the exception would be logged to file then thrown
			throw new IOException("Error deserializing object from " + fileSpecification + "\n" + e);
		} finally {
			if (in != null)
				in.close();
		}
	}

}
