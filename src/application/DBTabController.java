package application;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.*;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

public class DBTabController {
//	private MainTabController mainTabController;
	private Main main;
	private String userID;
	private boolean isEdit = false;
	@FXML private Text messageText;
	@FXML private DatePicker datePicker;
	@FXML private BorderPane dbTab;
	@FXML private CheckBox ant01, ant02, ant03, ant04, ant05, 
		ant06, ant07, ant08, ant09, ant10, ant11, ant12, ant13, ant14, ant15;
	private List<CheckBox> antList = new ArrayList<CheckBox> ();
	private List<String> antIDList = new ArrayList<String> ();
	@FXML private Text dateText, logNameText;
	@FXML private ChoiceBox<String> flareClassCB;
	@FXML private TextField stText, etText;
	@FXML private TextArea noteText;
	@FXML private GridPane dataField;
	@FXML private ToggleButton editBtn;
	@FXML private Button loadBtn, uploadBtn, insertBtn, deleteBtn, logoutBtn;
	@FXML private TableColumn<Flare, String> col1, col2, col3;
	@FXML private TableView<Flare> flareTable;
	@FXML private WebView webView;
	@FXML private TextField addressBar;
	
	private List<Node> nodes;
	private String date;
	private Connection connection;
	private MysqlCmd mysqlCmd = new MysqlCmd();
	private List<String> dateLog = new ArrayList<String>();
	private List<String> nameLog = new ArrayList<String>();
	private List<String> flareDate = new ArrayList<String>();
	private List<String> flareStartTime = new ArrayList<String>();
	private List<String> flareEndTime = new ArrayList<String>();
	private List<String> flareClass = new ArrayList<String>();
	private boolean isFound = false;
	ObservableList<String> flareClassList = (ObservableList<String>) 
			FXCollections.observableArrayList("A", "B", "C", "M", "X");
	private final ObservableList<Flare> flareData =
	        FXCollections.observableArrayList(
	        );
	private WebEngine webEngine;
	
	public static class Flare {
        private final SimpleStringProperty startTime;
        private final SimpleStringProperty endTime;
        private final SimpleStringProperty flareClass;
        private Flare(String flareClass, String startTime, String endTime) {
            this.startTime = new SimpleStringProperty(startTime);
            this.endTime = new SimpleStringProperty(endTime);
            this.flareClass = new SimpleStringProperty(flareClass);
        }

        public String getStartTime() {
            return startTime.get();
        }
        public void setStartTime(String st) {
            startTime.set(st);
        }
       
        public String getEndTime() {
            return endTime.get();
        }
        public void setEndTime(String et) {
            endTime.set(et);
        }
        
        public String getFlareClass() {
            return flareClass.get();
        }
        public void setFlareClass(String fc) {
            flareClass.set(fc);
        }
    }
	
	
	public void setMain(Main main) {
    	this.main = main;
    	// load the web content to the tab
    	webEngine = webView.getEngine();
		webEngine.load("https://www.google.com/");
		flareClassCB.setItems(flareClassList);
		addAnt2List();
		nodes = dataField.getChildren();
    	setAllDisabled(true);
    	clearAll();
    	connection = main.getConnector();
    }
    
	
	private void showAntLog() {
		if (isFound) {
			for (int i = 0; i < antIDList.size(); i++) {
				String temp = mysqlCmd.getColumnContents(connection, "tohban_logfile", "date", date, antIDList.get(i)).get(0);
				if (temp.equals("0")) {
					antList.get(i).setSelected(false);
				} else {
					antList.get(i).setSelected(true);
				}
			}
		}
	}
	
	private void showDateLog() {
		if (date != null && isFound) {
			dateText.setText(date);
		} else {
			dateText.setText("No Record");
		}
	}
	
	private void showNameLog() {
		nameLog = mysqlCmd.getColumnContents(connection, "tohban_logfile", "date", date, "user_id");
		if (nameLog == null || nameLog.size() == 0 || nameLog.get(0).equals("TBA") || nameLog.get(0).equals("")) {
			logNameText.setText("TBA");
			System.out.println("Set name log text to TBA");
		} else {
			String userID = nameLog.get(0);
			String firstName =  mysqlCmd.getColumnContents(connection, "user_information", "user_id", userID , "first_name").get(0);
			String lastName =  mysqlCmd.getColumnContents(connection, "user_information", "user_id", userID , "last_name").get(0);
			logNameText.setText(firstName + " " + lastName);
			System.out.println("Set name log text to " + firstName + " " + lastName);
		}
	}
		
