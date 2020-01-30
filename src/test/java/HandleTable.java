import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class HandleTable {
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
    public void hanleTable() throws IOException, ParseException {
        File file = new File(
                System.getProperty("user.dir") + "/src/test/java/resources/dataNew.json"
        );
        FileReader reader = new FileReader(file);
        JSONObject jsonObject = (JSONObject) ((Object) new JSONParser().parse(reader));
        String nestedTable = (String) jsonObject.get("nestedTable");

        driver.get(nestedTable);
        WebElement designatedTable = driver.findElement(By.xpath(
                "//table/tbody/tr[2]/td[2]/table/tbody/tr[1]/td[2]"
        ));
        System.out.println(designatedTable.getText());


    }
}
