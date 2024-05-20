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
		WebDriver driver = new ChromeDriver();//(Optional) Passing opt as arguments

		// Navigate to MagicBricks Website
		driver.get("https://www.magicbricks.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		driver.findElement(By.className("mb-search__tag-close")).click();//(optional) if by default getting any city name
																			
		// Input location Mumbai and click submit
		driver.findElement(By.className("mb-search__input")).sendKeys("Mumbai");
		driver.findElement(By.xpath("//div[@id=\"serachSuggest\"]/div[3]")).click();//(optional) if by default getting any other city
		driver.findElement(By.className("mb-search__btn")).click();

		// sorting in descending order by Website page
		// At first I thought this is the requirement then after i realize requirement 
//		driver.findElement(By.className("mb-srp__tabs__sortby--title")).click();
//		driver.findElement(By.xpath("//ul[@class='mb-srp__tabs__sortby__dd__list']/li[text()='Price - High to Low']")).click();
		List<WebElement> elements = driver.findElements(By.xpath("//div[@class='mb-srp__card__price--amount']"));
		// Sorting in descending order before printing
        TreeSet<WebElement> sortedSet = new TreeSet<>((e1, e2)->e2.getText().compareTo(e1.getText()));//initializes the TreeSet with a custom comparator defined by a lambda expression 
		sortedSet.addAll(elements);
		int count = 0;
		// Print the sorted elements in descending
		for (WebElement element : sortedSet) {
			String prices = element.getText();
			if (prices.contains("Lac")) {
				continue;
			} else {
				System.out.println(prices);
				count++;
				if (count == 5) {
					element.click();
			}	}}
		for (WebElement element : sortedSet) {
			String prices = element.getText();
			if (prices.contains("Cr")) {
				continue;
			} else {
				System.out.println(prices);
			}}
		//Switching focus to child window
		Set<String> s = driver.getWindowHandles();
		Iterator<String> I1 = s.iterator();
		String parent_window = I1.next();
		String child_window = I1.next();
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
