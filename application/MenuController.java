/**
 * This class represents the MenuController for the application.
 *
 * @author Sophia Atendido
 * @author Lizett Gonzalez
 * @author Paing Hein Kyaw
 * @author Tiffany Pham
 * @version 1.0
 * @since 2023-05-03
 */
package application;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

/**
 * The MenuController class is responsible for managing the user interface of
 * the main menu. It provides functionality for navigating between different
 * scenes in the application.
 */
public class MenuController {
	private Main main;

	@FXML
	private Button createRecommendationButton;
	@FXML
	private Button editSelectionsButton;
	@FXML
	private Button changePasswordButton;
	@FXML
	private Button searchRecommendationButton;
	@FXML
	private Button logoutButton;

	/**
	 * Sets the Main object associated with this controller.
	 *
	 * @param main the Main object to be associated with this controller
	 */
	public void setMainApp(Main main) {
		this.main = main;
	}

	/**
	 * Handles the action when the create recommendation button is clicked. It
	 * navigates the user to the recommendation creation scene.
	 */
	public void handleCreateRecommendation() {
		try {
			main.showRecommendationScene();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Handles the action when the edit selections button is clicked. It navigates
	 * the user to the edit selection scene.
	 */
	public void handleEditSelections() {
		try {
			main.showEditSelectionScene();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Handles the action when the change password button is clicked. It navigates
	 * the user to the change password scene.
	 */
	public void handleChangePassword() {
		try {
			main.showChangePasswordScene();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Handles the action when the search recommendation button is clicked. It
	 * navigates the user to the search recommendation scene.
	 */
	public void handleSearchRecommendation() {
		try {
			main.showSearchRecommendationScene();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Handles the action when the logout button is clicked. It navigates the user
	 * to the login scene.
	 */
	public void handleLogout() {
		try {
			main.showLoginScene();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Initializes the controller by setting event listeners for the buttons.
	 */
	@FXML
	public void initialize() {
		createRecommendationButton.setOnAction(event -> handleCreateRecommendation());
		editSelectionsButton.setOnAction(event -> handleEditSelections());
		changePasswordButton.setOnAction(event -> handleChangePassword());
		logoutButton.setOnAction(event -> handleLogout());
		searchRecommendationButton.setOnAction(event -> handleSearchRecommendation());
	}
}
