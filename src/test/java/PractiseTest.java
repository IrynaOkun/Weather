import org.openqa.selenium.By;
import org.testng.annotations.Test;
import runner.BaseTest;

import static org.testng.AssertJUnit.assertEquals;

public class PractiseTest extends BaseTest {

    final static By LOGO = By.xpath("//nav//a/img");

    public void clickElement(By by) {
        driver.findElement(by).click();
    }

    @Test
    public void testHumburger() {

        clickElement(LOGO);

        String actualResult = driver.getCurrentUrl();
        String expectedResult = "https://openweathermap.org/";

        assertEquals(actualResult, expectedResult);

    }



}
