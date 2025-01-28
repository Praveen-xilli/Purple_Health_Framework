package Test_Cases;

import java.io.IOException;

import org.testng.annotations.Test;

import Utilities.Xl_Utility;

public class TC_003 {
	//Xl_Utility xl = new Xl_Utility();
	@Test(groups = {"Datadriven"})
	public void entrytest() throws IOException
	{
		int doctorname = new Xl_Utility(".//UserInputdata//xl_inputdata.xlsx").getcellcount("Hospitalsheet", 0);
	    //.readDataFromExcel("Heart","Doctors", "Hospitalsheet");
		System.out.println("This is third test case and the message is "+doctorname);
		System.out.println("This is third test case and the message is ");
		System.out.println("This is third test case and the message is ");
		System.out.println("This is third test case and the message is ");
		System.out.println("This is third test case and the message is ");
	}

}
