import io.qameta.allure.Description;
import org.junit.jupiter.api.*;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;


// Класс-тестировщик целевой страницы
public class LandingTest extends BaseTest {
    // Тест перемещения на страницу регистрации
    @Test
    @DisplayName("Move to Sign Up page")
    @Description("Redirect to 'Sign Up' page via button link")
    public void MoveToSignUp(){
        LandingPage page = new LandingPage(driver);
        String LandingURL = page.getURL();
        page.clickOnSignUpButton();
        // Проверка URL адреса
        assertEquals(LandingURL + "sign-up", page.getURL());
    }

    // Тест перемещения на страницу авторизации
    @Test
    @DisplayName("Move to Sign In page")
    @Description("Redirect to 'Sign In' page via button link")
    public void MoveToSignIn(){
        LandingPage page = new LandingPage(driver);
        String LandingURL = page.getURL();
        page.clickOnSignInButton();
        // Проверка URL адреса
        assertEquals(LandingURL + "sign-in", page.getURL());
    }
}
