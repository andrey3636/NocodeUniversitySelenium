import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;



public class BasePage {
    // driver - Поле для обьекта класса WebDriver, который контролирует браузер
    protected WebDriver driver;

    // Метод создания обьекта класса BasePage
    public BasePage(WebDriver driver) {
        this.driver = driver;
        // Инициализация драйвера для запуска браузера
        PageFactory.initElements(driver, this);
    }

    // Метод ввода текста внутрь елемента Веб-страницы
    public void enterTextToElement(String text, WebElement element) {
        // Создание обьекта WebDriverWait, для ожидания ответа до 5 секунд
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        // Ожидание условия, при котором на елемент Веб-страницы можно нажать
        wait.until(ExpectedConditions.elementToBeClickable(element));
        element.click();
        element.clear();
        element.sendKeys(text);
    }

    // Метод имитации нажимания на елемент Веб-страницы
    public void clickOnTheElement(WebElement element) {
        // Создание обьекта WebDriverWait, для ожидания ответа до 5 секунд
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        // Ожидание условия, при котором на елемент Веб-страницы можно нажать
        wait.until(ExpectedConditions.elementToBeClickable(element));
        element.click();
    }

    // Метод считывания текста внутри елемента Веб-страницы
    public String getTextOfElement(WebElement element){
        // Создание обьекта WebDriverWait, для ожидания ответа до 5 секунд
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        // Ожидание условия, при котором на елемент Веб-страницы можно нажать
        wait.until(ExpectedConditions.textToBePresentInElement(element,""));
        return element.getText();
    }

    // Метод ожидания полной загрузки Веб-страницы
    public void loadPage() {
        // Создание обьекта WebDriverWait, для ожидания ответа до 5 секунд
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        // Ожидание условия, когда ниже приведенный сценарий (а именно "return document.readyState")
        // вернет ответ о полной загрузки Веб-страницы (а именно "complete")
        wait.until(webDriver -> ((JavascriptExecutor) webDriver)
                   .executeScript("return document.readyState")
                   .equals("complete"));
    }

    // Метод ожидания и считывания URL адреса Веб-страницы
    public String getURL() {
        loadPage();
        return driver.getCurrentUrl();
    }
}
