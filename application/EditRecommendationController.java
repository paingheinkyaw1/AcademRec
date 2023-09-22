/**
 * This class represents the EditRecommendationController for the application.
 *
 * @author Sophia Atendido
 * @author Lizett Gonzalez
 * @author Paing Hein Kyaw
 * @author Tiffany Pham
 * @version 1.0
 * @since 2023-05-03
 */
package application;

import java.io.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

/**
 * EditRecommendationController is responsible for handling the editing of
 * recommendation letters. It enables users to search for a specific
 * recommendation, load its contents, make changes, and save the changes to the
 * file.
 */
public class EditRecommendationController {
	private Main main;
	private SharedDataModel sharedDataModel;
	private RecommendationManager recommendationManager;
	private String selectedFile;

	@FXML
	Label titleLabel;
	@FXML
	Button backButton;
	@FXML
	TextField studentLastNameTextField;
	@FXML
	private ListView<String> fileListView;
	@FXML
	Button searchButton;
	@FXML
	TextArea resultsTextArea;
	@FXML
	Button saveButton;
	@FXML
	Button editButton;
	@FXML
	Button deleteButton;

	/**
	 * Initializes the EditRecommendationController by setting up the necessary
	 * event listeners and setting the contents of the text area.
	 */
	public EditRecommendationController(RecommendationManager recommendationManager, String selectedFile) {
		this.selectedFile = selectedFile;
		this.recommendationManager = recommendationManager;
	}

	/**
	 * Sets the RecommendationManager object for this controller.
	 * 
	 * @param recommendationManager The RecommendationManager object.
	 */
	public void setRecommendationManager(RecommendationManager recommendationManager) {
		this.recommendationManager = recommendationManager;
	}

	/**
	 * Sets the main application object for this controller.
	 * 
	 * @param main The main application object.
	 */
	public void setMainApp(Main main) {
		this.main = main;
	}
	
	/**
	 * Displays an alert dialog with the specified type, title, and message.
	 * 
	 * @param alertType The type of alert dialog.
	 * @param title     The title of the alert dialog.
	 * @param message   The message of the alert dialog.
	 */
	private void showAlert(Alert.AlertType alertType, String title, String message) {
		Alert alert = new Alert(alertType);
		alert.setTitle(title);
		alert.setHeaderText(null);
		alert.setContentText(message);
		alert.showAndWait();
	}

	/**
	 * Initializes the controller by setting up the necessary event listeners and 
	 * setting the contents of the text area.
	 */
	@FXML
	public void initialize() {
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
		saveButton.setOnAction(event -> handleSaveButton());
		setTextArea();

	}

	/**
	 * Handles the action of clicking the back button.
	 */
	@FXML
	public void handleBackButton() {
		try {
			main.showSearchRecommendationScene();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Populates the text area with the contents of the selected file.
	 */
	@FXML
	public void setTextArea() {
	    if (selectedFile != null) {
	        String text = "";
	        try {
	            Path path = Paths.get("CompiledRecommendations", selectedFile);
	            BufferedReader br = new BufferedReader(new FileReader(path.toFile()));
	            String line;
	            while ((line = br.readLine()) != null) {
	                text += line + '\n';
	            }
	            resultsTextArea.setText(text);
	            br.close();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    } else {
	        System.out.println("File not found");
	    }
	}

	/**
	 * Handles the action of clicking the save button.
	 */
	@FXML
	public void handleSaveButton() {
		if (selectedFile != null) {
			try {
	            Path filePath = Paths.get("CompiledRecommendations", selectedFile);
	            BufferedWriter writer = Files.newBufferedWriter(filePath);
				String text = resultsTextArea.getText();
				writer.write(text);
				writer.close();
				showAlert(Alert.AlertType.INFORMATION, "File Saved", "The file has been successfully Saved.");
				try {
					main.showMenuScene();
				} catch (Exception e) {
					e.printStackTrace();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
