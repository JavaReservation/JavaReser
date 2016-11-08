/**
 * 
 */
package dw317.lib;

/**
 * @author Werner Castanaza The name class is ment ot validate the first and
 *         last name (type string) if it does not meet standereds set. Date:
 *         27/09/2016
 *
 */
public class Name implements Comparable<Name> {

	private String firstName;
	private String lastName;

	/**
	 * The constructor will set the first and last name only if it meets the
	 * Validation standards
	 * 
	 * @param firstName
	 * @param lastName
	 */
	public Name(String firstName, String lastName) {

		this.firstName = validateName(firstName);
		this.lastName = validateName(lastName);

	}

	/**
	 * a string represation of the first name is returned
	 * 
	 * @return firstname
	 */
	public String getfirstName() {
		return firstName;
	}

	/**
	 * sets first name with a string type
	 * 
	 * @param firstName
	 */
	public void setfirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * a string representation of the last name is returned
	 * 
	 * @return lastName
	 */
	public String getlastName() {
		return lastName;
	}

	/**
	 * sets the last name with a string type
	 * 
	 * @param lastName
	 */
	public void setlastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * Validation of the name peram given in the constructor, if it does not
	 * meet standards it will throw an exception
	 * 
	 * @param name
	 * @return name validated
	 * @throws IllegalArgumentException
	 */
	private String validateName(String name) {

		if (name.matches(".*[0-9].*") || name == null || name.length() < 2)
			throw new IllegalArgumentException("The name contain a number or is too short");

		if (name.matches(".*(--|  | '').*"))
			throw new IllegalArgumentException("Valid characters repeated is invalid");

		if (!(name.matches("^([a-zA-Z][- '  ]*[a-zA-Z].*|[a-zA-Z]{2,}[- ' ]*[a-zA-Z].*)")))
			throw new IllegalArgumentException("The name is invalid by the rules");

		return name;
	}

	/**
	 * returns a string representation of the full name
	 * 
	 * @return fullName
	 */
	public String getFullName() {

		String fullName = firstName + " " + lastName;
		return fullName;
	}

	/**
	 * orrides hashCode to meet the the new standereds set by the overrided
	 * equals method
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		return result;
	}

	/**
	 * overrides equals, two names are considered equal if the first and last
	 * name are the same
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return false;

		if (obj == null)
			return false;

		if (this.getClass() != obj.getClass())
			return false;

		Name data = (Name) obj;

		if (this.firstName.equalsIgnoreCase(data.firstName))
			if (this.lastName.equalsIgnoreCase(data.lastName))
				return true;
		return false;
	}

	/**
	 * the orriden compareTo will compare if two full name strings are equal
	 */
	@Override
	public int compareTo(Name name) {

		return this.getFullName().compareToIgnoreCase(name.getFullName());

	}

	/**
	 * the overriden to string will return the first and last name of the name
	 * given.
	 */
	@Override
	public String toString() {
		return firstName + "*" + lastName;
	}

}
