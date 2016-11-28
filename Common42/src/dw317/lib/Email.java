package dw317.lib;

/**
 * The Email class validates an email address, if it meets the standereds set.
 * 
* @author Werner Castanaza
* date: 27/09/2016
*/
import java.io.Serializable;

/**
 * the email class implements Serializable and Comparable of email
 */

public class Email implements Serializable, Comparable<Email> {

	private static final long serialVersionUID = 42031768871L;

	private final String address;

	/**
	 * The constructor takes in a string type representation of an email and
	 * will assigned it only if it passes all validation tests.
	 * 
	 * @throws IllegalArgumentException
	 *             if address is invalid
	 */
	public Email(String address) {

		try {
			this.address = validateEmail(address);

		} catch (IllegalArgumentException ai) {
			throw new IllegalArgumentException(ai.getMessage());
		}

	}

	/**
	 * validates the email string param
	 * 
	 * @param email
	 * @return string
	 * @throws IllegalArgumentException
	 *             if the email does not meet standereds
	 */
	private String validateEmail(String email) {

		if (email == null || email.isEmpty() || email.indexOf('@') == -1
				|| (email.length() - email.replace("@", "").length()) != 1)
			throw new IllegalArgumentException("Your email is empty or invalid");
		String userId = email.substring(0, email.indexOf('@'));
		String hostName = email.substring(email.indexOf('@') + 1);

		String specialChar = ".*[$'\\^,!#%&*()+=|}{:\"\';><?/~`].*";

		// specialChar2 will handle the possibility that the user has a _ in his
		// host name
		String specialChar2 = ".*[$'\\^,!#%&*()+=|}{:\"\';><?/~`_].*";

		if (!validateUserId(userId, specialChar))
			throw new IllegalArgumentException("The UserId is invalid");
		if (!validateHostName(hostName, specialChar2))
			throw new IllegalArgumentException("The Host Name is invalid");

		return email;
	}

	/**
	 * validates the hostName
	 * 
	 * @param hostName,
	 *            specialChar
	 * @return string
	 * @throws IllegalArgumentException
	 *             if the email does not meet standards
	 */
	private static boolean validateHostName(String hostName, String specialChar) {

		if (hostName.length() > 32 || hostName.length() < 1 || !hostName.matches(".*[a-zA-Z].*")
				|| hostName.charAt(0) == '-' || hostName.charAt(hostName.length() - 1) == '-'
				|| hostName.matches(".*\\.\\..*") || hostName.matches(specialChar))
			return false;

		return true;
	}

	/**
	 * validates the userId
	 * 
	 * @param userId,
	 *            specialChar
	 * @return string
	 * @throws IllegalArgumentException
	 *             if the email does not meet standards
	 */
	private static boolean validateUserId(String userId, String specialChar) {

		if (userId.length() > 32 || userId.length() < 1 || userId.matches(specialChar)
				|| !userId.matches(".*[a-zA-Z].*")
				|| (userId.charAt(0) == '.' || userId.charAt(userId.length() - 1) == '.')
				|| userId.matches(".*\\.\\..*"))
			return false;

		return true;
	}

	/**
	 * this method returns the full email address
	 * 
	 * @return address type string
	 */
	public String getAddress() {
		return this.address;
	}

	/**
	 * This method returns the usir id in type string
	 * 
	 * @return userid
	 */
	public String getUserId() {

		return address.substring(0, address.indexOf('@'));

	}

	/**
	 * this method will return the host name of the email in type string
	 * 
	 * @return the host name
	 */
	public String getHost() {
		String host = this.address.substring(this.address.indexOf('@') + 1, this.address.length() - 1);
		return host;
	}

	/**
	 * overrrides the to string and returns a string represtation of the email
	 */
	@Override
	public String toString() {
		return "" + address + "";
	}

	/**
	 * orrides hashCode to meet the the new standereds set by the overrided
	 * equals method
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((address == null) ? 0 : address.hashCode());
		return result;
	}

	/**
	 * overrides equals, two email addresses are consider equal
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;

		if (this == obj)
			return true;

		if (this.getClass() != obj.getClass())
			return false;

		Email data = (Email) obj;

		if (this.address.equalsIgnoreCase(data.address))
			return true;

		return false;
	}

	/**
	 * returns deep copy of the email class
	 * 
	 * @return Email object
	 */
	public Email getEmail() {

		return new Email(this.getAddress());
	}

	/**
	 * Overridden compareTo method inorder to compare two email objects
	 */
	@Override
	public int compareTo(Email o) {

		if (this.getHost().equalsIgnoreCase(o.getHost()))
			return this.getUserId().compareToIgnoreCase(o.getUserId());

		return getHost().compareToIgnoreCase(o.getHost());
	}
}