package com.company;

import com.company.InferenceFilters.FilterOneDefInDef;
import com.company.InferenceFilters.FilterZeroInterjections;
import com.company.LearnWords.PythonScript;
import com.microsoft.sqlserver.jdbc.SQLServerException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;

public class InputComprehend extends Thread {
    private String input;

    public void run() {
        ArrayList<String> words = new ArrayList<>();
        String portion = "";
        for(int i = 0; i < input.length(); i++) {
            if(input.charAt(i) != ' ' && i < input.length() - 1) {
                portion = portion + input.charAt(i);
            }
            else if(input.charAt(i) != ' ' && i == input.length() - 1) {
                portion = portion + input.charAt(i);
                words.add(portion);
                portion = "";
            }
            else {
                words.add(portion);
                portion = "";
            }
        }
        /*
        System.out.println("-----------------------------");
        System.out.println("Word List:");
        for(int m = 0; m < words.size(); m++) {
            System.out.println(words.get(m));
        }
        System.out.println("------------------------------");

         */
        ArrayList<String[]> result = null;
        try {
            result = checkInput(words);
        } catch (SQLException | FileNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println(result.size() + " words known");

    }

    public void setInput(String input) {
        this.input = input;
    }

    public ArrayList<String> makeInputCopy(ArrayList<String> input) {
        ArrayList<String> returnList = new ArrayList<>();
        returnList.addAll(input);

        return returnList;
    }

    private ArrayList<String[]> checkInput(ArrayList<String> input) throws SQLException, FileNotFoundException {

        String URL = "**SQLURL**";
        ArrayList<String[]> returnList = new ArrayList<>();
        String UserName = "**USERNAME**";
        String Password = "**PASSWORD**";
        System.out.println("Connecting...");
        Connection connection = DriverManager.getConnection(URL, UserName, Password);

        FilterZeroInterjections f = new FilterZeroInterjections(connection, makeInputCopy(input));
        f.start();
        System.out.println("---------------------------------------------------SQL Connection Successful------------------------------------------------");
        ArrayList<String> copy = new ArrayList<>();
        ArrayList<String[]> knownWords = new ArrayList<>();
        //System.out.println("size:" + input.size());
        int i = 0;
        for(int n = 0; n < input.size(); n++) {
            String getTableList = "SELECT * from sysobjects where type = 'U'";
            Statement statements = connection.createStatement();
            Statement statements2 = connection.createStatement();
            ResultSet TableList = statements2.executeQuery(getTableList);
            while (TableList.next()) {
                if(!TableList.getString("name").equals("GrammarFreq") && !TableList.getString("name").equals("ObjFunction")) {
                    String getTable = "SELECT * FROM " + TableList.getString("name") + " WHERE Word = '" + input.get(n).toUpperCase() + "'";
                    //System.out.println("Table: " + TableList.getString("name"));
                    ResultSet resultSet = statements.executeQuery(getTable);
                    int size = 0;
                    while (resultSet.next()) {
                        size++;
                        returnList.add(new String[]{"" + n, resultSet.getString("FunctionID")});
                        copy.add(input.get(n));
                        knownWords.add(new String[]{input.get(n), TableList.getString("name")});
                    }
                }
            }
        }
        ArrayList<String> idkWords = new ArrayList<>();
        if(input.size() > 0) {

            File r = new File("**location of words.txt**");
            PrintWriter pw = new PrintWriter(r);
            for(String s : input) {
                int count = 0;
                if(copy.size() > 0) {
                    for (int y = 0; y < copy.size(); y++) {
                        if (s.equals(copy.get(y))) {

                            break;
                        } else if (y == copy.size() - 1) {
                            System.out.println("Learning " + s);
                            pw.println(s);
                            idkWords.add(s);
                        }
                    }

                }
                else {
                    System.out.println("Learning " + s);
                    pw.println(s);
                    idkWords.add(s);
                }
            }

            pw.close();
            if(r.length() > 0) {
                PythonScript ps = new PythonScript();
                ps.start();
            }

        }
        return returnList;
    }

    public ArrayList<String> getDefinites(ArrayList<String> input) throws SQLException {
        String URL = "**SQLURL**";
        ArrayList<String> returnList = new ArrayList<>();
        String UserName = "**USERNAME**";
        String Password = "**PASSWORD**";
        System.out.println("Connecting...");
        Connection connection = DriverManager.getConnection(URL, UserName, Password);
        System.out.println("Connected!");
        String[] grammarLabels = new String[input.size()];
        for(int g = 0; g < grammarLabels.length; g++) {
            grammarLabels[g] = "";
        }
        String getTable = "SELECT * FROM Definites";

        boolean isDefinitive = false;
        boolean isInDefinitive = false;
        boolean isAdjective = false;
        boolean isNoun = false;
        for(int n = 0; n < input.size(); n++) {
            Statement statementsDef = connection.createStatement();
            ResultSet resultSetDef = statementsDef.executeQuery(getTable);
            while (resultSetDef.next()) {
                String word = resultSetDef.getString("Word");
                //System.out.println("Word is " + input.get());
                if (input.get(n).equalsIgnoreCase(word)) {
                    isDefinitive = true;
                    System.out.println("Definitive detected n = " + n);
                    grammarLabels[n] = "Definitive";
                    break;
                }
            }
            if(isDefinitive) {
                try {
                    String getNouns = "SELECT * FROM Nouns WHERE Word = '"+ input.get(n+1).toUpperCase() + "'";// + input.get(n + 1).toUpperCase() + "'";
                    Statement statementN = connection.createStatement();
                    ResultSet resultSetN = statementN.executeQuery(getNouns);
                    int size = 0;
                    while(resultSetN.next()) {
                        size++;
                        grammarLabels[n + 1] = "Noun";
                    }
                    if(size==0) {
                        String getAdj = "SELECT * FROM Adjectives WHERE Word = '"+ input.get(n+1).toUpperCase() + "'";// + input.get(n + 1).toUpperCase() + "'";
                        Statement statementAdj = connection.createStatement();
                        ResultSet resultSetAdj = statementN.executeQuery(getAdj);
                        int ss = 0;
                        while(resultSetAdj.next()) {
                            ss++;
                            grammarLabels[n+1] = "Adjective";
                            grammarLabels[n+2] = "Nouns";
                        }
                    }
                    n++;
                }
                catch(SQLServerException e) {
                    System.out.println("Row not found");
                }
                isDefinitive = false;
            }
        }
        System.out.println(Arrays.toString(grammarLabels));
        return returnList;
    }
}
