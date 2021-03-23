package com.company.Action;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Action extends Thread{
    private String object;
    private String action;
    private Connection connection;
    public Action(Connection connection, String action, String obj)
    {
        object = obj;
        this.action = action;
        this.connection = connection;
    }
    public void run()
    {

        String query = "SELECT * FROM ObjFunction WHERE Type = '" + object.toUpperCase() + "'";
        //System.out.println("Query: " + query);
        Statement statement = null;
        try {
            //System.out.println("obj: " + object);
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            boolean funcFound = false;
            while(resultSet.next())
            {
                //System.out.println("result: " + resultSet.getString("Type"));
                if(resultSet.getString("Type").equalsIgnoreCase(object))
                {
                    funcFound = true;
                    // Connect to respective code and perform function
                }
            }
            if(!funcFound)
            {
                System.out.println("Object " + object + " not registered in function dictionary");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public ArrayList<String> makeInputCopy(ArrayList<String> input) {
        ArrayList<String> returnList = new ArrayList<>();
        returnList.addAll(input);

        return returnList;
    }
}
