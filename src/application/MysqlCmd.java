package application;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.mysql.jdbc.PreparedStatement;
//import com.mysql.jdbc.ResultSet;
import com.mysql.jdbc.Statement;

public class MysqlCmd {

  public static Connection connect() {

	System.out.println("-------- MySQL JDBC Connection Testing ------------");

	try {
		Class.forName("com.mysql.jdbc.Driver");
	} catch (ClassNotFoundException e) {
		System.out.println("Where is your MySQL JDBC Driver?");
		e.printStackTrace();
		return null;
	}
	
	System.out.println("MySQL JDBC Driver Registered!");
	Connection connection = null;
	try {	
		connection = DriverManager
		.getConnection("jdbc:mysql://sql2.njit.edu/zw56","zw56", "rrAdBNPHH");	

	} catch (SQLException e) {
		System.out.println("Connection Failed! Check output console");
		e.printStackTrace();
		return null;
	}

	if (connection != null) {
		System.out.println("You made it, take control your database now!");
		return connection;
	} else {
		System.out.println("Failed to make connection!");
		return null;
	}
  }
  
  
  // get all rows from the column named columnName from the table
  // overload
  public List<String> getColumnContents(Connection connection, String tableName, String columnName) {
	  String sql = "";
	  ResultSet rs = null;
	  sql = "SELECT " + columnName + " FROM " + tableName;
      List<String> results = new ArrayList<String>();
      try {
    	  rs = query(connection, sql);
    	  while(rs.next()) {
    		  results.add(rs.getString(columnName));
    	  }
      } catch (SQLException e) {
    	  System.out.println(e);
      }
      return results;
  }
    
  // look up the row/rows with the rowValue from the target column named targetColumnName from the table
  // overload
  public List<String> getColumnContents(Connection connection, String tableName, String targetColumn, 
		  String targetValue, String outputColumn) {
	  String sql = "";
	  ResultSet rs = null;
	  sql = "SELECT * FROM " + tableName + " WHERE " + targetColumn + " = '" + targetValue +"'";
      List<String> results = new ArrayList<String>();
      try {
    	  rs = query(connection, sql);
//    	  System.out.println(rs);
    	  while(rs.next()) {
    		  results.add(rs.getString(outputColumn));
    	  }
      } catch (SQLException e) {
    	  System.out.println(e);
      }
      return results;
  }
  
  
  private ResultSet query(Connection connection, String sql) throws SQLException {
//      System.out.println(sql);
      Statement stmt = (Statement) connection.createStatement();
      ResultSet rs = (ResultSet) stmt.executeQuery(sql);
      return rs;
  }
  
  /*
   * How to insert or update duplicate key
   *  reference: http://stackoverflow.com/questions/22743497/java-preparedstatement-and-on-duplicate-key-update-how-do-i-know-whether-row-wa
   */
  
  public void insert(Connection connection, String tableName, String date, String columnName, String columnValue) throws SQLException {
	  if (!columnName.equals("note")) {
		  PreparedStatement ps = (PreparedStatement) connection.prepareStatement(
			        "INSERT INTO " + tableName +" (date, note, " + columnName + ") " +
			        "VALUES (?, ?, ?) " +
			        "ON DUPLICATE KEY UPDATE " +
			            columnName + " = VALUES("+ columnName +")");
		  ps.setString(1, date);
		  ps.setString(2, "");
//		  System.out.println(columnName);
		  ps.setString(3, columnValue);
//		  System.out.println(ps);
		  int euReturnValue = ps.executeUpdate();
		  System.out.println(String.format("executeUpdate returned %d", euReturnValue));
	  } else {
		  PreparedStatement ps = (PreparedStatement) connection.prepareStatement(
			        "INSERT INTO " + tableName +" (date, " + columnName + ") " +
			        "VALUES (?, ?) " +
			        "ON DUPLICATE KEY UPDATE " +
			            columnName + " = VALUES("+ columnName +")");
		  ps.setString(1, date);
		  System.out.println(columnName);
		  ps.setString(2, columnValue);
		  System.out.println(ps);
		  int euReturnValue = ps.executeUpdate();
		  System.out.println(String.format("Insert executeUpdate returned %d", euReturnValue));
	  }
  } 
  
  public void insertFlareLog(Connection connection, String tableName, 
	  String date, String startTime, String endTime, String flareClass) throws SQLException {
	  PreparedStatement ps = (PreparedStatement) connection.prepareStatement(
		        "INSERT INTO " + tableName +" (date, start_time, end_time, flare_class) " +
		        "VALUES (?, ?, ?, ?) ");
	  ps.setString(1, date);
	  ps.setString(2, startTime);
	  ps.setString(3, endTime);
	  ps.setString(4, flareClass);
	  int euReturnValue = ps.executeUpdate();
	  System.out.println(String.format("Insert FlareLog executeUpdate returned %d", euReturnValue));
  }
  
  public void deleteFlareLog(Connection connection, String tableName, String date) throws SQLException {
	  String query = "DELETE FROM " + tableName + " WHERE date = '" + date + "'";
	  PreparedStatement ps = (PreparedStatement) connection.prepareStatement(query);
//	  System.out.println(ps);
	  ps.execute();
  }

}