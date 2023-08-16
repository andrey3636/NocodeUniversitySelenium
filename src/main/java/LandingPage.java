import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LandingPage extends BasePage {

    @Getter
    @FindBy(xpath = "//a[contains(translate(text(), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'sign up')]")
//    @FindBy(partialLinkText = "Sign up")
    private WebElement SignUpButton;

    @Getter
    @FindBy(xpath = "//a[contains(translate(text(), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'sign in')]")
//    @FindBy(partialLinkText = "Sign in")
    private WebElement SignInButton;

    public LandingPage(WebDriver driver) {
        super(driver);
//        driver.get(BASE_URL);
    }

    public void clickOnSignUpButton(){
        clickOnTheElement(SignUpButton);
    }

    public void clickOnSignInButton(){
        clickOnTheElement(SignInButton);
    }
}
