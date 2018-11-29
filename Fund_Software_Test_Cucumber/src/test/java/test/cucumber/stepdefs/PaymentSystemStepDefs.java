package test.cucumber.stepdefs;

import cucumber.api.PendingException;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import test.PaymentSystem.PaymentSystemPage;

import static org.junit.Assert.*;

public class PaymentSystemStepDefs {

    WebDriver driver;

    @Given("I am a user trying to process a payment")
    public void i_am_a_user_trying_to_process_a_payment() {
        driver.get("http://localhost:8080/Fund_Software_Test_Cucumber_Website_war_exploded/");
    }



    @When("^I fill in the form$")
    public void i_fill_in_form()throws Exception {
        PaymentSystemPage paymentPage = new PaymentSystemPage(driver);
        paymentPage.visit();
        paymentPage.inputName("Test user");
        paymentPage.inputAddress("111, test address, TST101");
        paymentPage.selectCardType("VISA");
        //TO CHECK IF THIS IS A VALID CARD NUMBER
        paymentPage.inputCardNumber("411111111111111");
        paymentPage.inputExpiryDate("05/2020");
        paymentPage.inputCVV("111");
        paymentPage.inputAmount("1000");
    }

    @When("^click on the clear button$")
    public void click_on_the_clear_button() throws Exception{
        PaymentSystemPage paymentPage = new PaymentSystemPage(driver);
        paymentPage.resetClick();

    }

  //  @Then("^the title should be \"([^\"]*)\"$")
  @Then("^the form data should be cleared$")
    public void the_form_data_should_be_cleared()  throws Exception {
      PaymentSystemPage paymentPage = new PaymentSystemPage(driver);
      assertEquals("",paymentPage.getName());
      assertEquals("",paymentPage.getAddress());
      assertEquals("",paymentPage.getCardNumber());
      assertEquals("",paymentPage.getExpiryDate());
      assertEquals("",paymentPage.getCVV());
      assertEquals("",paymentPage.getAmount());
      assertEquals("American Express", paymentPage.getCardType());
    }



    @Before
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "C:/Users/Owner/Desktop/GitHub Projects/Fund_Software_Test_Cucumber_Git/Fund_Soft_Test_Cucumber/Fund_Software_Test_Cucumber/chromedriver.exe");
        driver = new ChromeDriver();
    }

    @After
    public void teardown() {
        driver.quit();
    }

}
