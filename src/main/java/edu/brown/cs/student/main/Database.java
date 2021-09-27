package edu.brown.cs.student.main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Database {
  private static Connection conn = null;

  /**
   * Instantiates the database, creating a connection to the database
   * Automatically loads files.
   *
   * @param filename file name of SQLite3 database to open.
   * @throws SQLException if an error occurs in any SQL query.
   */
  Database(String filename) throws SQLException, ClassNotFoundException {

    // this line loads the driver manager class, and must be
    // present for everything else to work properly
    Class.forName("org.sqlite.JDBC");
    String urlToDB = "jdbc:sqlite:" + filename;

    // Creates a connection to the database.
    conn = DriverManager.getConnection(urlToDB);
  }
}