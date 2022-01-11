package put.selenium.linear;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import put.selenium.utils.ScreenshotAndQuitOnFailureRule;

import static org.junit.Assert.*;


public class AccountsLinearScriptAT {

    private WebDriver driver;

    @Rule
    public ScreenshotAndQuitOnFailureRule screenshotOnFailureAndWebDriverQuitRule =
        new ScreenshotAndQuitOnFailureRule();


    @Before
    public void setUp() throws Exception {
        System.setProperty("webdriver.chrome.driver", "chromedriver-97.exe");
        this.driver = new ChromeDriver();
        screenshotOnFailureAndWebDriverQuitRule.setWebDriver(driver);
        driver.get("http://localhost:8080/accounts/controller?action=db_reset");
    }

    @Test
    public void successfulUserRegistration() throws Exception {
        driver.get("http://localhost:8080/accounts/controller?action=db_reset");
        driver.manage().window().setSize(new Dimension(974, 1080));
        driver.findElement(By.linkText("Register")).click();
        driver.findElement(By.name("username")).click();
        driver.findElement(By.name("username")).sendKeys("Aabb bbaa");
        driver.findElement(By.name("password")).sendKeys("12345678");
        driver.findElement(By.name("repeat_password")).sendKeys("12345678");
        driver.findElement(By.name("name")).sendKeys("aa bb");
        driver.findElement(By.name("addressData")).sendKeys("aa bb");
        driver.findElement(By.name("submit")).click();
        driver.findElement(By.name("username")).click();
        driver.findElement(By.name("username")).sendKeys("Aabb bbaa");
        driver.findElement(By.name("password")).sendKeys("12345678");
        driver.findElement(By.name("password")).sendKeys(Keys.ENTER);
        driver.findElement(By.cssSelector("html")).click();
    }
}
