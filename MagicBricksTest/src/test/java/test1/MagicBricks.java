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

	public static void main(String[] args) throws InterruptedException
	{
		// To disable notification on chrome(Optional)
		ChromeOptions opt = new ChromeOptions();
		opt.addArguments("--disable-notifications");
		WebDriver driver = new ChromeDriver();// Passing opt as arguments(Optional)

		// Navigate to MagicBricks website
		driver.get("https://www.magicbricks.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));

		//driver.findElement(By.className("mb-search__tag-close")).click(); // optional if by default getting any city name
		// Input location Mumbai and click submit
		driver.findElement(By.className("mb-search__input")).sendKeys("Mumbai");
		//driver.findElement(By.xpath("//div[@id=\"serachSuggest\"]/div[3]")).click();  // optional if by default getting any city
		driver.findElement(By.className("mb-search__btn")).click();
		
		//sorting in decending order
		driver.findElement(By.className("mb-srp__tabs__sortby--title")).click();
		driver.findElement(By.xpath("//ul[@class='mb-srp__tabs__sortby__dd__list']/li[text()='Price - High to Low']")).click();
		Thread.sleep(1000);
		List<WebElement> prices = driver.findElements(By.xpath("//div[@class='mb-srp__card__price--amount']"));
		int count = 0;

		// Print all price values in sorted order(descending)
		for (WebElement price : prices) 
		{
			System.out.println(price.getText());
			count++;
			if (count == 5) {
				price.click();// Click on the 5th element
			}
		}
		Set<String> s = driver.getWindowHandles();
		Iterator<String> I1 = s.iterator();
		String parent_window = I1.next();
		String child_window = I1.next();
		Thread.sleep(1000);
		driver.switchTo().window(child_window);
		driver.findElement(By.xpath("//button[@class='contact-form__btn freecab']")).click();
		driver.findElement(By.id("userName")).sendKeys("Nilesh");
		driver.findElement(By.id("userEmail")).sendKeys("xyz@gmail.com");
		driver.findElement(By.id("userMobile")).sendKeys("9952752254");
		driver.findElement(By.className("contact-form__btn")).click();
		// Fill the form and submited
		driver.quit();
		// Close the browser
			}
		

	

}
