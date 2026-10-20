import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import pages.HomePage;
import org.testng.ITestResult;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.OutputType;
import java.nio.file.Files;
import java.io.File;

public class CategoryNavigationTest {
    private WebDriver driver;

    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
    if (result.getStatus() == ITestResult.FAILURE) {
        try {
            Files.createDirectories(java.nio.file.Path.of("target/screenshots"));
            File shot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            Files.copy(shot.toPath(),
            java.nio.file.Path.of("target/screenshots/" + result.getName() + ".png"));
            System.out.println("Screenshot saved for failed test: " + result.getName());
        } catch (Exception e) {
            System.out.println("Screenshot failed: " + e.getMessage());
        }
    }
    driver.quit();
    }

    @Test
    public void fictionCategoryLoads() {
        HomePage home = new HomePage(driver);
        home.open();
        home.clickFictionCategory();
        Assert.assertTrue(driver.getTitle().contains("Fiction"),
                "FAIL: expected Fiction page, got " + driver.getTitle());
        Assert.assertEquals(home.getCategoryResultCount(), "65",
                "FAIL: wrong result count on Fiction page");
    }
}