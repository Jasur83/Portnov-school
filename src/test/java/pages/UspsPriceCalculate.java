package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

public class UspsPriceCalculate extends Page {

    @FindBy(xpath = "//select[@id='CountryID']")
    WebElement destinationCountry;

    public void selectCountry(String value){
        new Select(destinationCountry).selectByValue(value);
    }

    public void choosePostcard(String value){
        click(getByXpath("//input[@value='" + value + "']"));
    }

}
