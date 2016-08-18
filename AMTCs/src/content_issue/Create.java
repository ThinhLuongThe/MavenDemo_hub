package content_issue;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import initialize.F1;

public class Create {
	WebDriver driver;
	WebElement Ob1;
	//	String BaseUrl = "http://qa_thinh_publication_name_2b-bd.audiencemedia.com";
	//	String Valid_User = "admin"; String Valid_Pass = "devteam";
	//	String Email = "thinh.luong+001@audiencemedia.com";
	int Fday_number = 1;

	//	Random ranNum = new Random();
	//	long rand = (long)(100*ranNum.nextDouble());
	//	String Issue_name = "Auto_Issue 001";
	//	String Issue_description = "Issue Description "+rand;
	//	String Section_name = "Section "+rand;
	//	String Section_description = "Section Description " +rand;

	@Test (groups = "login")
	public void Login_Valid () {
		Ob1 = driver.findElement(By.id("id1"));	  Ob1.sendKeys(F1.Valid_User);
		Ob1 = driver.findElement(By.id("id2"));	  Ob1.sendKeys(F1.Valid_Pass);
		Ob1.submit();  }

	@Test (enabled=false, priority=0)
	private void Validation() {
		//Check if Validation works or not
		driver.findElement(By.linkText("New issue")).click();
		driver.switchTo().frame("fancybox-frame");
		driver.findElement(By.name("NewIssueButton")).click();
		Ob1=driver.findElement(By.cssSelector("[class='textbox w40 fl gr required error']"));
		Assert.assertTrue(Ob1.isDisplayed());		System.out.println("Issue Name is validated");
		Ob1=driver.findElement(By.cssSelector("[class='datepicker w20 textbox fl required hasDatepicker error']"));
		Assert.assertTrue(Ob1.isDisplayed());		System.out.println("Cover Date is validated");
		Ob1=driver.findElement(By.cssSelector("[class='datepicker w20 textbox fr gr required hasDatepicker error']"));
		Assert.assertTrue(Ob1.isDisplayed());		System.out.println("Deadline is validated\n");
		driver.navigate().refresh();
	}

	@Test (enabled=false, priority=1)
	public void Create_Issue(){
		//INITIALIZAION
		Calendar cal = Calendar.getInstance();
		String Desired_coverdate = "3";
		String Cday = new SimpleDateFormat("d").format(cal.getTime()).toString();
		@SuppressWarnings("unused")
		String Cmon = new SimpleDateFormat("MMM").format(cal.getTime()).toString();
		@SuppressWarnings("unused")
		String Cyear = new SimpleDateFormat("yyyy").format(cal.getTime()).toString();
		cal.add(Calendar.DAY_OF_MONTH, Fday_number);
		//	  String mon_format = new Formatter().format("%tb", cal).toString();
		String Fday = new SimpleDateFormat("d").format(cal.getTime()).toString();
		String Fmon = new SimpleDateFormat("MMM").format(cal.getTime()).toString();
		String Fyear = new SimpleDateFormat("yyyy").format(cal.getTime()).toString();


		driver.findElement(By.linkText("New issue")).click();
		driver.switchTo().frame("fancybox-frame");
		Ob1=driver.findElement(By.cssSelector("[class='textbox w40 fl gr required']"));
		//	  Input Issue_name
		//	  Ob1.sendKeys(Issue_name);
		//Choose CoverDate
		Ob1=driver.findElement(By.cssSelector("[class='datepicker w20 textbox fl required hasDatepicker']"));
		Ob1.click();
		if (Desired_coverdate.equals(Cday)) {
			driver.findElement(By.cssSelector("[class*='ui-datepicker-today']>a")).click();
			System.out.println("Selected today: " + Cday);
		} else {
			//Select Month
			Select oselect = new Select(driver.findElement(By.cssSelector("[class='ui-datepicker-month']")));
			/*	  List<WebElement> months = oselect.getOptions();
	  for (WebElement submonth : months) {
		if (submonth.getText().equals(Fmon)) {
			oselect.selectByVisibleText(Fmon);
			System.out.println("Selected Month: " + Fmon);
			break;
		}	}*/
			oselect.selectByVisibleText(Fmon);
			System.out.println("Selected Month: " + Fmon);

			//Select Year
			Select oselect2 = new Select(driver.findElement(By.cssSelector("[class='ui-datepicker-year']")));
			/*List<WebElement> years = oselect2.getOptions();
	  for (WebElement subyear : years) {
		if (subyear.getText().equals(Fyear)) {
			oselect2.selectByVisibleText(Fyear);
			System.out.println("Selected Year: " + Fyear);
			break;
		}	}*/
			oselect2.selectByVisibleText(Fyear);
			System.out.println("Selected Year: " + Fyear);

			//Select Day
			List<WebElement> cells = driver.findElements(By.cssSelector("a[class='ui-state-default']"));
			for (WebElement subcell : cells) {
				if (subcell.getText().equals(Fday)) {
					subcell.click();
					System.out.println("Selected Day: " + Fday);
					break;
				}	}
		}

		//Then choose Deadline
		driver.findElement(By.cssSelector("[class='datepicker w20 textbox fr gr required hasDatepicker']")).click(); 
		//Select Day
		driver.findElement(By.cssSelector("td[class*='ui-datepicker-today']")).click();
		System.out.println("Selected Deadline successfully");

	}

	@Test (enabled=false,priority=2)
	public void Other_TC() {
		//Check Description
		Ob1 = driver.findElement(By.cssSelector("textarea[name='IssueDescription']"));
		Ob1.clear(); Ob1.sendKeys(F1.Issue_description);

		//Check Channels
		List<WebElement> ListOb1 = driver.findElements(By.cssSelector("div[class='cbox']>input"));
		for (WebElement subListOb1 : ListOb1) {
			if (!subListOb1.isSelected()) {
				subListOb1.click();	
			}	}

		//Add sections
		Ob1 = driver.findElement(By.id("add_new_section")); Ob1.click();
		Ob1 = driver.findElement(By.id("add_section")); Ob1.click();
		Assert.assertTrue(driver.findElement(By.cssSelector("[class='textbox w45 fl gr error']")).isDisplayed());
		driver.findElement(By.id("back_section")).click();
		driver.findElement(By.id("add_new_section")).click();
		Ob1 = driver.findElement(By.cssSelector("[class='textbox w45 fl gr error'][name='NewSection']"));
		Ob1.sendKeys(F1.Section_name);
		Ob1 = driver.findElement(By.id("section_description"));
		Ob1.sendKeys(F1.Section_description);
		driver.findElement(By.id("add_section")).click();

		//Check Issue_Pricing
		driver.findElement(By.cssSelector("div[class='fl w100']>input[type='radio'][value='free']")).click();
		Assert.assertTrue(driver.findElement(By.tagName("select")).isDisplayed());
		driver.findElement(By.cssSelector("div[class='fl w100']>input[type='radio'][value='custom_new']")).click();
		Assert.assertTrue(driver.findElement(By.id("custom_new_product_id")).isDisplayed());


		//Submit form
		driver.findElement(By.name("NewIssueButton")).click();
		driver.switchTo().defaultContent();

		//Assert Issue's creation
		Assert.assertTrue(driver.findElement(By.linkText(F1.Issue_name)).isDisplayed());
		System.out.println(F1.Issue_name + " is created succesfully");

	}

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.get(F1.BaseUrl + "/admin/issues/home");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	@AfterClass
	public void afterClass() {
		System.out.println("\n---------------DONE---------------\n");
		//	  driver.quit();
	}
}