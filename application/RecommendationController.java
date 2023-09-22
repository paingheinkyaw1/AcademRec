/**
 * This class represents the RecommendationController for the application.
 *
 * @author Sophia Atendido
 * @author Lizett Gonzalez
 * @author Paing Hein Kyaw
 * @author Tiffany Pham
 * @version 1.0
 * @since 2023-05-03
 */
package application;

import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;
import java.text.*;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

/**
 * The RecommendationController class is the controller for the Recommendation
 * application.
 */
public class RecommendationController {
	private SharedDataModel sharedDataModel;
	private RecommendationManager recommendationManager;
	private Main main;

	@FXML
	private TextField dateTextField;	
	@FXML
	private TextField firstTextField;
	@FXML
	private TextField lastNameField;
	@FXML
	private TextField firstSemesterYearTextField;
	@FXML
	private TextField firstCourseGradeField;
	@FXML
	private ChoiceBox<String> genderChoiceBox;
	@FXML
	private TextField targetSchoolTextField;
	@FXML
	private ListView<String> additionalCoursesListView;
	@FXML
	private TableView<Course> additionalCoursesTableView;
	@FXML
	private TableColumn<Course, String> courseNameColumn;
	@FXML
	private TableColumn<Course, String> courseGradeColumn;
	@FXML
	private ListView<String> personalCharacteristicsListView;
	@FXML
	private ListView<String> academicCharacteristicsListView;
	@FXML
	private ComboBox<String> firstCourseComboBox;
	@FXML
	private ComboBox<String> firstSemesterComboBox;
	@FXML
	private ChoiceBox<String> programChoiceBox;
	@FXML
	private ChoiceBox<String> additionalCoursesChoiceBox;
	@FXML
	private Button compileButton;
	@FXML
	private Button backButton;
	@FXML
	private Button addCourseButton;
	@FXML
	private TextField courseNameField;
	@FXML
	private TextField courseGradeField;
	@FXML
	private Label errorMessage;

	/**
	 * Constructor for the RecommendationController.
	 * 
	 * @param sharedDataModel the shared data model
	 */
	public RecommendationController(SharedDataModel sharedDataModel) {
		this.sharedDataModel = sharedDataModel;
		this.recommendationManager =  new RecommendationManager(sharedDataModel);
	}

	/**
	 * Sets the main application instance.
	 * 
	 * @param main the main application instance
	 */
	public void setMainApp(Main main) {
		this.main = main;
	}

	/**
	 * Populates choice boxes with data from the shared data model.
	 */
	private void populateChoiceBoxes() {
		genderChoiceBox.setItems(sharedDataModel.getGenderOptions());
		genderChoiceBox.getSelectionModel().select(0);

		firstSemesterComboBox.setItems(sharedDataModel.getSemesterOptions());
		firstSemesterComboBox.getSelectionModel().select(0);

		programChoiceBox.setItems(sharedDataModel.getProgramOptions());
		programChoiceBox.getSelectionModel().select(0);

		firstCourseComboBox.setItems(sharedDataModel.getCourseOptions());
		firstCourseComboBox.getSelectionModel().select(0);

		additionalCoursesChoiceBox.setItems(sharedDataModel.getAdditionalCourses());
		additionalCoursesChoiceBox.getSelectionModel().select(0);

		personalCharacteristicsListView
				.setItems(FXCollections.observableArrayList(sharedDataModel.getPersonalCharacterOptions()));
		personalCharacteristicsListView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

		academicCharacteristicsListView
				.setItems(FXCollections.observableArrayList(sharedDataModel.getAcademicCharacterOptions()));
		academicCharacteristicsListView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

	}

