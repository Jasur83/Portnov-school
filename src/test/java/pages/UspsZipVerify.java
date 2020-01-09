package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class UspsZipVerify extends Page {

    @FindBy(xpath = "//div[@id='zipByAddressDiv']")
    WebElement zipCode;

    public String getZip(){
        waitForVisible(zipCode);
        return zipCode.getText();
    }

}
