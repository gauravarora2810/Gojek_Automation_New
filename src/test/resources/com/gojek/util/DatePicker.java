package test.resources.com.gojek.util;

import java.util.Arrays;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;
import test.resources.com.gojek.base.*;

@Test
public class DatePicker extends TestBase {

	WebElement datePicker;
	List<WebElement> noOfColumns;
	List<String> monthList = Arrays.asList("January", "February", "March", "April", "May", "June", "July", "August",
			"September", "October", "November", "December");
	// Expected Date, Month and Year
	public int expMonth;
	public int expYear;
	public String expDate = null;
	// Calendar Month and Year
	String calMonth = null;
	String calYear = null;
	boolean dateNotFound;

	public void pickExpDate(String CalendarName) throws InterruptedException {

		// Click on date text box to open date picker popup.
		driver.findElement(By.xpath("//input[@name='"+CalendarName+"']")).click();
		dateNotFound = true;

		// This loop will be executed continuously till dateNotFound Is true.
		while (dateNotFound) {
			// Retrieve current selected month name from date picker popup.
			calMonth = driver.findElement(By.className("ui-datepicker-month")).getText();

			// Retrieve current selected year name from date picker popup.

			WebElement currentYear = driver.findElement(By.className("ui-datepicker-year"));
			Select select_dropdown = new Select(currentYear);

			List<WebElement> options = select_dropdown.getOptions();
			for (WebElement option : options) {
				if (option.isSelected()) {
					calYear = option.getText();
				}
			}

			// If current selected month and year are same as expected month and
			// year then go Inside this condition.
			if (monthList.indexOf(calMonth) + 1 == expMonth && (expYear == Integer.parseInt(calYear))) {
				// Call selectDate function with date to select and set
				// dateNotFound flag to false.
				selectDate(expDate);
				dateNotFound = false;
			}
			// If current selected month and year are less than expected month
			// and year then go Inside this condition.
			else if (monthList.indexOf(calMonth) + 1 < expMonth && (expYear == Integer.parseInt(calYear))
					|| expYear > Integer.parseInt(calYear)) {
				// Click on next button of date picker.
				driver.findElement(By.xpath(".//*[@id='ui-datepicker-div']/div/a[2]/span")).click();
			}
			// If current selected month and year are greater than expected
			// month and year then go Inside this condition.
			else if (monthList.indexOf(calMonth) + 1 > expMonth && (expYear == Integer.parseInt(calYear))
					|| expYear < Integer.parseInt(calYear)) {
				// Click on previous button of date picker.
				driver.findElement(By.xpath(".//*[@id='ui-datepicker-div']/div/a[1]/span")).click();
			}
		}
		Thread.sleep(500);
	}

	public void selectDate(String date) {
		datePicker = driver.findElement(By.id("ui-datepicker-div"));
		noOfColumns = datePicker.findElements(By.tagName("td"));

		// Loop will rotate till expected date not found.
		for (WebElement cell : noOfColumns) {
			// Select the date from date picker when condition match.
			if (cell.getText().equals(date)) {
				cell.findElement(By.linkText(date)).click();
				break;
			}
		}
	}
	
	
}