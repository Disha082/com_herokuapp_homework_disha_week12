package testsuite;

import browserfactory.BaseTest;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginTest extends BaseTest {
    String baseUrl = "http://the-internet.herokuapp.com/login";

    @Before
    public void setup(){
        openBrowser(baseUrl);
    }

    @Test
    public void UserSholdLoginSuccessfullyWithValidCredentials(){

        //Find Username field and Enter tomsmith
        driver.findElement(By.name("username")).sendKeys("tomsmith");

        //Find Password field and enter SuperSecretPassword!
        driver.findElement(By.name("password")).sendKeys("SuperSecretPassword!");

        //Click on ‘LOGIN’ button
        driver.findElement(By.xpath("//i[@class='fa fa-2x fa-sign-in']")).click();

        //Verify Error message "Secure Area"

        String expectedMessage = "Secure Area";
        WebElement actualTextMessage = driver.findElement(By.xpath("//h2[text()=' Secure Area']"));
        //Stored webelement into String
        String actualMessage = actualTextMessage.getText();
        //Compared actual and expected result
        Assert.assertEquals("Not logged in successfully",expectedMessage,actualMessage);
    }

    @Test
    public void verifyTheUsernameErrorMessage(){
        //Enter invalid username in username field
        driver.findElement(By.name("username")).sendKeys("tomsmith1");

        //Enter valid password in password field
        driver.findElement(By.name("password")).sendKeys("SuperSecretPassword!");

        //Click on "LOGIN" button
       driver.findElement(By.xpath("//i[@class='fa fa-2x fa-sign-in']")).click();

       //Verify dashboard text after login
        String expectedMessage = "Your username is invalid!\n" + "×";

        //Find Log out text element and get the text
        WebElement actualTextMessageElement = driver.findElement(By.xpath("//div[@class='flash error']"));

        String actualMessage = actualTextMessageElement.getText();

        //Validate actual and expected
        Assert.assertEquals("Error message not showing",expectedMessage,actualMessage);

    }

    @Test
    public void verifyThePasswordErrorMessage(){
        //Enter valid username in username field
        driver.findElement(By.name("username")).sendKeys("tomsmith");

        //Enter invalid password in password field
        driver.findElement(By.name("password")).sendKeys("SuperSecretPassword");

        //Click on 'Login' Button
        driver.findElement(By.xpath("//i[@class='fa fa-2x fa-sign-in']")).click();

        //Varify dashboard text after login
        String expectedMessage = "Your password is invalid!\n" + "×";
        //Find Log out text element and get the text
        WebElement actualTextMessageElement = driver.findElement(By.xpath("//div[@class='flash error']"));
        String actualMessage = actualTextMessageElement.getText();

        //Validate actual and expected
        Assert.assertEquals("Error message not displayed", expectedMessage, actualMessage);

    }
    @After
    public void teardown(){
        closeBrowser();
    }

}
