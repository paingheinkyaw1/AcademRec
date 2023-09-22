/**
 * This class represents the SearchRecommendationController for the application.
 *
 * @author Your Name
 * @version 1.0
 * @since 2023-05-03
 */
package application;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.*;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 * The SearchRecommendationController class is the controller for the search
 * recommendation functionality in the application. It allows users to search
 * for recommendations based on the last name of a student, edit or delete
 * existing recommendation files.
 */
public class SearchRecommendationController {
	private Main main;
	private RecommendationManager recommendationManager;
	
	@FXML
	Label titleLabel;
	@FXML
	Button backButton;
	@FXML
	TextField studentLastNameTextField;
	@FXML
	ListView<String> fileListView;
	@FXML
	Button searchButton;
	@FXML
	TextArea resultsTextArea;
	@FXML
	Button editButton;
	@FXML
	Button deleteButton;

	/**
	 * Constructs a new SearchRecommendationController with the specified RecommendationManager.
	 *
	 * @param recommendationManager the RecommendationManager to be used by this controller
	 */
	public SearchRecommendationController(RecommendationManager recommendationManager) {
		this.recommendationManager = recommendationManager;

	}

	/**
	 * Sets the RecommendationManager to be used by this controller.
	 *
	 * @param recommendationManager the RecommendationManager to set
	 */
	public void setRecommendationManager(RecommendationManager recommendationManager) {
		this.recommendationManager = recommendationManager;
	}

	/**
	 * Sets the Main instance associated with this controller.
	 *
	 * @param main the Main instance to set
	 */
	public void setMainApp(Main main) {
		this.main = main;
	}

	/**
	 * Shows an alert with the specified type, title, and message.
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
	 * Initializes the SearchRecommendationController, setting up event handlers for
	 * the various UI components.
	 */
	@FXML
	public void initialize() {
		backButton.setOnAction(event -> handleBackButton());
		editButton.setOnAction(event -> handleEditButton());
		searchButton.setOnAction(event -> handleSearchButton());
		deleteButton.setOnAction(event -> handleDeleteButton());

		if (recommendationManager == null) {
			System.out.println("recommendationManager is null");
		}
		List<String> fileNames = recommendationManager.getRecommendationFileNames();
		if (fileNames == null || fileNames.isEmpty()) {
			System.out.println("fileNames is null or empty");
		} else {
			System.out.println("fileNames: " + fileNames);
		}
	}

	/**
	 * Handles the "Back" button, returning the user to the main menu.
	 */
	@FXML
	public void handleBackButton() {
		try {
			main.showMenuScene();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Handles the "Search" button, searching for recommendation files based on the
	 * last name entered by the user.
	 */
	@FXML
	public void handleSearchButton() {
		String lastName = studentLastNameTextField.getText().trim();

		if (lastName == null || lastName.trim().isEmpty()) {
			showAlert(Alert.AlertType.ERROR, "No name entered", "Please enter a last name.");
		} else {
			fileListView.refresh();
			List<String> fileNames = recommendationManager.getRecommendationFileNames();
			ObservableList<String> filteredFiles = FXCollections.observableArrayList();
			for (String fileName : fileNames) {
	            if (fileName.toLowerCase().startsWith(lastName.toLowerCase())) {
					filteredFiles.add(fileName);
				}
			}
			if (filteredFiles.isEmpty()) {
				showAlert(Alert.AlertType.INFORMATION, "No files found",
						"No files found with the last name '" + lastName + "'.");
			} else {
				fileListView.setItems(filteredFiles);
			}
		}
	}

	/**
	 * Handles the "Edit" button, allowing the user to edit the selected
	 * recommendation file.
	 */
	@FXML
	public void handleEditButton() {

		String selectedFile = fileListView.getSelectionModel().getSelectedItem();

		if (selectedFile == null) {
			showAlert(Alert.AlertType.ERROR, "No file selected", "Please select a file to edit.");
		} else {
            Path filePath = Paths.get("CompiledRecommendations", selectedFile);
			if (Files.notExists(filePath)) {
				showAlert(Alert.AlertType.ERROR, "File Not Found", "The selected file could not be found.");
			} else {
				try {
					main.showEditRecommendationScene(selectedFile);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * Handles the "Delete" button, allowing the user to delete the selected
	 * recommendation file.
	 */
	@FXML
	public void handleDeleteButton() {

		String selectedFile = fileListView.getSelectionModel().getSelectedItem();

		if (selectedFile == null) {
			showAlert(Alert.AlertType.ERROR, "No file selected", "Please select a file to delete.");
		} else {
			Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
			alert.setTitle("Delete File");
			alert.setHeaderText("Are you sure you want to delete this file?");
			alert.setContentText(selectedFile);
			Optional<ButtonType> result = alert.showAndWait();

			if (result.isPresent() && result.get() == ButtonType.OK) {
				try {
					Path filePath = Paths.get("CompiledRecommendations", selectedFile);
					Files.delete(filePath);
					showAlert(Alert.AlertType.INFORMATION, "File deleted", "The file has been successfully deleted.");

					fileListView.getItems().remove(selectedFile);
					recommendationManager.removeRecommendationFile(selectedFile);

					try {
						main.showMenuScene();
					} catch (Exception e) {
						e.printStackTrace();
					}
				} catch (IOException e) {
					showAlert(Alert.AlertType.ERROR, "File deletion error",
							"An error occurred while trying to delete the file.");
					e.printStackTrace();
				}
			}
		}
	}
}
