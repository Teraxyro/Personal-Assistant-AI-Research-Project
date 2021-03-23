package com.company.InferenceFilters;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class FilterZeroInterjections extends Thread{
    private ArrayList<String> words = new ArrayList<>();
    private Connection connection;
    private ArrayList<String> input;

    public FilterZeroInterjections(Connection connection, ArrayList<String> input) {
        this.connection = connection;
        this.input = input;
    }

    public void run() {
        String query = "SELECT * FROM Interjections";
        Statement statement = null;
        ArrayList<String> trash = new ArrayList<>();
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while(resultSet.next()) {

                    String word = resultSet.getString("Word");
                    if(input.get(0).equalsIgnoreCase(word)) {
                        System.out.println(word + " was detected as Interjection");
                        input.remove(0);
                        break;
                    }

            }
            ArrayList<String> returnList = new ArrayList<>();
            returnList.addAll(input);
            FilterOneDefInDef f = new FilterOneDefInDef(connection, returnList);
            f.start();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
