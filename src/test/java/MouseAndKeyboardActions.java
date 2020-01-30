import org.checkerframework.checker.units.qual.A;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class MouseAndKeyboardActions {
    WebDriver driver;

    @BeforeTest
    public void setUp(){
        System.setProperty("webdriver.chrome.driver", "/Users/minhle/Desktop/driver/chromedriver");
        driver = new ChromeDriver();
    }
    @AfterTest
    public void tearDown(){
        driver.close();
    }

    @Test
    public void ActionClass() throws IOException, ParseException {
        FileReader reader = new FileReader(
                System.getProperty("user.dir") + "/src/test/java/resources/dataNew.json"
        );
        Object obj = new JSONParser().parse(reader);
        JSONObject jsonObject = (JSONObject) obj;
        String newTourLink = (String) jsonObject.get("newtourLinks");

        driver.get(newTourLink);

        WebElement linkTextHome = driver.findElement(By.xpath("//a[text()=\"Home\"]"));
        Actions action = new Actions(driver);
        // Move to element Home
        Action mouseOverHome = action.moveToElement(linkTextHome)
                .build();
        mouseOverHome.perform();

    }


}
