package testing;

import ForTest.Search;
import ForTest.ForThirdTest;
import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import org.apache.commons.io.output.ByteArrayOutputStream;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.openqa.selenium.*;
import org.openqa.selenium.remote.Augmenter;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import static org.junit.Assert.assertEquals;

public class ThirdTest extends WebDriverSettings {

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
                getFullScreenshot(driver,"Скриншот: ошибка");
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

    @Step("Ввод некорректных символов")
    public static void InvalidCharacters (WebDriver driver)throws IOException
    {
        Search InvalidCharacters = new Search(driver);
        InvalidCharacters.Search("ase6+21e");

        JavascriptExecutor jse = ((JavascriptExecutor) driver);
        getFullScreenshot(driver, "Скриншот страницы после ввод некорректных символов");
    }

    @Step("ввод коректных символов")
    public static void Сorrectly  (WebDriver driver)throws IOException
    {
        Search correctly = new Search(driver);
        correctly.Search("Сканер Epson Perfection V370 (B11B207313)");

        getFullScreenshot(driver, "Скриншот страницы после ввод коректных символов");
    }

    @Step("переход по товару 'Сканер Epson Perfection V370 (B11B207313)'")
    public static void Transition(WebDriver driver)throws IOException
    {
        JavascriptExecutor jse = ((JavascriptExecutor) driver);
        jse.executeScript("scroll(0, 250);");
        ForThirdTest transition = new ForThirdTest(driver);
        transition.transition();

        getFullScreenshot(driver, "Скриншот страницы с товаром 'Сканер Epson Perfection V370 (B11B207313)'");
    }

    @Step("добавить в избранное")
    public static void Favorites(WebDriver driver)throws IOException
    {
        ForThirdTest favorites = new ForThirdTest(driver);
        favorites.favorites();

        getFullScreenshot(driver, "Скриншот добавить в избранное");
    }

    @Step("переход 'в избранное'")
    public static void ToFavorites(WebDriver driver)throws IOException
    {
        ForThirdTest toFavorites = new ForThirdTest(driver);
        toFavorites.toFavorites();

        JavascriptExecutor jse = ((JavascriptExecutor) driver);
        jse.executeScript("scroll(0, 300);");
        getFullScreenshot(driver, "Скриншот страницы избранное");
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
    @DisplayName("тест для проверки: ввода некорректных символов,ввод коректных символов, добавление в избранное")
    public void Test3() throws InterruptedException,IOException
    {
        IntoOzon(driver);
        InvalidCharacters(driver);
        Сorrectly(driver);
        Transition(driver);
        Favorites(driver);
        ToFavorites(driver);
        WaitAndQuit(driver);
    }
}