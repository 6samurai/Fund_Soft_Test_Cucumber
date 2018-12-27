package test.PaymentSystem;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import test.PaymentSystem.enums.PaymentSystemInputs;

public class PaymentSystemPage {
    WebDriver driver;

    public PaymentSystemPage(WebDriver driver) {
        this.driver = driver;
    }

    //load web page
    public void visit() {
        driver.get("http://localhost:8080/Fund_Software_Test_Cucumber_Website_war_exploded/");
    }

    //click on submit button
    public void submitClick() {

        WebElement item = driver.findElement(By.id("submit"));
        item.click();
    }

    //click on reset button
    public void resetClick() {

        WebElement item = driver.findElement(By.id("reset"));
        item.click();
    }

    //input value to specified field
    public void inputValue(PaymentSystemInputs input, String value) {

        if (input.equals(PaymentSystemInputs.CARD_TYPE)) {
            WebElement mySelectElement = driver.findElement(By.id(input.toString().toLowerCase()));
            Select dropdown = new Select(mySelectElement);
            dropdown.selectByVisibleText(value);
        } else {
            WebElement item = driver.findElement(By.id(input.toString().toLowerCase()));
            item.sendKeys(value);
        }
    }

    //get value of specific field
    //validation message represents the warning message that is populated when some of the form criteria are not met
    public String getValue(PaymentSystemInputs input, boolean validationMessage) {

        //card type operations
        if (input.equals(PaymentSystemInputs.CARD_TYPE)) {
            WebElement mySelectElement = driver.findElement(By.id(input.toString().toLowerCase()));
            Select dropdown = new Select(mySelectElement);
            return dropdown.getFirstSelectedOption().getText();
        }

        // other form values

        WebElement item = driver.findElement(By.id(input.toString().toLowerCase()));

        //removes blank spaces that are introduced during input
        if (input.equals(PaymentSystemInputs.CARD_NUMBER) && !validationMessage) {
            return item.getAttribute("value").replace(" ", "");

        }

        if (!validationMessage)
            //value present in input
            return item.getAttribute("value");
        else
            //warning value present with input
            return item.getAttribute("validationMessage");
    }

    // capture value of alert message
    public String getPopUpMessage() {
        Alert alert = driver.switchTo().alert();

        String alertMessage = driver.switchTo().alert().getText();
        alert.accept();
        return alertMessage;
    }

}
