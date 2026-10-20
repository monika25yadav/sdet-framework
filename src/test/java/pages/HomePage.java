package pages; // a folder that groups page classes

import java.io.FileInputStream;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage {
    private WebDriver driver;

    // All locators live here — one place to fix when the UI changes

    public HomePage(WebDriver driver) {
        this.driver = driver; // the page receives the browser from the test
    }

    public void open() {
    try {
        Properties props = new Properties();
        props.load(new FileInputStream("config.properties"));
        driver.get(props.getProperty("baseUrl"));
    } catch (Exception e) {
        throw new RuntimeException(
            "Could not read config.properties, working dir = " + System.getProperty("user.dir"),
            e);
    }
    }

    public void clickCategory(String categoryName) {
        driver.findElement(By.linkText(categoryName)).click();
    }

    public String getCategoryResultCount() {
        return driver.findElement(By.cssSelector("form.form-horizontal strong:first-of-type")).getText();
    }
}