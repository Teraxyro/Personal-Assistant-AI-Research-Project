package com.company.InferenceFilters;

import com.company.Action.Action;
import com.company.Main;
import com.microsoft.sqlserver.jdbc.SQLServerException;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class FilterOneDefInDef extends Thread {
    private ArrayList<String> words = new ArrayList<>();
    private Connection connection;
    private ArrayList<String> input;
    public FilterOneDefInDef(Connection connection, ArrayList<String> input) {
        this.connection = connection;
        this.input = input;
    }

    public void run() {
        String queryD = "SELECT * FROM Definites";
        String queryI = "SELECT * FROM Indefinites";
        Statement statement = null;
        ArrayList<Integer> DIndexes = new ArrayList<>();
        try {
            statement = connection.createStatement();
            ResultSet resultSetD = statement.executeQuery(queryD);
            ResultSet resultSetI = statement.executeQuery(queryI);
            try {
                while (resultSetD.next()) {
                    words.add(resultSetD.getString("Word"));
                }
            }
            catch(SQLServerException e) {
                System.out.println("meh");
            }
            try {
                while (resultSetI.next()) {
                    words.add(resultSetI.getString("Word"));
                }
            }
            catch(SQLServerException e) {
                System.out.println("error!");
            }

            for(int i = 0; i < input.size(); i++) {
                for(int j = 0; j < words.size(); j++) {
                    if(input.get(i).equalsIgnoreCase(words.get(j))) {
                        DIndexes.add(i);
                        break;
                    }
                }
            }


                String firstWord = input.get(0);

                if(Main.active) {
                    for(int i = 0; i < input.size(); i++) {
                        if(input.get(i).equalsIgnoreCase("WOULD") || input.get(i).equalsIgnoreCase("COULD") || input.get(i).equalsIgnoreCase("PLEASE") || input.get(i).equalsIgnoreCase("YOU")|| input.get(i).equalsIgnoreCase("THE")|| input.get(i).equalsIgnoreCase("A")) {
                            input.remove(i);
                        }
                    }
                    System.out.println("Object: " + input.get(input.size()  - 1));
                    String obj = input.get(input.size()  - 1);
                    System.out.print("Action: ");
                    String act = "";
                    for(int i = 0; i < input.size() - 1; i++) {
                        act = act + input.get(i) + " ";
                    }
                    System.out.println(act);
                    Action func = new Action(connection, act, obj);
                    func.start();
                }
                else if(DIndexes.size() == 0) {
                    boolean canActive = false;
                    for(String s : input) {
                        if(s.equalsIgnoreCase("June")) {
                            canActive = true;
                            break;
                        }

                    }

                    if(canActive) {

                        Main.active = true;
                        //try {
                            for(String s : input) {
                                String queryP = "SELECT * FROM Pronouns WHERE Word = '" + s.toUpperCase() + "'";
                                String queryN = "SELECT * FROM Nouns WHERE Word = '" + s.toUpperCase() + "'";
                                Statement statementP = connection.createStatement();
                                Statement statementN = connection.createStatement();
                                ResultSet resultSetP = statementP.executeQuery(queryP);
                                ResultSet resultSetN = statementN.executeQuery(queryN);
                                while (resultSetP.next()) {
                                    if (!resultSetP.getString("Word").equalsIgnoreCase("YOU")) {
                                        Main.active = false;
                                        break;
                                    }
                                }
                                while (resultSetN.next()) {
                                    if (!resultSetN.getString("Word").equalsIgnoreCase("JUNE")) {
                                        Main.active = false;
                                        break;
                                    }
                                }
                            }
                        }
                        //catch(SQLServerException e) {
                          //  System.out.println("meh");
                        //}
                        if(Main.active) {
                            System.out.println("I am active");
                        }
                    }
                }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }


    //public void run
}
