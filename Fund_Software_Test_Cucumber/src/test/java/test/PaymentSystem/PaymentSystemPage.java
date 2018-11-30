package test.PaymentSystem;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import test.PaymentSystem.enums.PaymentSystemInputs;

public class PaymentSystemPage {
    WebDriver driver;

    public PaymentSystemPage(WebDriver driver){
        this.driver = driver;
    }

    public  void visit(){
        driver.get("http://localhost:8080/Fund_Software_Test_Cucumber_Website_war_exploded/");
    }

 /*   public void inputName(String value){

        WebElement item = driver.findElement(By.name("name"));
        item.sendKeys(value);
    }

    public void inputAddress(String value){

        WebElement item = driver.findElement(By.name("address"));
        item.sendKeys(value);
    }

    public void inputCardNumber(String value){

        WebElement item = driver.findElement(By.name("cardNumber"));
        item.sendKeys(value);
    }

    public void inputExpiryDate(String value){

        WebElement item = driver.findElement(By.name("expiryDate"));
        item.sendKeys(value);
    }

    public void inputCVV(String value){

        WebElement item = driver.findElement(By.name("cvvCode"));
        item.sendKeys(value);
    }

    public void inputAmount(String value){

        WebElement item = driver.findElement(By.name("amount"));
        item.sendKeys(value);
    }

    public void selectCardType(String value){
        WebElement mySelectElement = driver.findElement(By.id("cardType"));
        Select dropdown= new Select(mySelectElement);
        dropdown.selectByVisibleText(value);
    }
*/
    public void submitClick(){

        WebElement item = driver.findElement(By.id("submit"));
        item.click();
    }


    public void resetClick(){

        WebElement item = driver.findElement(By.id("reset"));
        item.click();
    }

    public void inputValue(PaymentSystemInputs input, String value){

        if(input.equals(PaymentSystemInputs.CARD_TYPE)){
            WebElement mySelectElement = driver.findElement(By.id(input.toString().toLowerCase()));
            Select dropdown= new Select(mySelectElement);
            dropdown.selectByVisibleText(value);
        } else{
            WebElement item = driver.findElement(By.id(input.toString().toLowerCase()));
            item.sendKeys(value);
        }
    }

    public String getValue(PaymentSystemInputs input, boolean validationMessage){

        if(input.equals(PaymentSystemInputs.CARD_TYPE)){
            WebElement mySelectElement = driver.findElement(By.id(input.toString().toLowerCase()));
            Select dropdown= new Select(mySelectElement);
            return dropdown.getFirstSelectedOption().getText();
        }

        WebElement item = driver.findElement(By.id(input.toString().toLowerCase()));

        if (input.equals(PaymentSystemInputs.CARD_NUMBER) && !validationMessage){
         return item.getAttribute("value").replace(" ", "");

        }

        if(!validationMessage)
            return item.getAttribute("value");
        else
            return item.getAttribute("validationMessage");
    }

/*
    public String getName(boolean getValue){

        WebElement item = driver.findElement(By.name("name"));
        if(getValue)
            return item.getAttribute("value");
        else
            return item.getAttribute("validationMessage");


    }

    public String getAddress(){

        WebElement item = driver.findElement(By.name("address"));
        String errorMessage = item.getAttribute("validationMessage");
        if(errorMessage.contains("Please fill in this field")){
            return errorMessage;
        }
        return item.getAttribute("value");
    }

    public String getCardNumber(){

        WebElement item = driver.findElement(By.name("cardNumber"));
        String errorMessage = item.getAttribute("validationMessage");
        if(errorMessage.contains("Please fill in this field")){
            return errorMessage;
        }
        return item.getAttribute("value");
    }

    public String getExpiryDate(){

        WebElement item = driver.findElement(By.name("expiryDate"));
        String errorMessage = item.getAttribute("validationMessage");
        if(errorMessage.contains("Please fill in this field")){
            return errorMessage;
        }
        return item.getAttribute("value");
    }

    public String getCVV(){

        WebElement item = driver.findElement(By.name("cvvCode"));
        String errorMessage = item.getAttribute("validationMessage");
        if(errorMessage.contains("Please fill in this field")){
            return errorMessage;
        }
        return item.getAttribute("value");
    }

    public String  getAmount(){

        WebElement item = driver.findElement(By.name("amount"));
        String errorMessage = item.getAttribute("validationMessage");
        if(errorMessage.contains("Please fill in this field")){
            return errorMessage;
        }
        return item.getAttribute("value");
    }


    public String getCardType(){
        WebElement mySelectElement = driver.findElement(By.id("cardType"));
        Select dropdown= new Select(mySelectElement);
        return dropdown.getFirstSelectedOption().getText();
    }
*/

}
