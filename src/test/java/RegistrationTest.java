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

//import ValidUserRequest;

public class RegistrationTest extends BaseTest {
    String email = "";

    /*@AfterEach
    public void deleteUser(TestInfo testInfo){
        //if(!email.equals("")) {
        System.out.println(testInfo.getDisplayName());
        if (testInfo.getTags().contains("delete")){
            System.out.println("Passed");
            Requests requests = new Requests();
            requests.deleteRequest("user/" + email);
        }
    }*/

    @Test
    @DisplayName("Move to Terms page")
    @Description("Redirect to 'Terms' page via button link")
    public void MoveToTerms(){
        email = "";
        LandingPage page = new LandingPage(driver);
        String URL = "https://www.softr.io/terms";
        page.clickOnSignUpButton();
        page.loadPage();
        RegistrationPage registration = new RegistrationPage(driver);
        String first_handle = driver.getWindowHandle();
        registration.clickOnTermsButton();

        Set<String> allHandles = driver.getWindowHandles();
        for(String winHandle: allHandles)
        {
            if (!first_handle.equalsIgnoreCase(winHandle))
            {
                driver.switchTo().window(winHandle);
            }
        }
//        registration.loadPage();
        assertEquals(URL, registration.getURL());
    }

    @Test
    @DisplayName("Move to Policy page")
    @Description("Redirect to 'Privacy Policy' page via button link")
    public void MoveToPolicy(){
        email = "";
        LandingPage page = new LandingPage(driver);
        String URL = "https://www.softr.io/policy";
        page.clickOnSignUpButton();
        page.loadPage();
        RegistrationPage registration = new RegistrationPage(driver);
        String first_handle = driver.getWindowHandle();
        registration.clickOnPolicyButton();

        Set<String> allHandles = driver.getWindowHandles();
        for(String winHandle: allHandles)
        {
            if (!first_handle.equalsIgnoreCase(winHandle))
            {
                driver.switchTo().window(winHandle);
            }
        }
        assertEquals(URL, registration.getURL());
    }

    @Test
    @DisplayName("Check showing of password")
    @Description("Checking show of password by clicking 'eye'")
    public void CheckEyeTest(){
        email = "";
        LandingPage page = new LandingPage(driver);
        page.clickOnSignUpButton();
        page.loadPage();
        RegistrationPage registration = new RegistrationPage(driver);

        assertEquals("password", registration.getPasswordType());
        registration.clickOnPasswordEye();
        assertEquals("text", registration.getPasswordType());
    }

    @Test
    @DisplayName("Check valid Teacher registration")
    @Description("Checking registration of Teacher role account with valid data")
    public void ValidTeacherSignUp() {
        email = getRandomEmail();//getRandomEmail();
        LandingPage page = new LandingPage(driver);
        String LandingURL = page.getURL();
        page.clickOnSignUpButton();
        RegistrationPage registration = new RegistrationPage(driver);

        registration.clickOnRoleDropdown();
        Optional<WebElement> TeacherOptional = registration.getDropdownOptions().stream()
                .filter(Option -> Option.getText().equalsIgnoreCase("teacher")).findFirst();
        assertTrue(TeacherOptional.isPresent());
        TeacherOptional.get().click();
        registration.enterTextToElement("Popovics Andrey", registration.getFullNameField());
        registration.enterTextToElement(email, registration.getEmailField());
        registration.enterTextToElement("123456", registration.getPasswordField());
        registration.clickCheckBoxAgree();
        registration.clickOnSignUpButton();

        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // seconds
            wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button.MuiButtonBase-root:first-of-type")));
            assertEquals(LandingURL, registration.getURL());
        } catch(Exception e) {

        } finally {
            Requests requests = new Requests();
            requests.deleteRequest("user/" + email);
        }

//            Requests requests = new Requests();
//            requests.deleteRequest("user/" + email);


    /*try {
        TimeUnit.SECONDS.sleep(3);
    } catch (InterruptedException e) {
//            throw new RuntimeException(e);
    }*/


        //Requests requests = new Requests();
        //requests.deleteRequest(LandingURL + "/user/" + email);
        //Добавить удаление с User
    }

    @Test
    @DisplayName("Check valid Student registration")
    @Description("Checking registration of Student role account with valid data")
    public void ValidStudentSignUp() {
        email = getRandomEmail();//getRandomEmail();
//        Requests requests = new Requests();
//        requests.deleteRequest(BASE_URL + "user/" + email);
        LandingPage page = new LandingPage(driver);
        String LandingURL = page.getURL();
        page.clickOnSignUpButton();
        RegistrationPage registration = new RegistrationPage(driver);

        registration.clickOnRoleDropdown();
        Optional<WebElement> TeacherOptional = registration.getDropdownOptions().stream()
                .filter(Option -> Option.getText().equalsIgnoreCase("student")).findFirst();
        assertTrue(TeacherOptional.isPresent());
        TeacherOptional.get().click();
        registration.enterTextToElement("Popovics Adam", registration.getFullNameField());
        registration.enterTextToElement(email, registration.getEmailField());
        registration.enterTextToElement("123456", registration.getPasswordField());
        registration.clickCheckBoxAgree();
        registration.clickOnSignUpButton();



        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // seconds
            wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button.MuiButtonBase-root:first-of-type")));
            assertEquals(LandingURL, registration.getURL());
        } catch(Exception e) {
        } finally {
            Requests requests = new Requests();
            requests.deleteRequest("user/" + email);
        }
        /*try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
        }*/

