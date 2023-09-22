/**
 * This class represents the LoginController for the application.
 *
 * @author Sophia Atendido
 * @author Lizett Gonzalez
 * @author Paing Hein Kyaw
 * @author Tiffany Pham
 * @version 1.0
 * @since 2023-05-03
 */
package application;

import java.io.BufferedReader;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * LoginController is responsible for handling user authentication. It allows
 * users to enter their password and validates it against a stored password.
 */
public class LoginController {
	private Main main;
	public static int loginCounter = 0;

	@FXML
	Button login;
	@FXML
	Label wrongLogin;
	@FXML
	TextField passwordField;

	/**
	 * Sets the main application object for this controller.
	 * 
	 * @param main The main application object.
	 */
	public void setMainApp(Main main) {
		this.main = main;
	}

	/**
	 * Handles the user login action.
	 * 
	 * @param event The event triggered by the user login action.
	 * @throws IOException If an error occurs while reading the password file.
	 */
	public void userLogin(ActionEvent event) throws IOException {
		checkLogin();
	}

	/**
	 * Checks the entered password against the stored password and grants access if
	 * they match.
	 * 
	 * @throws IOException If an error occurs while reading the password file.
	 */
	public void checkLogin() throws IOException {
		try {
			File pFile = new File("src/password.txt");
			boolean grantAccess = false;
			String enteredPassword = passwordField.getText();
			BufferedReader reader = new BufferedReader(new FileReader(pFile));
			String storedPassword = reader.readLine();
			reader.close();
			if (storedPassword.equals(enteredPassword)) {
				grantAccess = true;
			}
			try {
				if (grantAccess && (loginCounter == 0)) {
					main.showResetPasswordScene();
					loginCounter++;
				} else if (grantAccess && (loginCounter == 1)) {
					main.showMenuScene();
				} else {
					wrongLogin.setText("Wrong Password.");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
