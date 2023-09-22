/**
 * This class represents the ResetPasswordController for the application.
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

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;

/**
 * ResetPasswordController is a JavaFX controller class that handles the functionality
 * of the Change Password screen.
 */
public class ResetPasswordController {
    private Main main;

	@FXML Button backButton;
	@FXML Button changePass;
	@FXML Label wrongPass;
	@FXML PasswordField passwordField2;
	@FXML PasswordField checkPassword;
	@FXML PasswordField passwordField1;
	@FXML PasswordField oldPassword2;
	
	/**
	 * Initializes the ResetPasswordController by setting the action listener for the backButton.
	 */
	public void initialize() {
		backButton.setOnAction(event -> handleBackButton());
		
	}

    /**
     * Sets the main application reference to the provided Main instance.
     *
     * @param main The main application instance.
     */
    public void setMainApp(Main main) {
        this.main = main;
    }
    
    /**
     * Handles the password reset process by calling the createPassword() method.
     *
     * @param event The ActionEvent triggered by the reset password button.
     * @throws IOException If an error occurs while accessing the password file.
     */
	public void resetPassword(ActionEvent event) throws IOException{
		createPassword();
	}
	
	/**
	 * Processes the reset password operation by checking the old password, confirming the new password,
	 * and updating the password file.
	 *
	 * @throws IOException If an error occurs while accessing the password file.
	 */
	public void createPassword() throws IOException{
		try {
			
			
			String oldPassword = passwordField1.getText();
			String newPassword = passwordField2.getText();
			String checkNewPass = checkPassword.getText();
			
			File pFile = new File("src/password.txt");
			BufferedReader reader = new BufferedReader(new FileReader(pFile));
			String storedPassword = reader.readLine();
			reader.close();
			
			if(storedPassword.equals(oldPassword)) {
				if(checkNewPass.equals(newPassword)) {
					Alert alert = new Alert(AlertType.CONFIRMATION);
					alert.setTitle("Confirm Change");
					alert.setContentText("Are you sure you want to change password?");
					alert.showAndWait().ifPresent((btnType) -> {
					  if (btnType == ButtonType.OK) {
							try {
								FileWriter pWriter = new FileWriter(pFile, false);
								pWriter.write(newPassword);
								pWriter.close();
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							
							try {
								main.showMenuScene();
							} catch (Exception e) {
								e.printStackTrace();
							}
					  } else if (btnType == ButtonType.CANCEL) {
					    passwordField1.clear();
					    passwordField2.clear();
					    checkPassword.clear();
					    wrongPass.setText("");
					  }
					});
				} else {
					wrongPass.setText("New passwords don't match.");
				}
			} else {
				wrongPass.setText("Old passwords don't match.");
			}
		} catch(IOException e) {
			System.out.println("An error occurred while writing to the file: " + e.getMessage());
			e.printStackTrace();
		}
	}

	/**
	 * Handles the backButton click event by navigating back to the Menu scene.
	 */
	public void handleBackButton() {
		try {
			main.showMenuScene();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}