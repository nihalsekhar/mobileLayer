package com.cg.lab.utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import com.cg.lab.exceptions.LABException;

public class dbUtility {
	private static Connection connection = null;
	

	public static Connection getConnection() throws LABException {
		File file = null;
		FileInputStream inputStream = null;

		file = new File("resources/jdbc.properties");
		try {
			inputStream = new FileInputStream(file);
		} catch (FileNotFoundException e1) {
			throw new LABException("File is not found");
		}

		Properties properties = new Properties();

		try {
			properties.load(inputStream);
			String driver = properties.getProperty("db.driver");
			String url = properties.getProperty("db.url");
			String username = properties.getProperty("db.user");
			String password = properties.getProperty("db.pass");

			Class.forName(driver);
			connection = DriverManager.getConnection(url, username, password);

		} catch (IOException e) {
			throw new LABException("Unable to read file");
		} catch (ClassNotFoundException e) {
			throw new LABException("Class is not found");
		} catch (SQLException e) {
			throw new LABException("Connection is not created");
		}
		return connection;
	}
}
