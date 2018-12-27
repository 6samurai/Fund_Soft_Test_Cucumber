package test.cucumber.stepdefs;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import test.PaymentSystem.PaymentSystemPage;
import test.PaymentSystem.enums.PaymentSystemInputs;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class PaymentSystemStepDefs {

    WebDriver driver;
    PaymentSystemPage paymentPage;
    List<String> validDetails = new ArrayList<String>();


    @Given("^I am a user trying to process a payment$")
    public void iAmAUserTryingToProcessAPayment() {
        driver.get("http://localhost:8080/Fund_Software_Test_Cucumber_Website_war_exploded/");
    }


    @When("^I fill in the form$")
    public void iFillInTheForm() {
        validDetailsDiffCards("VISA");
        inputForm();
        paymentPage.visit();


    }


    @When("I submit correct details")
    public void iSubmitCorrectDetails() {
        //set up visa
        validDetailsDiffCards("VISA");
        inputForm();
        paymentPage.submitClick();
    }


    @And("^click on the clear button$")
    public void clickOnTheClearButton() {
        PaymentSystemPage paymentPage = new PaymentSystemPage(driver);
        paymentPage.resetClick();

    }

    //  @Then("^the title should be \"([^\"]*)\"$")
    @Then("^the form data should be cleared$")
    public void theFormDataShouldBeCleared() {
        assertEquals("", paymentPage.getValue(PaymentSystemInputs.NAME, false));
        assertEquals("", paymentPage.getValue(PaymentSystemInputs.ADDRESS, false));
        assertEquals("", paymentPage.getValue(PaymentSystemInputs.CARD_NUMBER, false));
        assertEquals("", paymentPage.getValue(PaymentSystemInputs.EXPIRY_DATE, false));
        assertEquals("", paymentPage.getValue(PaymentSystemInputs.CVV_CODE, false));
        assertEquals("", paymentPage.getValue(PaymentSystemInputs.AMOUNT, false));
        assertEquals("American Express", paymentPage.getValue(PaymentSystemInputs.CARD_TYPE, false));
    }


    @When("^I submit a form with all data except \"([^\"]*)\"$")
    public void iSubmitAFormWithAllDataExcept(String fieldname) {

        //set up visa card details
        validDetailsDiffCards("VISA");

        //maven's default compiler target bytecode version is 1.5 - this version does not support switch statements with strings.
        //Thus for compatibility reasons this is not modified and a sequence of if statements are used instead of a switch(string)

        if (fieldname.contains("Name"))
            validDetails.set(0, "");

        if (fieldname.contains("Address"))
            validDetails.set(1, "");

        if (fieldname.contains("CardNumber"))
            validDetails.set(3, "");

        if (fieldname.contains("ExpiryDate"))
            validDetails.set(4, "");

        if (fieldname.contains("CVVCode"))
            validDetails.set(5, "");

        if (fieldname.contains("Amount"))
            validDetails.set(6, "");

        inputForm();
        paymentPage.submitClick();
    }


    @Then("I should be told that \"([^\"]*)\" is required")
    public void iShouldBeToldThatIsRequired(String field) {
        //maven's default compiler target bytecode version is 1.5 - this version does not support switch statements with strings.
        //Thus for compatibility reasons this is not modified and a sequence of if statements are used instead of a switch(string)

        if (field.contains("Name"))
            assertEquals("Please enter name", paymentPage.getValue(PaymentSystemInputs.NAME, true));
        else
            assertEquals(validDetails.get(0), paymentPage.getValue(PaymentSystemInputs.NAME, false));

        if (field.contains("Address"))
            assertEquals("Please enter address", paymentPage.getValue(PaymentSystemInputs.ADDRESS, true));
        else
            assertEquals(validDetails.get(1), paymentPage.getValue(PaymentSystemInputs.ADDRESS, false));

        if (field.contains("CardNumber"))
            assertEquals("Please enter card number", paymentPage.getValue(PaymentSystemInputs.CARD_NUMBER, true));
        else
            assertEquals(validDetails.get(3), paymentPage.getValue(PaymentSystemInputs.CARD_NUMBER, false));

        if (field.contains("ExpiryDate"))
            assertEquals("Please enter expiry date", paymentPage.getValue(PaymentSystemInputs.EXPIRY_DATE, true));
        else
            assertEquals(validDetails.get(4), paymentPage.getValue(PaymentSystemInputs.EXPIRY_DATE, false));

        if (field.contains("CVVCode"))
            assertEquals("Please enter CVV code", paymentPage.getValue(PaymentSystemInputs.CVV_CODE, true));
        else
            assertEquals(validDetails.get(5), paymentPage.getValue(PaymentSystemInputs.CVV_CODE, false));

        if (field.contains("Amount"))
            assertEquals("Please enter amount", paymentPage.getValue(PaymentSystemInputs.AMOUNT, true));
        else
            assertEquals(validDetails.get(6), paymentPage.getValue(PaymentSystemInputs.AMOUNT, false));

    }

    @Then("I should be told that the payment was successful")
    public void iShouldBeToldThatThePaymentWasSuccessful() {
        assertEquals("The payment was successful", paymentPage.getPopUpMessage());
    }

    @When("I submit correct details using a \"([^\"]*)\" card")
    public void iSubmitCorrectDetailsUsingCard(String field) {

        validDetailsDiffCards(field);
        inputForm();
        paymentPage.submitClick();

    }

    @When("I submit a form with any invalid that which the processing system rejects")
    public void iSubmitAFormWithAnyInvalidThatWhichTheProcessingSystemRejects() {
        invalidForm();
        inputForm();

        paymentPage.submitClick();
    }

    @Then("I should be told that there was an error processing my transaction")
    public void iShouldBeToldThatThereWasAnErrorProcessingMyTransaction() {
        assertEquals("Invalid Card Number", paymentPage.getPopUpMessage());
    }

    @Before
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "C:/Users/Owner/Desktop/GitHub Projects/Fund_Software_Test_Cucumber_Git/Fund_Soft_Test_Cucumber/Fund_Software_Test_Cucumber/chromedriver.exe");
        driver = new ChromeDriver();
        paymentPage = new PaymentSystemPage(driver);

    }

    @After
    public void teardown() {
        driver.quit();
    }

    //set up valid card details for different cards
    private void validDetailsDiffCards(String card) {
        if (card.contains("American Express")) {
            validDetails.clear();
            validDetails.add("Test user American");
            validDetails.add("222, test address, TST101");
            validDetails.add("American Express");
            validDetails.add("378282246310005");
            validDetails.add("05/2020");
            validDetails.add("1111");
            validDetails.add("1000");

        } else if (card.contains("Mastercard")) {
            validDetails.clear();
            validDetails.add("Test user");
            validDetails.add("333, test address, TST101");
            validDetails.add("Mastercard");
            validDetails.add("5555555555554444");
            validDetails.add("05/2020");
            validDetails.add("111");
            validDetails.add("100");
        } else if (card.contains("VISA")) {

            validDetails.clear();
            validDetails.add("Test user");
            validDetails.add("111, test address, TST101");
            validDetails.add("VISA");
            validDetails.add("4111111111111111");
            validDetails.add("05/2020");
            validDetails.add("111");
            validDetails.add("1000");
        }
    }

    //set up an invalid form which the card number do
    private void invalidForm() {
        validDetails.clear();
        validDetails.add("Test user");
        validDetails.add("333, test address, TST101");
        validDetails.add("Mastercard");
        validDetails.add("6555555555554444");
        validDetails.add("05/2020");
        validDetails.add("111");
        validDetails.add("100");
    }

    //function to send values to web app
    private void inputForm() {
        paymentPage.inputValue(PaymentSystemInputs.NAME, validDetails.get(0));
        paymentPage.inputValue(PaymentSystemInputs.ADDRESS, validDetails.get(1));
        paymentPage.inputValue(PaymentSystemInputs.CARD_TYPE, validDetails.get(2));
        paymentPage.inputValue(PaymentSystemInputs.CARD_NUMBER, validDetails.get(3));
        paymentPage.inputValue(PaymentSystemInputs.EXPIRY_DATE, validDetails.get(4));
        paymentPage.inputValue(PaymentSystemInputs.CVV_CODE, validDetails.get(5));
        paymentPage.inputValue(PaymentSystemInputs.AMOUNT, validDetails.get(6));
    }

}
