import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeClass;


import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.*;


import io.github.bonigarcia.wdm.WebDriverManager;
import org.testng.annotations.AfterClass;

import org.testng.annotations.Test;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

import org.openqa.selenium.chrome.ChromeOptions;

public class RegistrationTest {
    private static WebDriver driver;
    private static ExtentReports extent;
    private static ExtentTest test;
    String screenshotPath;

    
    /*   @BeforeClass
    public void setUp() {

     extent = ExtentManager.getInstance();
         test = ExtentManager.createTest("Registration Page Test");

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

         test.log(Status.INFO, "Browser Launched");

         if (extent == null) {
            ExtentSparkReporter spark = new ExtentSparkReporter(new File("./reports/ExtentReport.html"));
            extent = new ExtentReports();
            extent.attachReporter(spark);
        }
            
    } */

    
    
    
    
  

    @BeforeClass
    public void setUp() {
    	ExtentSparkReporter sparkReporter = new ExtentSparkReporter("test-output/ExtentReport.html");
        sparkReporter.config().setTheme(Theme.STANDARD);
        sparkReporter.config().setDocumentTitle("Automation Test Report");
        sparkReporter.config().setReportName("Registration Test Report");

        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);
        extent.setSystemInfo("Tester", "Your Name");
        
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        test = extent.createTest("Registration Page Test");
        test.log(Status.INFO, "Browser Launched");
    }
    
   

//    @Before
//    public void setUp() {
//        System.setProperty("webdriver.chrome.driver", "path/to/chromedriver");
//        driver = new ChromeDriver();
//        driver.get("file:///path/to/your/registration.html"); // Update with actual file path
//    }

