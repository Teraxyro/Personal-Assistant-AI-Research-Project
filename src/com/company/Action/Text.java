package com.company.Action;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
public class Text extends Thread {

    public static String message = "";
    public static boolean ready = false;

    public void run()
    {
        ChromeOptions option = new ChromeOptions();
        option.addArguments("use-fake-ui-for-media-stream");

        System.setProperty("webdriver.chrome.driver", "**AddressOfChromeWebDriver**");
        WebDriver driver = new ChromeDriver(option);

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        driver.get("https://web.whatsapp.com/");

        while(!message.equalsIgnoreCase("code t")) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Getting ready to text");
        driver.findElement(By.xpath("/html/body/div/div/div/div[3]/div/div[2]/div[1]/div/div/div[11]")).click();

        message = "";

        while(true)
        {

            if(!message.equals(""))
            {
                driver.findElement(By.xpath("/html/body/div/div/div/div[4]/div/footer/div[1]/div[2]/div/div[2]")).sendKeys(message);
                driver.findElement(By.xpath("/html/body/div/div/div/div[4]/div/footer/div[1]/div[2]/div/div[2]")).sendKeys(Keys.ENTER);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                message = "";
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

}
