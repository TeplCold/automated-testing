package testing;

        import org.junit.Before;
        import org.openqa.selenium.chrome.ChromeDriver;

public class WebDriverSettings {

    public ChromeDriver driver;
    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver","D:/test_ozon.ru/chromedriver/chromedriver.exe");
        driver = new ChromeDriver();
    }
}
