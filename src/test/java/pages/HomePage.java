package pages; // a folder that groups page classes

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage {
    private WebDriver driver;

    // All locators live here — one place to fix when the UI changes
    private final By fictionLink = By.linkText("Fiction");

    public HomePage(WebDriver driver) {
        this.driver = driver; // the page receives the browser from the test
    }

    public void open() {
        driver.get("https://books.toscrape.com/");
    }

    public void clickFictionCategory() {
        driver.findElement(fictionLink).click();
    }

    public String getCategoryResultCount() {
        return driver.findElement(By.cssSelector("form.form-horizontal strong:first-of-type")).getText();
    }
}