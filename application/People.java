/**
 * This class represents the People for the application.
 *
 * @author Sophia Atendido
 * @author Lizett Gonzalez
 * @author Paing Hein Kyaw
 * @author Tiffany Pham
 * @version 1.0
 * @since 2023-05-03
 */
package application;

/**
 * The People class is an abstract class representing a person with a first and
 * last name.
 */
public abstract class People {
	private String firstName;
	private String lastName;

	/**
	 * Constructor for the People class.
	 *
	 * @param firstName the first name of the person
	 * @param lastName  the last name of the person
	 */
	public People(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
	}

	/**
	 * Returns the first name of the person.
	 *
	 * @return the first name of the person
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * Sets the first name of the person.
	 *
	 * @param firstName the new first name of the person
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * Returns the last name of the person.
	 *
	 * @return the last name of the person
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * Sets the last name of the person.
	 *
	 * @param lastName the new last name of the person
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
}