//    @Test
//    public void testRegistrationForm() throws InterruptedException {
//    	
////    	 String url = "https://keerthanalatha.github.io/nextJSBlog/index.html"; // Update with actual file path or hosted URL
//         String url = "https://keerthanalatha.github.io/nextJSBlog/registration.html";
//    	driver.get(url);
//         test.log(Status.PASS, "Navigated to Pages");
//         
//
//         Thread.sleep(10000);
//         
//         LocalDateTime now = LocalDateTime.now();
//         DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH-mm-ss");
//         String timestamp = now.format(formatter);
//         System.out.println("Current Timestamp: " + timestamp);
//
//        screenshotPath = ScreenshotUtil.captureScreenshot(driver, "ScreenshotCapture_" + timestamp);
//       
//
//         
//        driver.findElement(By.id("username")).sendKeys("TestUser");
//        driver.findElement(By.id("email")).sendKeys("test@example.com");
//        driver.findElement(By.id("password")).sendKeys("Test@1234");
//        driver.findElement(By.id("phone")).sendKeys("1234567890");
//        driver.findElement(By.id("dob")).sendKeys("2000-01-01");
//        driver.findElement(By.id("gender")).sendKeys("Male");
//        driver.findElement(By.className("btn")).click();
//        
//        String alertText = driver.switchTo().alert().getText();
//        // assertEquals("Registration successful!", alertText);
//        if(alertText.equals("Registration successful!")) {
//        	 test.pass("Registration successful!")
//             .addScreenCaptureFromPath(screenshotPath);
//        }
//        else {
//        	 test.fail("Registration is not successful!")
//             .addScreenCaptureFromPath(screenshotPath);
//        }
//        driver.switchTo().alert().accept();
//        driver.switchTo().defaultContent();
//        Thread.sleep(5000);
//    }
//    
     
    


     @Test
     public void testRegistrationForm() {
         String url = "https://keerthanalatha.github.io/nextJSBlog/registration.html";
         driver.get(url);
         test.log(Status.PASS, "Navigated to Registration Page");

         // Wait for page to load
         WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
         wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("username")));

         // Capture Timestamp for Screenshot Naming
         LocalDateTime now = LocalDateTime.now();
         DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH-mm-ss");
         String timestamp = now.format(formatter);

         // Capture Screenshot Before Submission
         String screenshotPath = ScreenshotUtil.captureScreenshot(driver, "ScreenshotBefore_" + timestamp);
         test.log(Status.INFO, "Captured Screenshot Before Submission")
             .addScreenCaptureFromPath(screenshotPath);

         screenshotPath = ScreenshotUtil.captureScreenshot(driver, "ScreenshotAfter_" + timestamp);
         // Fill Registration Form
         driver.findElement(By.id("username")).sendKeys("TestUser");
         driver.findElement(By.id("email")).sendKeys("test@example.com");
         driver.findElement(By.id("password")).sendKeys("Test@1234");
         driver.findElement(By.id("phone")).sendKeys("1234567890");
         driver.findElement(By.id("dob")).sendKeys("2000-01-01");
         driver.findElement(By.id("gender")).sendKeys("Male");
         driver.findElement(By.className("btn")).click();

         // Handle Alert Properly
         try {

             wait.until(ExpectedConditions.alertIsPresent()); // Wait for alert to be present
             Alert alert = driver.switchTo().alert(); // Switch to alert
             String alertText = alert.getText();
             
             // Capture Screenshot After Submission


             if (alertText.equals("Registration successful!")) {
                 test.pass("Registration successful!")
                     .addScreenCaptureFromPath(screenshotPath);
             } else {
                 test.fail("Registration failed! Expected 'Registration successful!', but got: " + alertText)
                     .addScreenCaptureFromPath(screenshotPath);
             }
             alert.accept(); // Dismiss alert
         } catch (NoAlertPresentException e) {
             test.fail("No alert was present after form submission.");
         }
     }

     

    @Test
    public void testEmptyUsername() throws InterruptedException {
    	
    	
//    	driver.findElement(By.id("username")).clear();
//        driver.findElement(By.id("email")).clear();
//        driver.findElement(By.id("password")).clear();
//        driver.findElement(By.id("phone")).clear();
//        driver.findElement(By.id("dob")).clear();
//        driver.findElement(By.id("gender")).clear();
    	String url = "https://keerthanalatha.github.io/nextJSBlog/registration.html";
    	driver.navigate().to(url);
    	
    	
    	 
        Thread.sleep(5000);
        driver.findElement(By.id("email")).sendKeys("test@example.com");
        driver.findElement(By.id("password")).sendKeys("Test@1234");
        driver.findElement(By.id("phone")).sendKeys("1234567890");
        driver.findElement(By.id("dob")).sendKeys("2000-01-01");
        driver.findElement(By.id("gender")).sendKeys("Male");
        driver.findElement(By.className("btn")).click();
        
        WebElement error = driver.findElement(By.id("usernameError"));
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH-mm-ss");
        String timestamp = now.format(formatter);
        System.out.println("Current Timestamp: " + timestamp);
        
        screenshotPath = ScreenshotUtil.captureScreenshot(driver, "ScreenshotCapture_"+timestamp);
        
        // assertEquals("Username must be at least 3 characters long", error.getText());
        if(error.getText().equals("Username must be at least 3 characters long")) {
       	 test.pass("Username field validation is successful!" )
            .addScreenCaptureFromPath(screenshotPath);
       }
       else {
       	 test.fail("Username field validation is not successful!" )
            .addScreenCaptureFromPath(screenshotPath);
       }
        
        Thread.sleep(5000);
    }
    
    @Test
    public void testInvalidEmail() throws InterruptedException {
//    	driver.findElement(By.id("username")).clear();
//        driver.findElement(By.id("email")).clear();
//        driver.findElement(By.id("password")).clear();
//        driver.findElement(By.id("phone")).clear();
//        driver.findElement(By.id("dob")).clear();
//        driver.findElement(By.id("gender")).clear();
    	String url = "https://keerthanalatha.github.io/nextJSBlog/registration.html";
    	driver.navigate().to(url);
        Thread.sleep(5000);
        driver.findElement(By.id("username")).sendKeys("TestUser");
        driver.findElement(By.id("email")).sendKeys("invalidemail");
        driver.findElement(By.id("password")).sendKeys("Test@1234");
        driver.findElement(By.id("phone")).sendKeys("1234567890");
        driver.findElement(By.id("dob")).sendKeys("2000-01-01");
        driver.findElement(By.id("gender")).sendKeys("Male");
        driver.findElement(By.className("btn")).click();
        
        WebElement error = driver.findElement(By.id("emailError"));
        
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH-mm-ss");
        String timestamp = now.format(formatter);
        System.out.println("Current Timestamp: " + timestamp);
        
        screenshotPath = ScreenshotUtil.captureScreenshot(driver, "ScreenshotCapture_"+timestamp);
        // assertEquals("Invalid email format", error.getText());
        if(error.getText().equals("Invalid email format")) {
          	 test.pass("Email field validation is successful!" )
               .addScreenCaptureFromPath(screenshotPath);
          }
          else {
          	 test.fail("Email field validation is not successful!" )
               .addScreenCaptureFromPath(screenshotPath);
          }
        Thread.sleep(5000);
    }
    
    @Test
    public void testWeakPassword() throws InterruptedException {
//    	driver.findElement(By.id("username")).clear();
//        driver.findElement(By.id("email")).clear();
//        driver.findElement(By.id("password")).clear();
//        driver.findElement(By.id("phone")).clear();
//        driver.findElement(By.id("dob")).clear();
//        driver.findElement(By.id("gender")).clear();
    	String url = "https://keerthanalatha.github.io/nextJSBlog/registration.html";
    	driver.navigate().to(url);
        Thread.sleep(5000);
        driver.findElement(By.id("username")).sendKeys("TestUser");
        driver.findElement(By.id("email")).sendKeys("test@example.com");
        driver.findElement(By.id("password")).sendKeys("weakpass");
        driver.findElement(By.id("phone")).sendKeys("1234567890");
        driver.findElement(By.id("dob")).sendKeys("2000-01-01");
        driver.findElement(By.id("gender")).sendKeys("Male");
        driver.findElement(By.className("btn")).click();
        
        WebElement error = driver.findElement(By.id("passwordError"));
        
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH-mm-ss");
        String timestamp = now.format(formatter);
        System.out.println("Current Timestamp: " + timestamp);
        
        screenshotPath = ScreenshotUtil.captureScreenshot(driver, "ScreenshotCapture_"+timestamp);
        // assertEquals("Password must be at least 8 characters long, include an uppercase letter, a lowercase letter, a number, and a special character.", error.getText());
        if(error.getText().equals("Password must be at least 8 characters long, include an uppercase letter, a lowercase letter, a number, and a special character.")) {
         	 test.pass("Password field validation is successful!" )
              .addScreenCaptureFromPath(screenshotPath);
         }
         else {
         	 test.fail("Password field validation is not successful!" )
              .addScreenCaptureFromPath(screenshotPath);
         }
        Thread.sleep(5000);
    }
    
    @Test
    public void testInvalidPhoneNumber() throws InterruptedException {
//    	driver.findElement(By.id("username")).clear();
//        driver.findElement(By.id("email")).clear();
//        driver.findElement(By.id("password")).clear();
//        driver.findElement(By.id("phone")).clear();
//        driver.findElement(By.id("dob")).clear();
//        driver.findElement(By.id("gender")).clear();
    	String url = "https://keerthanalatha.github.io/nextJSBlog/registration.html";
    	driver.navigate().to(url);
        Thread.sleep(5000);
        
        driver.findElement(By.id("username")).sendKeys("TestUser");
        driver.findElement(By.id("email")).sendKeys("test@example.com");
        driver.findElement(By.id("password")).sendKeys("Test@1234");
        driver.findElement(By.id("phone")).sendKeys("12345");
        driver.findElement(By.id("dob")).sendKeys("2000-01-01");
        driver.findElement(By.id("gender")).sendKeys("Male");
        driver.findElement(By.className("btn")).click();
        
        WebElement error = driver.findElement(By.id("phoneError"));
        
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH-mm-ss");
        String timestamp = now.format(formatter);
        System.out.println("Current Timestamp: " + timestamp);
        
        screenshotPath = ScreenshotUtil.captureScreenshot(driver, "ScreenshotCapture_"+timestamp);
        
        // assertEquals("Invalid phone number", error.getText());
        if(error.getText().equals("Invalid phone number")) {
        	 test.pass("Phone number field validation is successful!")
             .addScreenCaptureFromPath(screenshotPath);
        }
        else {
        	 test.fail("Phone number field validation is not successful!" )
             .addScreenCaptureFromPath(screenshotPath);
        }
        Thread.sleep(5000);
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }
    
    @AfterClass
    public static void tearDownReport() {
        extent.flush(); // THIS IS CRITICAL
    }
}
