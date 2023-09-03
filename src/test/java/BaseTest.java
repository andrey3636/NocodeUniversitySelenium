import io.qameta.allure.Attachment;
import org.junit.After;
import org.junit.AssumptionViolatedException;
import org.junit.Before;
import org.junit.jupiter.api.*;
import org.junit.Rule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.Random;


// Базовый класс тестирования BaseTest
public class BaseTest {
    // driver - Поле для обьекта класса WebDriver, который контролирует браузер
    WebDriver driver;

    // Строка, с базовым URL адресом Веб-приложения
    protected String BASE_URL = "https://erich416.softr.app";

    @Before
    // Метод, который активируется перед запуском всех тестов
    public void setUp() {
        // Создание обьекта класа ChromeOptions для настройки драйвера Google Chrome
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\MR Popovics\\OneDrive\\Рабочий стол\\Drive\\chromedriver.exe");
        driver = new ChromeDriver();
        // Изменение разрешения окна до максимальных размеров
        driver.manage().window().maximize();
        // Переход по URL адресу
        driver.get(BASE_URL);
    }

    // Обьект, который определяет правило для каждого теста
    @Rule
    public TestWatcher screenShotFailure = new TestWatcher() {
        // Если тест провалился
        @Override
        protected void failed(Throwable e, Description description) {
            // Сделать снимок экрана
            makeScreenshotOnFailure();
            driver.close();
            driver.quit();
        }
        
        // Если тест был пройден
        @Override
        protected void succeeded(Description description) {
            driver.close();
            driver.quit();
        }

        // Если тест был пропущен
        @Override
        protected void skipped(AssumptionViolatedException e, Description description) {
            makeScreenshotOnFailure();
            driver.close();
            driver.quit();
        }

        // Метод-дополнение для преобразования снимка экрана
        @Attachment
        public byte[] makeScreenshotOnFailure(){
            return ((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES);
        }
    };

    // Метод генерации случайного электронного адреса
    public static String getRandomEmail() {
        // Строка с доступными символами
        String SALTCHARS = "abcdefghijklmnopqrstufwxyz1234567890";
        StringBuilder salt = new StringBuilder();
        // Генератор случайных чисел
        Random rnd = new Random();
        while (salt.length() < 20) { // Пока длина случайного адреса меньше 20
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            // Добавить случайный символ в конец строки
            salt.append(SALTCHARS.charAt(index));
        }
        String saltStr = salt.toString();
        return saltStr + "@gmail.com";
    }
}
