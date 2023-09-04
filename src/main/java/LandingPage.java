import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class LandingPage extends BasePage {
    @Getter
    @FindBy(xpath = "//a[contains(translate(text(), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'sign up')]")
    private WebElement SignUpButton;

    @Getter
    @FindBy(xpath = "//a[contains(translate(text(), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'sign in')]")
    private WebElement SignInButton;

    public LandingPage(WebDriver driver) {
        super(driver);
    }

    public void clickOnSignUpButton(){
        clickOnTheElement(SignUpButton);
    }

    public void clickOnSignInButton(){
        clickOnTheElement(SignInButton);
    }
}
