import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import static org.testng.AssertJUnit.assertEquals;

public class IrynaOkunTest {

    //TC_1_1  - Тест кейс:
    //1. Открыть страницу https://openweathermap.org/
    //2. Набрать в строке поиска город Paris
    //3. Нажать пункт меню Search
    //4. Из выпадающего списка выбрать Paris, FR
    //5. Подтвердить, что заголовок изменился на "Paris, FR"

    @Test
    public void testH2TagText_WhenSearchingCityCountry() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "/Applications/soft/chromedriver");
        WebDriver driver = new ChromeDriver();
        //arrange
        String url = "https://openweathermap.org/";
        String cityName = "Paris";
        String expectedResult = "Paris, FR";
        //act

        driver.get(url);
        Thread.sleep(5000);

        WebElement searchCityField = driver.findElement(
                By.xpath("//input[@placeholder = 'Search city']")
        );
        searchCityField.click();
        searchCityField.sendKeys(cityName);

        WebElement searchButton = driver.findElement(
                By.xpath("//div[@id='weather-widget']//button[@type='submit']")
        );
        searchButton.click();

        Thread.sleep(1000);

        WebElement searchParisFRChoiceDropdownMenu = driver.findElement(
                By.xpath("//ul[@class='search-dropdown-menu']/li/span[text() = 'Paris, FR ']")
        );
        searchParisFRChoiceDropdownMenu.click();

        WebElement h2CityCountryNameHeader = driver.findElement(
                By.xpath("//div[@id='weather-widget']//h2")
        );

        Thread.sleep(5000);

        String actualResult = h2CityCountryNameHeader.getText();
        Thread.sleep(5000);
        //Assert

        Assert.assertEquals(actualResult, expectedResult);

        //Thread.sleep(3000);

        driver.quit();
        //driver.close();
    }

    /**TC_11_01
     * 1.  Открыть базовую ссылку
     * 2.  Нажать на пункт меню Guide
     * 3.  Подтвердить, что вы перешли на страницу со ссылкой https://openweathermap.org/guide и что title этой
     * страницы OpenWeatherMap API guide - OpenWeatherMap
     */

    @Test
    public void testTitleOpenWeatherMap() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "/Applications/soft/chromedriver");
        WebDriver driver = new ChromeDriver();
        String url = "https://openweathermap.org/";

        driver.get(url);
        Thread.sleep(5000);

        WebElement guideButton = driver.findElement(By.xpath("//nav//ul/div/ul/li/a[@href='/guide']"));
        guideButton.click();


        String actualResult = driver.getCurrentUrl();
        String expectedResult = "https://openweathermap.org/guide";

        Assert.assertEquals(actualResult, expectedResult);

        //
        WebElement titleOfPage = driver.findElement(
                By.xpath("//main/div/div/div//h1[@class='breadcrumb-title']")
        );
        String actualResult2 = titleOfPage.getText();
        String expectedResult2 = "Guide";

        Assert.assertEquals(actualResult2, expectedResult2);

        driver.quit();

    }

    /**TC_11-2
     * 1.  Открыть базовую ссылку
     * 2.  Нажать на единицы измерения
     * 3. Подтвердить, что температура для города показана в Фарингейтах
     */

    @Test
    public void testTemperatureUI() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "/Applications/soft/chromedriver");
        WebDriver driver = new ChromeDriver();
        String url = "https://openweathermap.org/";

        driver.get(url);
        Thread.sleep(5000);

        WebElement uiF = driver.findElement(
                By.xpath("//div/div/div/div/div/div/div[text()='Imperial: °F, mph']")
        );
        uiF.click();

        WebElement temperatureF = driver.findElement(
                By.xpath("//main//div[@class='section-content']//div/span[@class='heading']")
        );

        String text = temperatureF.getText();
        String actualResult = text.substring(text.length()-2);

        //span[@class='heading']
        //span[text()='54°F']
        //main//div[@class='section-content']//div/span[@class='heading']

        String expectedResult = "°F";

        Assert.assertEquals(actualResult, expectedResult);

        driver.quit();
    }

    /**TC_11_03
     * 1.  Открыть базовую ссылку
     * 2. Подтвердить, что внизу страницы есть панель с текстом
     * “We use cookies which are essential for the site to work. We also use non-essential cookies to help us
     * improve our services. Any data collected is anonymised. You can allow all cookies or manage them individually.”
     * 3. Подтвердить, что на панели внизу страницы есть 2 кнопки “Allow all” и “Manage cookies”
     */

    @Test
    public void testCookies() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "/Applications/soft/chromedriver");
        WebDriver driver = new ChromeDriver();
        String url = "https://openweathermap.org/";

        driver.get(url);
        Thread.sleep(3000);

        WebElement cookiePopup = driver.findElement(
            By.xpath("//div[@class='stick-footer-panel']")
        );

        cookiePopup.isDisplayed();

        WebElement allowAllButton = driver.findElement(
                By.xpath("//div[@class='stick-footer-panel']/div/div/div/div/button[text()='Allow all']")
        );

        String actualResult1 = allowAllButton.getText();
        String expectedResult1 = "Allow all";
        Assert.assertEquals(actualResult1, expectedResult1);

        WebElement manageCookies = driver.findElement(
                By.xpath("//div[@class='stick-footer-panel']/div/div/div/div/a[@href='/cookies-settings']")
        );
        String actualResult2 = manageCookies.getText();
        String expectedResult2 = "Manage cookies";
        Assert.assertEquals(actualResult2, expectedResult2);

        driver.quit();

    }

    /**TC_11_04
     * 1.  Открыть базовую ссылку
     * 2.  Подтвердить, что в меню Support есть 3 подменю с названиями “FAQ”, “How to start” и “Ask a question”
     */

    @Test
    public void testMenuSupport() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "/Applications/soft/chromedriver");
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        String url = "https://openweathermap.org/";
        String expectedResultFaq = "FAQ";
        String expectedResultHowToStart = "How to start";
        String expectedResultAskAQuestion = "Ask a question";

        driver.get(url);
        Thread.sleep(5000);

        WebElement headerMenuSupport = driver.findElement(
                By.xpath("//ul/li/div[@id='support-dropdown']")
        );
        headerMenuSupport.click();
        Thread.sleep(3000);

        WebElement faq = driver.findElement(
                By.xpath("//ul/li/ul/li/a[@href='/faq']")
        );
        WebElement howToStart = driver.findElement(
                By.xpath("//ul/li/ul/li/a[@href='/appid']")
        );
        WebElement askAQuestion = driver.findElement(
                By.xpath("//ul/li/ul/li/a[@href='https://home.openweathermap.org/questions']")
        );

        String actualResult1 = faq.getText();
        String actualResult2 = howToStart.getText();
        String actualResult3 = askAQuestion.getText();

        assertEquals(actualResult1, expectedResultFaq);
        assertEquals(actualResult2, expectedResultHowToStart);
        assertEquals(actualResult3, expectedResultAskAQuestion);

        driver.quit();

    }

    /** TC_11_05
     * 1. Открыть базовую ссылку
     * 2. Нажать пункт меню Support → Ask a question
     * 3. Заполнить поля Email, Subject, Message
     * 4. Не подтвердив CAPTCHA, нажать кнопку Submit
     * 5. Подтвердить, что пользователю будет показана ошибка “reCAPTCHA verification failed, please try again.”
     */

    @Test
    public void testSubmitError() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "/Applications/soft/chromedriver");
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        String url = "https://openweathermap.org/";

        String email = "123@gmail.com";
        //String subject = "Other";
        String message = "I want to get your cast at 5 AM";

        String expectedResult = "reCAPTCHA verification failed, please try again.";

        driver.get(url);
        Thread.sleep(4000);

        WebElement supportMenu = driver.findElement(By.xpath("//div[@id='support-dropdown']"));
        supportMenu.click();

        WebElement askAQuestion = driver.findElement(
                By.xpath("//ul/li/ul/li/a[@href='https://home.openweathermap.org/questions']")
        );
        askAQuestion.click();

        for (String windowHandle : driver.getWindowHandles()) {
            driver.switchTo().window(windowHandle);
            System.out.println("window: " + windowHandle);
        }
        Thread.sleep(5000);

        WebElement verifyThePage = driver.findElement(By.xpath("//h4[@class='headline']"));
        //verifyThePage.isDisplayed();
        Assert.assertTrue(verifyThePage.isDisplayed());

        WebElement emailField = driver.findElement(
                By.xpath("//div/input[@class='form-control string email required']")
        );
        emailField.sendKeys(email);
        Thread.sleep(6000);

        WebElement subjectField = driver.findElement(
                By.id("question_form_subject")
        );
        subjectField.click();
        WebElement selectDropDown = driver.findElement(
                By.xpath("//div/select[@id='question_form_subject']/option[@value='Other']")
        );
        selectDropDown.click();
        Thread.sleep(6000);

        WebElement messageField = driver.findElement(
                By.xpath("//div/textarea[@id='question_form_message']")
        );
        messageField.click();
        messageField.sendKeys(message);

        WebElement submitButton = driver.findElement(
          By.xpath("//input[@data-disable-with='Create Question form']")
        );
        submitButton.click();
        Thread.sleep(7000);

        WebElement errorMessage = driver.findElement(
                By.xpath("//div[@class='help-block']")
        );
        String actualResult = errorMessage.getText();

        assertEquals(actualResult, expectedResult);

        driver.quit();
    }

    /**TC_11_06
     * 1.  Открыть базовую ссылку
     * 2.  Нажать пункт меню Support → Ask a question
     * 3.  Оставить значение по умолчанию в checkbox Are you an OpenWeather user?
     * 4. Оставить пустым поле Email
     * 5. Заполнить поля  Subject, Message
     * 6. Подтвердить CAPTCHA
     * 7. Нажать кнопку Submit
     * 8. Подтвердить, что в поле Email пользователю будет показана ошибка “can't be blank”
     */

    @Test
    public void testLeaveEmptyEmailField() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "/Applications/soft/chromedriver");
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        String url = "https://openweathermap.org/";

        String message = "You have perfect API";

        String expectedResult = "can't be blank";

        driver.get(url);
        Thread.sleep(4000);

        WebElement supportMenu = driver.findElement(By.xpath("//div[@id='support-dropdown']"));
        supportMenu.click();

        WebElement askAQuestion = driver.findElement(
                By.xpath("//ul/li/ul/li/a[@href='https://home.openweathermap.org/questions']")
        );
        askAQuestion.click();

        for (String windowHandle : driver.getWindowHandles()) {
            driver.switchTo().window(windowHandle);
            System.out.println("window: " + windowHandle);
        }
        Thread.sleep(5000);

        WebElement verifyThePage = driver.findElement(By.xpath("//h4[@class='headline']"));
        Assert.assertTrue(verifyThePage.isDisplayed());

        WebElement subjectField = driver.findElement(
                By.xpath("//select[@id='question_form_subject']")
        );
        subjectField.click();
        WebElement dropDownChooseApiDocs = driver.findElement(
                By.xpath("//select[@id='question_form_subject']/option[@value='Tech Issue']")
        );
        dropDownChooseApiDocs.click();

        WebElement messageField = driver.findElement(
                By.xpath("//textarea[@id='question_form_message']")
        );
        messageField.sendKeys(message);

        WebElement captchaVerify = driver.findElement(
               By.xpath("//iframe[@title='reCAPTCHA']")
        );
        captchaVerify.click();
        Thread.sleep(7000);

        WebElement submitButton = driver.findElement(
                By.xpath("//input[@data-disable-with='Create Question form']")
        );
        submitButton.click();

        driver.quit();

    }

    /**TC_11_07
     * 1.  Открыть базовую ссылку
     * 2.  Нажать на единицы измерения Imperial: °F, mph
     * 3.  Нажать на единицы измерения Metric: °C, m/s
     * 4.  Подтвердить, что в результате этих действий, единицы измерения температуры изменились с F на С
     */

    @Test
    public void testChangeUI() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "/Applications/soft/chromedriver");
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        String url = "https://openweathermap.org/";

        String expectedResult = "C";

        driver.get(url);
        Thread.sleep(4000);

        WebElement temperatureChangeToF = driver.findElement(
                By.xpath("//div[@class='grey-container']//div/div/div/div/div[3]")
        );
        temperatureChangeToF.click();

        WebElement temperatureChengeToC = driver.findElement(
               By.xpath("//div[@class='grey-container']//div/div/div/div/div[text()='Metric: °C, m/s']")
        );
        temperatureChengeToC.click();

        WebElement verifyUI = driver.findElement(
                By.xpath("//span[@class='heading']")
        );

        String text = verifyUI.getText();
        String actualResult = text.substring(text.length()-1);

        assertEquals(actualResult, expectedResult);

        driver.quit();
    }

    /** TC_11_08
     * 1.  Открыть базовую ссылку
     * 2.  Нажать на лого компании
     * 3.  Дождаться, когда произойдет перезагрузка сайта, и подтвердить, что текущая ссылка не изменилась
     */

    @Test
    public void testLogoLink() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "/Applications/soft/chromedriver");
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        String url = "https://openweathermap.org/";

        driver.get(url);
        Thread.sleep(4000);

        WebElement logo = driver.findElement(
               By.xpath("//nav//a/img")
        );
        logo.click();

        String actualResult = driver.getCurrentUrl();
        String expectedResult = url;

        assertEquals(actualResult, expectedResult);

        driver.quit();
    }

    /**TC_11_09
     * 1.  Открыть базовую ссылку
     * 2.  В строке поиска в навигационной панели набрать “Rome”
     * 3.  Нажать клавишу Enter
     * 4.  Подтвердить, что вы перешли на страницу в ссылке которой содержатся слова “find” и “Rome”
     * 5. Подтвердить, что в строке поиска на новой странице вписано слово “Rome”
     */

    @Test
    public void testVerifyRomeinURL() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "/Applications/soft/chromedriver");
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        String url = "https://openweathermap.org/";
        String textRome = "Rome";

        driver.get(url);
        Thread.sleep(4000);

        WebElement inputTextInNavMenu = driver.findElement(
                By.xpath("//div/form/input[@name='q']")
        );
        //inputTextInNavMenu.click();
        inputTextInNavMenu.sendKeys(textRome);
        inputTextInNavMenu.submit();

        Thread.sleep(2000);

        String expectedResult1 = "true";
        String expectedResult2 = "Rome";

        String actualResult1 = String.valueOf(driver.getCurrentUrl().contains("find") &&
                driver.getCurrentUrl().contains("Rome") );

        Assert.assertTrue(Boolean.parseBoolean(actualResult1));

        WebElement containsRomeInInput = driver.findElement(
               By.xpath("//div/input[@name='q']")
        );
        String actualResult2 = containsRomeInInput.getAttribute("value");
        assertEquals(actualResult2, expectedResult2);

        driver.quit();
    }

    /**TC_11_10
     * 1.  Открыть базовую ссылку
     * 2.  Нажать на пункт меню API
     * 3.  Подтвердить, что на открывшейся странице пользователь видит 30 оранжевых кнопок
     */

    @Test
    public void testSubmit30OrangeButtons() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "/Applications/soft/chromedriver");
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        String url = "https://openweathermap.org/";
        String expectedResult = "30";

        driver.get(url);
        Thread.sleep(4000);

        WebElement apiTab = driver.findElement(
                By.xpath("//div/ul/li/a[@href='/api']")
        );
        apiTab.click();

        String actualResult = String.valueOf(driver.findElements(
                By.xpath("//a[contains(@class, 'orange')]")
        ).size());

        assertEquals(actualResult, expectedResult);
        
        driver.quit();

    }

}

//    @Test
//    public void test_name() {
//        System.setProperty("webdriver.chrome.driver", "/Applications/soft/chromedriver");
//        WebDriver driver = new ChromeDriver();
//
//        driver.quit();
//        driver.close();
//    }
