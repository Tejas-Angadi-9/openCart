package testBase;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class BaseClass {

    public WebDriver driver;
    public Logger logger;
    public Properties prop;

    @Parameters({"os","browser"})
    @BeforeClass
    public void setup(String os, String browser) throws IOException {
        prop = new Properties();
        FileInputStream file = new FileInputStream("D:\\1_Cognizant Prep\\Opencart\\src\\test\\resources\\config.properties");
        prop.load(file);

        logger = LogManager.getLogger(this.getClass());

        switch (browser.toLowerCase()) {
            case "chrome": {
                driver = new ChromeDriver();
                break;
            }
            case "edge": {
                driver = new EdgeDriver();
                break;
            }
            default:
                System.out.println("No matching browser");
        }


        driver.manage().deleteAllCookies();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

//        driver.get("http://localhost/opencart/upload/index.php");
        driver.get(prop.getProperty("appURl"));
        driver.manage().window().maximize();
    }

    @AfterClass
    public void tearDown() {
        driver.close();
    }


    public String randomeString() {
        String generatedString = RandomStringUtils.randomAlphabetic(5);
        return generatedString;
    }

    public String randomeNumber() {
        String generatedString = RandomStringUtils.randomNumeric(10);
        return generatedString;
    }

    public String randomAlphaNumeric() {
        String str = RandomStringUtils.randomAlphabetic(3);
        String num = RandomStringUtils.randomNumeric(3);

        return (str + "@" + num);
    }
}
