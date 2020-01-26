package Pages;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

public class ClsMenuNavigation {
	
	public static Properties prop=new Properties();   
	
	//Function returning list of Submenu headers for given Menu from Menuesandsubmenues.properties file
	public List<String> GetSubmenuHeaders(String Menuname) throws IOException
	{
		FileInputStream input = new FileInputStream(new File("E:\\\\Shital\\\\My Learning\\\\Selenium\\\\Workspaces\\\\CucumberBDDTest\\\\Aviva_Navigation\\\\configs\\\\Menuesandsubmenues.properties"));
		prop.load(new InputStreamReader(input, Charset.forName("UTF-8")));
		List<String> headerslst=new ArrayList<String>();
		try
		{
		String headersstr=prop.getProperty(Menuname); 
		headerslst=Arrays.asList(headersstr.split(":"));
		}
		catch (Exception e)
		{
			//This is for negative case if menu not available
			System.out.println("Menu:"+Menuname+"  not available in property file"); 
			
		}
		finally
		{
			if(headerslst==null)
				headerslst.clear();
		}
		return headerslst;		
	}
	
	//Function returning list of Submenues for given SubmenuHeader from Menuesandsubmenues.properties file
	public List<String> GetSubmenu(String SubmenuHeader)
	{
		List<String> Submenueslst=new ArrayList<String>();
		try
		{
		String Submenustr=prop.getProperty(SubmenuHeader);
		Submenueslst=Arrays.asList(Submenustr.split(":"));
		}
		catch (Exception e)
		{
			System.out.println("SubmenusHeader" +SubmenuHeader+ " not available in property file"); 
			
		}
		finally
		{
			if(Submenueslst==null)
				Submenueslst.clear();;
		}
		return Submenueslst;		
	}
	
	}

