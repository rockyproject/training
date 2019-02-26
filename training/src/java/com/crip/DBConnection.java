
package com.crip;

import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;

import java.io.FileReader;
import java.io.IOException;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import org.json.simple.JSONObject; 
import org.json.simple.parser.*; 

public class DBConnection {
    private String url;
    private String user;
    private String password;
    private String driver;
    private Connection connection;
    private Statement statement;
    private ResultSet resultSet;
    
    private void connect() throws ClassNotFoundException, SQLException, FileNotFoundException, IOException, ParseException
    {        
        FacesContext context = FacesContext.getCurrentInstance();
        ExternalContext externalContext = context.getExternalContext();
        ServletContext sc = (ServletContext)externalContext.getContext();
        String path= sc.getRealPath("/WEB-INF/conString.json");
        Object obj = new JSONParser().parse(new FileReader(path));
        
        JSONObject jo = (JSONObject) obj; 
        
        url = (String) jo.get("url"); 
        user = (String) jo.get("user");
        password = (String) jo.get("password"); 
        driver = (String) jo.get("driver"); 
        
        Class.forName(driver);
        connection=DriverManager.getConnection(url,user,password);
        statement=connection.createStatement();
    }
    
    public ResultSet Data_Source(String query) throws ClassNotFoundException, SQLException, IOException, FileNotFoundException, ParseException
    {
        connect();
        resultSet=statement.executeQuery(query);
        return resultSet;
    }
    
    public void Execute_Query(String query) throws ClassNotFoundException, SQLException, IOException, FileNotFoundException, ParseException
    {
        connect();
        statement.executeUpdate(query);        
    }
    
    public String Show_Data(String query, String field, int rowNumber) throws ClassNotFoundException, SQLException, IOException, FileNotFoundException, ParseException
    {
       connect(); 
       statement=connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
       resultSet=statement.executeQuery(query);
       resultSet.absolute(rowNumber);
       return resultSet.getString(field);
    }
}
