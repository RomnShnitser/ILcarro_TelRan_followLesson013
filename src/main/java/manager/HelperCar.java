package manager;

import models.Car;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HelperCar extends Helper_Base {
	public HelperCar (WebDriver wd) {
		super(wd);
	}

	public void openCarForm () {
		pauseThreadSleep(2000);
		mouseClick(By.xpath("(//a[@id='1'])[1]"));
	}

	public boolean isCarFormPresent() {

		return new WebDriverWait(wd, 10)
				.until(ExpectedConditions.textToBePresentInElement(
						wd.findElement(By.xpath("//h1[normalize-space()='Let the car work']"))
						,"Let the car work"
				));
	}

	public void typeLocation(String address) {
		type(By.id("pickUpPlace"),address);
		pauseThreadSleep(3000);
		mouseClick(By.cssSelector("div.pac-item"));
		pauseThreadSleep(3000);
	}

	public void select(By locator, String option) {
		new Select(wd.findElement(locator)).selectByValue(option);
	}

	public void fillCarForm (Car car) {
		typeLocation(car.getAddress());
		type(By.id("make"), car.getMake());
		type(By.id("model"), car.getModel());
		type(By.id("year"), car.getYear());
		select(By.id("fuel"), car.getFuel());
		type(By.id("seats"), car.getSeats());
		//type(By.id("class"), car.getCarClass());
		type(By.id("classs"), car.getCarClass());
		type(By.id("serialNumber"), car.getCarRegNumber());
		type(By.id("price"), car.getPrice());



	}

	public void submitForm () {
		mouseClick(By.cssSelector("button[type='submit']"));
	}

	public void clickOkOnCarAdd () {
		mouseClick(By.xpath("//button[normalize-space()='Show car']"));
	}
}
