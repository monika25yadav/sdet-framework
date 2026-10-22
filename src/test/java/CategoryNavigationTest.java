import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.OutputType;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.AfterMethod;
import pages.HomePage;
import org.testng.ITestResult;
import java.nio.file.Files;
import java.io.File;

public class CategoryNavigationTest {
    private WebDriver driver;

    @BeforeMethod
    public void setUp() {
    ChromeOptions options = new ChromeOptions();
    if (System.getenv("CI") != null) {        // only on the CI server
        options.addArguments("--headless=new");
    }
    driver = new ChromeDriver(options);
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

    @DataProvider(name = "categories")
    public Object[][] categoryData() {
    return new Object[][] {
        { "Fiction", "65" },   // each row: category name, expected count
        { "Poetry", "19" },
        { "Travel", "11" }
    };
    }

    @Test(dataProvider = "categories")
    public void categoryLoadsAndCountMatches(String category, String expectedCount) {
    HomePage home = new HomePage(driver);
    home.open();
    home.clickCategory(category);
    Assert.assertTrue(driver.getTitle().contains(category),
            "FAIL: expected " + category + " page, got " + driver.getTitle());
    Assert.assertEquals(home.getCategoryResultCount(), expectedCount,
            "FAIL: wrong result count on " + category + " page");
    }
}