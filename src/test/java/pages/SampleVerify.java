package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SampleVerify extends Page {

    @FindBy(xpath = "//div[@id='samplePageResult']")
    private WebElement result;
    @FindBy(xpath = "//b[@name='password']")
    private WebElement passwordResult;
    @FindBy(xpath = "//b[@name='allowedToContact']")
    private WebElement allowedToContact;
    @FindBy(xpath = "//b[@name='agreedToPrivacyPolicy']")
    private WebElement agreedToPrivacyPolicy;

    public String getResult(){
        waitForVisible(result);
        return result.getText();
    }

    public String getContact(){
        return allowedToContact.getText();
    }

    public String getPolicy(){
        return agreedToPrivacyPolicy.getText();
    }

    public String getPassword(){
        return passwordResult.getText();
    }
}
