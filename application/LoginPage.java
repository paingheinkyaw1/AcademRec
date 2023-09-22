/**
 * This class represents the LoginPage for the application.
 *
 * @author Sophia Atendido
 * @author Lizett Gonzalez
 * @author Paing Hein Kyaw
 * @author Tiffany Pham
 * @version 1.0
 * @since 2023-05-03
 */
package application;

/**
 * Represents a login page for user authentication. Handles the login process,
 * password changes, and logout actions.
 */
public class LoginPage {
	private String password;
	private String defaultPassword;
	private int loginCount;
	private boolean loggedIn;
	private boolean firstLogin;

	/**
	 * Default constructor for the LoginPage class. Initializes the login page with
	 * a default password and sets the login count to 1.
	 */
	public LoginPage() {
		this.defaultPassword = "p";
		this.password = defaultPassword;
		this.loginCount = 1;
		this.loggedIn = false;
		this.firstLogin = true;
	}

	/**
	 * Attempts to log in with the given password.
	 * 
	 * @param password The password to be checked.
	 * @return true if the password is correct and the user is logged in, false
	 *         otherwise.
	 */
	public boolean login(String password) {
		if (this.password.equals(password)) {
			if (firstLogin) {
				firstLogin = false;
				loggedIn = false;
				return false;
			}
			loggedIn = true;
			loginCount++;
			return true;
		}
		loggedIn = false;
		return false;
	}

	/**
	 * Checks if the user is currently logged in.
	 * 
	 * @return true if the user is logged in, false otherwise.
	 */
	public boolean isLoggedIn() {
		return loggedIn;
	}

	/**
	 * Logs the user out by setting the loggedIn flag to false.
	 */
	public void logout() {
		loggedIn = false;
	}

	/**
	 * Changes the user's password if the old password is correct.
	 * 
	 * @param oldPassword The user's current password.
	 * @param newPassword The new password to be set.
	 * @return true if the password change is successful, false otherwise.
	 */
	public boolean changePassword(String oldPassword, String newPassword) {
		if (password.equals(oldPassword)) {
			password = newPassword;
			return true;
		}
		return false;
	}

	/**
	 * Retrieves the login count.
	 * 
	 * @return The number of times the user has logged in.
	 */
	public int getLoginCount() {
		return loginCount;
	}

	/**
	 * Checks if this is the first login attempt.
	 * 
	 * @return true if it is the first login attempt, false otherwise.
	 */
	public boolean isFirstLogin() {
		return (loginCount == 1);
	}
}