	/**
	 * Populates choice boxes with data from the shared data model.
	 */
	private void onAddCourseButtonClicked() {
		String courseName = additionalCoursesChoiceBox.getValue();
		String courseGrade = courseGradeField.getText().trim();
		String firstCourse = firstCourseComboBox.getValue();

		if (courseName.equals(firstCourse)) {
			showAlert(Alert.AlertType.WARNING, "INVALID",
					"The first course cannot be added as an additional course. Please select a different course.");
			return;
		}

		if (!courseName.isEmpty() && !courseGrade.isEmpty() && (courseGrade.matches("[a-fA-F]"))) {
			Course newCourse = new Course(courseName, courseGrade);
			if (!additionalCoursesTableView.getItems().contains(newCourse)) {
				additionalCoursesTableView.getItems().add(newCourse);
				showAlert(Alert.AlertType.INFORMATION, "Success", "Course: \"" + courseName + "\" with grade \""
						+ courseGrade.toUpperCase() + "\" successfully added.");
				courseGradeField.clear();
			} else {
				showAlert(Alert.AlertType.WARNING, "INVALID",
						"The selected course is already added as an additional course. Please select a different course.");
			}
		} else {
			showAlert(Alert.AlertType.WARNING, "INVALID",
					"Please enter a valid course grade and select a course before adding.");
		}
	}

