package testing;

import ForTest.ForSecondTest;
import ForTest.Search;
import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import org.apache.commons.io.output.ByteArrayOutputStream;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.Augmenter;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import static org.junit.Assert.assertEquals;

public class SecondTest extends WebDriverSettings {

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

    @Step("Поиск товара 'Минимойка Karcher K 3 EU'")
    public static void Search_Karcher_K_3 (WebDriver driver) throws IOException, InterruptedException {
        Search search = new Search(driver);
        search.Search("Минимойка Karcher K 3 EU");

        getFullScreenshot(driver, "скрин: Поиск товара 'Минимойка Karcher K 3 EU'");

        Thread.sleep(100);
        JavascriptExecutor jse = ((JavascriptExecutor) driver);
        jse.executeScript("scroll(0, 300);");
        getFullScreenshot(driver, "скрин: страницы с результатом поиска Karcher_K_3");

    }

    @Step("Переход на страницу с товаром Karcher_K_3")
    public static void description_Karcher_K_3  (WebDriver driver) throws IOException
    {
        ForSecondTest description_Karcher_K_3 = new ForSecondTest(driver);
        description_Karcher_K_3.description();

        JavascriptExecutor jse = ((JavascriptExecutor) driver);
        jse.executeScript("scroll(0, 300);");
        getFullScreenshot(driver, "скрин: страницы с товаром Karcher_K_3");
    }

    @Step("добавить в сравнение KarcherK3")
    public static void ComparisonKarcherK3(WebDriver driver)throws IOException
    {
        ForSecondTest ComparisonKarcherK3 = new ForSecondTest(driver);
        ComparisonKarcherK3.Comparison();

        getFullScreenshot(driver, "скрин: добавление в сравнение KarcherK3");
    }

    @Step("Поиск товара 'Минимойка Karcher K4 Compact, 1.637-500.0, желтый, черный'")
    public static void Search_Karcher_K4_Compact (WebDriver driver ) throws IOException, InterruptedException {
        Search search = new Search(driver);
        search.Search("Минимойка Karcher K4 Compact, 1.637-500.0, желтый, черный");

        getFullScreenshot(driver, "скрин: Поиск товара 'Минимойка Karcher K4 Compact'");

        Thread.sleep(100);
        JavascriptExecutor jse = ((JavascriptExecutor) driver);
        jse.executeScript("scroll(0, 300);");
        getFullScreenshot(driver, "скрин: страницы после поиска Karcher K4 Compact");
    }

    @Step("Переход на страницу с товаром Karcher K4 Compact")
    public static void description_Karcher_K4_Compact  (WebDriver driver) throws IOException
    {
        ForSecondTest description_Karcher_K4_Compact = new ForSecondTest(driver);
        description_Karcher_K4_Compact.description();

        JavascriptExecutor jse = ((JavascriptExecutor) driver);
        jse.executeScript("scroll(0, 300);");
        getFullScreenshot(driver, "скрин: страницы с товаром Karcher K4 Compact");
    }

    @Step("добавить в сравнение Karcher K4 Compact")
    public static void ComparisonKarcher_K4_Compact(WebDriver driver)throws IOException
    {
        ForSecondTest Comparison_Karcher_K4_Compact = new ForSecondTest(driver);
        Comparison_Karcher_K4_Compact.Comparison();

        getFullScreenshot(driver, "скрин: добавление в сравнение Karcher K4 Compact");
    }

    @Step("перейти на страницу сравнение товаров")
    public static void Сomparison(WebDriver driver)throws IOException
    {
        WebElement myElement = driver.findElement(By.xpath("//*[@id=\"__layout\"]/div[1]/div[1]/header[1]/div[2]/div[3]/div[1]/div[1]"));
        Actions builder = new Actions(driver);
        builder.moveToElement(myElement).build().perform();
        getFullScreenshot(driver, "скрин: наведение на popup menu");
        driver.findElement(By.xpath("//*[@id=\"__layout\"]/div[1]/div[1]/header[1]/div[2]/div[3]/div[1]/div[1]/div[2]/div[2]/a[1]")).click();

        JavascriptExecutor jse = ((JavascriptExecutor) driver);
        jse.executeScript("scroll(0, 200);");
        getFullScreenshot(driver, "скрин: страницы 'сравнение товаров'");
    }

    @Step("очистить список товаров сравнения")
    public static void ComparisonClear(WebDriver driver)throws IOException
    {
        ForSecondTest Claer = new ForSecondTest(driver);
        Claer.Claer();

        getFullScreenshot(driver, "скрин: страница после очищения списка сравнения");
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
    @DisplayName("тест для проверки:поиск, добавление, сравннения товаров")
    public void Test2() throws InterruptedException,IOException
    {
        IntoOzon(driver);
        Search_Karcher_K_3(driver);
        description_Karcher_K_3(driver);
        ComparisonKarcherK3(driver);
        Search_Karcher_K4_Compact(driver);
        description_Karcher_K4_Compact(driver);
        ComparisonKarcher_K4_Compact(driver);
        Сomparison(driver);
        ComparisonClear(driver);
        WaitAndQuit(driver);
    }
}
