package application;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Main class for the application. Handles the loading of various scenes and
 * manages the application's primary stage.
 */
public class Main extends Application {
	private SharedDataModel sharedDataModel;
	private RecommendationManager recommendationManager;
	private Stage primaryStage;

	/**
	 * The main method to launch the JavaFX application.
	 * 
	 * @param args Command line arguments.
	 */
	public static void main(String[] args) {
		launch(args);
	}

	/**
	 * Starts the JavaFX application and initializes the primary stage.
	 * 
	 * @param primaryStage The primary stage for this JavaFX application.
	 * @throws Exception If there is an issue loading the scenes.
	 */

	@Override
	public void start(Stage primaryStage) throws Exception {
		this.primaryStage = primaryStage;
		sharedDataModel = new SharedDataModel();
		recommendationManager =  new RecommendationManager(sharedDataModel);
		primaryStage.sizeToScene();
		primaryStage.show();
		primaryStage.setMinWidth(1024);
		primaryStage.setMinHeight(600);
		showLoginScene();
	}

	/**
	 * Displays the login scene.
	 * 
	 * @throws Exception if there's an error loading the FXML file
	 */
	public void showLoginScene() throws Exception {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/Login.fxml"));
		LoginController controller = new LoginController();
		controller.setMainApp(this);
		loader.setController(controller);
		Parent root = loader.load();
		Scene scene = new Scene(root, 1024, 600);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Login");
	}

	/**
	 * Displays the change password scene.
	 * 
	 * @throws Exception if there's an error loading the FXML file
	 */
	public void showChangePasswordScene() throws Exception {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("ChangePassword.fxml"));
		ResetPasswordController controller = new ResetPasswordController();
		controller.setMainApp(this);
		loader.setController(controller);
		Parent root = loader.load();
		Scene scene = new Scene(root, 1024, 600);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Change Password");
	}

	/**
	 * Displays the reset password scene.
	 * 
	 * @throws Exception if there's an error loading the FXML file
	 */
	public void showResetPasswordScene() throws Exception {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("ResetPassword.fxml"));
		ResetPasswordController2 controller = new ResetPasswordController2();
		controller.setMainApp(this);
		loader.setController(controller);
		Parent root = loader.load();
		Scene scene = new Scene(root, 1024, 600);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Reset Password");
	}

	/**
	 * Displays the menu scene.
	 * 
	 * @throws Exception if there's an error loading the FXML file
	 */
	public void showMenuScene() throws Exception {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("Menu.fxml"));
		MenuController controller = new MenuController();
		controller.setMainApp(this);
		loader.setController(controller);
		Parent root = loader.load();
		Scene scene = new Scene(root, 1024, 600);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Menu");
	}

	/**
	 * Displays the recommendation scene.
	 * 
	 * @throws Exception if there's an error loading the FXML file
	 */
	public void showRecommendationScene() throws Exception {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("Recommendation.fxml"));
		RecommendationController controller = new RecommendationController(sharedDataModel);
		controller.setMainApp(this);
		loader.setController(controller);
		Parent root = loader.load();
		Scene scene = new Scene(root, 1024, 600);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Create Recommendation");
	}

	/**
	 * Displays the edit selection scene.
	 * 
	 * @throws Exception if there's an error loading the FXML file
	 */
	public void showEditSelectionScene() throws Exception {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("DropdownSelection.fxml"));
		DropdownSelectionController controller = new DropdownSelectionController(sharedDataModel);
		controller.setMainApp(this);
		loader.setController(controller);
		Parent root = loader.load();
		Scene scene = new Scene(root, 1024, 600);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Edit Selections");
	}

	/**
	 * Displays the recommendation draft scene with the given recommendation and
	 * draft text.
	 * 
	 * @param recommendation      The recommendation object
	 * @param recommendationDraft The text of the recommendation draft
	 * @throws Exception if there's an error loading the FXML file
	 */
	public void showRecommendationDraftScene(Recommendation recommendation, String recommendationDraft)
			throws Exception {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("RecommendationDraft.fxml"));
		RecommendationDraftController controller = new RecommendationDraftController(recommendationManager,
				recommendation.getFirstName(), recommendation.getLastName());
		controller.setMainApp(this);
		loader.setController(controller);
		Parent root = loader.load();

		controller.setRecommendationText(recommendationDraft);

		Scene recommendationDraftScene = new Scene(root, 1024, 800);
		primaryStage.setScene(recommendationDraftScene);
		primaryStage.show();
	}

	/**
	 * Displays the edit recommendation scene for the specified file.
	 * 
	 * @param selectedFile The file containing the recommendation to be edited
	 * @throws Exception if there's an error loading the FXML file
	 */
	public void showEditRecommendationScene(String selectedFile) throws Exception {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("EditRecommendationScene.fxml"));
		EditRecommendationController controller = new EditRecommendationController(recommendationManager, selectedFile);
		controller.setMainApp(this);
		loader.setController(controller);
		Parent root = loader.load();
		Scene scene = new Scene(root, 1024, 800);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Edit Recommendation");
	}

	/**
	 * Displays the search recommendation scene.
	 */
	public void showSearchRecommendationScene() {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("SearchRecommendation.fxml"));
			SearchRecommendationController controller = new SearchRecommendationController(recommendationManager);
			controller.setMainApp(this);
			loader.setController(controller);
			Parent root = loader.load();
			Scene scene = new Scene(root, 1024, 600);
			primaryStage.setScene(scene);
			primaryStage.setTitle("Edit Recommendation");
			controller.setRecommendationManager(recommendationManager);
			primaryStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public SharedDataModel getSharedDataModel() {
		return sharedDataModel;
	}

	/**
	 * Retrieves the primary stage for this JavaFX application.
	 * 
	 * @return The primary stage.
	 */
	public Stage getPrimaryStage() {
		return primaryStage;
	}

}
