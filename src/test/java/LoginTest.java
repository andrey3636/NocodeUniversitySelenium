import io.qameta.allure.Description;
import org.junit.jupiter.api.*;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

// Класс-тестировщик страницы авторизации
public class LoginTest extends BaseTest {
    // Строка с электронным адресом учителя
    static String TeacherEmail = "bmw@gmail.com";
    // Строка с электронным адресом студента
    static String StudentEmail = "malik@example.com";
    // Строка с паролем к акаунтам с адресами выше
    static String Password = "123456";

    // Тест перемещения на страницу регистрации
    @Test
    @DisplayName("Move to Sign Up page")
    @Description("Redirect to 'Sign up' page via button link")
    public void MoveToSignUp(){
        LandingPage page = new LandingPage(driver);
        String LandingURL = page.getURL();
        page.clickOnSignInButton();
        page.loadPage();
        LoginPage login = new LoginPage(driver);
        login.clickOnSignUpButton();
        // Проверка URL-адреса
        assertEquals(LandingURL + "sign-up", login.getURL());
    }

    // Тест перемещения на страницу "Forgot password"
    @Test
    @DisplayName("Move to 'Forgot password' page")
    @Description("Redirect to 'Forgot password' page via link")
    public void MoveToForgotPassword(){
        LandingPage page = new LandingPage(driver);
        String LandingURL = page.getURL();
        page.clickOnSignInButton();
        page.loadPage();
        LoginPage login = new LoginPage(driver);
        login.clickOnForgotPasswordLink();

        // Проверка URL-адреса
        assertEquals(LandingURL + "forgot-password", login.getURL());
    }

    // Тест отображения пароля при нажатии на иконку-глаз
    @Test
    @DisplayName("Check showing of password")
    @Description("Checking show of password by clicking 'eye'")
    public void CheckEyeTest(){
        LandingPage page = new LandingPage(driver);
        page.clickOnSignInButton();
        page.loadPage();
        LoginPage login = new LoginPage(driver);

        // Проверка типа поля как пароля
        assertEquals("password", login.getPasswordType());
        login.clickOnPasswordEye();
        // Проверка типа поля как текста
        assertEquals("text", login.getPasswordType());
    }

    // Тест авторизации при пустых полях ввода
    @Test
    @DisplayName("Check empty Sign in form")
    @Description("Click on 'Sign in' button form with empty fields to check validation")
    public void CheckValidationEmptySignIn(){
        LandingPage page = new LandingPage(driver);
        page.clickOnSignInButton();
        page.loadPage();
        LoginPage login = new LoginPage(driver);
        // Проверка на отсутствие ошибок
        assertEquals("", login.getLoginErrorDiv().getText());
        login.clickOnSignInButton();

        // Проверка на присутствие какой-либо ошибки
        assertNotEquals("", login.getLoginErrorDiv().getText());
    }

    // Тест авторизации при правильных данных авторизации студента
    @Test
    @DisplayName("Check Sign in with valid Student data")
    @Description("Check Sign in with valid data of Student role")
    public void CheckValidSignInStudent(){
        LandingPage page = new LandingPage(driver);
        String LandingURL = page.getURL();
        page.clickOnSignInButton();
        page.loadPage();
        LoginPage login = new LoginPage(driver);
        // Проверка на отсутствие ошибок
        assertEquals("", login.getLoginErrorDiv().getText());

        login.enterTextToElement(StudentEmail, login.getEmailField());
        login.enterTextToElement(Password, login.getPasswordField());
        login.clickOnSignInButton();

        // Ожидание до 10 секунд
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        // Ожидание, пока элемент иконки профиля не будет загружен
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button.MuiButtonBase-root:first-of-type")));

        // Проверка URL-адреса
        assertEquals(LandingURL, login.getURL());
    }

