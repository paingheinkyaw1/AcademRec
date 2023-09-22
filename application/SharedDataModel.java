package application;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * The SharedDataModel class stores shared data used by the application, such as
 * gender options, semester options, program options, course options, personal
 * character options, academic character options, and additional courses. It
 * provides methods to load and save this data from and to files.
 */
public class SharedDataModel {
	private ObservableList<String> genderOptions = FXCollections.observableArrayList();
	private ObservableList<String> semesterOptions = FXCollections.observableArrayList();
	private ObservableList<String> programOptions = FXCollections.observableArrayList();
	private ObservableList<String> courseOptions = FXCollections.observableArrayList();
	private ObservableList<String> personalCharacterOptions = FXCollections.observableArrayList();
	private ObservableList<String> academicCharacterOptions = FXCollections.observableArrayList();
	private ObservableList<String> additionalCourses = FXCollections.observableArrayList();
	private Faculty faculty;

	/**
	 * Constructs a SharedDataModel instance with default values for gender options
	 * and loads data from files.
	 */
	public SharedDataModel() {
		faculty = new Faculty("John","Doe","Professor","Example University","Computer Science","john.doe@example.com","555-123-4567");
		genderOptions.addAll("he", "she", "they");
		loadDataFromFiles();
	}

	/**
	 * Sets the gender options.
	 *
	 * @param genderOptions An ObservableList of String containing the new gender
	 *                      options.
	 */
	public void setGenderOptions(ObservableList<String> genderOptions) {
		this.genderOptions = genderOptions;
	}

	/**
	 * Sets the gender options.
	 *
	 * @param genderOptions An ObservableList of String containing the new gender
	 *                      options.
	 */
	public void setSemesterOptions(ObservableList<String> semesterOptions) {
		this.semesterOptions = semesterOptions;
	}

	/**
	 * Sets the program options.
	 *
	 * @param programOptions An ObservableList of String containing the new program
	 *                       options.
	 */
	public void setProgramOptions(ObservableList<String> programOptions) {
		this.programOptions = programOptions;
	}

	/**
	 * Sets the program options.
	 *
	 * @param programOptions An ObservableList of String containing the new program
	 *                       options.
	 */
	public void setCourseOptions(ObservableList<String> courseOptions) {
		this.courseOptions = courseOptions;
	}

	/**
	 * Sets the additional courses.
	 *
	 * @param additionalCourses An ObservableList of String containing the new
	 *                          additional courses.
	 */
	public void setAdditionalCourses(ObservableList<String> additionalCourses) {
		this.additionalCourses = additionalCourses;
	}

	/**
	 * Sets the personal characteristics.
	 *
	 * @param additionalCourses An ObservableList of String containing the new
	 *                          personal characteristics.
	 */
	public void setPersonalCharacterOptions(ObservableList<String> personalCharacterOptions) {
		this.personalCharacterOptions = personalCharacterOptions;
	}

	/**
	 * Sets the academic characteristics.
	 *
	 * @param additionalCourses An ObservableList of String containing the new
	 *                          academic characteristics.
	 */
	public void setAcademicCharacterOptions(ObservableList<String> academicCharacterOptions) {
		this.academicCharacterOptions = academicCharacterOptions;
	}

	/**
	 * Sets the faculty object.
	 *
	 * @param faculty A Faculty object.
	 */
	public void setFaculty(Faculty faculty) {
		this.faculty = faculty;
	}

	/**
	 * Returns the gender options.
	 *
	 * @return An ObservableList of String containing the gender options.
	 */
	public ObservableList<String> getGenderOptions() {
		return genderOptions;
	}

	/**
	 * Returns the semester options.
	 *
	 * @return An ObservableList of String containing the semester options.
	 */
	public ObservableList<String> getSemesterOptions() {
		return semesterOptions;
	}

	/**
	 * Returns the program options.
	 *
	 * @return An ObservableList of String containing the program options.
	 */
	public ObservableList<String> getProgramOptions() {
		return programOptions;
	}

	/**
	 * Returns the course options.
	 *
	 * @return An ObservableList of String containing the course options.
	 */
	public ObservableList<String> getCourseOptions() {
		return courseOptions;
	}

	/**
	 * Returns the personal character options.
	 *
	 * @return An ObservableList of String containing the personal character
	 *         options.
	 */
	public ObservableList<String> getPersonalCharacterOptions() {
		return personalCharacterOptions;
	}

	/**
	 * Returns the academic character options.
	 *
	 * @return An ObservableList of String containing the academic character
	 *         options.
	 */
	public ObservableList<String> getAcademicCharacterOptions() {
		return academicCharacterOptions;
	}

	/**
	 * Returns the additional courses.
	 *
	 * @return An ObservableList of String containing the additional courses.
	 */
	public ObservableList<String> getAdditionalCourses() {
		return additionalCourses;
	}

	/**
	 * Returns the faculty object.
	 *
	 * @return A Faculty object.
	 */
	public Faculty getFaculty() {
		return faculty;
	}

	/**
	 * Loads the shared data from various files.
	 */
	public void loadDataFromFiles() {
		try {
			File semesterFile = new File("semesterOptions.txt");
			Scanner semesterScanner = new Scanner(semesterFile);
			while (semesterScanner.hasNextLine()) {
				semesterOptions.add(semesterScanner.nextLine());
			}
			semesterScanner.close();

			File programFile = new File("programOptions.txt");
			Scanner programScanner = new Scanner(programFile);
			while (programScanner.hasNextLine()) {
				programOptions.add(programScanner.nextLine());
			}
			programScanner.close();

			File personalFile = new File("personalCharacterOptions.txt");
			Scanner personalScanner = new Scanner(personalFile);
			while (personalScanner.hasNextLine()) {
				personalCharacterOptions.add(personalScanner.nextLine());
			}
			personalScanner.close();

			File academicFile = new File("academicCharacterOptions.txt");
			Scanner academicScanner = new Scanner(academicFile);
			while (academicScanner.hasNextLine()) {
				academicCharacterOptions.add(academicScanner.nextLine());
			}
			academicScanner.close();

			File courseFile = new File("coursesOptions.txt");
			Scanner courseScanner = new Scanner(courseFile);
			while (courseScanner.hasNextLine()) {
				courseOptions.add(courseScanner.nextLine());
			}
			courseScanner.close();

			File additionalCourseFile = new File("coursesOptions.txt");
			Scanner additionalCourseScanner = new Scanner(additionalCourseFile);
			while (additionalCourseScanner.hasNextLine()) {
				additionalCourses.add(additionalCourseScanner.nextLine());
			}
			additionalCourseScanner.close();

		} catch (FileNotFoundException e) {
			System.out.println("File not found: " + e.getMessage());
		} catch (Exception e) {
			System.out.println("Error while reading file: " + e.getMessage());
		}
	}

	/**
	 * Saves the specified data to a file with the specified file name.
	 *
	 * @param fileName the name of the file to save the data to
	 * @param data     the data to save
	 */
	public void saveDataToFile(String fileName, List<String> data) {
		try {
			FileWriter fileWriter = new FileWriter(fileName);
			BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

			for (String line : data) {
				bufferedWriter.write(line);
				bufferedWriter.newLine();
			}

			bufferedWriter.close();
			fileWriter.close();
		} catch (IOException e) {
			System.out.println("Error while writing file: " + e.getMessage());
		}
	}
}
