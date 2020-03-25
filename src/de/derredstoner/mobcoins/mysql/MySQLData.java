package de.derredstoner.mobcoins.mysql;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MySQLData {
	
	private static MySQLData instance = new MySQLData();
	
	public static MySQLData getInstance() {
		return instance;
	}
	
	public boolean playerExists(String playername) {
		try {
			PreparedStatement statement = MySQL.getInstance().getConnection().
					prepareStatement("SELECT * FROM "+MySQL.getInstance().table+" WHERE NAME=?");
			statement.setString(1, playername);
			
			ResultSet results = statement.executeQuery();
			
			if(results.next()) {
				return true;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public void createPlayer(String playername) {
		try {
			PreparedStatement statement = MySQL.getInstance().getConnection().
					prepareStatement("SELECT * FROM "+MySQL.getInstance().table+" WHERE NAME=?");
			statement.setString(1, playername);
			
			if(playerExists(playername) != true) {
				PreparedStatement insert = MySQL.getInstance().getConnection().
						prepareStatement("INSERT INTO "+MySQL.getInstance().table+" (NAME,COINS) VALUES (?,?)");
				insert.setString(1, playername);
				insert.setInt(2, 0);
				insert.executeUpdate();
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void updateCoins(String playername, int coins) {
		try {
			PreparedStatement statement = MySQL.getInstance().getConnection().
					prepareStatement("UPDATE "+MySQL.getInstance().table+" SET COINS=? WHERE NAME=?");
			statement.setInt(1, coins);
			statement.setString(2, playername);
			statement.executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public int getCoins(String playername) {
		try {
			PreparedStatement statement = MySQL.getInstance().getConnection().
					prepareStatement("SELECT * FROM "+MySQL.getInstance().table+" WHERE NAME=?");
			statement.setString(1, playername);
			
			ResultSet results = statement.executeQuery();
			results.next();
			return results.getInt("COINS");
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return -1;
	}

}
