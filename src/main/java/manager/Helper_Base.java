package manager;

import com.google.common.io.Files;
import org.openqa.selenium.*;

import java.io.File;
import java.io.IOException;

public class Helper_Base {
	WebDriver wd;

	public Helper_Base (WebDriver wd) {
		this.wd = wd;
	}

	//methods
	//===========================================================================================
	public boolean isElementPresent (By locator) {
		return wd.findElements(locator).size() > 0;
	}

	//- - - - - - - - - - - - - - - - - - - - - - -
	public void type (By locator, String s) {
		WebElement element = wd.findElement(locator);

		element.click(); element.clear();
		element.sendKeys(s);
	}

	//- - - - - - - - - - - - - - - - - - - - - - -
	public void mouseClick (By locator) {
		wd.findElement(locator).click();
	}

	//- - - - - - - - - - - - - - - - - - - - - - -
	public String getText (By locator) {
		return wd.findElement(locator).getText();
	}

	//- - - - - - - - - - - - - - - - - - - - - - -
	public void pauseThreadSleep (int milliseconds) {
		try {
			Thread.sleep(milliseconds);
		} catch(InterruptedException e) {
			throw new RuntimeException(e);
		}
	}

	public void goHome () {
		mouseClick(By.cssSelector("a.logo"));
	}

	public void takesScreenshot (String link) {
		//File (java.io); Files (com.google.common.io)
		File tmp = ((TakesScreenshot)wd).getScreenshotAs(OutputType.FILE);
		File screenshot = new File(link);
		try {
			Files.copy(tmp, screenshot);
		} catch(IOException e) {
			e.printStackTrace();
		}
	}

}
