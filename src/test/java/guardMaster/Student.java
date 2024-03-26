package guardMaster;

import java.io.IOException;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

public class Student extends reuse {

	reuse invoke = new reuse();

	@Test
	public void studentSignUp() throws InterruptedException {
		invoke.init();
		invoke.openApp();
		invoke.studentSignup();
	}
	
	
	

	@AfterMethod
	public void Aftermethod(ITestResult result) throws IOException {
		if (ITestResult.FAILURE == result.getStatus()) {
			invoke.TakeScreenshot(result.getName());
		}
	}
}
