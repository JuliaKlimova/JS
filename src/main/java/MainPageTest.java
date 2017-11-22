import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.concurrent.TimeUnit;

/**
 * Created by Julia Klimova
 */

public class MainPageTest extends DriverInit {

    @Test
    public void case00() throws Exception {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;

        // 1. Открыть гугл
        driver.get("https://google.com");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        // 2. Узнать ширину поля ввода в пикселях
        WebElement element = driver.findElement(By.id("lst-ib"));
        System.out.println("Ширина поля ввода в пикселях: " +
                getTextByJavascript(element));

        // 3. Проверить наличие скрола на странице
        System.out.println("Проверка скрола: " +
                checkScroll());

        // 4. Ввести запрос в поле поиска
        element.sendKeys("JavaScript");

        // 5. Снять фокус с поля ввода (удалить фокус)
         cancelFocus(element);

        // 6. Выпослнить поиск (дождаться открытия страницы с результатами)
        element.submit();
        waitForWebElement_title("JavaScript", 10);

        // 7. Проверить наличие скрола на странице
        System.out.println("Проверка скрола: " +
                checkScroll());

        Assert.assertTrue(
                driver.getTitle().contains("JavaScript"), "Case 00 is failed.");
        System.out.println("Case 00 is passed.");
    }
    public String getTextByJavascript(final WebElement element) {
        String script = "var element = arguments[0];"
                + "return element.clientWidth;"
                ;
        return (String) ((JavascriptExecutor)driver).executeScript(script, element).toString();
    }

    public String checkScroll() {
        String script = "if (document.body.offsetHeight > window.innerHeight) {\n" +
                "        return (\"Скролл есть\");\n" +
                "    } else {\n" +
                "        return(\"Скролла нет\");\n" +
                "    }"
                ;
        return (String) ((JavascriptExecutor)driver).executeScript(script).toString();
    }

    public void cancelFocus(final WebElement element) {
        String script = "element.blur();"
                ;
    }

    public void waitForWebElement_title(String title, int count) {
        WebDriverWait wait = new WebDriverWait(driver,count);
        wait.until(ExpectedConditions.titleContains(title));
    }
}
