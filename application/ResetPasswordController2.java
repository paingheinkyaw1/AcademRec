/**
 * This class represents the ResetPasswordController2 for the application.
 *
 * @author Your Name
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
 * ResetPasswordController2 is a JavaFX controller class that handles the functionality
 * of the Reset Password screen for new users.
 */
public class ResetPasswordController2 {
    private Main main;

	@FXML Button changePass;
	@FXML Label wrongPass;
	@FXML PasswordField passwordField;
	@FXML PasswordField checkPassword;
	
	/**
	 * Sets the main application reference to the provided Main instance.
	 *
	 * @param main The main application instance.
	 */
	public void setMainApp(Main main) {
	    this.main = main;
	}
	/**
	 * Handles the password reset process for new users by calling the createPassword() method.
	 *
	 * @param event The ActionEvent triggered by the reset password button.
	 * @throws IOException If an error occurs while accessing the password file.
	 */
	public void resetPassword(ActionEvent event) throws IOException{
		createPassword();
	}
	
	/**
	 * Processes the reset password operation for new users by confirming the new password
	 * and updating the password file.
	 *
	 * @throws IOException If an error occurs while accessing the password file.
	 */
	public void createPassword() throws IOException{
		String newPassword = passwordField.getText();
		String confirmPassword = checkPassword.getText();
		
		if(confirmPassword.equals(newPassword)) {
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("Confirm Change");
			alert.setContentText("Are you sure you want to change password?");
			alert.showAndWait().ifPresent((btnType) -> {
			  if (btnType == ButtonType.OK) {
					try {
						File pFile = new File("src/password.txt");
						FileWriter pWriter = new FileWriter(pFile, false);
						pWriter.write(newPassword);
						pWriter.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					try {
						main.showLoginScene();
					} catch (Exception e) {
						e.printStackTrace();
					}
				} else if (btnType == ButtonType.CANCEL) {
				    passwordField.clear();
				    checkPassword.clear();
				    wrongPass.setText("");
				  }
			}); 
			} else {
			wrongPass.setText("Passwords don't match.");
		}
	}
	
	
}