package ForTest;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Search {
    private static String OZON_URL = "https://www.ozon.ru";

    WebDriverWait waitFor;

    @FindBy(xpath = "//*[@id=\"__layout\"]/div[1]/div[1]/header[1]/div[1]/div[3]/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/input[1]")
    private WebElement searchBox;

    public Search(WebDriver driver) {
        if (!driver.getCurrentUrl().contains(OZON_URL)) {
            throw new IllegalStateException("This is not the page you are expected");
        }
        PageFactory.initElements(driver, this);
        this.waitFor = new WebDriverWait(driver, 3, 300);
    }

    public void Search(String item){
        searchBox.clear();
        waitFor.until(ExpectedConditions.visibilityOf(searchBox));
        searchBox.clear();
        searchBox.sendKeys(item);
        searchBox.sendKeys(Keys.ENTER);
        searchBox.clear();
    }
}
