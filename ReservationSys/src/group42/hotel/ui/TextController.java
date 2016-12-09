package group42.hotel.ui;

import java.io.IOException;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import dw317.hotel.data.*;
import dw317.lib.Email;
import dw317.lib.creditcard.CreditCard;
import dw317.lib.creditcard.CreditCard.CardType;
import group42.hotel.business.Hotel;
import dw317.hotel.business.RoomType;
import dw317.hotel.business.interfaces.*;

public class TextController {
	private HotelManager model;

	private enum Command {
		CUSTOMER_INFO, REGISTER_CUSTOMER, CREATE_RESERVATION, RESERVATION_INFO, CHANGE_CREDITCARD, STOP
	}

	public TextController(Hotel model) {
		this.model = model;
	}

	
	public void run() {
		Scanner keyboard = new Scanner(System.in);
		Command[] commands = Command.values();
		String menu = createMenu(commands);
		Command choice;
		// you should validate the input.
		do {
			System.out.print(menu);
			choice = getUserChoice(commands, keyboard);
			switch (choice) {
			case REGISTER_CUSTOMER:
				newCustomer(keyboard);
				break;
			case CREATE_RESERVATION:
				newReservation(keyboard);
				break;
			case CUSTOMER_INFO:
				customerInfo(keyboard);
				break;
			case RESERVATION_INFO:
				reservationInfo(keyboard);
				break;
			case CHANGE_CREDITCARD:
				updateCard(keyboard);
				break;

			case STOP:
				// nothing
			}
		} while (choice != Command.STOP);

		// When stopped, need to disconnect
		try {
			this.model.closeHotel();
		} catch (IOException ioe) {
			System.out.println("An error occured when closing the database.");
		}
	}

	private void newCustomer(Scanner keyboard) {
		keyboard.nextLine(); // consume any previous value

		// get name
		String firstName = getInput(keyboard, "\nPlease enter the first name: ");
		String lastName = getInput(keyboard, "Please enter the last name: ");

		// get email
		String email = getEmail(keyboard);

		// TODO

		boolean valid;
		do {
			try {
				this.model.registerCustomer(firstName, lastName, email);
				valid = false;
			} catch (DuplicateCustomerException e) {
				System.out.println("I am sorry that customer is already in our databse ");
				valid = true;
			}
		} while (valid);

	}

	private void newReservation(Scanner keyboard) {
		keyboard.nextLine(); // consume any previous value

		// TODO
		
		boolean valid;

		do {

			try {
				this.model.createReservation(this.model.findCustomer(getEmail(keyboard)), this.getDate(keyboard, "Pleas input your desire check in date"),
						this.getDate(keyboard, "pleas enter your desire check out date"), this.getRoomType(keyboard));
				valid = false;
			} catch (Exception ai) {
				System.out.println(ai.getMessage());
				valid = true;
			}
		} while (valid);

	}

	private void customerInfo(Scanner keyboard) {
		keyboard.nextLine(); // consume any previous value
		System.out.println("customer info");

		String email = getEmail(keyboard);
		boolean valid;

		do {
			try {
				System.out.println("pree");
				this.model.findCustomer(email);
				valid = false;
				System.out.println("after");
			} catch (Exception ai) {
				System.out.println(ai.getMessage());
				valid = true;
			}
		} while (valid);

	}

	private void reservationInfo(Scanner keyboard) {
		keyboard.nextLine(); // consume any previous value

		// TODO
		//Customer cus = this.newCus(keyboard);
		boolean valid;

		do {
			try {

				this.model.findReservations((this.model.findCustomer(getEmail(keyboard))));
				valid = false;

			} catch (Exception ai) {
				System.out.println(ai.getMessage());
				valid = true;
			}
		} while (valid);

	}

	private void updateCard(Scanner keyboard) {
		keyboard.nextLine();

		// TODO

		String card = getCardType(keyboard).toString();
		String email = this.getEmail(keyboard);
		String cardnumber = this.getInput(keyboard, "Pleas enter the card number");

		boolean valid;

		do {
			try {
				this.model.updateCreditCard(email, card, cardnumber);
				valid = false;
			} catch (Exception ai) {
				System.out.println(ai.getMessage());
				valid = true;
			}
		} while (valid);

	}

