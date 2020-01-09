package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class UspsHeader extends Page {

    public UspsHeader() {
        setUrl("https://www.usps.com/");
    }

    @FindBy(xpath = "//a[@id='mail-ship-width']")
    private WebElement mailAndShip;
    @FindBy(xpath = "//a[contains(text(),'Look Up a ZIP Code')]")
    private WebElement lookUpZipCode;
    @FindBy(xpath = "//li[@class='tool-calc']//a[text()='Calculate a Price']")
    private WebElement calculatePrice;

    public void mouseOverMail(){
        moveToElement(mailAndShip);
    }

    public void clickCalculatePrice(){
        moveToElement(mailAndShip);
        click(calculatePrice);
    }

    public void clickLookUpZipCode(){
        moveToElement(mailAndShip);
        click(lookUpZipCode);
    }
}
