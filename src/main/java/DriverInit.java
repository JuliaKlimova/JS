import java.io.IOException;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

/**
 * Created by Julia Klimova
 */
public class DriverInit {

    public static WebDriver driver;

    @BeforeClass
    public static void setUp() throws IOException {
        System.setProperty("webdriver.chrome.driver", "./libs/chromedriver.exe");

        try {

            driver = new ChromeDriver();
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        } catch (Exception e) {
            System.out.println("Ошибка связи с удаленным сервером: " + ". Возможно такой адрес не найден.\n" + e.getMessage());
        }
    }

    @AfterClass
    public static void tearDown() {
        driver.close();
        driver.quit();
    }

}
