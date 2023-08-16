import io.qameta.allure.Description;
import org.junit.jupiter.api.*;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class LandingTest extends BaseTest {
    @Test
    @DisplayName("Move to Sign Up page")
    @Description("Redirect to 'Sign Up' page via button link")
    public void MoveToSignUp(){
        LandingPage page = new LandingPage(driver);
        String LandingURL = page.getURL();
        page.clickOnSignUpButton();
        assertEquals(LandingURL + "sign-up", page.getURL());
    }

    @Test
    @DisplayName("Move to Sign In page")
    @Description("Redirect to 'Sign In' page via button link")
    public void MoveToSignIn(){
        LandingPage page = new LandingPage(driver);
        String LandingURL = page.getURL();
        page.clickOnSignInButton();
        assertEquals(LandingURL + "sign-in", page.getURL());
    }
}
