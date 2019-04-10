package firstmaven;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Maven_Project {
    protected static WebDriver driver;

    //  public void Fourth(){
    static String getCurrentDateTime() {
        //creat object of simple date format class and decide format
        DateFormat dateformat = new SimpleDateFormat("MMddyyyyHHmmss");
        // get current date time with date()
        Date date = new Date();
        // new format for date
        String date1 = dateformat.format(date);
        // print the date
        System.out.println(" Current date and time is " + date1);
        return date1;
    }

    @Before
    public void Third() {
        System.setProperty("webdriver.chrome.driver", "src\\chromedriver.exe");
        driver = new ChromeDriver();
        //implicit wait applied to driver instance - which will be applied to driver until driver instance
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        //asking driver to get url
        driver.get("https://demo.nopcommerce.com/");
    }
        @Test
        public void Register(){
            //register for new user
            driver.findElement(By.xpath("//a[@class= \'ico-register\']")).click();
            // Enter gender detail
            driver.findElement(By.xpath("//input[@id='gender-female']")).click();
            //Enter first name
            driver.findElement(By.xpath("//*[@name='FirstName']")).sendKeys("Martin");
           // Enter last name
            driver.findElement(By.xpath("//*[@id=\"LastName\"]")).sendKeys("Patel");
           // Enter email id
            driver.findElement(By.xpath("//*[@id=\"Email\"]")).sendKeys("kd" + getCurrentDateTime() + "@yahoo.com");
            // Enter password
            driver.findElement(By.xpath("//input[@id='Password']")).sendKeys("pruthvi");
           // Enter confirm password
            driver.findElement(By.xpath("//*[@name='ConfirmPassword']")).sendKeys("pruthvi");
            // click on register button
            driver.findElement(By.xpath("//input[@id='register-button']")).click();
            String actualRegistrationSuccessMessage = driver.findElement(By.xpath("//div[@class='result']")).getText();
            Assert.assertEquals("Your registration completed", actualRegistrationSuccessMessage);

        }
    @Test
    public void Login() {


        // click on login
        driver.findElement(By.xpath("//a[@class='ico-login']")).click();

        // Enter email-id
        driver.findElement(By.xpath("//input[@id='Email']")).sendKeys("krunal@yahoo.com");

        // Enter Password
        driver.findElement(By.xpath("//input[@name='Password']")).sendKeys("pruthvi");
        //Click on login button
        driver.findElement(By.xpath("//input[@value='Log in']")).click();
        // Click on log out button
        String message = driver.findElement(By.xpath("//a[@class='ico-logout']")).getText();
        Assert.assertEquals("Log out", message);
        System.out.println("User logged in successfully ");
    }
    @Test
    public void Notebooks() {

        // User should be on homepage.
        driver.findElement(By.xpath("//a[contains(text(),'Computers')]")).click();
        // click on notebooks
        driver.findElement(By.partialLinkText("Notebooks")).click();

        String actualUserShouldBeNevigateToNotebooks = driver.findElement(By.xpath("//div[@class='page-title']")).getText();
        Assert.assertEquals("Notebooks", actualUserShouldBeNevigateToNotebooks);
        System.out.println(" User should be able to nevigate to notebooks category ");
    }
    @Test
    public void Electronics() {


        //Click on electronics category
        driver.findElement(By.linkText("Electronics")).click();

        //click on cell phone
        driver.findElement(By.partialLinkText("Cell phone")).click();

        String message = driver.findElement(By.xpath("//div[@class='page-title']")).getText();
        Assert.assertEquals("Cell phones", message);

        System.out.println(" User should be able to nevigate to electronics category");
    }
    @Test
    public void Shopping_Cart() {


        driver.findElement(By.linkText("Jewelry")).click();
        driver.findElement(By.linkText("Flower Girl Bracelet")).click();
        driver.findElement(By.xpath("//input[@value='Add to cart']")).click();
        String actualresult = driver.findElement(By.xpath("//p[@class='content']")).getText();
        Assert.assertEquals("The product has been added to your shopping cart", actualresult);
        System.out.println(" User should be able to add products ");
    }

     @After
       public void closeBrowser(){
            driver.close();

     }
}