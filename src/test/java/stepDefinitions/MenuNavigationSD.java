package stepDefinitions;
import Pages.ClsMenuNavigation;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;


import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;



public class MenuNavigationSD {
	private static boolean initialized = false;
    private static WebDriver driver; 	
	ClsMenuNavigation navigationobj=new ClsMenuNavigation();
		
	@Given("^the User navigates to given website$")
	public void the_User_navigates_to_website() {
		//Singletone Webdriver Object
		if (!initialized){
            initialized = true;
		System.setProperty("webdriver.chrome.driver", "E:\\Shital\\My Learning\\Selenium\\Workspaces\\CucumberBDDTest\\Aviva_Navigation\\src\\test\\resources\\libs\\chromedriver.exe");		
		ChromeOptions chromeoptions = new ChromeOptions();
		//Language setting for Chrome Browser
		chromeoptions.addArguments("–lang= pl");
		driver =new ChromeDriver();	
		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(50,TimeUnit.SECONDS);
		driver.get("https://www.aviva.pl/");
		}
	}

	@When("^the user clicks on \"([^\"]*)\" menu$")
	public void the_user_clicks_on_menu(String string) {	   
		//Explicit wait for menu visibility
		try
		{
		WebDriverWait wait = new WebDriverWait(driver, 40);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText(string))).click();
		}
		catch(Exception e)
		{
			System.out.println("Menu "+string+ " is not displayed on website");
		}
	}

	@Then("^Verify all submenues appears for menu \"([^\"]*)\" test$")
	public void verify_all_submenues_appears_for_menu_test(String string) throws IOException {	   
		  List<String> headerslst=navigationobj.GetSubmenuHeaders(string);
		  if(headerslst.size()>0)
		  System.out.println("\nMenu: "+string);
	  	   for(String header : headerslst)
	  	   {
		   List<WebElement> Submenueslist =driver.findElements(By.xpath("//a[contains(text(),header)]/parent::h4/following-sibling::ul[@class='a-list-plain m-primary-navigation-sub-section__link-list']//span"));
		   List<String> ExpectedSubmenueslist=navigationobj.GetSubmenu(header);
		   //Stream and map is used to get the text of each submenu Webelement
		   List<String> ActualSubmenueslist=Submenueslist.stream().map(WebElement::getText).collect(Collectors.toList());
		   //Here Stream and filter is used for comparision of Actual Submenu List and Expected Sub Menu List 
		   List<String> result = ExpectedSubmenueslist.stream()
                   .filter(not(new HashSet<>(ActualSubmenueslist)::contains))
                   .collect(Collectors.toList());
		   if(result.size()>0)	
		   {
		   System.out.println("Following Submenues are not Appearing under SubmenuHeader: "+header); 
            result.stream().forEach(System.out::println);
		   }
		   else
			   System.out.println("All Submenues are appearing for SubmenuHeader: " +header);
	  	   }
	  	   if(string.equalsIgnoreCase("Kontakt i pomoc"))	  	   
	  	   driver.close();
	}
	private static <T> Predicate<T> not(Predicate<T> predicate) {
	    return predicate.negate();
	}

	}

