package manager;

import models.User;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HelperUser extends Helper_Base {

	public HelperUser (WebDriver wd) {
		super(wd);
	}

	public void openLoginForm () {
		mouseClick(By.xpath("//a[@class='navigation-link'][normalize-space()='Log in']"));
	}

	public boolean isLogged () {
		return isElementPresent(By.xpath("//a[normalize-space()='Logout']"));
	}

	public boolean isLoggedSuccess () {
		WebDriverWait wait = new WebDriverWait(wd, 5);
		WebElement element = wd.findElement(By.cssSelector(".dialog-container"));
		wait.until(ExpectedConditions.visibilityOf(element));
//		WebElement element = (new WebDriverWait(wd,))
		return element.getText().contains("success");
	}

	public void logout () {
		mouseClick(By.xpath("//a[normalize-space()='Logout']"));
	}

	public void fillLoginForm (String mail, String password) {
		type(By.xpath("//input[@id='email']"), mail);
		type(By.xpath("//input[@id='password']"), password);
	}

	public void submitLogin () {
		mouseClick(By.xpath("//button[@type='submit']"));
	}

	public void clickOkButton () {
		mouseClick(By.xpath("//button[@type='button']"));
	}

	public void fillLoginForm (User data) {
		type(By.xpath("//input[@id='email']"), data.getEmail());
		type(By.xpath("//input[@id='password']"), data.getPassword());
	}

	public void login (User user) {
		openLoginForm();
		fillLoginForm(user);
		submitLogin();
		pauseThreadSleep(1500);
		clickOkButton();
		pauseThreadSleep(1500);
	}

	public void openRegistrationForm () {
		mouseClick(By.xpath("//a[text()=' Sign up ']"));
	}

	public void fillRegistrationForm (User user) {
		type(By.id("name"), user.getName());
		type(By.id("lastName"), user.getLastName());
		type(By.id("email"), user.getEmail());
		type(By.id("password"), user.getPassword());
		checkPolicy();
	}

	public void checkPolicy () {
		JavascriptExecutor js = (JavascriptExecutor) wd;
		js.executeScript("document.querySelector('#terms-of-use').click();");

	}

	public void submitRegistrationForm () {
		mouseClick(By.xpath("//button[@type='submit']"));
	}

	public boolean isRegistered () {
		WebDriverWait wait = new WebDriverWait(wd, 10);
		wait.until(ExpectedConditions.visibilityOf(wd.findElement(By.xpath("//div[@class='dialog-container']"))));
		return wd.findElement(By.xpath("//div[@class='dialog-container']")).getText().contains("success");
	}

	public void closeRegistration () {
		mouseClick(By.cssSelector("button.positive-button"));
	}
}
