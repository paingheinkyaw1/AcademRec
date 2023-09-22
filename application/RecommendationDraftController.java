/**
 * This class represents the RecommendationDraftController for the application.
 *
 * @author Sophia Atendido
 * @author Lizett Gonzalez
 * @author Paing Hein Kyaw
 * @author Tiffany Pham
 * @version 1.0
 * @since 2023-05-03
 */
package application;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextArea;
import javafx.scene.control.Alert.AlertType;

/**
 * Controller for the Recommendation Draft application.
 */
public class RecommendationDraftController {
	private Main main;
	private RecommendationManager recommendationManager;
	private String studentFirstName;
	private String studentLastName;

	@FXML
	private TextArea recommendationTextArea;
	@FXML
	private Button saveButton;
	@FXML
	private Button backButton;

	/**
	 * Constructor for the RecommendationDraftController.
	 *
	 * @param recommendationManager the recommendation manager
	 * @param studentFirstName      the student's first name
	 * @param studentLastName       the student's last name
	 */
	public RecommendationDraftController(RecommendationManager recommendationManager, String studentFirstName,
			String studentLastName) {
		this.recommendationManager = recommendationManager;
	    this.studentFirstName = studentFirstName;
	    this.studentLastName = studentLastName;
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
	 * Sets the recommendation text in the TextArea.
	 *
	 * @param text the text to be set in the recommendation TextArea
	 */
	public void setRecommendationText(String text) {
		recommendationTextArea.setText(text);
	}

	/**
	 * Handles the save button click event.
	 */
	private void onSaveButtonClicked() {

		String recommendationText = recommendationTextArea.getText();
		if (!recommendationText.isEmpty()) {
			String fileName;
			fileName =studentLastName + "_" + studentFirstName + ".txt";
			Path filePath = Paths.get("CompiledRecommendations", fileName);

	        try {
	            Files.createDirectories(filePath.getParent());
	            Files.write(filePath, recommendationText.getBytes());
	            showAlert(Alert.AlertType.INFORMATION, "Recommendation Saved", "Recommendation saved successfully.");
	            System.out.println("File " + fileName + " saved successfully!");
	            recommendationManager.addRecommendationFile(fileName);
	            System.out.println("filenamelist: " + recommendationManager.getRecommendationFileNames());

	            recommendationManager.lastNameFileMap.put(studentLastName, fileName);
	            System.out.println("hashmap here: " + recommendationManager.lastNameFileMap);
	            try {
	                main.showMenuScene();
	            } catch (Exception e) {
	                e.printStackTrace();
	            }

	        } catch (IOException e) {
	            showAlert(Alert.AlertType.ERROR, "Error Saving Recommendation",
	                    "An error occurred while saving the recommendation. Please try again.");
	            System.out.println("Failed to save " + fileName + " file!");
	        }
	    } else {
	        showAlert(Alert.AlertType.ERROR, "No Recommendation",
	                "The recommendation text area is empty. Please generate the recommendation first.");
	    }
	}

	/**
	 * Handles the back button click event.
	 */
	private void handleBackButton() {
		try {
			main.showRecommendationScene();
		} catch (Exception e) {
			e.printStackTrace();
		}
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
	 * Initializes the controller, setting up event listeners.
	 */
	@FXML
	public void initialize() {
		saveButton.setOnAction(event -> onSaveButtonClicked());
		backButton.setOnAction(event -> {
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("Confirm Cancel");
			alert.setHeaderText("Are you sure you want to cancel?");
			alert.setContentText("Press OK to cancel, or Cancel to continue.");

			Optional<ButtonType> result = alert.showAndWait();
			if (result.isPresent() && result.get() == ButtonType.OK) {
				handleBackButton();
			}
		});

	}
}
