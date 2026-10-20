import org.openqa.selenium.WebDriver; // the browser remote-control interface
import org.openqa.selenium.chrome.ChromeDriver; // the Chrome implementation
import org.openqa.selenium.WebElement; // the handle to one element on the page
import org.openqa.selenium.By; // the locator toolkit (By.cssSelector, By.id, ...)

public class ReadBookTest {
    public static void main(String[] args) {
        WebDriver driver = new ChromeDriver(); // opens a real Chrome window
        driver.get("https://books.toscrape.com/"); // navigates to the page
        String title = driver.getTitle(); // reads the page's title
        System.out.println("Page title: " + title);
        // find the first product title in the results grid
        WebElement firstTitle = driver.findElement(By.cssSelector("article.product_pod h3 a"));
        System.out.println("First book: " + firstTitle.getText());
        System.out.println("First book: " + firstTitle.getAttribute("title"));
        WebElement Price = driver.findElement(By.cssSelector("article.product_pod div p.price_color"));
        System.out.println("Price: " + Price.getText());
        driver.quit(); // closes the browser
    }
}