	private RoomType getRoomType(Scanner keyboard) {
		String menu = "\nSelect the room type from the menu:\n";
		int maxChoiceValue = RoomType.values().length;
		RoomType result = null;

		for (int i = 0; i < maxChoiceValue; i++)
			menu += "\t" + (i + 1) + " - " + RoomType.values()[i] + "\n";
		menu += "\nEnter your choice: ";

		System.out.print(menu);

		boolean invalid;
		int userChoice = 0;
		do {
			try {
				invalid = false;
				userChoice = keyboard.nextInt();
				if (userChoice <= 0 || userChoice > maxChoiceValue) {
					System.out
							.print("Invalid choice! Enter a number in " + " the range of 1 to " + maxChoiceValue + " ");
					invalid = true;
				}
			} catch (java.util.InputMismatchException e) {
				System.out.print("Invalid choice! You must enter a" + " numeric value in the range of 1 to "
						+ maxChoiceValue + " ");
				invalid = true;
				keyboard.nextLine(); // consume the invalid value
			}
		} while (invalid);
		result = RoomType.values()[userChoice - 1];
		return result;
	}

	private CreditCard.CardType getCardType(Scanner keyboard) {
		String menu = "\nSelect the credit card type from the menu:\n";
		int maxChoiceValue = CreditCard.CardType.values().length;
		CreditCard.CardType result = null;

		for (int i = 0; i < maxChoiceValue; i++)
			menu += "\t" + (i + 1) + " - " + CreditCard.CardType.values()[i] + "\n";
		menu += "\nEnter your choice: ";

		System.out.print(menu);

		boolean invalid;
		int userChoice = 0;
		do {
			try {
				invalid = false;
				userChoice = keyboard.nextInt();
				if (userChoice <= 0 || userChoice > maxChoiceValue) {
					System.out
							.print("Invalid choice! Enter a number in " + " the range of 1 to " + maxChoiceValue + " ");
					invalid = true;
				}
			} catch (java.util.InputMismatchException e) {
				System.out.print("Invalid choice! You must enter a" + " numeric value in the range of 1 to "
						+ maxChoiceValue + " ");
				invalid = true;
				keyboard.nextLine(); // consume the invalid value
			}
		} while (invalid);
		result = CreditCard.CardType.values()[userChoice - 1];
		return result;
	}

	private String getEmail(Scanner keyboard) {
		boolean invalid;
		String email;
		do {
			invalid = false;
			email = getInput(keyboard, "Please enter the email address: ");
			try {
				@SuppressWarnings("unused")
				Email emailObj = new Email(email);
			} catch (IllegalArgumentException e) {
				System.out.println("Invalid email!" + e.getMessage());
				System.out.print("Please try again: ");

				invalid = true;
			}
		} while (invalid);
		return email;
	}

	private String getInput(Scanner keyboard, String message) {
		// used for any general string input
		System.out.print(message);
		return keyboard.nextLine();
	}

	private int getInt(Scanner keyboard, String message) {
		System.out.print(message);
		// user entered something that is not an int
		while (!keyboard.hasNextInt()) {
			// consume the invalid token, including any leading whitespace
			keyboard.next();
			System.out.print("Invalid � Enter only a whole number ");
		}
		return keyboard.nextInt();
	}

	private LocalDate getDate(Scanner keyboard, String message) {
		boolean invalid;
		int year, month, day;
		LocalDate date = null;
		System.out.println(message);

		do {
			invalid = false;
			year = getInt(keyboard, "Please enter the year : ");
			month = getInt(keyboard, "Please enter the month (1-12): ");
			day = getInt(keyboard, "Please enter the day (1-31): ");
			try {
				date = LocalDate.of(year, month, day);
			} catch (DateTimeException e) {
				System.out.println("Invalid date!" + e.getMessage());
				System.out.print("Please try again: ");

				invalid = true;
			}
		} while (invalid);
		return date;
	}

	private String createMenu(Command[] commands) {
		String menu = "\nDawson Hotel Menu\nSelect a choice from the menu:\n";
		int numChoices = commands.length;
		for (int i = 0; i < numChoices; i++)
			menu += "\t" + (i + 1) + " - " + commands[i] + "\n";
		menu += "\nEnter your choice: ";

		return menu;
	}

	private Command getUserChoice(Command[] commands, Scanner keyboard) {
		boolean invalid;
		int maxChoiceValue = commands.length;
		int userChoice = 0;
		do {
			try {
				invalid = false;
				userChoice = keyboard.nextInt();
				if (userChoice <= 0 || userChoice > maxChoiceValue) {
					System.out
							.print("Invalid choice! Enter a number in " + " the range of 1 to " + maxChoiceValue + " ");
					invalid = true;
				}
			} catch (java.util.InputMismatchException e) {
				System.out.print("Invalid choice! You must enter a" + " numeric value in the range of 1 to "
						+ maxChoiceValue + " ");
				invalid = true;
				keyboard.nextLine(); // consume the invalid value
			}
		} while (invalid);
		return commands[userChoice - 1];
	}

}