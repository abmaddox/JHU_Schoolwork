/**
 * @author Andrew Maddox
 * @since Apr 13, 2024
 */
package edu.jhuep.maddox.sql.connector;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * This object creates the connection to the database upon creation and makes it available.
 */
public class ConnectorBHC {
	//the following constants are set based on the structure given in the lecture 11 JDBC example
	private static final String URL = "jdbc:mysql://web6.jhuep.com:3306/";
	private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
	private static final String USER = "johncolter";
	private static final String PASSWORD = "LetMeIn";
	private static final String DB = "class";
	
	// Contains error messages for DB connection
	private List<String> errors = new ArrayList<String>();

	private Connection connection = null;

	public ConnectorBHC() {
		try {
			Class.forName(DRIVER);
			this.connection = DriverManager.getConnection(URL + DB, USER, PASSWORD);
		} catch (SQLException e) {
			e.printStackTrace();
			this.errors.add("ERRROR: Unable to connect to database");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			this.errors.add("ERRROR: Unable to connect to database");
		}
	}
	
	public List<String> getErrors(){
		return this.errors;
	}
	
	public Connection getConnection() {
		return connection;
	}
	
	//used to check if a connection was made and if a result was obtained without error
	public boolean isValid() {
		return (errors.size() == 0);
	}

	public void close() {
		try {
			this.connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
