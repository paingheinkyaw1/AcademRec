/**
 * This class represents the Course for the application.
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
 * Represents a Course with its name and grade.
 */
public class Course {
	private String name;
	private String grade;

	/**
	 * Constructs a Course with the specified name and grade.
	 *
	 * @param name  the name of the course
	 * @param grade the grade for the course
	 */
	public Course(String name, String grade) {
		this.name = name;
		this.grade = grade;
	}

	/**
	 * Sets the name of the course.
	 *
	 * @param name the new name for the course
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Sets the grade of the course.
	 *
	 * @param grade the new grade for the course
	 */
	public void setGrade(String grade) {
		this.grade = grade;
	}

	/**
	 * @return the name of the course
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the grade of the course
	 */
	public String getGrade() {
		return grade;
	}

	/**
	 * Serializes the Course object into a comma-separated string.
	 *
	 * @return the serialized representation of the course
	 */
	public String serialize() {
		return String.format("%s,%s", getName().replace(",", "\\,"), getGrade().replace(",", "\\,"));
	}

	/**
	 * Deserializes the data and creates a Course object.
	 *
	 * @param data the serialized data for the course
	 * @return a new Course instance
	 */
	public static Course deserialize(String data) {
		String[] parts = data.split(",", -1);
		String name = unescapeCommas(parts[0]);
		String grade = unescapeCommas(parts[1]);
		return new Course(name, grade);
	}

	/**
	 * Replaces escaped commas with actual commas in the input string.
	 *
	 * @param input the input string
	 * @return the input string with unescaped commas
	 */
	private static String unescapeCommas(String input) {
		return input.replace("\\,", ",");
	}
}
