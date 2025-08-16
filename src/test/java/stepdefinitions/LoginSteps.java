package stepdefinitions;

import driver.DriverFactory;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import pages.LoginPage;

public class LoginSteps {
    private LoginPage loginPage;

    public LoginSteps() {
        this.loginPage = new LoginPage(DriverFactory.getDriver());
    }
    @Given("I launch the application")
    public void i_launch_the_application() {
        // Already launched in Hooks
    }

    @When("login with {string} and {string}")
    public void loginWithAnd(String un, String pwd) throws InterruptedException {
        loginPage.enterUsername(un);
        loginPage.enterPassword(pwd);
        loginPage.clickLogin();
        loginPage.verifyLogin();

    }

    @Then("I should be on the dashboard")
    public void i_should_be_on_the_dashboard() {
    }


}
