/**
 * This class represents the Faculty for the application.
 *
 * @author Sophia Atendido
 * @author Lizett Gonzalez
 * @author Paing Hein Kyaw
 * @author Tiffany Pham
 * @version 1.0
 * @since 2023-05-03
 */
package application;

import java.util.StringJoiner;

/**
 * Represents a faculty member with their personal information and contact
 * details. This class extends the People class, which holds the first name and
 * last name of the faculty member.
 */
public class Faculty extends People {
	public String role;
	public String department;
	public String school;
	public String email;
	public String phone;

	/**
	 * Default constructor for the Faculty class. Initializes an empty faculty
	 * member with all fields set to an empty string.
	 */
	public Faculty() {
		super("", "");
		this.role = "";
		this.department = "";
		this.school = "";
		this.email = "";
		this.phone = "";
	}

	/**
	 * Constructor for the Faculty class, taking first name and last name as
	 * parameters.
	 * 
	 * @param firstName The faculty member's first name.
	 * @param lastName  The faculty member's last name.
	 */
	public Faculty(String firstName, String lastName) {
		super(firstName, lastName);
	}

	/**
	 * Constructor for the Faculty class, taking all fields as parameters.
	 * 
	 * @param firstName  The faculty member's first name.
	 * @param lastName   The faculty member's last name.
	 * @param role       The faculty member's role.
	 * @param school     The faculty member's school.
	 * @param department The faculty member's department.
	 * @param email      The faculty member's email address.
	 * @param phone      The faculty member's phone number.
	 */
	public Faculty(String firstName, String lastName, String role, String school, String department, String email,
			String phone) {
		super(firstName, lastName);
		this.role = role;
		this.school = school;
		this.department = department;
		this.email = email;
		this.phone = phone;
	}

	/**
	 * Returns the faculty member's signature.
	 * 
	 * @return A formatted string containing the faculty member's full name, role,
	 *         department, school, email, and phone number.
	 */
	public String getSignature() {
		return String.format("%s %s\n%s\n%s\n%s\nEmail: %s\nPhone: %s", getFirstName(), getLastName(), role, department,
				school, email, phone);
	}

	/**
	 * Sets the role of the faculty member.
	 * 
	 * @param role The role to set, e.g. "Professor"
	 */
	public void setRole(String role) {
		this.role = role;
	}

	/**
	 * Sets the department of the faculty member.
	 * 
	 * @param department The department to set, e.g. "Computer Science"
	 */
	public void setDepartment(String department) {
		this.department = department;
	}

	/**
	 * Sets the school of the faculty member.
	 * 
	 * @param school The school to set, e.g. "Example University"
	 */
	public void setSchool(String school) {
		this.school = school;
	}

	/**
	 * Sets the email of the faculty member.
	 * 
	 * @param email The email to set, e.g. "john.doe@example.com"
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Sets the phone number of the faculty member.
	 * 
	 * @param phone The phone number to set, e.g. "555-123-4567"
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}

	/**
	 * Returns the role of the faculty member.
	 * 
	 * @return The role of the faculty member, e.g. "Professor"
	 */
	public String getRole() {
		return role;
	}

	/**
	 * Returns the department of the faculty member.
	 * 
	 * @return The department of the faculty member, e.g. "Computer Science"
	 */
	public String getDepartment() {
		return department;
	}

	/**
	 * Returns the school of the faculty member.
	 * 
	 * @return The school of the faculty member, e.g. "Example University"
	 */
	public String getSchool() {
		return school;
	}

	/**
	 * Returns the email of the faculty member.
	 * 
	 * @return The email of the faculty member, e.g. "john.doe@example.com"
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Returns the phone number of the faculty member.
	 * 
	 * @return The phone number of the faculty member, e.g. "555-123-4567"
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * Serializes the faculty member's data into a single string.
	 * 
	 * @return A comma-separated string containing the faculty member's data.
	 */
	public String serialize() {
		StringJoiner joiner = new StringJoiner(",");
		joiner.add(escapeCommas(getFirstName()));
		joiner.add(escapeCommas(getLastName()));
		joiner.add(escapeCommas(role));
		joiner.add(escapeCommas(school));
		joiner.add(escapeCommas(department));
		joiner.add(escapeCommas(email));
		joiner.add(escapeCommas(phone));
		return joiner.toString();
	}

	/**
	 * Deserializes the faculty member's data from a string.
	 * 
	 * @param data The comma-separated string containing the faculty member's data.
	 * @return A Faculty object with the deserialized data.
	 */
	public static Faculty deserialize(String data) {
		String[] parts = data.split(",", -1);
		String firstName = unescapeCommas(parts[0]);
		String lastName = unescapeCommas(parts[1]);
		String role = unescapeCommas(parts[2]);
		String school = unescapeCommas(parts[3]);
		String department = unescapeCommas(parts[4]);
		String email = unescapeCommas(parts[5]);
		String phone = unescapeCommas(parts[6]);
		return new Faculty(firstName, lastName, role, school, department, email, phone);
	}

	/**
	 * Escapes commas in the input string by replacing them with a backslash
	 * followed by a comma.
	 * 
	 * @param input The input string to escape commas in.
	 * @return The input string with commas escaped.
	 */
	private static String escapeCommas(String input) {
		return input.replace(",", "\\,");
	}

	/**
	 * Unescapes commas in the input string by replacing a backslash followed by a
	 * comma with a comma.
	 * 
	 * @param input The input string to unescape commas in.
	 * @return The input string with commas unescaped.
	 */
	private static String unescapeCommas(String input) {
		return input.replace("\\,", ",");
	}

	/**
	 * Returns a string representation of the faculty member.
	 * 
	 * @return A string containing the faculty member's full name.
	 */
	@Override
	public String toString() {
		return String.format("%s %s", getFirstName(), getLastName());
	}

}