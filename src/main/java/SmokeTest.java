import org.openqa.selenium.WebDriver; // the browser remote-control interface
import org.openqa.selenium.chrome.ChromeDriver; // the Chrome implementation

public class SmokeTest {
    public static void main(String[] args) {
        WebDriver driver = new ChromeDriver(); // opens a real Chrome window
        driver.get("https://books.toscrape.com/"); // navigates to the page
        String title = driver.getTitle(); // reads the page's title
        System.out.println("Page title: " + title);
        driver.quit(); // closes the browser
    }
}