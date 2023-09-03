import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

// Класс тестирования страницы авторизации
public class LoginPage extends BasePage {

    @Getter
    // Аннотация FindBy, которая ищет елемент Веб-страницы по заданому имени name
    // (А именно - "email")
    @FindBy(name = "email")
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
    // Аннотация FindBy, которая ищет елемент Веб-страницы по заданому xpath
    // (А именно - по тексту внутри елемента-ссылки независимо от регистра)
    @FindBy(xpath = "//a[contains(translate(text(), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'forgot password')]")
    // Ссылка "Forgot Password"
    private WebElement ForgotPasswordLink;

    @Getter
    // Аннотация FindBy, которая ищет елемент Веб-страницы по значению атрибута class
    // (А именно - "login-error")
    @FindBy(className = "login-error")
    // Элемент вывода ошибок
    private WebElement LoginErrorDiv;

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
    public LoginPage(WebDriver driver) {
        super(driver);
    }
    
    // Метод нажимания на иконку-глаз
    public void clickOnPasswordEye() {
        clickOnTheElement(PasswordEye);
    }

    // Метод нажимания на кнопку "sign up"
    public void clickOnSignUpButton(){
        clickOnTheElement(SignUpButton);
    }

    // Метод нажимания на кнопку "sign in"
    public void clickOnSignInButton(){
        clickOnTheElement(SignInButton);
    }

    // Метод нажимания на ссылку "Forgot password?"
    public void clickOnForgotPasswordLink(){
        clickOnTheElement(ForgotPasswordLink);
    }

    // Метод получения значения атрибута "type" в поле ввода пароля
    public String getPasswordType() {
        return PasswordField.getAttribute("type");
    }
}
