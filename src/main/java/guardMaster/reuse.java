package guardMaster;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.github.bonigarcia.wdm.WebDriverManager;

public class reuse {
	WebDriver driver;
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

	// initialize Chrome driver

	public WebDriver init() {
		WebDriverManager.chromedriver().clearDriverCache().setup();
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--remote-allow-origins=*");
		options.addArguments("--disable notifications");
		DesiredCapabilities cp = new DesiredCapabilities();
		cp.setCapability(ChromeOptions.CAPABILITY, options);
		options.merge(cp);
		driver = new ChromeDriver(options);
		return driver;
	}

	// Method to capture screenshot on test failure
	public void TakeScreenshot(String screenshotname) throws IOException {

		File screenshotfile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(screenshotfile, new File("./screenshots/" + screenshotname + ".png"));
		System.out.println("Screenshot captured: " + screenshotname);

	}

	// Lauch Admin
	public void launchApp() {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		driver.get("https://guard-admin.netlify.app/login");

	}

	// Admin Login
	public void adminLogin() {
		launchApp();
		driver.findElement(By.xpath("//input[@placeholder='Enter Email']")).sendKeys("admin@test.com");
		driver.findElement(By.xpath("//input[@placeholder='Enter Password']")).sendKeys("Dandytech@2022");
		driver.findElement(By.xpath("//button[text()='Login']")).click();

	}

	// Admin Add Sub-Admin
	public void admin_Add_Admin() throws InterruptedException {

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.findElement(By.xpath("(//span[@class='cursor-pointer'])[1]")).click();
		driver.findElement(By.xpath("//a[text()='Admin']")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//div[text()='Add New Admin User']")).click();
		driver.findElement(By.xpath("//input[@placeholder='Enter First Name']")).sendKeys("Test");
		driver.findElement(By.xpath("//input[@placeholder='Enter Last Name']")).sendKeys("Admin");
		driver.findElement(By.xpath("//input[@placeholder='Enter Email']")).sendKeys("greenmouseapp+admin2@gmail.com");
		driver.findElement(By.xpath("//input[@placeholder='Enter Phone Number']")).sendKeys("09034376724");
		driver.findElement(By.xpath("//input[@placeholder='Enter Password']")).sendKeys("Dandytech@2022");
		driver.findElement(By.xpath("//input[@placeholder='Re-enter Password']")).sendKeys("Dandytech@2022");
		driver.findElement(By.xpath("//button[text()='Submit']")).click();

		wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//div[text()='Admin account created successfully.']")));

		WebElement success = driver.findElement(By.xpath("//div[text()='Admin account created successfully.']"));
		String successText = success.getText();
		String text = "Admin account created successfully.";

		Assert.assertEquals(successText, text);
		Thread.sleep(5000);
	}

	// Admin Add Instructor
	public void admin_Add_Instuctor() throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.findElement(By.xpath("(//span[@class='cursor-pointer'])[1]")).click();
		driver.findElement(By.xpath("//a[text()='Instructors']")).click();
		driver.findElement(By.xpath("//div[text()='Add New Instuctor']")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//input[@placeholder='Enter First Name']")).sendKeys("Test");
		driver.findElement(By.xpath("//input[@placeholder='Enter Last Name']")).sendKeys("Instructor2");
		driver.findElement(By.xpath("//input[@placeholder='Enter Email']")).sendKeys("greenmouseapp+inst4@gmail.com");
		driver.findElement(By.xpath("//input[@placeholder='Enter Phone Number']")).sendKeys("090871163464");
		driver.findElement(By.xpath("//input[@placeholder='Enter Password']")).sendKeys("Dandytech@2022");
		driver.findElement(By.xpath("//input[@placeholder='Re-enter Password']")).sendKeys("Dandytech@2022");
		driver.findElement(By.xpath("//button[text()='Submit']")).click();

		wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//div[text()='Admin account created successfully.']")));

		WebElement success = driver.findElement(By.xpath("//div[text()='instructor account created successfully.']"));
		String successText = success.getText();
		String text = "Instructor account created successfully.";

		Assert.assertEquals(successText, text);
		Thread.sleep(5000);
	}

	// Admin add Program
	public void admin_Add_Program() throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.findElement(By.xpath("(//span[@class='cursor-pointer'])[2]")).click();
		driver.findElement(By.xpath("//a[@href='/programs']")).click();
		driver.findElement(By.xpath("//div[text()='Add New Program']")).click();
		driver.findElement(By.xpath("//input[@placeholder='Enter Program Title']")).sendKeys("Test_Program");
		driver.findElement(By.xpath("//button[text()='Submit']")).click();

	}

