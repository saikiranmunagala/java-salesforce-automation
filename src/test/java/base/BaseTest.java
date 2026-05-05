package base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;

public class BaseTest {

    protected WebDriver driver;

    public String browser = "chrome";

    @BeforeMethod
    public void setUp() {

        //Using Switch case Method to include all the browsers
        switch (browser) {

            case "chrome":
                driver = new ChromeDriver();
                break;

            case "edge":
                driver = new EdgeDriver();
                break;

            case "firefox":
                driver = new FirefoxDriver();
                break;

            case "safari":
                driver = new SafariDriver();
                break;

            default:
                System.out.println("Invalid browser. Use: chrome, edge, firefox, safari");
        }


        //maximize the browser window
        driver.manage().window().maximize();
    }

    @AfterMethod
    public void tearDown() {
        //closing the browser

        if (driver != null) {
            driver.quit();
        }
    }
}