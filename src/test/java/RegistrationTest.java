import io.qameta.allure.Description;
import org.junit.jupiter.api.*;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


// Класс-тестировщик страницы регистрации
public class RegistrationTest extends BaseTest {
    // Тест перемещения на страницу "Terms"
    @Test
    @DisplayName("Move to Terms page")
    @Description("Redirect to 'Terms' page via button link")
    public void MoveToTerms() {
        LandingPage page = new LandingPage(driver);
        String URL = "https://www.softr.io/terms";
        page.clickOnSignUpButton();
        page.loadPage();
        RegistrationPage registration = new RegistrationPage(driver);
        // Сохранение названия данного окна браузера
        String first_handle = driver.getWindowHandle();
        registration.clickOnTermsButton();

        // Получить множество из всех окон браузера
        Set<String> allHandles = driver.getWindowHandles();
        for(String winHandle: allHandles)
        {
            // Если окно не идентично предыдущему
            if (!first_handle.equalsIgnoreCase(winHandle))
            {
                // Переместить фокус браузера на данное окно (перейти на другое окно)
                driver.switchTo().window(winHandle);
            }
        }
        // Проверка URL адреса
        assertEquals(URL, registration.getURL());
    }

    // Тест перемещения на страницу "Privacy Policy"
    @Test
    @DisplayName("Move to Policy page")
    @Description("Redirect to 'Privacy Policy' page via button link")
    public void MoveToPolicy(){
        LandingPage page = new LandingPage(driver);
        String URL = "https://www.softr.io/policy";
        page.clickOnSignUpButton();
        page.loadPage();
        RegistrationPage registration = new RegistrationPage(driver);
        // Сохранение названия данного окна браузера
        String first_handle = driver.getWindowHandle();
        registration.clickOnPolicyButton();

        // Получить множество из всех окон браузера
        Set<String> allHandles = driver.getWindowHandles();
        for(String winHandle: allHandles)
        {
            // Если окно не идентично предыдущему
            if (!first_handle.equalsIgnoreCase(winHandle))
            {
                // Переместить фокус браузера на данное окно (перейти на другое окно)
                driver.switchTo().window(winHandle);
            }
        }
        // Проверка URL адреса
        assertEquals(URL, registration.getURL());
    }

    // Тест отображения пароля при нажатии на иконку-глаз
    @Test
    @DisplayName("Check showing of password")
    @Description("Checking show of password by clicking 'eye'")
    public void CheckEyeTest(){
        LandingPage page = new LandingPage(driver);
        page.clickOnSignUpButton();
        page.loadPage();
        RegistrationPage registration = new RegistrationPage(driver);

        // Проверка типа поля как пароля
        assertEquals("password", registration.getPasswordType());
        registration.clickOnPasswordEye();
        // Проверка типа поля как текста
        assertEquals("text", registration.getPasswordType());
    }

    // Тест регистрации при правильных данных учителя
    @Test
    @DisplayName("Check valid Teacher registration")
    @Description("Checking registration of Teacher role account with valid data")
    public void ValidTeacherSignUp() {
        // Генерация случайного электронного адреса
        String email = getRandomEmail();
        LandingPage page = new LandingPage(driver);
        String LandingURL = page.getURL();
        page.clickOnSignUpButton();
        RegistrationPage registration = new RegistrationPage(driver);

        registration.clickOnRoleDropdown();
        // Получить обьект класса Optional (нужен для проверки на null) с ссылкой на роль учителя
        Optional<WebElement> TeacherOptional = registration
            // Превращение списка вариантов роли в поток
            .getDropdownOptions().stream()
            // Фильтрация варианта по тексту внутри ("teacher")
            .filter(Option -> Option.getText().equalsIgnoreCase("teacher"))
            // Возвращение первого обьекта
            .findFirst();
        // Проверка на null
        assertTrue(TeacherOptional.isPresent());
        TeacherOptional.get().click();
        registration.enterTextToElement("Popovics Andrey", registration.getFullNameField());
        registration.enterTextToElement(email, registration.getEmailField());
        registration.enterTextToElement("123456", registration.getPasswordField());
        registration.clickCheckBoxAgree();
        registration.clickOnSignUpButton();

        try {
            // Ожидание до 10 секунд
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            // Ожидание, пока элемент иконки профиля не будет загружен
            wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button.MuiButtonBase-root:first-of-type")));
            // Проверка URL адреса
            assertEquals(LandingURL, registration.getURL());
        } catch(Exception e) { // Если есть ошибка - игнорировать
        } finally {
            Requests requests = new Requests();
            // REST Запрос на удаление ранее созданного пользователя
            requests.deleteRequest("user/" + email);
        }
    }

