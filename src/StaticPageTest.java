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
        // Use Selenium Manager to auto-detect ChromeDriver
        System.setProperty("webdriver.http.factory", "jdk-http-client");  // Fix for newer Selenium versions

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");  // Run in headless mode
        options.addArguments("--no-sandbox");  // Required in CI environments
        options.addArguments("--disable-dev-shm-usage");  // Prevents memory crashes
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("--disable-gpu"); // Disables GPU acceleration
        options.addArguments("--window-size=1920,1080"); // Set screen size for screenshots

        // Fix user data directory issue
        try {
            Path tempDir = Files.createTempDirectory("chrome-user-data");
            options.addArguments("--user-data-dir=" + tempDir.toAbsolutePath().toString());
        } catch (Exception e) {
            System.out.println("Failed to create a temp user data directory: " + e.getMessage());
        }

        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
    }

    // @BeforeClass
    // public void setUp() {
    //     WebDriverManager.chromedriver().setup();
    //     driver = new ChromeDriver();
    //     driver.manage().window().maximize();
    // }

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
