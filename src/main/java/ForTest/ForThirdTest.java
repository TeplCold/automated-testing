package ForTest;

        import org.openqa.selenium.WebDriver;
        import org.openqa.selenium.WebElement;
        import org.openqa.selenium.support.FindBy;
        import org.openqa.selenium.support.PageFactory;
        import org.openqa.selenium.support.ui.WebDriverWait;

public class ForThirdTest {
    private static String OZON_URL = "https://www.ozon.ru";

    WebDriverWait waitFor;

    @FindBy(xpath = "//*[@id=\"name\"]")
    private WebElement transition;

    @FindBy(xpath = "//*[@id=\"__layout\"]/div[1]/div[1]/div[4]/div[1]/div[2]/div[3]/div[1]/div[2]/div[2]/button[1]")
    private WebElement favorites;

    @FindBy(xpath = "//*[@id=\"__layout\"]/div[1]/div[1]/header[1]/div[1]/div[4]/a[1]")
    private WebElement toFavorites;

    public ForThirdTest(WebDriver driver) {
        if (!driver.getCurrentUrl().contains(OZON_URL)) {
            throw new IllegalStateException("This is not the page you are expected");
        }
        PageFactory.initElements(driver, this);
        this.waitFor = new WebDriverWait(driver, 3, 300);
    }

    public void transition(){transition.click();}
    public void favorites(){favorites.click();}
    public void toFavorites(){toFavorites.click();}
}
