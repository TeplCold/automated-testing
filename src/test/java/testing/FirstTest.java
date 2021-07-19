package testing;
import ForTest.ForFirstTest;
import ForTest.Search;
import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import org.apache.commons.io.output.ByteArrayOutputStream;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.Augmenter;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import static org.junit.Assert.assertEquals;

public class FirstTest extends WebDriverSettings {

    @Attachment(value = "{name}", type = "image/png")
    public static byte[] getFullScreenshot(WebDriver driver, String name) throws IOException {
        WebDriver augmented = new Augmenter().augment(driver);
        Screenshot shot = new AShot().takeScreenshot(augmented);
        BufferedImage img = shot.getImage();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(img, "png", baos);
        baos.flush();
        byte[] imageInByte = baos.toByteArray();
        baos.close();
        return imageInByte;
    }

    @Rule
    public TestWatcher screenshotOnFailure = new TestWatcher() {
        @Override
        protected void failed(Throwable e, Description description) {
            try {
                getFullScreenshot(driver,"Скриншот при ошибки");
            } catch (IOException f) { }
        }
    };

    @Step("Заходим на сайт Ozon")
    public static void IntoOzon(WebDriver driver)throws IOException
    {
        driver.get("https://www.ozon.ru/");
        assertEquals("OZON — интернет-магазин. Миллионы товаров по выгодным ценам",driver.getTitle());

        getFullScreenshot(driver, "скрин: Главная страница");
    }

    @Step("Поиск товара 'Bluetooth-гарнитура Xiaomi Mini Earphones'")
    public static void Search (WebDriver driver)throws IOException
    {
        Search search = new Search(driver);
        search.Search("Bluetooth-гарнитура Xiaomi Mini Earphones");

        getFullScreenshot(driver, "скрин: Поиск товара по названию");
    }

    @Step("добавление в корзину'")
    public static void InGarbage(WebDriver driver)throws IOException
    {
        JavascriptExecutor jse = ((JavascriptExecutor) driver);
        jse.executeScript("scroll(0, 320);");
        ForFirstTest InGarbage = new ForFirstTest(driver);
        InGarbage.InGarbage();

        getFullScreenshot(driver, "скрин: добавление в корзину");
    }

    @Step("переход в Корзину")
    public static void Basket(WebDriver driver) throws IOException, InterruptedException {
        ForFirstTest Basket = new ForFirstTest(driver);
        Basket.Basket();

        Thread.sleep(100);
        JavascriptExecutor jse = ((JavascriptExecutor) driver);
        jse.executeScript("scroll(0, 300);");
        getFullScreenshot(driver, "скрин: товара в корзине");
    }

    @Step("нажатие на кнопку удаление из Корзину")
    public static void Deletion(WebDriver driver) throws IOException, InterruptedException {
        ForFirstTest Deletion = new ForFirstTest(driver);
        Deletion.Deletion();

        getFullScreenshot(driver, "скрин: удаления из Корзины");
    }

    @Step("подтверждение удаления из Корзины")
    public static void Сonfirm(WebDriver driver)throws IOException
    {
        ForFirstTest Сonfirm = new ForFirstTest(driver);
        Сonfirm.Сonfirm();

        getFullScreenshot(driver, "скрин: корзины после удаления");
    }

    @Step("Выход из браузера")
    public static void WaitAndQuit(WebDriver driver) throws InterruptedException
    {
        Thread.sleep(1000);
        driver.close();
        driver.quit();
        System.out.println("test close");
    }

    @Test
    @DisplayName("тест для проверки:поиск, добавление и удаление из корзины товара")
    public void Test1() throws InterruptedException,IOException
    {
        IntoOzon(driver);
        Search(driver);
        InGarbage(driver);
        Basket(driver);
        Deletion(driver);
        Сonfirm(driver);
        WaitAndQuit(driver);
    }
}