    // Тест авторизации при правильных данных авторизации учителя
    @Test
    @DisplayName("Check Sign in with valid Teacher data")
    @Description("Check Sign in with valid data of Teacher role")
    public void CheckValidSignInTeacher(){
        LandingPage page = new LandingPage(driver);
        String LandingURL = page.getURL();
        page.clickOnSignInButton();
        page.loadPage();
        LoginPage login = new LoginPage(driver);
        // Проверка на отсутствие ошибок
        assertEquals("", login.getLoginErrorDiv().getText());

        login.enterTextToElement(TeacherEmail, login.getEmailField());
        login.enterTextToElement(Password, login.getPasswordField());
        login.clickOnSignInButton();

        // Ожидание до 10 секунд
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        // Ожидание, пока элемент иконки профиля не будет загружен
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button.MuiButtonBase-root:first-of-type")));

        // Проверка URL-адреса
        assertEquals(LandingURL, login.getURL());
    }

    // Тест авторизации при правильном электронном адресе студента и пустым паролем
    @Test
    @DisplayName("Check Sign in with Student email and empty password")
    @Description("Check Sign in with valid Student email, but empty password field")
    public void CheckSignInStudentEmptyPassword(){
        LandingPage page = new LandingPage(driver);
        page.clickOnSignInButton();
        page.loadPage();
        LoginPage login = new LoginPage(driver);
        // Проверка на отсутствие ошибок
        assertEquals("", login.getLoginErrorDiv().getText());

        login.enterTextToElement(StudentEmail, login.getEmailField());
        login.clickOnSignInButton();

        // Проверка на присутствие какой-либо ошибки
        assertNotEquals("", login.getLoginErrorDiv().getText());
    }

    // Тест авторизации при пустом электронном адресе и правильным паролем
    @Test
    @DisplayName("Check Sign in with empty email and valid password")
    @Description("Check Sign in with valid password, but empty email field")
    public void CheckSignInStudentEmptyEmail(){
        LandingPage page = new LandingPage(driver);
        page.clickOnSignInButton();
        page.loadPage();
        LoginPage login = new LoginPage(driver);
        // Проверка на отсутствие ошибок
        assertEquals("", login.getLoginErrorDiv().getText());

        login.enterTextToElement(Password, login.getPasswordField());
        login.clickOnSignInButton();

        // Проверка на присутствие какой-либо ошибки
        assertNotEquals("", login.getLoginErrorDiv().getText());
    }

    // Тест авторизации при неправильных данных авторизации учителя
    @Test
    @DisplayName("Check Sign in with invalid Teacher data")
    @Description("Check Sign in with invalid data of Teacher role")
    public void CheckInvalidSignInTeacher(){
        LandingPage page = new LandingPage(driver);
        page.clickOnSignInButton();
        page.loadPage();
        LoginPage login = new LoginPage(driver);
        String URL = login.getURL();
        // Проверка на отсутствие ошибок
        assertEquals("", login.getLoginErrorDiv().getText());

        login.enterTextToElement(TeacherEmail, login.getEmailField());
        login.enterTextToElement("1", login.getPasswordField());
        login.clickOnSignInButton();

        // Ожидание - 1 секунда
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
        }
        // Проверка URL адреса
        assertEquals(URL, login.getURL());
        // Проверка на присутствие какой-либо ошибки
        assertNotEquals("", login.getLoginErrorDiv().getText());
    }

    // Тест авторизации при неправильных данных авторизации студента
    @Test
    @DisplayName("Check Sign in with invalid Student data")
    @Description("Check Sign in with invalid data of Student role")
    public void CheckInvalidSignInStudent(){
        LandingPage page = new LandingPage(driver);
        page.clickOnSignInButton();
        page.loadPage();
        LoginPage login = new LoginPage(driver);
        String URL = login.getURL();
        // Проверка на отсутствие ошибок
        assertEquals("", login.getLoginErrorDiv().getText());

        login.enterTextToElement("random@gmail.com", login.getEmailField());
        login.enterTextToElement(Password, login.getPasswordField());
        login.clickOnSignInButton();

        // Ожидание - 1 секунда
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
        }
        // Проверка URL адреса
        assertEquals(URL, login.getURL());
        // Проверка на присутствие какой-либо ошибки
        assertNotEquals("", login.getLoginErrorDiv().getText());
    }
}
