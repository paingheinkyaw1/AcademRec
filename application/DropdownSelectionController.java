/**
 /**
 * This class represents the DropdownSelectionController for the application.
 *
 * @author Sophia Atendido
 * @author Lizett Gonzalez
 * @author Paing Hein Kyaw
 * @author Tiffany Pham
 * @version 1.0
 * @since 2023-05-03
 */
package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

/**
 * DropdownSelectionController is responsible for managing the dropdown
 * selections in the application. It allows users to add new items to various
 * list views and edit faculty data.
 */
public class DropdownSelectionController {
	private Main main;
	private SharedDataModel sharedDataModel;
	private Faculty faculty;

	@FXML
	private Button compileButton;
	@FXML
	private Button compileButton1;
	@FXML
	private Button backButton;

	@FXML
	private ListView<String> programListView;
	@FXML
	private ListView<String> personalCharacteristicsListView;
	@FXML
	private ListView<String> academicCharacteristicsListView;
	@FXML
	private ListView<String> coursesListView;
	@FXML
	private ListView<String> semesterListView;

	@FXML
	private Label fullNameLabel;
	@FXML
	private Label titleLabel;
	@FXML
	private Label schoolLabel;
	@FXML
	private Label departmentLabel;
	@FXML
	private Label emailLabel;
	@FXML
	private Label phoneLabel;
	@FXML
	private Label errorLabel;

	@FXML
	private TextField programTextField;
	@FXML
	private TextField personalCharacteristicsTextField;
	@FXML
	private TextField academicCharacteristicsTextField;
	@FXML
	private TextField coursesTextField;
	@FXML
	private TextField semesterTextField;

	@FXML
	private TextField fullNameTextField;
	@FXML
	private TextField titleTextField;
	@FXML
	private TextField schoolTextField;
	@FXML
	private TextField departmentTextField;
	@FXML
	private TextField emailTextField;
	@FXML
	private TextField phoneNumberTextField;

	/**
	 * Constructor for the DropdownSelectionController.
	 * 
	 */
	public DropdownSelectionController() {
	}

	/**
	 * Constructor for the DropdownSelectionController.
	 * 
	 * @param sharedDataModel the shared data model
	 */
	public DropdownSelectionController(SharedDataModel sharedDataModel) {
		this.sharedDataModel = sharedDataModel;
	}

	/**
	 * Sets the main application object for this controller.
	 * 
	 * @param main The main application object.
	 */
	public void setMainApp(Main main) {
		this.main = main;
		this.sharedDataModel = main.getSharedDataModel();
	}

	private void addItemToListView(TextField textField, ListView<String> listView, Runnable saveOption) {
		String text = textField.getText().trim();
		if (!text.isEmpty()) {
			listView.getItems().add(text);
			textField.clear();
			saveOption.run();
		}
	}

	private boolean areAllTextFieldsEmpty() {
		return programTextField.getText().trim().isEmpty()
				&& personalCharacteristicsTextField.getText().trim().isEmpty()
				&& academicCharacteristicsTextField.getText().trim().isEmpty()
				&& coursesTextField.getText().trim().isEmpty() && semesterTextField.getText().trim().isEmpty()
				&& fullNameTextField.getText().trim().isEmpty() && titleTextField.getText().trim().isEmpty()
				&& schoolTextField.getText().trim().isEmpty() && departmentTextField.getText().trim().isEmpty()
				&& emailTextField.getText().trim().isEmpty() && phoneNumberTextField.getText().trim().isEmpty();

	}

