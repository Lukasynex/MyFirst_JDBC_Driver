package com.luk.jdbc_scvc;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;

import javax.sql.ConnectionEvent;

import org.sqlite.SQLiteConfig;

public class CustomSQLDriver {
	
    public static int currentID=0;
    public static String DATABASE_NAME = "";
    private boolean connected = false;

	public CustomSQLDriver(String databaseName) {
		DATABASE_NAME = databaseName;
	}

	public String insert(String TableName, String Id, String Question, String Answer) {
		return "INSERT INTO " + TableName + " VALUES (" + Id + ", '" + Question + "', '" + Answer + "');";
	}

	private String updateById(String TableName, String ToUpdateString, String Id, String newValue) {
		return "UPDATE " + TableName + " SET " + ToUpdateString + "= '" + newValue + "' WHERE ID= '" + Id + "');";
	}

	public String updateQuestionById(String TABLE, String id, String pyt) {
		return updateById(TABLE, "PYTANIE", id, pyt);
	}

	public String updateAnswerById(String TABLE, String id, String ans) {
		return updateById(TABLE, "ODPOWIEDZ", id, ans);
	}
	public void Connect_To_Local_SQLite_Database() throws ClassNotFoundException{
		Connection connection = null;
		Statement statement = null;
		try{
			Class.forName("org.sqlite.JDBC");
			connection = DriverManager.getConnection("jdbc:sqlite:"+ DATABASE_NAME + ".db");
			connection.setAutoCommit(false);
			System.out.println("Database opened successfully");
			statement = connection.createStatement();
			String sql = "CREATE TABLE "+ DATABASE_NAME + " (ID INT PRIMARY KEY NOT NULL,"
					+"QUESTION TEXT NOT NULL,"
					+"ANSWER TEXT NOT NULL);";
			statement.executeUpdate(sql);
			
			
			
		}catch(SQLException e){
			System.err.print("\nUnable to connect");
		}
	}

}


















