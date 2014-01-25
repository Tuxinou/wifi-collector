package info.wifi;

import java.io.*;
import java.util.ArrayList;

/**
 * @author Pat and Dan
 */

public class DataFiles 
{
	/**
	 * Returns an ArrayList of the contents of the file
	 * @param filePath The file path of the file you want to read
	 * @return The contents of the given file in ArrayList form
	 */
	public static ArrayList<String> getFile(String filePath)
	{
		ArrayList<String> fileContents = new ArrayList<String>();
		
		try
		{	
			FileInputStream fis = new FileInputStream(filePath);
			BufferedReader br = new BufferedReader(new InputStreamReader(fis));
			
			String str;
			
			while((str = br.readLine()) != null)
			{
				fileContents.add(str);
			}
			
			fis.close();
		} 
		catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		
		return fileContents;
	}
}
