import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginNegativeTests extends TestBase {
	@BeforeMethod
	public void precondition () {
		if(app.getUser().isLogged()) {
			app.getUser().logout();
		}
	}

	@Test
	public void Test () {

	}

	@AfterMethod
	public void postCondition () {
		app.getUser().pauseThreadSleep(1000);
		if(app.getUser().isLogged()) {
			app.getUser().logout();
		}
		app.getUser().goHome();
	}


}
