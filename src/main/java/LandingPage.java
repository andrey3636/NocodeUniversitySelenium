import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

// Класс тестирования целевой страницы
public class LandingPage extends BasePage {
    @Getter
    // Аннотация FindBy, которая ищет елемент Веб-страницы по заданому xpath
    // (А именно - по тексту внутри елемента-ссылки независимо от регистра)
    @FindBy(xpath = "//a[contains(translate(text(), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'sign up')]")
    // Кнопка-ссылка "sign up"
    private WebElement SignUpButton;

    @Getterr
    // Аннотация FindBy, которая ищет елемент Веб-страницы по заданому xpath
    // (А именно - по тексту внутри елемента-ссылки независимо от регистра)
    @FindBy(xpath = "//a[contains(translate(text(), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'sign in')]")
    // Кнопка-ссылка "sign in"
    private WebElement SignInButton;

    // Метод создания обьекта класса 
    public LandingPage(WebDriver driver) {
        // Передача аргументов конструктору унаследованного класса BasePage
        super(driver);
    }

    // Метод нажимания на кнопку "sign up"
    public void clickOnSignUpButton(){
        clickOnTheElement(SignUpButton);
    }

    // Метод нажимания на кнопку "sign in"
    public void clickOnSignInButton(){
        clickOnTheElement(SignInButton);
    }
}
