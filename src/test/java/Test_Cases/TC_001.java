package Test_Cases;
import java.io.IOException;

import org.testng.annotations.Test;

import Test_Base.Base_class;
import Utilities.Dataproviders;

public class TC_001 extends Base_class {
	@Test(dataProvider = "Logindata",dataProviderClass = Dataproviders.class,groups = {"Master","Regression"})
	public void entrytest(String msg1,String msg2,String msg3,String msg4,String msg5) throws IOException
	{
		logger.info("************TC_001__Tes case started**********************");
		System.out.println("This is First test case and the message is "+msg1);
		System.out.println("This is First test case and the message is "+msg2);
		System.out.println("This is First test case and the message is "+msg3);
		System.out.println("This is First test case and the message is "+msg4);
		System.out.println("This is First test case and the message is "+msg5);
	}
}