	private void onCompileButtonClicked(ActionEvent event) {

		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Confirm Change");
		alert.setContentText("Are you sure you want to save changes?");
		alert.showAndWait().ifPresent((btnType) -> {
			if (btnType == ButtonType.OK) {
				addItemToListView(programTextField, programListView, this::saveNewProgram);
				addItemToListView(personalCharacteristicsTextField, personalCharacteristicsListView,
						this::saveNewPersonalCharacteristic);
				addItemToListView(academicCharacteristicsTextField, academicCharacteristicsListView,
						this::saveNewAcademicCharacteristic);
				addItemToListView(coursesTextField, coursesListView, this::saveNewCourse);
				addItemToListView(semesterTextField, semesterListView, this::saveNewSemester);

				String fullName = fullNameTextField.getText();
				String[] nameParts = fullName.split(" ");
				String firstName = nameParts.length >= 1 ? nameParts[0] : "";
				String lastName = nameParts.length >= 2 ? nameParts[1] : "";
				String title = titleTextField.getText();
				String school = schoolTextField.getText();
				String department = departmentTextField.getText();
				String email = emailTextField.getText();
				String phoneNumber = phoneNumberTextField.getText();

				boolean isFacultyFieldsFilled = !fullName.isEmpty() && !title.isEmpty() && !school.isEmpty()
						&& !department.isEmpty() && !email.isEmpty() && !phoneNumber.isEmpty();
				boolean isFacultyFieldsEmpty = fullName.isEmpty() && title.isEmpty() && school.isEmpty()
						&& department.isEmpty() && email.isEmpty() && phoneNumber.isEmpty();

				if (isFacultyFieldsFilled) {

					if (!email.matches("[^@]+@[^@]+\\.[^@]+")) {
						errorLabel.setText("Please enter a valid email address.");
						return;
					}

					if (!phoneNumber.matches("\\d{10}")) {
						errorLabel.setText("Please enter a valid 10-digit phone number.");
						return;
					}

					errorLabel.setText("");
					if (faculty == null) {
						faculty = new Faculty(firstName, lastName, title, school, department, email, phoneNumber);
					} else {
						if (nameParts.length >= 2) {
							faculty.setFirstName(nameParts[0]);
							faculty.setLastName(nameParts[1]);
						}
						faculty.setRole(title);
						faculty.setSchool(school);
						faculty.setDepartment(department);
						faculty.setEmail(email);
						faculty.setPhone(phoneNumber);
					}
					sharedDataModel.setFaculty(faculty);
					saveFullName();
					saveTitle();
					saveSchool();
					saveDepartment();
					saveEmail();
					savePhone();
				} else if ((!isFacultyFieldsFilled) && (!isFacultyFieldsEmpty)) {
					Alert alert2 = new Alert(AlertType.ERROR);
					alert2.setTitle("Error");
					alert2.setHeaderText("Please fill in all faculty fields before saving changes.");
					alert2.showAndWait();
				}

				sharedDataModel.setProgramOptions(programListView.getItems());
				sharedDataModel.setPersonalCharacterOptions(personalCharacteristicsListView.getItems());
				sharedDataModel.setAcademicCharacterOptions(academicCharacteristicsListView.getItems());
				sharedDataModel.setCourseOptions(coursesListView.getItems());
				sharedDataModel.setSemesterOptions(semesterListView.getItems());

			}
		});
	}

	public void saveFullName() {
		String fullName = fullNameTextField.getText();
		if (!fullName.isEmpty()) {
			fullNameLabel.setText(fullName);
			errorLabel.setText("");
			fullNameTextField.clear();

		}
	}

	public void saveTitle() {
		String title = titleTextField.getText();
		if (!title.isEmpty()) {
			titleLabel.setText(title);
			errorLabel.setText("");
			titleTextField.clear();
		}
	}

	public void saveSchool() {
		String school = schoolTextField.getText();
		if (!school.isEmpty()) {
			schoolLabel.setText(school + ", ");
			errorLabel.setText("");
			schoolTextField.clear();
		}
	}

	public void saveDepartment() {
		String department = departmentTextField.getText();
		if (!department.isEmpty()) {
			departmentLabel.setText(department);
			errorLabel.setText("");
			departmentTextField.clear();
		}
	}

