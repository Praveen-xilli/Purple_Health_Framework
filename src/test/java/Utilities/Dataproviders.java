package Utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class Dataproviders {
	@DataProvider(name ="Logindata")
	public Object[][] getdata() throws IOException
	{
		String path = ".//UserInputdata//xl_inputdata.xlsx";
		Xl_Utility utility = new Xl_Utility(path);
		
		int totalrow = utility.getrowcount("Hospital");
		Object[][] logindata = new Object[totalrow][];
		for(int i=0;i<totalrow;i++)
		{
			int totalcol = utility.getcellcount("Hospital", i);
			Object[] rowdata= new Object[totalcol];
			for(int j=0;j<totalcol;j++)
			{
				rowdata[j] = utility.getCelldata("Hospital", i, j);
			}
			logindata[i]=rowdata;
		}
		return logindata;
	}

}