	// Admin Delete program
	public void admin_Delete_Program() throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.findElement(By.xpath("(//span[@class='cursor-pointer'])[2]")).click();
		driver.findElement(By.xpath("//div/a[@href='/programs']")).click();
		// Find the WebElement using XPath
		WebElement cell = driver.findElement(By.xpath("(//tbody/tr)[1]"));

		// Get the text of the WebElement
		String cellText = cell.getText();

		// Check if the text contains "test"
		if (cellText.contains("Test_Program")) {
			driver.findElement(By.xpath("(//tr//button)[1]")).click();
			driver.findElement(By.xpath("//button[text()=' Delete']")).click();
			Thread.sleep(3000);
			driver.findElement(By.xpath("//button[text()='Delete']")).click();
		} else {
			System.out.println("Text 'test' does not exist in the first column of the first row.");

		}

	}

	public void admin_Add_Course() throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.findElement(By.xpath("(//span[@class='cursor-pointer'])[2]")).click();
		driver.findElement(By.xpath("//div/a[@href='/courses']")).click();
		driver.findElement(By.xpath("//div[text()='Add New Course']")).click();
		Thread.sleep(3000);

		driver.findElement(By.xpath("//input[@placeholder='Enter Course Title']")).sendKeys("Test_Course");

		WebElement programs = driver.findElement(By.xpath("//select[@name='program']"));
		Select program = new Select(programs);
		program.selectByVisibleText("Test_Program");

		driver.findElement(By.xpath("//input[@placeholder='Enter Course Price']")).sendKeys("435");
		driver.findElement(By.xpath("//input[@placeholder='Enter Short Description']")).sendKeys("We are Testing");

		WebElement fileInput = driver.findElement(By.xpath("//input[@type='file']"));
		String filePath = "/Users/user/Desktop/wordPress/QA.png";
		fileInput.sendKeys(filePath);
		driver.findElement(By.xpath("//textarea[@placeholder='Enter Course Description']"))
				.sendKeys("We are Testing for good");
		Thread.sleep(5000);
		driver.findElement(By.xpath("//button[text()='Submit']")).click();
		Thread.sleep(300);
	}

	public void admin_Delete_Course() throws InterruptedException {
		// Find and click on the desired elements
		driver.findElement(By.xpath("(//span[@class='cursor-pointer'])[2]")).click();
		driver.findElement(By.xpath("//div/a[@href='/courses']")).click();

		WebElement cell_delete = driver.findElement(By.xpath("(//tbody/tr)[1]"));

		String cellText_delete = cell_delete.getText();

		System.out.println(cell_delete);
		// Check if the text contains "Test_Course"
		if (cellText_delete.contains("Test_Course")) {
			Thread.sleep(5000);
			System.out.println(cellText_delete);

			// Find the table element by its tag name
			WebElement table = driver.findElement(By.xpath("//div[@class=' overflow-x-auto']"));

			int scrollAmount = 1500; // Adjust this value based on your requirement

			// Scroll the table horizontally using JavaScriptExecutor
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].scrollLeft += arguments[1];", table, scrollAmount);
			js.executeScript("window.scrollTo(arguments[0], window.scrollY);", table, scrollAmount);

			driver.findElement(By.xpath("(//tr//button)[1]")).click();
			driver.findElement(By.xpath("//button[text()=' Delete']")).click();
			driver.findElement(By.xpath("//button[text()='Delete']")).click();

		} else {
			System.out.println("Text 'Test_Course' does not exist in the first column of the first row.");
		}

	}

	public void admin_Logout() {
		driver.findElement(By.xpath("//span[text()='Logout']")).click();
		driver.findElement(By.xpath("//button[text()='Yes']")).click();

	}

	public void openApp() {
		driver.get("https://guardmaster-new.netlify.app/");
		driver.manage().window().maximize();
	}

	// Student Sign Up
	public void studentSignup() throws InterruptedException {
		driver.findElement(By.xpath("//button[text()='Accept All']")).click();

		driver.findElement(By.xpath("//a[@href='/auth/login']")).click();
		driver.findElement(By.xpath("//a[text()='Sign Up']")).click();
		driver.findElement(By.xpath("//input[@placeholder='Enter first name']")).sendKeys("QA TESTER");

		driver.findElement(By.xpath("//input[@placeholder='Enter last name']")).sendKeys("GREEN");

		driver.findElement(By.xpath("//input[@placeholder='Enter Email']")).sendKeys("greenmouseapp+g2@gmail.com");

		WebElement countries = driver.findElement(By.xpath("//select[@class='PhoneInputCountrySelect']"));

		Select country = new Select(countries);

		country.selectByVisibleText("Nigeria");
		driver.findElement(By.xpath("//input[@type='tel']")).sendKeys("07083122645");
		driver.findElement(By.xpath("(//input[@name='password'])[1]")).sendKeys("Dandytech@2022");
		driver.findElement(By.xpath("(//input[@name='password'])[2]")).sendKeys("Dandytech@2022");
		driver.findElement(By.xpath("//button[text()='Sign Up']")).click();
		
		Thread.sleep(7000);
		// wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[text()='Continue']")));

		driver.findElement(By.xpath("//a[text()='Continue']")).click();

		// open continue in a new window
		driver.switchTo().newWindow(WindowType.WINDOW);

		Set<String> handles = driver.getWindowHandles();

		java.util.Iterator<String> it = handles.iterator();

		// String parentWinId = it.next();

		String childWinId = it.next();

		driver.switchTo().window(childWinId);

		driver.get(
				"https://accounts.google.com/v3/signin/identifier?continue=https%3A%2F%2Fmail.google.com%2Fmail%2F&ifkv=ARZ0qKJcn0pof_BrU6yIfbmFL4GT1VkvXzNM4PaLSGWeMBXAx29gSYWy2riYS2rz1scHthF-WgZhvQ&rip=1&sacu=1&service=mail&flowName=GlifWebSignIn&flowEntry=ServiceLogin&dsh=S1627587947%3A1710947192414743&theme=glif&ddm=0");
		driver.findElement(By.xpath("//input[@type='email']")).sendKeys("greenmouseapp");
		driver.findElement(By.xpath("//span[text()='Next']")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@type='password']")));
		driver.findElement(By.xpath("//input[@type='password']")).sendKeys("Greentech@2022");
		driver.findElement(By.xpath("//span[text()='Next']")).click();
		
		
		
		// switch back to parent window
		// driver.switchTo().window(parentWinId);
	}

	public void tearDown() {
		driver.quit();
		// driver.close();
	}

//		int k = 1;
//		while (k <= 7) {
//			driver.findElement(By.xpath("(//span[@data-toggle='modal'])[2]")).click();
//			driver.findElement(By.xpath("(//button[text()='Delete'])[1]")).click();
//			driver.findElement(By.xpath("//button[text()='Close']")).click();
//			k++;
//		}
//
//		System.out.println(k);
//
//	}

//	// Assistant Manager launch App
//	public void launchUsers() {
//		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
//		driver.manage().window().maximize();
//		driver.get("https://donandchyke.com.ng/");
//	}

//		// Locate the file input element
//		WebElement fileInput = driver.findElement(By.xpath("(//input[@name='receipt_image'])[1]"));
//		// Provide the file path to be uploaded
//		String filePath = "C:\\Users\\user\\Downloads\\Screenshot (7).png";
//		fileInput.sendKeys(filePath);
//		driver.findElement(By.xpath("(//button[@name='save'])[1]")).click();

}
