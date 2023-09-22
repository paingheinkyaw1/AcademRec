/**
 * This class represents the DropdownSelection for the application.
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
 * Represents a DropdownSelection with course name, semesters, personal
 * characteristics, academic characteristics, and program.
 */
public class DropdownSelection {
	public String courseName;
	public String semesters;
	public String personalChar;
	public String academicChar;
	public String program;

	/**
	 * Updates the DropdownSelection with the new values.
	 *
	 * @param courseName   the course name
	 * @param semesters    the semesters
	 * @param personalChar the personal characteristics
	 * @param academicChar the academic characteristics
	 * @param program      the program
	 */
	public void editSelection(String courseName, String semesters, String personalChar, String academicChar,
			String program) {
		this.courseName = courseName;
		this.semesters = semesters;
		this.personalChar = personalChar;
		this.academicChar = academicChar;
		this.program = program;
	}
}