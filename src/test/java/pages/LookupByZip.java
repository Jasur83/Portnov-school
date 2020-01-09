package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LookupByZip extends Page {

    @FindBy(xpath = "//a[contains(text(),'Find by Address')]")
    private WebElement findAddress;

    public void clickFindAddress(){
        click(findAddress);
    }
}
