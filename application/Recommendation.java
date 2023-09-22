/**
 * This class represents the Recommendation for the application.
 *
 * @author Sophia Atendido
 * @author Lizett Gonzalez
 * @author Paing Hein Kyaw
 * @author Tiffany Pham
 * @version 1.0
 * @since 2023-05-03
 */
package application;

import java.util.List;
import java.util.StringJoiner;
import java.util.stream.Collectors;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * The Recommendation class represents a recommendation for a student. It
 * contains information such as the student's name, gender, target school,
 * program, and courses taken.
 */
public class Recommendation {
	private String firstName;
	private String lastName;
	private String gender;
	private String targetSchool;
	private String todayDate;
	private String program;
	private String firstSemester;
	private String firstCourse;
	private String firstCourseGrade;
	private List<Course> coursesTaken;
	private List<String> personalCharacteristics;
	private List<String> academicCharacteristics;

	/**
	 * Constructor for the Recommendation object.
	 *
	 * @param firstName               the first name of the student
	 * @param lastName                the last name of the student
	 * @param gender                  the gender of the student
	 * @param todayDate               the current date
	 * @param targetSchool            the target school of the student
	 * @param program                 the program the student is applying for
	 * @param firstSemester           the student's planned first semester
	 * @param firstCourse             the student's planned first course
	 * @param coursesTaken            a list of courses the student has taken
	 * @param personalCharacteristics a list of the student's personal
	 *                                characteristics
	 * @param academicCharacteristics a list of the student's academic
	 *                                characteristics
	 */
	public Recommendation(String firstName, String lastName, String gender, String todayDate, String targetSchool,
			String program, String firstSemester, String firstCourse, String firstCourseGrade, List<Course> coursesTaken,
			ArrayList<String> personalCharacteristics, ArrayList<String> academicCharacteristics) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.gender = gender;
		this.todayDate = todayDate;
		this.targetSchool = targetSchool;
		this.program = program;
		this.firstSemester = firstSemester;
		this.firstCourse = firstCourse;
		this.firstCourseGrade = firstCourseGrade;
		this.coursesTaken = coursesTaken;
		this.personalCharacteristics = personalCharacteristics;
		this.academicCharacteristics = academicCharacteristics;
	}

	/**
	 * Constructs a new empty Recommendation.
	 */
	public Recommendation() {
		this.firstName = "";
		this.lastName = "";
		this.gender = "";
		this.todayDate = "";
		this.targetSchool = "";
		this.program = "";
		this.firstSemester = "";
		this.firstCourse = "";
		this.coursesTaken = null;
		this.personalCharacteristics = null;
		this.academicCharacteristics = null;
	}

	/**
	 * Sets the student's first name.
	 *
	 * @param firstName the student's first name
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * Sets the student's last name.
	 *
	 * @param lastName the student's last name
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * Sets the student's gender.
	 *
	 * @param gender the student's gender
	 */
	public void setGender(String gender) {
		this.gender = gender;
	}

	/**
	 * Sets the target school.
	 *
	 * @param targetSchool the target school
	 */
	public void setTargetSchool(String targetSchool) {
		this.targetSchool = targetSchool;
	}

	/**
	 * Sets the date of the recommendation letter.
	 *
	 * @param todayDate the date of the recommendation letter
	 */
	public void setTodayDate(String todayDate) {
		this.todayDate = todayDate;
	}

	/**
	 * Sets the student's program of interest.
	 *
	 * @param program the student's program of interest
	 */
	public void setProgram(String program) {
		this.program = program;
	}

	/**
	 * Sets the student's first semester.
	 *
	 * @param firstSemester the student's first semester
	 */
	public void setFirstSemester(String firstSemester) {
		this.firstSemester = firstSemester;
	}

	/**
	 * Sets the student's first course.
	 *
	 * @param firstCourse the student's first course
	 */
	public void setFirstCourse(String firstCourse) {
		this.firstCourse = firstCourse;
	}

	/**
	 * Sets the list of courses the student has taken.
	 *
	 * @param coursesTaken the list of courses the student has taken
	 */
	public void setCoursesTaken(List<Course> coursesTaken) {
		this.coursesTaken = coursesTaken;
	}

	/**
	 * Sets the list of the student's personal characteristics.
	 *
	 * @param personalCharacteristics the list of the student's personal
	 *                                characteristics
	 */
	public void setPersonalCharacteristics(List<String> personalCharacteristics) {
		this.personalCharacteristics = personalCharacteristics;
	}

	/**
	 * Sets the list of the student's academic characteristics.
	 *
	 * @param academicCharacteristics the list of the student's academic
	 *                                characteristics
	 */
	public void setAcademicCharacteristics(List<String> academicCharacteristics) {
		this.academicCharacteristics = academicCharacteristics;
	}

	/**
	 * Returns the student's first name.
	 *
	 * @return the student's first name
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * Returns the student's last name.
	 *
	 * @return the student's last name
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * Returns the student's gender.
	 *
	 * @return the student's gender
	 */
	public String getGender() {
		return gender;
	}

	/**
	 * Returns the student's target school.
	 *
	 * @return the student's target school
	 */
	public String getTargetSchool() {
		return targetSchool;
	}

	/**
	 * Returns today's date.
	 *
	 * @return today's date
	 */
	public String getTodayDate() {
		return todayDate;
	}

	/**
	 * Returns the student's program.
	 *
	 * @return the student's program
	 */
	public String getProgram() {
		return program;
	}

	/**
	 * Returns the student's first semester.
	 *
	 * @return the student's first semester
	 */
	public String getFirstSemester() {
		return firstSemester;
	}

	/**
	 * Returns the student's first course.
	 *
	 * @return the student's first course
	 */
	public String getFirstCourse() {
		return firstCourse;
	}
	
	public String getFirstCourseGrade() {
		return firstCourseGrade;
	}

	/**
	 * Returns the list of courses the student has taken.
	 *
	 * @return the list of courses the student has taken
	 */
	public List<Course> getCourses() {
		return coursesTaken;
	}

	/**
	 * Returns the list of personal characteristics the student has.
	 *
	 * @return the list of personal characteristics the student has
	 */
	public List<String> getPersonalCharacteristics() {
		return personalCharacteristics;
	}

	/**
	 * Returns the list of academic characteristics the student has.
	 *
	 * @return the list of academic characteristics the student has
	 */
	public List<String> getAcademicCharacteristics() {
		return academicCharacteristics;
	}

	/**
	 * Serializes the Recommendation object into a comma-separated string.
	 *
	 * @return the serialized Recommendation object
	 */
	public String serialize() {
		StringJoiner joiner = new StringJoiner(",");
		joiner.add(String.valueOf(firstName));
		joiner.add(String.valueOf(lastName));
		joiner.add(String.valueOf(gender));
		joiner.add(escapeCommas(targetSchool));
		joiner.add(escapeCommas(todayDate));
		joiner.add(escapeCommas(program));
		joiner.add(escapeCommas(firstSemester));
		joiner.add(String.valueOf(firstCourse));
		joiner.add(coursesTaken.stream().map(course -> course.serialize()).collect(Collectors.joining(";")));
		joiner.add(serializeList(personalCharacteristics));
		joiner.add(serializeList(academicCharacteristics));
		return joiner.toString();
	}

	/**
	 * Deserializes a string into a Recommendation object.
	 *
	 * @param data the serialized Recommendation string
	 * @return the deserialized Recommendation object
	 */
	public static Recommendation deserialize(String data) {
		String[] parts = data.split(",", -1);
		String firstName = unescapeCommas(parts[0]);
		String lastName = unescapeCommas(parts[1]);
		String gender = unescapeCommas(parts[2]);
		String targetSchool = unescapeCommas(parts[3]);
		String todayDate = unescapeCommas(parts[4]);
		String program = unescapeCommas(parts[5]);
		String firstSemester = unescapeCommas(parts[6]);
		String firstCourse = unescapeCommas(parts[7]);
		String firstCourseGrade = unescapeCommas(parts[8]);
		List<Course> courses = Arrays.stream(parts[9].split(";")).map(Course::deserialize).collect(Collectors.toList());
		ArrayList<String> personalCharacteristics = deserializeList(parts[10]);
		ArrayList<String> academicCharacteristics = deserializeList(parts[11]);

		return new Recommendation(firstName, lastName, gender, todayDate, targetSchool, program, firstSemester,
				firstCourse, firstCourseGrade, courses, personalCharacteristics, academicCharacteristics);
	}

	/**
	 * Serializes a list of strings into a semicolon-separated string. Commas in the
	 * strings are escaped.
	 *
	 * @param list the list of strings to serialize
	 * @return the serialized list as a semicolon-separated string
	 */
	private String serializeList(List<String> list) {
		StringJoiner joiner = new StringJoiner(";");
		for (String item : list) {
			joiner.add(escapeCommas(item));
		}
		return joiner.toString();
	}

	/**
	 * Deserializes a semicolon-separated string into a list of strings. Escaped
	 * commas in the strings are unescaped.
	 *
	 * @param data the semicolon-separated string to deserialize
	 * @return the deserialized list of strings
	 */
	private static ArrayList<String> deserializeList(String data) {
		String[] parts = data.split(";", -1);
		ArrayList<String> list = new ArrayList<>();
		for (String part : parts) {
			list.add(unescapeCommas(part));
		}
		return list;
	}

	/**
	 * Escapes commas in the input string.
	 *
	 * @param input the input string to escape commas
	 * @return the input string with escaped commas
	 */
	private String escapeCommas(String input) {
		return input.replace(",", "\\,");
	}

	/**
	 * Unescapes commas in the input string.
	 *
	 * @param input the input string to unescape commas
	 * @return the input string with unescaped commas
	 */
	private static String unescapeCommas(String input) {
		return input.replace("\\,", ",");
	}

}
