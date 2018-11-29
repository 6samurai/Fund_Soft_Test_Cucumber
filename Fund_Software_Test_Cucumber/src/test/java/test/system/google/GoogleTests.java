package test.system.google;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.Assert.*;

public class GoogleTests {

    WebDriver driver;

    @Before
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "C:/Users/Owner/Desktop/GitHub Projects/Fund_Software_Test_Cucumber/Fund_Soft_Test_Cucumber/chromedriver.exe");
        driver = new ChromeDriver();
    }

    @After
    public void teardown() {
        driver.quit();
    }

  /*  @Test
    public void testSimpleSearch() {
        browser.get("http://www.google.com");
        browser.findElement(By.name("q")).sendKeys("Malta");
        browser.findElement(By.name("btnK")).click();
        assertEquals("Malta - Google Search", browser.getTitle());
    }*/



}
