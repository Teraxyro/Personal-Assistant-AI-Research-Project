package com.company.AudioInput;

import com.company.Action.Text;
import com.company.InputComprehend;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class Listen extends Thread{
    public void run() {
        boolean texting = true;
        ChromeOptions option = new ChromeOptions();
        option.addArguments("use-fake-ui-for-media-stream");


        System.setProperty("webdriver.chrome.driver", "**AddressOfChromeWebDriver**");
        WebDriver driver = new ChromeDriver(option);
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.get("https://www.speechtexter.com/");
        System.out.println("---------------------Opening Success-----------------------");
        driver.findElement(By.id("btn-start")).click();
        String set = "";
        Text t = new Text();
        while(true) {
            String input = driver.findElement(By.id("editor")).getText();
            if(!input.equals(set)) {

                if(set.equals("")) {
                    if(!texting) {
                        InputComprehend i = new InputComprehend();
                        i.setInput(input);
                        i.start();
                    }
                    else
                    {
                        t.start();
                    }
                    System.out.println(input);
                }
                else {
                    StringBuilder element = new StringBuilder(input);
                    String part = element.delete(0, set.length()+1).toString();
                    if(!texting) {
                        InputComprehend i = new InputComprehend();
                        i.setInput(part);
                        i.start();
                    }
                    else
                    {

                        System.out.println("Texting input: " + part);
                            t.message = part;

                    }
                    System.out.println(part);
                }
                set = input;
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
