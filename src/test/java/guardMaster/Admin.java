package guardMaster;

import java.io.IOException;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;



public class Admin extends reuse{
reuse invoke = new reuse();



@BeforeTest
public void adminLogin() {
	invoke.init();
	invoke.adminLogin();
}

@Test
public void admin_Add_Admin() throws InterruptedException {
	
	invoke.admin_Add_Admin();
		
}
@Test
public void admin_Add_Instructor() throws InterruptedException {
	
	invoke.admin_Add_Instuctor();;
		
}



@Test
public void Admin_Add_Program() throws InterruptedException {
	invoke.admin_Add_Program();
}



@Test
public void admin_Delete_Program() throws InterruptedException {
	invoke.admin_Delete_Program();
}

@Test
public void admin_Add_Course() throws InterruptedException{
	invoke.admin_Add_Course();
}

@Test
public void admin_Delete_Course() throws InterruptedException {
	invoke.admin_Delete_Course();
}



@Test
public void admin_Logout() {
	invoke.admin_Logout();
}




@AfterMethod
public void Aftermethod(ITestResult result) throws IOException
{
	if(ITestResult.FAILURE==result.getStatus())
	{
		invoke.TakeScreenshot(result.getName());
	}
}

}





