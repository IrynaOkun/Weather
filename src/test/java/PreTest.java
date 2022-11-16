import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;
import runner.BaseTest;

import java.util.List;

import static java.sql.DriverManager.getDriver;
import static org.testng.AssertJUnit.assertEquals;

public class PreTest extends BaseTest {

    @Test
    public void testHumburger() {

        WebElement logo = driver.findElement(
                By.xpath("//nav//a/img")
        );
        logo.click();

        String actualResult = driver.getCurrentUrl();
        String expectedResult = "https://openweathermap.org/";

        assertEquals(actualResult, expectedResult);
    }

    //Actions builder = new Actions(driver);
    //builder.sendKeys(Keys.chord(Keys.CONTROL, "+")).perform();
}
