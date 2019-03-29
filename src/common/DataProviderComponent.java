package common;

import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.DataProvider;

import generics.Excel;

public class DataProviderComponent implements AutomationConstants{
	
	@DataProvider(name = "userData_SE")
	public static Object[][] userData_SE(){
		
		List<String> obj = userData(AutomationConstants.INPUT_PATH,"NewEmail");
		int size = obj.size();
		int j = 0;
		Object [][] data = new Object[size][10];
		for(String s : obj) {
			
			if((s.split(";")[0]).toString().equals("SE")) {
				
				data[j][0] = s.split(";")[1];
				data[j][1] = s.split(";")[2];
				data[j][2] = s.split(";")[3];
				data[j][3] = s.split(";")[4];
				data[j][4] = s.split(";")[5];
				data[j][5] = s.split(";")[6];
				data[j][6] = s.split(";")[7];
				data[j][7] = s.split(";")[8];
				data[j][8] = s.split(";")[9];
				data[j][9] = s.split(";")[10];
				j++;
			}
		}
		
		return data;
	}
	
	
	private static List<String> userData(String path,String sheet){
		
		int count = Excel.getRowCount(path, sheet);
		System.out.println(count);
		List <String> listData = new ArrayList<String>();
		for(int i = 1; i<=count;i++) {
			
			String countryCode = Excel.getCellValue(path, sheet, i, 0);
			String subscription = Excel.getCellValue(path, sheet, i, 1);
			String email = Excel.getCellValue(path, sheet, i, 2);
			String password = Excel.getCellValue(path, sheet, i, 3);
			String gateway = Excel.getCellValue(path, sheet, i, 4);
			String cardnumber = Excel.getCellValue(path, sheet, i, 5);
			String cvc = Excel.getCellValue(path, sheet, i, 6);
			String month = Excel.getCellValue(path, sheet, i, 7);
			String year = Excel.getCellValue(path, sheet, i, 8);
			String bankname = Excel.getCellValue(path, sheet, i, 9);
			String personnum = Excel.getCellValue(path, sheet, i, 10);
			
			listData.add(countryCode+";"+subscription+";"+email+";"+password+";"+gateway+";"+cardnumber+";"+cvc+";"+month+";"+year+";"+bankname+";"+personnum);
			}
		return listData;
	}
	
}

