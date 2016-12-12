package application;
	
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.media.*;

public class Main extends Application {
	Stage primaryStage;
	private StackPane stackPane;
	private Scene scene;
	private Connection connection;
	private Media media;
	private MediaPlayer mediaPlayer;
	private MediaView mediaView;
	private LoginController loginController;
	private DBTabController dbTabController;
//	private MainTabController mainTabController;
	private String userName;
	private String userID;
//	private MainTabController mainTabController;
	@Override
	public void start(Stage primaryStage) {
		try {
			this.primaryStage = primaryStage;
			this.primaryStage.setTitle("EOVSA Tohban Database Management System");
			showLoginView();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
		
	public void showLoginView() throws IOException {
		stackPane = new StackPane();
		// load login trailer
		media = new Media(getClass().getClassLoader()
	             .getResource("ALMA.mp4").toString());
	    mediaPlayer = new MediaPlayer(media);
	    mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
	    mediaPlayer.play();
	    mediaView = new MediaView(mediaPlayer);
	    stackPane.getChildren().add(mediaView);
	    // load login.fxml
	    FXMLLoader loader = new FXMLLoader(getClass().getResource("login.fxml"));
		Pane root = loader.load();
		// register the controller
        loginController = (LoginController)loader.getController();
        // register the main
        loginController.setMain(this);
        // add root to scene
		stackPane.getChildren().add(root);
		scene = new Scene(stackPane,1200,700);
		scene.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                switch (event.getCode()) {
                    case ENTER: try {
						loginController.loginButtonClicked();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				default:
					break;
                }
            }
        });
		primaryStage.setScene(scene);
		primaryStage.show();
		// connect to the database
//		MysqlConnector mysqlConnector = new MysqlConnector();
//		System.out.println("Connecting to the database now.");
//		mysqlConnector.connect();
//		System.out.println(connection);
	}
	
	public LoginController getLoginController() {
		return loginController;
	}
	
	public void setDBTabController(DBTabController dbTabController) {
		this.dbTabController = dbTabController;
	}
	
	
	public void showMainView() throws IOException {
		// load login.fxml
//		FXMLLoader loader = new FXMLLoader(getClass().getResource("dataTab.fxml"));
//		Parent root = loader.load();
//		dbTabController = (DBTabController) loader.getController();
//		dbTabController.setMain(this);
//		dbTabController.setUserName(userName);
//		dbTabController.setUserID(userID);
//		scene = new Scene(root,1200,700);
//		primaryStage.setScene(scene);
//		primaryStage.show();
		
		FXMLLoader loader2 = new FXMLLoader(getClass().getResource("mainView.fxml"));
		Parent root2 = loader2.load();
		dbTabController = (DBTabController) loader2.getController();
		System.out.println(dbTabController);
		dbTabController.setMain(this);
		dbTabController.setUserName(userName);
		dbTabController.setUserID(userID);	
		scene = new Scene(root2,1200,700);
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	
	public void setConnector(Connection connection) {
		this.connection = connection;
	}
	
	public Connection getConnector() {
		return this.connection;
	}
	
	public void setUserName(String userName) {
		this.userName = userName; 
	}
	
	public void setUserID(String userID) {
		this.userID = userID; 
	}
	
	public String getUserID() {
		return userID;
	}
	
	public String getUserName() {
		return userName;
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
