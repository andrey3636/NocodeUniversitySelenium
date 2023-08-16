import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {

    @Getter
    @FindBy(name = "email")
//    @FindBy(xpath = "a[contains(translate(text(), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'sign up')]")
    private WebElement EmailField;

    @Getter
    @FindBy(name = "password")
//    @FindBy(xpath = "a[contains(translate(text(), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'sign in')]")
    private WebElement PasswordField;

    @Getter
    @FindBy(className = "show-password")
    private WebElement PasswordEye;

    @Getter
    @FindBy(xpath = "//a[contains(translate(text(), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'forgot password')]")
    private WebElement ForgotPasswordLink;

    @Getter
    @FindBy(className = "login-error")
    private WebElement LoginErrorDiv;

    @Getter
    @FindBy(xpath = "//a[contains(translate(text(), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'sign in')]")
    private WebElement SignInButton;

    @Getter
    @FindBy(xpath = "//a[contains(translate(text(), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'sign up')]")
    private WebElement SignUpButton;

    public void clickOnPasswordEye() {
        clickOnTheElement(PasswordEye);
    }

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void clickOnSignUpButton(){
        clickOnTheElement(SignUpButton);
    }

    public void clickOnSignInButton(){
        clickOnTheElement(SignInButton);
    }

    public void clickOnForgotPasswordLink(){
        clickOnTheElement(ForgotPasswordLink);
    }

    public String getPasswordType() {
        return PasswordField.getAttribute("type");
    }
}
