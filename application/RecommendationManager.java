/**
 * This class represents the RecommendationManager for the application.
 *
 * @author Sophia Atendido
 * @author Lizett Gonzalez
 * @author Paing Hein Kyaw
 * @author Tiffany Pham
 * @version 1.0
 * @since 2023-05-03
 */
package application;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Manages recommendations for the application.
 */
public class RecommendationManager {
	private SharedDataModel sharedDataModel;
	private List<Recommendation> recommendations;
	public HashMap<String, String> lastNameFileMap;
	Faculty faculty;
	private ObservableList<String> recommendationFileNames = FXCollections.observableArrayList();

	/**
	 * Constructor for the RecommendationManager.
	 */
	public RecommendationManager(SharedDataModel sharedDataModel) {
		recommendations = new ArrayList<>();
		this.sharedDataModel = sharedDataModel != null ? sharedDataModel : new SharedDataModel();
		this.lastNameFileMap = new HashMap<>();
	}

	/**
	 * Constructor for the RecommendationManager with a faculty member and the
	 * shared data model.
	 *
	 * @param faculty the faculty instance
	 */
	public RecommendationManager(Faculty faculty, SharedDataModel sharedDataModel) {
		recommendations = new ArrayList<>();
		this.sharedDataModel = sharedDataModel != null ? sharedDataModel : new SharedDataModel();

		this.faculty = faculty != null ? faculty : new Faculty(); 
																	
		this.sharedDataModel.setFaculty(this.faculty);
	}

	/**
	 * Adds a recommendation and saves it as a text file.
	 *
	 * @param recommendation the recommendation object
	 * @param filePath       the file path for saving the recommendation
	 */
	public void addRecommendation(Recommendation recommendation, String filePath) {
		System.out.println("Before adding recommendation: " + recommendations);
		recommendations.add(recommendation);
		System.out.println("After adding recommendation: " + recommendations);
		saveAsTextFile(recommendation, filePath);
		System.out.println("Recommendation added: " + recommendation.toString());
	}

