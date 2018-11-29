package test.PaymentSystem;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class PaymentSystemPage {
    WebDriver driver;

    public PaymentSystemPage(WebDriver driver){
        this.driver = driver;
    }

    public  void visit(){
        driver.get("http://localhost:8080/Fund_Software_Test_Cucumber_Website_war_exploded/");
    }

    public void inputName(String value){

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

    public void submitClick(){

        WebElement item = driver.findElement(By.id("submit"));
        item.click();
    }


    public void resetClick(){

        WebElement item = driver.findElement(By.id("reset"));
        item.click();
    }





    public String getName(){

        WebElement item = driver.findElement(By.name("name"));

        String errorMessage = item.getAttribute("required");
        if(errorMessage.contains("Please fill out this field")){
            return errorMessage;
        }

        return item.getAttribute("value");
    }

    public String getAddress(){

        WebElement item = driver.findElement(By.name("address"));
        return item.getAttribute("value");
    }

    public String getCardNumber(){

        WebElement item = driver.findElement(By.name("cardNumber"));
        return item.getAttribute("value");
    }

    public String getExpiryDate(){

        WebElement item = driver.findElement(By.name("expiryDate"));
        return item.getAttribute("value");
    }

    public String getCVV(){

        WebElement item = driver.findElement(By.name("cvvCode"));
        return item.getAttribute("value");
    }

    public String  getAmount(){

        WebElement item = driver.findElement(By.name("amount"));
        return item.getAttribute("value");
    }


    public String getCardType(){
        WebElement mySelectElement = driver.findElement(By.id("cardType"));
        Select dropdown= new Select(mySelectElement);
        return dropdown.getFirstSelectedOption().getText();
    }
}
