/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.Queue;

/**
 *
 * @author 10jon
 */
public class DB_CachedConnection {
    
    private Queue<Statement> statements = new LinkedList<>();
    private Queue<Statement> scrollableStatements = new LinkedList<>();
    private Connection connection;

    public DB_CachedConnection(Connection connection) {
        this.connection = connection;
    }
    
    public Statement getStatement() throws SQLException{
        if(connection == null){
            throw new RuntimeException("not connected to DB");
        }
        if(!statements.isEmpty()){
            return statements.poll();
        }
        return connection.createStatement();
    }
    
    public Statement getScrollableStatement() throws SQLException{
        if(connection == null){
            throw new RuntimeException("not connected to DB");
        }
        if(!scrollableStatements.isEmpty()){
            return scrollableStatements.poll();
        }
        
        return connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
    }
    
    public void releaseStatement(Statement statement){
        if(connection == null){
            throw new RuntimeException("not connected to DB");
        }
        statements.offer(statement);
    }
    
    public void releaseScrollableStatement(Statement statement){
        if(connection == null){
            throw new RuntimeException("not connected to DB");
        }
        scrollableStatements.offer(statement);
    }
    
}
