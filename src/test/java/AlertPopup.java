import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;

import java.io.File;
import java.io.FileReader;
import java.util.Iterator;
import java.util.Properties;
import java.util.Set;

public class AlertPopup {
    WebDriver driver;
    @BeforeTest
    public void setUp(){
        System.setProperty("webdriver.chrome.driver", "/Users/minhle/Desktop/driver/chromedriver");
        driver = new ChromeDriver();
    }
    @Test
    @Parameters("URL")
    public void PopupHandling( String strURL ){
        String fileName = System.getProperty("user.dir") + "/src/test/java/resources/data.properties";
        // 0. Get data from properties:
        Properties pro = new Properties();
        String URL = "";
        try{
            FileReader reader = new FileReader(fileName);
            pro.load(reader);
            URL = (String) pro.get("URL");
        }
        catch (Exception e){
            e.printStackTrace();
        }
        // 1. Launch the site
        driver.get(URL);

        // 2. Click on the link "Click Here"
        WebElement txtClickHere = driver.findElement(By.cssSelector("[target$=\"blank\"]"));
        txtClickHere.click();

        // 3. New child window opens.
        // 4. Switch to child window > Enter user ID and click on button "Submit"
        String mainWindow = driver.getWindowHandle();
        Set <String> setOfWindow = driver.getWindowHandles();
        Iterator<String> it = setOfWindow.iterator();
        while(it.hasNext()){
            String child = it.next();
            if( !mainWindow.equalsIgnoreCase( child ) ){
                driver.switchTo().window( child );
                WebElement txtEmailId = driver.findElement( By
                        .cssSelector( "input[name=\"emailid\"]" ) );
                WebElement btnSubmit = driver.findElement( By
                        .cssSelector( "[type=\"submit\"][name=\"btnLogin\"]" ) );

                txtEmailId.sendKeys("leminh@gmail.com" );
                btnSubmit.click();
            }
        }
        driver.switchTo().window(mainWindow);


    }

    @Test
    public void handleAlert(){
        Properties pro = new Properties();
        String name = "";
        String strURLAlert = "";
        try{
            FileReader reader = new FileReader(
                    new File(
                            System.getProperty("user.dir") + "/src/test/java/resources/data.properties"
                    )
            );
            pro.load(reader);
            name = pro.getProperty("name");
            strURLAlert = pro.getProperty("URLAlert");
        }catch (Exception e){
            e.printStackTrace();
        }

        driver.get(strURLAlert);
        WebElement txtCustomerID = driver.findElement(
                By.cssSelector("[name=\"cusid\"]"));
        WebElement btnSubmit = driver.findElement(By.cssSelector("[name=\"submit\"]"));

        txtCustomerID.sendKeys(name);
        btnSubmit.click();

        Alert alert = driver.switchTo().alert();
        alert.accept();

    }

    @AfterTest
    public void tearDown(){
        driver.close();
    }
}
