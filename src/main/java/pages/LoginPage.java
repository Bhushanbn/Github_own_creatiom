package pages;

import driver.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class LoginPage {
    private WebDriver driver ;
    private WebDriverWait wait;

    private By username = By.id("user-name");
    private By password = By.xpath("//input[@id='password']");
    private By loginBtn = By.id("login-button");
    private By header = By.xpath("//div[@class='app_logo']");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void enterUsername(String uname) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(username));
        driver.findElement(username).sendKeys(uname);
    }

    public void enterPassword(String pwd) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(password));
        driver.findElement(password).sendKeys(pwd);
    }

    public void clickLogin() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(loginBtn));
        driver.findElement(loginBtn).click();
    }

    public void verifyLogin() {
        try {
            WebElement headerElement = wait.until(ExpectedConditions.visibilityOfElementLocated(header));
            Assert.assertTrue(headerElement.isDisplayed(), "Login failed");
        } catch (TimeoutException e) {
            throw new AssertionError("Login verification failed — header not found. Possible locator issue.", e);
        }
    }


}