	private void writeNameLog() {
		if (userID != null) {
			try {
				mysqlCmd.insert(connection, "tohban_logfile", date, "user_id", userID);
			} catch (SQLException e) {
				System.out.println("Error updating user ID");
				updateMessage("Error updating user ID");
			}
		}
	}
	
	private void writeAntLog() {
		for (int i = 0; i < antList.size(); i++) {
			try {
				if (antList.get(i).isSelected()) {
					mysqlCmd.insert(connection, "tohban_logfile", date, antIDList.get(i), "1");
				} else {
					mysqlCmd.insert(connection, "tohban_logfile", date, antIDList.get(i), "0"); 
				}
				updateMessage("Table has been updated.");
			} catch (SQLException e) {
				updateMessage("Error, fail to upload!");
			} 
		}
	}
	
	private void showFlareLog() {
		flareData.clear();
		flareTable.setItems(null);
		if (date != null) {
			flareDate = mysqlCmd.getColumnContents(connection, "flare_log", "date", date, "date");		
			if (flareDate != null && flareDate.size() > 0) {
				flareStartTime = mysqlCmd.getColumnContents(connection, "flare_log", "date", date, "start_time");
				flareEndTime = mysqlCmd.getColumnContents(connection, "flare_log", "date", date, "end_time");
				flareClass = mysqlCmd.getColumnContents(connection, "flare_log", "date", date, "flare_class");
				
				for (int i = 0; i < flareDate.size(); i++) {
					flareData.add(new Flare(flareClass.get(i), flareStartTime.get(i), flareEndTime.get(i)));
				}

				flareTable.setEditable(true); 
		        col1.setCellValueFactory(new PropertyValueFactory<Flare,String>("flareClass"));
		        col2.setCellValueFactory(new PropertyValueFactory<Flare,String>("startTime"));
		        col3.setCellValueFactory(new PropertyValueFactory<Flare,String>("endTime"));                        
		        flareTable.setItems(flareData);
			}			
		}
	}
	
	public void addFlareLog() {
		if (flareClassCB.getValue() != null) {
			flareTable.setEditable(true);
	        col1.setCellValueFactory(new PropertyValueFactory<Flare,String>("flareClass"));
	        col2.setCellValueFactory(new PropertyValueFactory<Flare,String>("startTime"));
	        col3.setCellValueFactory(new PropertyValueFactory<Flare,String>("endTime"));
			flareData.add(new Flare(flareClassCB.getValue(), stText.getText(), etText.getText()));
			System.out.println(flareData);
			System.out.println(flareClassCB.getValue() + ", " + stText.getText() + " " + etText.getText());
			flareTable.setItems(flareData);
		}		
	}
	
	public void deleteFlareLog() {	
		Flare flare = flareTable.getSelectionModel().getSelectedItem();
		flareData.remove(flare);
		flareTable.setItems(flareData);
	}
	
	private void writeFlareLog() throws SQLException {
		// delete all records on that date
		mysqlCmd.deleteFlareLog(connection, "flare_log", date);
		//add current records in the table to the data base
		if (date != null && isFound && flareData != null && flareData.size() > 0) {
//			System.out.println("Insert value in to flare_log");
//			System.out.println(date);
			for (Flare item:flareData) {
				mysqlCmd.insertFlareLog(connection, "flare_log", date, item.getStartTime(), item.getEndTime(), item.getFlareClass());
			}
		}
	}
	
	private void showNoteLog() {
		if (date != null && isFound) {
			String temp = mysqlCmd.getColumnContents(connection, "tohban_logfile", "date", date, "note").get(0);
			noteText.setText(temp);
		}
	}
	
	
	private void writeNoteLog() throws SQLException {
		mysqlCmd.insert(connection, "tohban_logfile", date, "note", noteText.getText());
	}
	
    public void setConnector(Connection connection) {
    	this.connection = connection;
    }
    
