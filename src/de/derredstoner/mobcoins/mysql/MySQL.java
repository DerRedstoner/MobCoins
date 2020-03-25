package de.derredstoner.mobcoins.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import de.derredstoner.mobcoins.main.Main;

public class MySQL {

	private static MySQL instance = new MySQL();
	private Connection connection;
	public String host, database, username, password, table;
	public int port;
	
	public static MySQL getInstance() {
        return instance;
    }
	
	public void mysqlSetup() {
		host = Main.getPlugin().getConfig().getString("mysql.host");
		database = Main.getPlugin().getConfig().getString("mysql.database");
		username = Main.getPlugin().getConfig().getString("mysql.username");
		password = Main.getPlugin().getConfig().getString("mysql.password");
		port = Main.getPlugin().getConfig().getInt("mysql.port");
		table = "coins";
		
		try {
			synchronized(this) {
				if(getConnection() != null && !getConnection().isClosed()) {
					return;
				}
				
				Class.forName("com.mysql.jdbc.Driver");
				setConnection(DriverManager.getConnection("jdbc:mysql://"+this.host+":"+this.port+"/"+this.database+"?autoReconnect=true", this.username, this.password));
				createTable();
			}
		} catch(SQLException e) {
			e.printStackTrace();
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public Connection getConnection() {
		return connection;
	}

	public void setConnection(Connection connection) {
		this.connection = connection;
	}
	
	private void createTable() {
		try {
			PreparedStatement statement = getConnection().prepareStatement("CREATE TABLE IF NOT EXISTS COINS(NAME VARCHAR(255) NOT NULL, COINS INT(16), PRIMARY KEY (NAME))");
			statement.executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
}
