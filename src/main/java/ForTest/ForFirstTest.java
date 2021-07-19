package ForTest;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ForFirstTest {
    private static String OZON_URL = "https://www.ozon.ru";

    WebDriverWait waitFor;

    @FindBy(xpath = "//*[@id=\"__layout\"]/div[1]/div[1]/div[3]/div[2]/div[2]/div[2]/div[1]/div[1]/div[1]/div[1]/a[1]/div[2]/div[4]/button[1]")
    private WebElement InGarbage;

    @FindBy(xpath = "//*[@id=\"__layout\"]/div[1]/div[1]/header[1]/div[1]/div[4]/a[2]")
    private WebElement Basket;

    @FindBy(xpath = "//*[@id=\"__layout\"]/div[1]/div[1]/div[1]/div[1]/div[2]/div[3]/div[1]/div[2]/div[1]/div[2]/div[3]/div[2]/div[2]/span[2]")
    private WebElement Deletion;

    @FindBy(className = "button button blue")
    private WebElement Сonfirm;

    public ForFirstTest(WebDriver driver) {
        if (!driver.getCurrentUrl().contains(OZON_URL)) {
            throw new IllegalStateException("This is not the page you are expected");
        }
        PageFactory.initElements(driver, this);
        this.waitFor = new WebDriverWait(driver, 3, 300);
    }

    public void InGarbage(){ InGarbage.click();}
    public void Basket(){Basket.click();}
    public void Deletion(){ Deletion.click();}
    public void Сonfirm(){Сonfirm.click();}
}

