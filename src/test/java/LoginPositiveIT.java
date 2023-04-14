import manager.NGListener;
import models.User;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(NGListener.class)
public class LoginPositiveIT extends TestBase{
	@BeforeMethod
	public void precondition () {
		if(app.getUser().isLogged()){
			app.getUser().logout();
		}
	}

	@Test
	public void loginTest () {
		app.getUser().openLoginForm();
		app.getUser().fillLoginForm("asd@fgh.com", "$Asdf1234");
		app.getUser().submitLogin();
		app.getUser().pauseThreadSleep(1500);
		app.getUser().clickOkButton();
	}

	@Test
	public void loginTestModel() {
		User data = new User().withEmail("asd@fgh.com").withPassword("$Asdf1234");
		app.getUser().openLoginForm();
		app.getUser().fillLoginForm(data);
		app.getUser().submitLogin();
		app.getUser().pauseThreadSleep(1500);
		Assert.assertTrue(app.getUser().isLoggedSuccess());
		app.getUser().clickOkButton();
	}

	@AfterMethod
	public void postCondition () {
		app.getUser().pauseThreadSleep(1000);
		if(app.getUser().isLogged()){
			app.getUser().logout();
		}
		app.getUser().goHome();
	}
}