	/**
	 * Saves a recommendation as a text file.
	 *
	 * @param recommendation the recommendation object
	 * @param filePath       the file path for saving the recommendation
	 */
	public void saveAsTextFile(Recommendation recommendation, String filePath) {
		String fileName = generateFormattedText(recommendation);

		String folderPath = "src/CompiledRecommendations/";
		File file = new File(folderPath + fileName);

		try {
			File folder = new File(folderPath);
			if (!folder.exists()) {
				folder.mkdirs();
			}

			FileWriter fileWriter = new FileWriter(file);
			BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

			bufferedWriter.write(filePath);

			bufferedWriter.close();
			fileWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Saves a recommendation as a text file.
	 *
	 * @param recommendation the recommendation object
	 * @param filePath       the file path for saving the recommendation
	 */
	public List<Recommendation> getRecommendations() {

		return recommendations;
	}

	/**
	 * Updates a recommendation at a given index and saves it as a text file.
	 *
	 * @param index                 the index of the recommendation to be updated
	 * @param updatedRecommendation the updated recommendation object
	 * @param filePath              the file path for saving the updated
	 *                              recommendation
	 */
	public void updateRecommendation(int index, Recommendation updatedRecommendation, String filePath) {
		recommendations.set(index, updatedRecommendation);
		saveAsTextFile(updatedRecommendation, filePath);
	}

	/**
	 * Generates a formatted text string for a given recommendation.
	 *
	 * @param recommendation the recommendation object
	 * @return a formatted text string for the recommendation
	 */
	public String generateFormattedText(Recommendation recommendation) {
		StringBuilder text = new StringBuilder();

		List<Course> courses = recommendation.getCourses();
		String firstName = recommendation.getFirstName().substring(0, 1).toUpperCase()
				+ recommendation.getFirstName().substring(1);
		String lastName = recommendation.getLastName().substring(0, 1).toUpperCase()
				+ recommendation.getLastName().substring(1);

		String additionalCoursesText = "";
		if (courses.size() > 1) {
			List<String> courseTexts = new ArrayList<>();
			for (int i = 0; i < courses.size(); i++) {
				Course course = courses.get(i);
				courseTexts.add(String.format("%s from my \"%s\"", formatGrade(course.getGrade()), course.getName()));
			}
			if (courseTexts.size() > 1) {
				additionalCoursesText = String.join(", ", courseTexts.subList(0, courseTexts.size() - 1));
				additionalCoursesText += " and " + courseTexts.get(courseTexts.size() - 1);
			} else {
				additionalCoursesText = courseTexts.get(0);
			}
			additionalCoursesText = String.format("\n%s also earned %s course%s.", firstName, additionalCoursesText,
					courses.size() > 1 ? "s" : "");
		}

		String gender = recommendation.getGender().toLowerCase();
		String genderCapitalized = gender.substring(0, 1).toUpperCase() + gender.substring(1);
		String genderObjective = gender.equals("he") ? "him" : (gender.equals("she") ? "her" : "them");

		text.append("Letter of Recommendation\n");
		text.append("For: ").append(firstName).append(" ").append(lastName).append("\n");
		text.append("\n").append("Date: ").append(recommendation.getTodayDate()).append("\n\n");
		text.append("To: Graduate Admissions Committee\n");
		text.append("\n").append("I am writing this letter to recommend my former student ").append(firstName)
				.append(" ").append(lastName).append(", who is applying for the ").append(recommendation.getProgram())
				.append(" in your school.\n");
		text.append("\n").append("I met ").append(firstName).append(" in ").append(recommendation.getFirstSemester())
				.append(" when ").append(gender).append(" enrolled in my \"").append(recommendation.getFirstCourse())
				.append("\" course.");
		text.append("\n").append(firstName).append(" earned \"").append(recommendation.getFirstCourseGrade())
				.append("\" from this tough course, and this shows how knowledgeable and a hard worker ").append(gender)
				.append(" is.");
		text.append("\n");
		if (!additionalCoursesText.isEmpty()) {
			text.append(additionalCoursesText);
		}
		text.append("\n").append(genderCapitalized).append(" ")
				.append(String.join(", ", recommendation.getAcademicCharacteristics()));
		if (recommendation.getAcademicCharacteristics().size() > 1) {
			int lastIndex = text.lastIndexOf(",");
			text.replace(lastIndex, lastIndex + 1, ", and");
		}
		text.append(".");
		text.append("\n").append(genderCapitalized).append(" was always ")
				.append(String.join(", ", recommendation.getPersonalCharacteristics()));
		if (recommendation.getPersonalCharacteristics().size() > 1) {
			int lastIndex = text.lastIndexOf(",");
			text.replace(lastIndex, lastIndex + 1, ", and");
		}
		text.append(".");
		text.append("\n").append("\nFurthermore, I noticed from the term project result, ").append(gender)
				.append(" developed leadership, time management, and problem-solving skills. ")
				.append(genderCapitalized)
				.append(" worked effectively with the team members and delegated tasks appropriately. They were able to deliver a successful project in a timely fashion.\n");
		text.append("\nI believe that ").append(firstName).append(
				" has the capacity to excel at a higher education program and this is my pleasure to highly recommend ")
				.append(genderObjective).append(". ");
		text.append("Please do not hesitate to contact me with further questions.\n");
		text.append("\n").append("Very Respectfully,\n");

		Faculty faculty = sharedDataModel.getFaculty();
		if (faculty != null) {
			text.append(faculty.getSignature());
		} else {
			text.append(
					"Faculty First Name Faculty Last Name\nFaculty Role\nFaculty School\nFaculty Department\nEmail: Faculty Email\nPhone: Faculty Phone");
		}
		return text.toString();
	}

	/**
	 * Formats the given grade by converting it to uppercase and surrounding it with
	 * quotes.
	 *
	 * @param grade the grade to format
	 * @return the formatted grade
	 */
	private String formatGrade(String grade) {
		return "\"" + grade.toUpperCase() + "\"";
	}

	/**
	 * Updates the recommendation with the specified last name using the provided
	 * updated text and file path.
	 *
	 * @param lastName    the last name of the student whose recommendation should
	 *                    be updated
	 * @param updatedText the updated text to replace the existing recommendation
	 * @param filePath    the file path to update the recommendation
	 */

	/**
	 * Deletes the recommendation with the specified last name.
	 *
	 * @param lastName the last name of the student whose recommendation should be
	 *                 deleted
	 */
	public void deleteRecommendationByLastName(String lastName) {
		recommendations.removeIf(recommendation -> recommendation.getLastName().equalsIgnoreCase(lastName));
	}

	/**
	 * Adds the specified file name to the list of recommendation files.
	 *
	 * @param fileName the name of the recommendation file to add
	 */
	public void addRecommendationFile(String fileName) {
		recommendationFileNames.add(fileName);
	}

	/**
	 * Returns an ObservableList of recommendation file names.
	 *
	 * @return an ObservableList containing the names of recommendation files
	 */
	public ObservableList<String> getRecommendationFileNames() {
		return recommendationFileNames;
	}

	/**
	 * Removes the specified recommendation file from the list and deletes the file.
	 *
	 * @param fileName the name of the recommendation file to remove
	 */
	public void removeRecommendationFile(String fileName) {
		recommendationFileNames.remove(fileName);
		try {
			Files.deleteIfExists(Paths.get(fileName));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}