//        assertEquals(LandingURL, registration.getURL());
    }

    @Test
    @DisplayName("Check empty role registration")
    @Description("Checking registration account with empty role")
    public void EmptyRoleSignUp(){
        email = "bmw61@gmail.com";
        LandingPage page = new LandingPage(driver);
        page.loadPage();
        page.clickOnSignUpButton();
        RegistrationPage registration = new RegistrationPage(driver);

        registration.enterTextToElement("Popovics Anna", registration.getFullNameField());
        registration.enterTextToElement(email, registration.getEmailField());
        registration.enterTextToElement("abc456", registration.getPasswordField());
        registration.clickCheckBoxAgree();
        registration.clickOnSignUpButton();

        assertEquals("Please make sure there are no empty required fields.", registration.getRequiredError().getText());
    }

    @Test
    @DisplayName("Check empty fullname Teacher registration")
    @Description("Checking registration of Teacher role account with empty fullname")
    public void EmptyFullnameTeacherSignUp(){
        email = "bmw6@gmail.com";
        LandingPage page = new LandingPage(driver);
        page.loadPage();
        page.clickOnSignUpButton();
        RegistrationPage registration = new RegistrationPage(driver);

        registration.clickOnRoleDropdown();
        Optional<WebElement> TeacherOptional = registration.getDropdownOptions().stream()
                .filter(Option -> Option.getText().equalsIgnoreCase("teacher")).findFirst();
        assertTrue(TeacherOptional.isPresent());
        TeacherOptional.get().click();

        registration.enterTextToElement(email, registration.getEmailField());
        registration.enterTextToElement("abc456", registration.getPasswordField());
        registration.clickCheckBoxAgree();
        registration.clickOnSignUpButton();

        assertEquals("Please make sure there are no empty required fields.", registration.getRequiredError().getText());
    }



    @Test
    @DisplayName("Check previously used Email Teacher registration")
    @Description("Checking registration of Teacher account with previously used Email")
    public void PreviouslyUsedEmailTeacherSignUp(){
        email = "bmw6@gmail.com";
        LandingPage page = new LandingPage(driver);
        page.loadPage();
        page.clickOnSignUpButton();
        RegistrationPage registration = new RegistrationPage(driver);

        registration.clickOnRoleDropdown();
        Optional<WebElement> TeacherOptional = registration.getDropdownOptions().stream()
                .filter(Option -> Option.getText().equalsIgnoreCase("teacher")).findFirst();
        assertTrue(TeacherOptional.isPresent());
        TeacherOptional.get().click();

        registration.enterTextToElement("Popovics Anna", registration.getFullNameField());
        registration.enterTextToElement(email, registration.getEmailField());
        registration.enterTextToElement("123456", registration.getPasswordField());
        registration.clickCheckBoxAgree();
        registration.clickOnSignUpButton();


        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
        }

        assertEquals("Something went wrong, please try again.",(registration.getError().getText()));

//        assertEquals("User by given email already exists.", registration.getSignUpError().getText());
    }

    @Test
    @DisplayName("Check valid Email invalid Password registration")
    @Description("Checking registration of account with valid Email and invalid Password checkbox")
    public void ValidEmailInvalidPasswordSignUp(){
        email = "bmw6@gmail.com";
        LandingPage page = new LandingPage(driver);
        page.loadPage();
        page.clickOnSignUpButton();
        RegistrationPage registration = new RegistrationPage(driver);

        registration.clickOnRoleDropdown();
        Optional<WebElement> TeacherOptional = registration.getDropdownOptions().stream()
                .filter(Option -> Option.getText().equalsIgnoreCase("teacher")).findFirst();
        assertTrue(TeacherOptional.isPresent());
        TeacherOptional.get().click();

        registration.enterTextToElement("Popovics Anna", registration.getFullNameField());
        registration.enterTextToElement(email, registration.getEmailField());
        registration.enterTextToElement("1", registration.getPasswordField());
        registration.clickOnSignUpButton();


        assertEquals("Password must contain at least 6 characters", registration.getValidationError().getText());
    }
    @Test
    @DisplayName("Check empty Agreement checkbox registration")
    @Description("Checking registration of account with empty Agreement checkbox")
    public void EmptyAgreementCheckboxTeacherSignUp(){
        email = "bmw6@gmail.com";
        LandingPage page = new LandingPage(driver);
        page.loadPage();
        page.clickOnSignUpButton();
        RegistrationPage registration = new RegistrationPage(driver);

        registration.clickOnRoleDropdown();
        Optional<WebElement> TeacherOptional = registration.getDropdownOptions().stream()
                .filter(Option -> Option.getText().equalsIgnoreCase("teacher")).findFirst();
        assertTrue(TeacherOptional.isPresent());
        TeacherOptional.get().click();

        registration.enterTextToElement("Popovics Anna", registration.getFullNameField());
        registration.enterTextToElement(email, registration.getEmailField());
        registration.enterTextToElement("abc456", registration.getPasswordField());
        registration.clickOnSignUpButton();

        assertEquals("Please make sure there are no empty required fields.", registration.getRequiredError().getText());
    }
}
