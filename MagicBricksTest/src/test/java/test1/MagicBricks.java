package test1;

import java.time.Duration;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class MagicBricks {

	public static void main(String[] args) throws InterruptedException {
		// To disable notification on chrome(Optional)
		ChromeOptions opt = new ChromeOptions();
		opt.addArguments("--disable-notifications");
		WebDriver driver = new ChromeDriver();// passing opt as arguments(Optional)

		// Navigate to MagicBricks website
		driver.get("https://www.magicbricks.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));

		// Input location Mumbai and click submit
		driver.findElement(By.className("mb-search__input")).sendKeys("Mumbai");
		driver.findElement(By.className("mb-search__btn")).click();
		Thread.sleep(1000);
		List<WebElement> prices = driver.findElements(By.xpath("//div[@class='mb-srp__card__price--amount']"));
		TreeSet<WebElement> priceSet = new TreeSet<>(prices);
		int count = 0;

		// Print all price values in sorted order(descending)
		for (WebElement price : priceSet.descendingSet()) {
			System.out.println(price.getText());
			count++;
			if (count == 5) {
				price.click();// Click on the 5th element
			}
		}
		String parent = driver.getWindowHandle();
		Set<String> s = driver.getWindowHandles();
		Iterator<String> I1 = s.iterator();
		while (I1.hasNext()) {
			String child_window = I1.next();
			if (!parent.equals(child_window)) {
				driver.switchTo().window(child_window);

				// Click on Get Phone No. button
				driver.findElement(By.className("contact-form__btn freecab")).click();
				driver.findElement(By.id("userName")).sendKeys("Nilesh");
				driver.findElement(By.id("userEmail")).sendKeys("xyz@gmail.com");
				driver.findElement(By.id("userMobile")).sendKeys("9952752254");
				driver.findElement(By.className("contact-form__btn")).click();
				// Fill the form and submited
				driver.quit();
				// Close the browser
			}
		}

	}

}