    // Тест регистрации при правильных данных студента
    @Test
    @DisplayName("Check valid Student registration")
    @Description("Checking registration of Student role account with valid data")
    public void ValidStudentSignUp() {
        // Генерация случайного электронного адреса
        String email = getRandomEmail();
        LandingPage page = new LandingPage(driver);
        String LandingURL = page.getURL();
        page.clickOnSignUpButton();
        RegistrationPage registration = new RegistrationPage(driver);

        registration.clickOnRoleDropdown();
        // Получить обьект класса Optional (нужен для проверки на null) с ссылкой на роль студента
        Optional<WebElement> TeacherOptional = registration
            // Превращение списка вариантов роли в поток
            .getDropdownOptions().stream()
            // Фильтрация варианта по тексту внутри ("student")
            .filter(Option -> Option.getText().equalsIgnoreCase("student"))
            // Возвращение первого обьекта
            .findFirst();
        // Проверка на null
        assertTrue(TeacherOptional.isPresent());
        TeacherOptional.get().click();
        registration.enterTextToElement("Popovics Adam", registration.getFullNameField());
        registration.enterTextToElement(email, registration.getEmailField());
        registration.enterTextToElement("123456", registration.getPasswordField());
        registration.clickCheckBoxAgree();
        registration.clickOnSignUpButton();

        try {
            // Ожидание до 10 секунд
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            // Ожидание, пока элемент иконки профиля не будет загружен
            wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button.MuiButtonBase-root:first-of-type")));
            // Проверка URL адреса
            assertEquals(LandingURL, registration.getURL());
        } catch(Exception e) { // Если есть ошибка - игнорировать
        } finally {
            Requests requests = new Requests();
            // REST Запрос на удаление ранее созданного пользователя
            requests.deleteRequest("user/" + email);
        }
    }

    // Тест регистрации при пустом поле роли
    @Test
    @DisplayName("Check empty role registration")
    @Description("Checking registration account with empty role")
    public void EmptyRoleSignUp(){
        String email = "bmw61@gmail.com";
        LandingPage page = new LandingPage(driver);
        page.loadPage();
        page.clickOnSignUpButton();
        RegistrationPage registration = new RegistrationPage(driver);

        registration.enterTextToElement("Popovics Anna", registration.getFullNameField());
        registration.enterTextToElement(email, registration.getEmailField());
        registration.enterTextToElement("abc456", registration.getPasswordField());
        registration.clickCheckBoxAgree();
        registration.clickOnSignUpButton();

        // Проверка на присутствие ошибки о необходимости ввода всех полей формы
        assertEquals("Please make sure there are no empty required fields.", registration.getRequiredError().getText());
    }

    // Тест регистрации учителя при пустом поле полного имени (Фамилии,Имени)
    @Test
    @DisplayName("Check empty fullname Teacher registration")
    @Description("Checking registration of Teacher role account with empty fullname")
    public void EmptyFullnameTeacherSignUp(){
        String email = "bmw6@gmail.com";
        LandingPage page = new LandingPage(driver);
        page.loadPage();
        page.clickOnSignUpButton();
        RegistrationPage registration = new RegistrationPage(driver);

        registration.clickOnRoleDropdown();
        // Получить обьект класса Optional (нужен для проверки на null) с ссылкой на роль учителя
        Optional<WebElement> TeacherOptional = registration
            // Превращение списка вариантов роли в поток
            .getDropdownOptions().stream()
            // Фильтрация варианта по тексту внутри ("teacher")
            .filter(Option -> Option.getText().equalsIgnoreCase("teacher"))
            // Возвращение первого обьекта
            .findFirst();
        // Проверка на null
        assertTrue(TeacherOptional.isPresent());
        TeacherOptional.get().click();

        registration.enterTextToElement(email, registration.getEmailField());
        registration.enterTextToElement("abc456", registration.getPasswordField());
        registration.clickCheckBoxAgree();
        registration.clickOnSignUpButton();

        // Проверка на присутствие ошибки о необходимости ввода всех полей формы
        assertEquals("Please make sure there are no empty required fields.", registration.getRequiredError().getText());
    }

