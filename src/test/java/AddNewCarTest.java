import models.Car;
import models.User;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class AddNewCarTest extends TestBase {
	@BeforeMethod
	public void precondition () {
		if(app.getUser().isLogged() == false) {
			app.getUser().login(new User()
					.withEmail("asd@fgh.com")
					.withPassword("$Asdf1234")
			);
		}
	}

	@Test
	public void addNewCarPositiveTest () {
		int i = (int) (System.currentTimeMillis() / 1000) % 3600;

		Car car = Car.builder()
				.address("Tel Aviv")
				.make("KIA")
				.model("Sportage")
				.year("2020")
				.fuel("Petrol")
				.seats("4")
				.carClass("5")
				.carRegNumber("100-200-" + i)
				.price("150")
				.build();
		app.getUser().pauseThreadSleep(1600);
		app.getCar().openCarForm();
		app.getUser().pauseThreadSleep(1600);
		//Assert.assertTrue(app.getCar().isCarFormPresent());
		app.getCar().fillCarForm(car);
		app.getCar().submitForm();
		app.getCar().pauseThreadSleep(1500);
		app.getCar().clickOkOnCarAdd();

		logger.info("Successfully added new car with: " + car.toString());
	}

	@AfterMethod
	public void postCondition () {
		app.getUser().pauseThreadSleep(2000);
		app.getUser().goHome();
	}
}
