package Homework3;

import BrowserUtils.BrowserUtils;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

public class homework3 {

    @Test
    public void Task2AndTask3() throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        WebDriver driver=new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        driver.navigate().to("http://uitestpractice.com/Students/Index");

        WebElement clickNewButton= driver.findElement(By.linkText("Create New"));
        clickNewButton.click();
        Thread.sleep(1000);

        driver.switchTo().frame("aswift_2");
        driver.switchTo().frame("ad_iframe");
        WebElement closeAdd= driver.findElement(By.xpath("//div[@id='dismiss-button']"));
        closeAdd.click();
        driver.switchTo().parentFrame();

        Thread.sleep(1000);
        WebElement firstName= driver.findElement(By.cssSelector("#FirstName"));
        firstName.sendKeys("Alice");

        WebElement lastName= driver.findElement(By.cssSelector("#LastName"));
        lastName.sendKeys("Belkina");

        WebElement date= driver.findElement(By.cssSelector("#EnrollmentDate"));
        date.sendKeys("03/02/2023");

        WebElement submit= driver.findElement(By.xpath("//input[@type='submit']"));
        submit.click();

        WebElement search= driver.findElement(By.cssSelector("#Search_Data"));
        search.sendKeys("Alice");
        search.sendKeys(Keys.ENTER);

        Thread.sleep(2000);
        List<WebElement> validation=driver.findElements(By.xpath("//tr[2]//td"));
        List<String> expected= Arrays.asList("Alice","Belkina","3/2/2023 12:00:00 AM");
        for (int i=0;i<validation.size()-1;i++) {
            Assert.assertEquals(BrowserUtils.getText(validation.get(i)),expected.get(i));

        }

        driver.navigate().to("http://uitestpractice.com/Students/Index");

        search= driver.findElement(By.cssSelector("#Search_Data"));
        search.sendKeys("Belkina");
        search.sendKeys(Keys.ENTER);

        WebElement edit= driver.findElement(By.xpath("//tr[2]//td//button[.='EDIT']"));
        edit.click();

        WebElement editNAme= driver.findElement(By.cssSelector("#FirstName"));
        editNAme.clear();
        Thread.sleep(500);
        editNAme.sendKeys("Alexa");

        WebElement saveButton= driver.findElement(By.xpath("//input[@type='submit']"));
        saveButton.click();
        Thread.sleep(1000);

        search= driver.findElement(By.cssSelector("#Search_Data"));
        search.sendKeys("Alexa");
        search.sendKeys(Keys.ENTER);

        WebElement nameValidation=driver.findElement(By.xpath("//tr[2]//td[1]"));
        Assert.assertEquals(BrowserUtils.getText(nameValidation),"Alexa");

        driver.navigate().to("http://uitestpractice.com/Students/Index");

        search= driver.findElement(By.cssSelector("#Search_Data"));
        search.sendKeys("Belkina");
        search.sendKeys(Keys.ENTER);
        WebElement deleteButton= driver.findElement(By.xpath("//tr[2]//td//button[.='DELETE']"));
        deleteButton.click();
        Thread.sleep(1000);
        WebElement deleteConfirm= driver.findElement(By.xpath("//input[@value='Delete']"));
        deleteConfirm.click();

        search= driver.findElement(By.cssSelector("#Search_Data"));
        search.sendKeys("Belkina");
        search.sendKeys(Keys.ENTER);

        WebElement validation2= driver.findElement(By.xpath("//div[@class='container body-content']"));
        Assert.assertTrue(BrowserUtils.getText(validation2).contains("There are zero students with this search text Page 0 of 0"));

    }
    @Test
    public void Test4() throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        WebDriver driver=new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        driver.navigate().to("http://uitestpractice.com/");

        WebElement draggable= driver.findElement(By.cssSelector("#draggable"));
        WebElement droppable=driver.findElement(By.cssSelector("#droppable"));
        Actions actions=new Actions(driver);
        Thread.sleep(1000);
        actions.dragAndDrop(draggable,droppable).perform();
        droppable=driver.findElement(By.cssSelector("#droppable"));
        Assert.assertEquals(BrowserUtils.getText(droppable),"Dropped!");


    }
    @Test
    public void Task5() throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        WebDriver driver=new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        driver.navigate().to("http://uitestpractice.com/");
        Actions actions=new Actions(driver);

        WebElement doubleclick= driver.findElement(By.xpath("//button[@name='dblClick']"));
        actions.doubleClick(doubleclick).perform();

        Assert.assertEquals(driver.switchTo().alert().getText().trim(),"Double Clicked !!");
        Thread.sleep(2000);
        driver.switchTo().alert().accept();

    }
    @Test
    public void Task6() throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        WebDriver driver=new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        driver.navigate().to("http://uitestpractice.com/");
        Actions actions=new Actions(driver);

        driver.switchTo().frame("iframe_a");
        WebElement name= driver.findElement(By.cssSelector("#name"));
        name.sendKeys("Alexa");
        driver.switchTo().parentFrame();

        WebElement clickLinkInsideFrame=driver.findElement(By.linkText("uitestpractice.com"));
        clickLinkInsideFrame.click();
        driver.switchTo().frame("iframe_a");
        Thread.sleep(1000);
        WebElement hiddenText= driver.findElement(By.xpath("//div[@id='sub-frame-error']"));
        actions.moveToElement(hiddenText).perform();
        Thread.sleep(2000);

        WebElement validation= driver.findElement(By.xpath("//div[@id='sub-frame-error-details']"));
        Assert.assertEquals(BrowserUtils.getText(validation),"www.uitestpractice.com refused to connect.");


    }

    @Test
    public void Task7() throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        WebDriver driver=new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        driver.navigate().to("http://uitestpractice.com/");
        Actions actions=new Actions(driver);

        WebElement openWindowClick= driver.findElement(By.linkText("Click here to watch videos on C#"));
        Thread.sleep(1000);
        actions.moveToElement(openWindowClick);
        Thread.sleep(2000);
        openWindowClick.click();

        BrowserUtils.switchByTitle(driver,"C# Beginner to advanced - Lesson 29");
        Assert.assertTrue(driver.getTitle().contains("C# Beginner to advanced"));
        Assert.assertTrue(driver.getCurrentUrl().contains("youtube"));

    }

}