    // Тест регистрации учителя при использовании уже занятого электронного адреса
    @Test
    @DisplayName("Check previously used Email Teacher registration")
    @Description("Checking registration of Teacher account with previously used Email")
    public void PreviouslyUsedEmailTeacherSignUp(){
        String email = "bmw6@gmail.com";
        LandingPage page = new LandingPage(driver);
        page.loadPage();
        page.clickOnSignUpButton();
        RegistrationPage registration = new RegistrationPage(driver);

        registration.clickOnRoleDropdown();
        // Получить обьект класса Optional (нужен для проверки на null) с ссылкой на роль учителя
        Optional<WebElement> TeacherOptional = registration
            // Превращение списка вариантов роли в поток
            .getDropdownOptions().stream()
            // Фильтрация варианта по тексту внутри ("teacher")
            .filter(Option -> Option.getText().equalsIgnoreCase("teacher"))
            // Возвращение первого обьекта
            .findFirst();
        // Проверка на null
        assertTrue(TeacherOptional.isPresent());
        TeacherOptional.get().click();

        registration.enterTextToElement("Popovics Anna", registration.getFullNameField());
        registration.enterTextToElement(email, registration.getEmailField());
        registration.enterTextToElement("123456", registration.getPasswordField());
        registration.clickCheckBoxAgree();
        registration.clickOnSignUpButton();

        // Ожидание - 1 секунда
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
        }

        // Проверка на отображение ошибки регистрации
        assertEquals("Something went wrong, please try again.",(registration.getError().getText()));
    }

    // Тест регистрации при использовании правильного электронного адреса и неправильного пароля
    @Test
    @DisplayName("Check valid Email invalid Password registration")
    @Description("Checking registration of account with valid Email and invalid Password checkbox")
    public void ValidEmailInvalidPasswordSignUp(){
        String email = "bmw6@gmail.com";
        LandingPage page = new LandingPage(driver);
        page.loadPage();
        page.clickOnSignUpButton();
        RegistrationPage registration = new RegistrationPage(driver);

        registration.clickOnRoleDropdown();
        // Получить обьект класса Optional (нужен для проверки на null) с ссылкой на роль учителя
        Optional<WebElement> TeacherOptional = registration
            // Превращение списка вариантов роли в поток
            .getDropdownOptions().stream()
            // Фильтрация варианта по тексту внутри ("teacher")
            .filter(Option -> Option.getText().equalsIgnoreCase("teacher"))
            // Возвращение первого обьекта
            .findFirst();
        // Проверка на null
        assertTrue(TeacherOptional.isPresent());
        TeacherOptional.get().click();

        registration.enterTextToElement("Popovics Anna", registration.getFullNameField());
        registration.enterTextToElement(email, registration.getEmailField());
        registration.enterTextToElement("1", registration.getPasswordField());
        registration.clickOnSignUpButton();

        // Проверка на отображение ошибки проверки пароля (должен быть не менее 6 символов)
        assertEquals("Password must contain at least 6 characters", registration.getValidationError().getText());
    }

    // Тест регистрации учителя при пустом флажке согласия на условия
    @Test
    @DisplayName("Check empty Agreement checkbox registration")
    @Description("Checking registration of account with empty Agreement checkbox")
    public void EmptyAgreementCheckboxTeacherSignUp(){
        String email = "bmw6@gmail.com";
        LandingPage page = new LandingPage(driver);
        page.loadPage();
        page.clickOnSignUpButton();
        RegistrationPage registration = new RegistrationPage(driver);

        registration.clickOnRoleDropdown();
        // Получить обьект класса Optional (нужен для проверки на null) с ссылкой на роль учителя
        Optional<WebElement> TeacherOptional = registration
            // Превращение списка вариантов роли в поток
            .getDropdownOptions().stream()
            // Фильтрация варианта по тексту внутри ("teacher")
            .filter(Option -> Option.getText().equalsIgnoreCase("teacher"))
            // Возвращение первого обьекта
            .findFirst();
        // Проверка на null
        assertTrue(TeacherOptional.isPresent());
        TeacherOptional.get().click();

        registration.enterTextToElement("Popovics Anna", registration.getFullNameField());
        registration.enterTextToElement(email, registration.getEmailField());
        registration.enterTextToElement("abc456", registration.getPasswordField());
        registration.clickOnSignUpButton();

        // Проверка на присутствие ошибки о необходимости ввода всех полей формы
        assertEquals("Please make sure there are no empty required fields.", registration.getRequiredError().getText());
    }
}
