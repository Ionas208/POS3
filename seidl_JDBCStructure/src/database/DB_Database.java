/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author 10jon
 */
public class DB_Database {
    private String db_url;
    private String db_driver;
    private String db_username;
    private String db_password;
    private Connection connection;
    private DB_CachedConnection cachedConnection;

    public DB_Database() throws ClassNotFoundException, SQLException {
        loadProperties();
        Class.forName(db_driver);
    }
    
    public void connect() throws SQLException{
        if(connection != null){
           connection.close();
        }
        connection = DriverManager.getConnection(db_url, db_username, db_password);
        cachedConnection = new DB_CachedConnection(connection);
        
    }
    
    public void disconnect() throws SQLException{
        if(connection != null){
            connection.close();
            cachedConnection = null;
        }
    }
    
    private void loadProperties(){
        db_url = DB_Properties.getProperties("url");
        db_driver = DB_Properties.getProperties("driver");
        db_username = DB_Properties.getProperties("username");
        db_password = DB_Properties.getProperties("password");
    }

    public Connection getConnection() {
        return connection;
    }
    
    public Statement getStatement() throws SQLException{
        if(connection == null || cachedConnection == null){
            throw new RuntimeException("database connection error");
        }
        return cachedConnection.getStatement();
    }
    
    public void releaseStatement(Statement statement){
        if(connection == null || cachedConnection == null){
            throw new RuntimeException("database connection error");
        }
        cachedConnection.releaseStatement(statement);
    }
    
    
    
}
