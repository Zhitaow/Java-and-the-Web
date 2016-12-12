package application;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
//import com.mysql.jdbc.*;
import javafx.fxml.*;
import javafx.scene.control.*;
import application.Main;

public class LoginController implements Runnable {
	protected Thread thread;
    @FXML private TextField userField;
    @FXML private PasswordField passwordField;
    @FXML private Button loginBtn;
	private Connection connection;
	private MysqlCmd mysqlCmd = new MysqlCmd();
    private List<String> userList, userFirstName, userLastName, passwordList;
    private Main main;
    private boolean exit = false;
    
    public void loginButtonClicked() throws SQLException, IOException {
    	// first made connection to the database
    	if (connection == null) {
            thread = new Thread(this);
            System.out.println("Connecting to the database now ...");
            thread.start();   
            try {
				thread.join(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}   		
    		// connect to the database
    		connection = MysqlCmd.connect();
            if (connection != null) {
            	System.out.println("The database is connected now.");
            	setConnector(connection);
            	main.setConnector(connection);
        		mysqlCmd = new MysqlCmd();
            } else {
            	System.out.println("The data base is offline. Check your network setting.");
            }
    	} 
    	// if it's connected, log user in
    	if (connection != null) {
        	userList = mysqlCmd.getColumnContents(connection, "user_information","user_id");
        	userFirstName = mysqlCmd.getColumnContents(connection, "user_information","first_name");
        	userLastName = mysqlCmd.getColumnContents(connection, "user_information","last_name");
        	passwordList = mysqlCmd.getColumnContents(connection, "user_information","password");
        	/*
        	 *  check if the input fields match with user credentials in the database
        	 *  1. yes then login user in
        	 *  2. no then prompt message to screen
        	 */
        	for (int i = 0; i < userList.size(); i++) {
        		if (userList.get(i).equals(getUserName())) {
        			if (passwordList.get(i).equals(getPassword())) {
        				System.out.println("Login successfully!");
        				main.setUserName(userFirstName.get(i) + " " + userLastName.get(i));
        				main.setUserID(userList.get(i));
        				// change the scene
        				main.showMainView();
        				// the life cycle of thread ends here
        				exit = true;
        				break;
        			} else {
        				System.out.println("Password not correct!");
        				passwordField.setText("");
        				break;
        			}
        		} else if (i == userList.size() - 1) {
        			System.out.println("User Name not Macth!");
        			userField.setText("");
        			passwordField.setText("");
        		}
        	}
        }
    }
    
    public void setMain(Main main) {
    	this.main = main;
    }
        
    public String getUserName() {
        return userField.getText();
    }

    public String getPassword() {
        return passwordField.getText();
    }

    public void setConnector(Connection connection) {
    	this.connection = connection;
    }
    
    public Connection getConnector() {
    	return connection;
    }

	@SuppressWarnings("static-access")
	@Override
    public void run() {
		while (!exit) {
            try {
            	thread.sleep(2000);
            	thread.interrupt();
    	    } catch (Exception e) {}
        }
    }
	
	public void stop() {
		exit = true;
	}
}