	public void saveEmail() {
		String email = emailTextField.getText();
		if ((email.contains("@")) && (email.contains(".")) && (!email.isEmpty())) {
			emailLabel.setText(email);
			emailTextField.clear();
			errorLabel.setText("");

		} else if ((!email.isEmpty())) {
			errorLabel.setText("Please write a valid email");
		}
	}

	public void savePhone() {
		String phone = phoneNumberTextField.getText();
		int count = phone.length();
		if ((phone.matches("\\d*")) && (!phone.isEmpty()) && (count == 10)) {
			phoneLabel.setText(phone);
			phoneNumberTextField.clear();
			errorLabel.setText("");

		} else if ((!phone.isEmpty())) {
			errorLabel.setText("Please write a valid phone number");
		}
	}

	public void saveNewProgram() {
		String newProgram = programTextField.getText();

		if (!newProgram.isEmpty()) {
			sharedDataModel.getProgramOptions().add(newProgram);
			sharedDataModel.saveDataToFile("programOptions.txt", sharedDataModel.getProgramOptions());
		}
	}

	public void saveNewPersonalCharacteristic() {
		String newPersonalCharacteristic = personalCharacteristicsTextField.getText();

		if (!newPersonalCharacteristic.isEmpty()) {
			sharedDataModel.getPersonalCharacterOptions().add(newPersonalCharacteristic);
			sharedDataModel.saveDataToFile("personalCharacterOptions.txt",
					sharedDataModel.getPersonalCharacterOptions());
		}
	}

	public void saveNewAcademicCharacteristic() {
		String newAcademicCharacteristic = academicCharacteristicsTextField.getText();

		if (!newAcademicCharacteristic.isEmpty()) {
			sharedDataModel.getAcademicCharacterOptions().add(newAcademicCharacteristic);
			sharedDataModel.saveDataToFile("academicCharacterOptions.txt",
					sharedDataModel.getAcademicCharacterOptions());
		}
	}

	public void saveNewCourse() {
		String newCourse = coursesTextField.getText();

		if (!newCourse.isEmpty()) {
			sharedDataModel.getCourseOptions().add(newCourse);
			sharedDataModel.saveDataToFile("coursesOptions.txt", sharedDataModel.getCourseOptions());
		}
	}

	public void saveNewSemester() {
		String newSemester = semesterTextField.getText();

		if (!newSemester.isEmpty()) {
			sharedDataModel.getSemesterOptions().add(newSemester);
			sharedDataModel.saveDataToFile("semesterOptions.txt", sharedDataModel.getSemesterOptions());
		}
	}

	private void loadFacultyData() {
		if (sharedDataModel.getFaculty() != null) {
			faculty = sharedDataModel.getFaculty();
			fullNameLabel.setText(faculty.getFirstName() + " " + faculty.getLastName());
			titleLabel.setText(faculty.getRole());
			schoolLabel.setText(faculty.getSchool() + ", ");
			departmentLabel.setText(faculty.getDepartment());
			emailLabel.setText(faculty.getEmail());
			phoneLabel.setText(faculty.getPhone());
		}
	}

	private void handleBackButton() {
		try {
			main.showMenuScene();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Initializes the DropdownSelectionController by setting up the necessary event
	 * listeners and populating the list views.
	 */
	@FXML
	public void initialize() {
		compileButton.setOnAction(this::onCompileButtonClicked);
		compileButton1.setOnAction(this::onCompileButtonClicked);
		backButton.setOnAction(event -> handleBackButton());

		programListView.setItems(sharedDataModel.getProgramOptions());
		personalCharacteristicsListView.setItems(sharedDataModel.getPersonalCharacterOptions());
		academicCharacteristicsListView.setItems(sharedDataModel.getAcademicCharacterOptions());
		coursesListView.setItems(sharedDataModel.getCourseOptions());
		semesterListView.setItems(sharedDataModel.getSemesterOptions());

		sharedDataModel = main.getSharedDataModel();
		loadFacultyData();
	}

	@FXML
	private void handleEditSelections() {
		try {
			main.showEditSelectionScene();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}