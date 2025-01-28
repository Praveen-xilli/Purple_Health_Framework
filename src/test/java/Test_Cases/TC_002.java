package Test_Cases;

import org.testng.annotations.Test;

import Utilities.Dataproviders;

public class TC_002 {
	@Test(dataProvider = "Logindata",dataProviderClass = Dataproviders.class,groups = {"Master","Sanity"})
	public void tabledata(String Msg1,String Msg2,String Msg3,String Msg4,String Msg5)
	{
		System.out.println("This is second test case and the message is "+Msg1);
		System.out.println("This is second test case and the message is "+Msg2);
		System.out.println("This is second test case and the message is "+Msg3);
		System.out.println("This is second test case and the message is "+Msg4);
		System.out.println("This is second test case and the message is "+Msg5);
	}

}