	/**
	 * Handles the compile button click event.
	 */
	private void onCompileButtonClicked() {

		String date = dateTextField.getText().trim();
		String firstName = firstTextField.getText().trim();
		String lastName = lastNameField.getText().trim();
		String gender = genderChoiceBox.getValue();
		String targetSchool = targetSchoolTextField.getText().trim();
		String firstCourse = firstCourseComboBox.getValue();
		String firstSemester = firstSemesterComboBox.getValue();
		String program = programChoiceBox.getValue();
		String year = firstSemesterYearTextField.getText().trim();
		String firstCourseGrade = firstCourseGradeField.getText().trim();
		ObservableList<String> personalCharacteristicsList = personalCharacteristicsListView.getSelectionModel()
				.getSelectedItems();
		ObservableList<String> academicCharacteristicsList = academicCharacteristicsListView.getSelectionModel()
				.getSelectedItems();

		if (date.isEmpty() || firstName.isEmpty() || lastName.isEmpty() || gender == null || year.isEmpty()
				|| targetSchool.isEmpty() || firstCourseGrade.isEmpty() || personalCharacteristicsList.isEmpty()
				|| academicCharacteristicsList.isEmpty() || firstCourse.isEmpty() || firstSemester.isEmpty()
				|| program == null) {
			showAlert(Alert.AlertType.WARNING, "INCOMPLETE",
					"Please fill out all fields. (Additional courses are not required.)");
		} else if (!isFormValid()) {
			showAlert(Alert.AlertType.WARNING, "INVALID", "Please fill out all fields correctly.");
			return;
		}

		Recommendation recommendation = new Recommendation(firstName, lastName, gender, date, targetSchool, program,
				firstSemester, firstCourse, firstCourseGrade, new ArrayList<>(additionalCoursesTableView.getItems()),
				new ArrayList<String>(personalCharacteristicsList), new ArrayList<String>(academicCharacteristicsList));

		String recommendationDraft = recommendationManager.generateFormattedText(recommendation);

		try {
			main.showRecommendationDraftScene(recommendation, recommendationDraft);
		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println(
				"Recommendation added to recommendation manager: " + recommendationManager.getRecommendations().size());
	}

	/**
	 * Checks if all the TextFields are strings / empty and if the date is correct.
	 * 
	 * @return true if the form is valid, false otherwise
	 */
	private boolean isFormValid() {
		return isDateValid() && isFirstNameValid() && isLastNameValid() && isTargetSchoolValid()
				&& isSemesterYearValid() && isCourseGradeValid();
	}

	/**
	 * Checks if the date is valid.
	 * 
	 * @return true if the date is valid, false otherwise
	 */
	private boolean isDateValid() {
		String date = dateTextField.getText().trim();
		try {
			SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
			dateFormat.setLenient(false);
			Date parsedDate = dateFormat.parse(date);

			// Check that the parsed date is not null and that it is not in the future
			if (parsedDate != null && !parsedDate.after(new Date())) {
				dateTextField.setStyle("-fx-border-color: white ; -fx-border-width: 2px ;");
				return true;
			}
	        dateTextField.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");
			return false;
			
		} catch (ParseException e) {
			return false;
		}
	}

	/**
	 * Checks if the first name is valid.
	 * 
	 * @return true if the first name is valid, false otherwise
	 */
	private boolean isFirstNameValid() {
		String firstName = firstTextField.getText().trim();
		if (firstName.matches("[a-zA-Z]+")) {
            firstTextField.setStyle("-fx-border-color: white ; -fx-border-width: 2px ;");
            return true;
        } else {
            firstTextField.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");
            return false;
        }
	}

	/**
	 * Checks if the last name is valid.
	 * 
	 * @return true if the last name is valid, false otherwise
	 */
	private boolean isLastNameValid() {
		String lastName = lastNameField.getText().trim();
		if (lastName.matches("[a-zA-Z]+")) {
			lastNameField.setStyle("-fx-border-color: white ; -fx-border-width: 2px ;");
            return true;
        } else {
        	lastNameField.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");
            return false;
        }
	}

	/**
	 * Checks if the target school is valid.
	 * 
	 * @return true if the target school is valid, false otherwise
	 */
	private boolean isTargetSchoolValid() {
		String targetSchool = targetSchoolTextField.getText().trim();
		if (targetSchool.matches("[a-zA-Z]+")) {
			targetSchoolTextField.setStyle("-fx-border-color: white ; -fx-border-width: 2px ;");
            return true;
        } else {
        	targetSchoolTextField.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");
            return false;
        }
	}

	/**
	 * Checks if the course grade is valid.
	 * 
	 * @return true if the course grade is valid, false otherwise
	 */
	private boolean isCourseGradeValid() {
		String firstCourseGrade = firstCourseGradeField.getText().trim();
		if (!firstCourseGrade.matches("[a-fA-F]")) {
        	firstCourseGradeField.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");
			return false;
		}
    	firstCourseGradeField.setStyle("-fx-border-color: white ; -fx-border-width: 2px ;");
		return true;
		
	}

	/**
	 * Checks if the semester year is valid.
	 * 
	 * @return true if the semester year is valid, false otherwise
	 */
	private boolean isSemesterYearValid() {
		int year;
		try {
			year = Integer.parseInt(firstSemesterYearTextField.getText().trim());
		} catch (NumberFormatException e) {
        	firstSemesterYearTextField.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");
			return false;
		}
    	firstSemesterYearTextField.setStyle("-fx-border-color: white ; -fx-border-width: 2px ;");
		return year >= 2000 && year <= 2023;
	}

	/**
	 * Shows an alert with the given alert type, title, and message.
	 * 
	 * @param alertType the type of the alert
	 * @param title     the title of the alert
	 * @param message   the message of the alert
	 */
	private void showAlert(Alert.AlertType alertType, String title, String message) {
		Alert alert = new Alert(alertType);
		alert.setTitle(title);
		alert.setHeaderText(null);
		alert.setContentText(message);
		alert.showAndWait();
	}

	/**
	 * Handles the back button click event.
	 */
	private void handleBackButton() {
		try {
			main.showMenuScene();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Initializes the controller, setting up event listeners and populating the UI
	 * components.
	 */
	@FXML
	public void initialize() {
		populateChoiceBoxes();
		addCourseButton.setOnAction(event -> onAddCourseButtonClicked());
		compileButton.setOnAction(event -> {
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("Confirm Compile");
			alert.setHeaderText("Are you sure you want to compile?");
			alert.setContentText("Press OK to compile, or cancel to continue editing.");

			Optional<ButtonType> result = alert.showAndWait();
			if (result.isPresent() && result.get() == ButtonType.OK) {
				onCompileButtonClicked();
			}
		});
		backButton.setOnAction(event -> {
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("Confirm Cancel");
			alert.setHeaderText("Are you sure you want to cancel? File will not be saved");
			alert.setContentText("Press OK to cancel, or Cancel to continue.");

			Optional<ButtonType> result = alert.showAndWait();
			if (result.isPresent() && result.get() == ButtonType.OK) {
				handleBackButton();
			}
		});
	}
}