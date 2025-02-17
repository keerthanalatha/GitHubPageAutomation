import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class StaticPageTest {
    private WebDriver driver;

    @BeforeClass
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void testStaticPage() throws Exception{
        String url = "https://keerthanalatha.github.io/nextJSBlog/index.html"; // Update with actual file path or hosted URL
        driver.get(url);

        Thread.sleep(10000);

        
        // Verify the page title
        String expectedTitle = "Static HTML Page";
        String actualTitle = driver.getTitle();
        Assert.assertEquals(actualTitle, expectedTitle, "Title verification failed!");

        // Verify the header text
        WebElement header = driver.findElement(By.tagName("h1"));
        Assert.assertEquals(header.getText(), "Welcome to My Static Page", "Header text mismatch!");

        // Verify the paragraph content
        WebElement paragraph = driver.findElement(By.tagName("p"));
        Assert.assertEquals(paragraph.getText(), "This is a simple static HTML page with basic styling.", "Paragraph text mismatch!");
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();  // Close the browser
        }
    }
}
