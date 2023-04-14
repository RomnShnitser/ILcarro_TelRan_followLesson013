import models.User;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class RegistrationTest extends TestBase {
	@BeforeMethod
	public void precondition(){
		if(app.getUser().isLogged()){
			app.getUser().logout();
		}
	}

	@Test
	public void registrationPositiveTest () {
		int i = (int) ((System.currentTimeMillis() / 1000) % 3600);
		User user = new User()
				.withName("Joe")
				.withLastName("Doe")
				.withEmail("joe" + i + "@mail.com")
				.withPassword("$Asdf1234");

		logger.info("registrationPositiveTest() with " + user.getEmail() + " and password: " + user.getPassword());
		app.getUser().openRegistrationForm();
		app.getUser().fillRegistrationForm(user);
		app.getUser().pauseThreadSleep(3000);
		app.getUser().submitRegistrationForm();
		app.getUser().pauseThreadSleep(3000);
		Assert.assertTrue(app.getUser().isRegistered());
		app.getUser().closeRegistration();
	}


}
