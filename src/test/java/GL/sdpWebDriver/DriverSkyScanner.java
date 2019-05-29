package GL.sdpWebDriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;

public class DriverSkyScanner {

	public static void main(String[] args)  throws InterruptedException {
		// TODO Auto-generated method stub
		int implicityWaitingTime=5;
		String baseURL="https://www.google.com/";
		String skyScanner= "https://www.skyscanner.pl/";
		System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
		ChromeOptions chromeOptions = new ChromeOptions();
		chromeOptions.setHeadless(false);
		WebDriver driver=new ChromeDriver(chromeOptions);
		driver.manage().timeouts().implicitlyWait(implicityWaitingTime, TimeUnit.SECONDS);
		
		driver.get(skyScanner);
		
		// element Z po ID
		WebElement z=driver.findElement(By.xpath("//input[@id='fsc-origin-search']"));
		z.click();
		
		Thread.sleep(1000); //żeby zobaczyć czy się kliknęło 
		z.sendKeys("Berlin");
		
		// element do po parent class-> tagname
		WebElement doWE=driver.findElement(By.xpath("//div[@class='SingleDestControls_destination-wrapper__FcyJu SingleDestControls_destination-wrapper-oneline__3brcO']//div[@class='bpk-autosuggest__container']/child::input"));
		doWE.sendKeys("Warszawa");
		
		// element wylot po ancestor class -> first child-> tag name
		WebElement wylot=driver.findElement(By.xpath("//div[@class='DateRangeSelector_DateRangeSelector__1H106']/child::div[1]/child::button"));
		wylot.click();
		
		WebElement wybranaDataWylotu= driver.findElement(By.xpath("//tr[3]//td[3]//button[1]"));
		wybranaDataWylotu.click();
		Thread.sleep(1000);
		
		//element powrót po ancestor class ->last child-> tag name
		WebElement powrot=driver.findElement(By.xpath("//div[@class='DateRangeSelector_DateRangeSelector__1H106']/child::div[last()]/child::button"));
		powrot.click();
		
		WebElement wybranaDataPowrotu= driver.findElement(By.xpath("//tr[3]//td[5]//button[1]"));
		wybranaDataPowrotu.click();
		
		//element klasa podrozy i podrozni po span contains text"dorosły" -> parent
		WebElement klasa=driver.findElement(By.xpath("//span[contains(text(),'dorosły')]/parent::button"));
		klasa.click();
		
		WebElement dodajOsobe= driver.findElement(By.xpath("//div/button[2]"));
		dodajOsobe.click();
		WebElement gotowe=driver.findElement(By.cssSelector("button.BpkLink_bpk-link__2e_PE"));
		gotowe.click();
		
		//wszystkie checkboxy
		List<WebElement> checkBoxList=driver.findElements(By.xpath("//input[@type='checkbox']"));
		//zaznaczenie wszystkich checkboxów
		for(WebElement checkBox:checkBoxList){
			if(!checkBox.isSelected())
				checkBox.click();
		}
		
		// szukaj po parent->attribute of element
		WebElement szukaj=driver.findElement(By.xpath("//div[@class='App_flights-sc-wrapper__2iz2o']/form/div[3]/child::*[@type='submit']"));
		
		// z: Dodaj lotniska w pobliżu - xpath skopiowany z ChromePath
		WebElement zDodajLotniskaWPoblizuCheckBox=driver.findElement(By.xpath("//input[@name='originFlexible']"));
		
		//odznaczenie checkboxa Z: Dodaj Lotniska w Pobliżu
		if(zDodajLotniskaWPoblizuCheckBox.isSelected()) {
			Actions actions = new Actions(driver);
			actions.moveToElement(zDodajLotniskaWPoblizuCheckBox).click().build().perform();
		}	
		// Do: Dodaj lotniska w pobliżu - xpath skopiowany z ChromePath
		WebElement doDodajLotniskaWPoblizuCheckBox=driver.findElement(By.xpath("//input[@name='destinationFlexible']"));
		
		//odznaczenie checkboxa Do: Dodaj Lotniska w Pobliżu
		if(doDodajLotniskaWPoblizuCheckBox.isSelected()){
			Actions actions = new Actions(driver);
			actions.moveToElement(doDodajLotniskaWPoblizuCheckBox).click().build().perform();
		}
		
		//naciśnięcie przycisku szukaj
		
		szukaj.click();
		
		Thread.sleep(3000);// żeby zobaczyć czy naciśnięty
		driver.close();
		driver.quit();
		
	}

}
