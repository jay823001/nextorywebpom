package common;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import generics.Excel;

public class RandomEmail {
	
	public static String [] subs_SE = {"Silver","Guld","Familj2","Familj3","Familj4","Silver","Guld"};
	
	public static void email() throws EncryptedDocumentException, InvalidFormatException, IOException 
	{
		SimpleDateFormat sd = new SimpleDateFormat("ddMMMyy_HHmmss");
		
		for (int i = 1; i <= 7; i++)
		{
			Excel.setExcelData(AutomationConstants.INPUT_PATH, "NewEmail", i, 0, subs_SE[i-1]);
			Excel.setExcelData(AutomationConstants.INPUT_PATH, "NewEmail", i, 1, "test" + sd.format(new Date()) + "@frescano.se");
			Excel.setExcelData(AutomationConstants.INPUT_PATH, "NewEmail", i, 2, "password");
			if(i%2 == 0)
				Excel.setExcelData(AutomationConstants.INPUT_PATH, "NewEmail", i, 3, "ADYEN");
			else
				Excel.setExcelData(AutomationConstants.INPUT_PATH, "NewEmail", i, 3, "TRUSTLY");
			Excel.setExcelData(AutomationConstants.INPUT_PATH, "NewEmail", i, 4, "5555444433331111");
			Excel.setExcelData(AutomationConstants.INPUT_PATH, "NewEmail", i, 5, "737");
			Excel.setExcelData(AutomationConstants.INPUT_PATH, "NewEmail", i, 6, "08");
			Excel.setExcelData(AutomationConstants.INPUT_PATH, "NewEmail", i, 7, "2018");
			Excel.setExcelData(AutomationConstants.INPUT_PATH, "NewEmail", i, 8, "Danske");
			Excel.setExcelData(AutomationConstants.INPUT_PATH, "NewEmail", i, 9, "1111111111");

		}
	}
	/*public static void main(String[] args) throws EncryptedDocumentException, InvalidFormatException, IOException
	{	
		for (int i = 1; i <= 7; i++)
		{
			Excel.setExcelData(AutomationConstants.INPUT_PATH, "NewEmail", i, 0, subs_SE[i-1]);
			Excel.setExcelData(AutomationConstants.INPUT_PATH, "NewEmail", i, 1, email());
			Excel.setExcelData(AutomationConstants.INPUT_PATH, "NewEmail", i, 2, "password");
			if(i%2 == 0)
				Excel.setExcelData(AutomationConstants.INPUT_PATH, "NewEmail", i, 3, "ADYEN");
			else
				Excel.setExcelData(AutomationConstants.INPUT_PATH, "NewEmail", i, 3, "TRUSTLY");
			Excel.setExcelData(AutomationConstants.INPUT_PATH, "NewEmail", i, 4, "5555444433331111");
			Excel.setExcelData(AutomationConstants.INPUT_PATH, "NewEmail", i, 5, "737");
			Excel.setExcelData(AutomationConstants.INPUT_PATH, "NewEmail", i, 6, "08");
			Excel.setExcelData(AutomationConstants.INPUT_PATH, "NewEmail", i, 7, "2018");
			Excel.setExcelData(AutomationConstants.INPUT_PATH, "NewEmail", i, 8, "Danske");
			Excel.setExcelData(AutomationConstants.INPUT_PATH, "NewEmail", i, 9, "1111111111");
		}
	}
*/

}
