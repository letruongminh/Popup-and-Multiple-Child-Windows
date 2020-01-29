import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.Iterator;
import java.util.Set;

public class AlertPopup {
    WebDriver driver;
    @BeforeTest
    public void setUp(){
        System.setProperty("webdriver.chrome.driver", "/Users/minnhle/Desktop/driver/chromedriver");
        driver = new ChromeDriver();
    }
    @Test
    public void PopupHandling(){

        // 1. Launch the site
        driver.get("http://demo.guru99.com/popup.php");

        // 2. Click on the link "Click Here"
        WebElement txtClickHere = driver.findElement(By.cssSelector("[target$=\"blank\"]"));
        txtClickHere.click();

        // 3. New child window opens.
        // 4. Switch to child window > Enter user ID and click on button "Submit"
        String mainWindow = driver.getWindowHandle();
        Set <String> setOfWindow = driver.getWindowHandles();
        Iterator<String> it = setOfWindow.iterator();


    }
}
