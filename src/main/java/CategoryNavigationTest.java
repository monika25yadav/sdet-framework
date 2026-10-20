import org.openqa.selenium.WebDriver; // the browser remote-control interface
import org.openqa.selenium.chrome.ChromeDriver; // the Chrome implementation
import org.openqa.selenium.WebElement; // the handle to one element on the page
import org.openqa.selenium.By; // the locator toolkit (By.cssSelector, By.id, ...)

public class CategoryNavigationTest {
    public static void main(String[] args) {
        WebDriver driver = new ChromeDriver(); // opens a real Chrome window
        driver.get("https://books.toscrape.com/"); // navigates to the page
        // System.out.println("Price: " + Price.getText());
        // Option A: attribute selector (pure CSS)
        WebElement navigateUsingCssSelector = driver.findElement(By.cssSelector("a[href$='fiction_10/index.html']"));
        // Option B: link text — Selenium shortcut for anchors, matches visible text
        // exactly
        WebElement navigateUsingLinkText = driver.findElement(By.linkText("Fiction"));
        navigateUsingCssSelector.click(); // clicks the link to navigate to the Fiction category
        String title = driver.getTitle(); // reads the page's title
        System.out.println("Page title: " + title);
        if (title.contains("Fiction")) {
            System.out.println("Navigation to Fiction category --> PASS");
        } else {
            System.out.println("Navigation to Fiction category --> FAIL");
        }
        // WebElement resultsCount = driver.findElement(By.id("promotions"));
        WebElement resultsCount = driver.findElement(By.cssSelector("form.form-horizontal strong:first-of-type"));
        System.out.println("Results count: " + resultsCount.getText());

        driver.quit(); // closes the browser
    }
}
