package com.company.LearnWords;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class PythonScript extends Thread{
    Process mProcess;

    public void run() {
        System.out.println("Python Script Called");
        runScript();
    }

    public void runScript(){
        Process process;
        try{
            process = Runtime.getRuntime().exec(new String[]{"**LocationOfLearnExecutableFile**"});
            mProcess = process;
        }catch(Exception e) {
            System.out.println("Exception Raised" + e.toString());
        }
        InputStream stdout = mProcess.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(stdout,StandardCharsets.UTF_8));
        String line;
        try{
            while((line = reader.readLine()) != null){
                if(!line.equals("Here") || !line.equals("There"))
                    System.out.println("Python: "+ line);
            }
        }catch(IOException e){
            System.out.println("Exception in reading output"+ e.toString());
        }
    }
}
