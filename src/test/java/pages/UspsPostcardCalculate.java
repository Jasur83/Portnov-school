package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class UspsPostcardCalculate extends Page{

    @FindBy(xpath = "//input[@id='quantity-0']")
    WebElement quantity;
    @FindBy(xpath = "//input[@value='Calculate']")
    WebElement calculateButton;
    @FindBy(xpath = "//div[@id='total']")
    WebElement totalCost;

    public void fillQuantity(String text) {
        sendKeys(quantity, text);
    }
    public void clickCalculate(){
        click(calculateButton);
    }

    public String getCost(){
        return totalCost.getText();
    }


}
