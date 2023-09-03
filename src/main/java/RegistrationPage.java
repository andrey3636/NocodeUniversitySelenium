import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.Random;

// Класс тестирования страницы авторизации
public class RegistrationPage extends BasePage {
    @Getter
    // Аннотация FindBy, которая ищет елемент Веб-страницы по заданому xpath
    // (А именно - по тексту внутри елемента-ссылки независимо от регистра)
    @FindBy(xpath = "//a[contains(translate(text(), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'terms')]")
    // Кнопка-ссылка "terms"
    private WebElement TermsButton;

    @Getter
    // Аннотация FindBy, которая ищет елемент Веб-страницы по заданому xpath
    // (А именно - по тексту внутри елемента-ссылки независимо от регистра)
    @FindBy(xpath = "//a[contains(translate(text(), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'privacy policy')]")
    // Кнопка-ссылка "privacy policy"
    private WebElement PolicyButton;

    @Getter
    // Аннотация FindBy, которая ищет елемент Веб-страницы по заданому имени name
    // (А именно - "full_name")
    @FindBy(name = "full_name")
    // Поле ввода полного имени (Фамилия Имя)
    private WebElement FullNameField;

    @Getter
    // Аннотация FindBy, которая ищет елемент Веб-страницы по заданому имени name
    // (А именно - "static_email")
    @FindBy(name = "static_email")
    // Поле ввода электронного адреса
    private WebElement EmailField;

    @Getter
    // Аннотация FindBy, которая ищет елемент Веб-страницы по заданому имени name
    // (А именно - "password")
    @FindBy(name = "password")
    // Поле ввода пароля
    private WebElement PasswordField;

    @Getter
    // Аннотация FindBy, которая ищет елемент Веб-страницы по значению атрибута class
    // (А именно - "show-password")
    @FindBy(className = "show-password")
    // Иконка-глаз, для отображения пароля
    private WebElement PasswordEye;

    @Getter
    // Аннотация FindBy, которая ищет елемент Веб-страницы по заданому CSS селектору
    @FindBy(css = ".checkmark.position-relative.sw-checkbox")
    // Поле флажка - для согласия на условия
    private WebElement CheckBoxAgree;

    @Getter
    // Аннотация FindBy, которая ищет елемент Веб-страницы по заданому xpath
    // (А именно - по тексту внутри елемента-кнопки)
    @FindBy(xpath = "//button[@title='Select your role']")
    // Раскрывающаяся кнопка "Role"
    private WebElement RoleDropdown;
    
    // Аннотация FindBy, которая ищет елементы Веб-страницы по заданому CSS селектору, и оформляет в виде списка
    // (В данном случае - ссылка с атрибутом role="option")
    @FindBy(css = "a[role=\"option\"]")
    // Список вариантов раскрывающейся кнопки "Role"
    private List<WebElement> DropdownOptions;

    @Getter
    // Аннотация FindBy, которая ищет елемент Веб-страницы по значению атрибута class
    // (А именно - "other-error")
    @FindBy(className = "other-errors")
    // Элемент вывода общего рода ошибок
    private WebElement Error;

    @Getter
    // Аннотация FindBy, которая ищет елемент Веб-страницы по значению атрибута class
    // (А именно - "required-errors")
    @FindBy(className = "required-errors")
    // Элемент вывода ошибок, связанных с заполнением нужных полей
    private WebElement RequiredError;

    @Getter
    // Аннотация FindBy, которая ищет елемент Веб-страницы по значению атрибута class
    // (А именно - "signup-error")
    @FindBy(className = "signup-error")
    // Элемент вывода ошибок, связанных с регистрацией
    private WebElement SignUpError;

    @Getter
    // Аннотация FindBy, которая ищет елемент Веб-страницы по значению атрибута class
    // (А именно - "signup-error")
    @FindBy(className = "validation-message")
    // Элемент вывода ошибок, связанных с проверкой полей
    private WebElement ValidationError;

    @Getter
    // Аннотация FindBy, которая ищет елемент Веб-страницы по заданому xpath
    // (А именно - по тексту внутри елемента-ссылки независимо от регистра)
    @FindBy(xpath = "//a[contains(translate(text(), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'sign in')]")
    // Кнопка-ссылка "sign in"
    private WebElement SignInButton;

    @Getter
    // Аннотация FindBy, которая ищет елемент Веб-страницы по заданому xpath
    // (А именно - по тексту внутри елемента-ссылки независимо от регистра)
    @FindBy(xpath = "//a[contains(translate(text(), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'sign up')]")
    // Кнопка-ссылка "sign up"
    private WebElement SignUpButton;

    
    // Метод создания обьекта класса 
    public RegistrationPage(WebDriver driver) {
        super(driver);
    }

    // Метод нажимания на кнопку "terms"
    public void clickOnTermsButton() {
        clickOnTheElement(TermsButton);
    }
    
    // Метод нажимания на кнопку "privacy policy"
    public void clickOnPolicyButton() {
        clickOnTheElement(PolicyButton);
    }

    // Метод нажимания на иконку-глаз
    public void clickOnPasswordEye() {
        clickOnTheElement(PasswordEye);
    }

    // Метод нажимания на раскрывающуюся кнопку "Role"
    public void clickOnRoleDropdown() {
        clickOnTheElement(RoleDropdown);
    }

    // Метод нажимания на поле флажка - для согласия на/отказа от условий
    public void clickCheckBoxAgree() {
        clickOnTheElement(CheckBoxAgree);
    }

    // Метод нажимания на кнопку "sign up"
    public void clickOnSignUpButton(){
        clickOnTheElement(SignUpButton);
    }

    // Метод для получения списка элементов раскрывающеся кнопки "Role"
    public List<WebElement> getDropdownOptions() {
        // Создание обьекта WebDriverWait, для ожидания ответа до 5 секунд
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        // Ожидание загрузки елементов, которые подходят селектору CSS
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("a[role=\"option\"]")));
        // Поиск элементов, которые подходят селектору CSS (ссылки с атрибутом role="option")
        DropdownOptions = driver.findElements(By.cssSelector("a[role=\"option\"]"));
        return DropdownOptions;
    }
    
    // Метод получения значения атрибута "type" в поле ввода пароля
    public String getPasswordType() {
        return PasswordField.getAttribute("type");
    }
}