    public Connection getConnector() {
    	return connection;
    }
	
	public void loadBtnClicked() {
		if (datePicker.getValue() != null) {
			date = datePicker.getValue().toString();
			dateLog = mysqlCmd.getColumnContents(connection, "tohban_logfile", "date", date, "date");
			if (dateLog != null && dateLog.size() > 0 && !dateLog.get(0).equals("")) {
				isFound = true;
				updateMessage("Viewing the log on " + date);
			} else {
				isFound = false;
				updateMessage("No log exists on " + date);
				clearAll();
			}
			showDateLog();
			showAntLog();
			showNameLog();
			showNoteLog();
			showFlareLog();
		}
	}
	
	public void uploadBtnClicked() throws SQLException {
		if (datePicker.getValue() != null && isEdit) {
			writeAntLog(); 
			writeNameLog();
			writeNoteLog();
			writeFlareLog();
		}
	}
	
	private void setAllDisabled(boolean isDisabled) {
		if (nodes != null) {
			if (isDisabled) {
				for (int i = 0; i < nodes.size(); i++) {
					nodes.get(i).setDisable(true);
				}
				System.out.println("Set disable true");
			} else {
				for (int i = 0; i < nodes.size(); i++) {
					nodes.get(i).setDisable(false);
				}
				System.out.println("Set disable false");
			}
		}
	}
	
	
	public void editBtnClicked() {
		if (datePicker.getValue() != null) {
			if (editBtn.isSelected()) {
				setAllDisabled(false);
				isEdit = true;
			} else {
				setAllDisabled(true);
				isEdit = false;
			}
			System.out.println(isEdit);
		}
	}
	
	public void setUserName(String userName) {
		if (main != null) {
			if (userName != null) {
				updateMessage("Welcome, " + userName);
			}
		}
	}
	
	public void setUserID(String userID) {
		if (main != null) {
			this.userID = userID;
		}
	}
	
	
	
	private void updateMessage(String msg) {
		if (msg != null) {
			messageText.setText(msg);
		}
	}
	
	
	public void logoutButtonClicked() throws IOException, SQLException {
		if (main != null) {
			main.showLoginView();
			connection.close();
		}
	}
	
	private void clearAll() {
		dateText.setText("No Record");
		logNameText.setText("TBA");
		for (CheckBox ant:antList) {
			ant.setSelected(false);
		}
		stText.clear();
		etText.clear();
		noteText.clear();
		System.out.println("Clear flare log");
		flareData.clear();
		flareTable.setItems(flareData);
	}
	
	private void addAnt2List() {
		antList.add(ant01);
		antList.add(ant02);
		antList.add(ant03);
		antList.add(ant04);
		antList.add(ant05);
		antList.add(ant06);
		antList.add(ant07);
		antList.add(ant08);
		antList.add(ant09);
		antList.add(ant10);
		antList.add(ant11);
		antList.add(ant12);
		antList.add(ant13);
		antList.add(ant14);
		antList.add(ant15);
		antIDList.add("ant01");
		antIDList.add("ant02");
		antIDList.add("ant03");
		antIDList.add("ant04");
		antIDList.add("ant05");
		antIDList.add("ant06");
		antIDList.add("ant07");
		antIDList.add("ant08");
		antIDList.add("ant09");
		antIDList.add("ant10");
		antIDList.add("ant11");
		antIDList.add("ant12");
		antIDList.add("ant13");
		antIDList.add("ant14");
		antIDList.add("ant15");
	}
	
	// load url to the engine
	public void loadWeb(String url) {
		webEngine.load(url);
	}
	
	public void loadGoogle() {
		loadWeb("https://www.google.com/");
	}
	
	public void loadStatus() {
		loadWeb("http://ovsa.njit.edu/EOVSA/status.php");
	}
	
	public void loadWiki() {
		loadWeb("http://www.ovsa.njit.edu/wiki/index.php");
	}
	
	public void loadHelio() {
		loadWeb("https://www.helioviewer.org/");
	}
	
	public void readUrl() {
		if (!addressBar.getText().contains("https://")) {
			loadWeb("https://" + addressBar.getText());
		} else {
			loadWeb(addressBar.getText());
		}
	}
}
