package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import static support.TestContext.getDriver;

public class SampleForm extends Page{
    public SampleForm() {
        setUrl("http://skryabin.com/webdriver/html//sample.html");
    }

    @FindBy (xpath = "//input[@name='username']")
    private WebElement username;
    @FindBy (xpath = "//input[@name='email']")
    private WebElement email;
    @FindBy (xpath = "//input[@id='name']")
    private WebElement name;
    @FindBy (xpath = "//input[@id='password']")
    private WebElement password;
    @FindBy (xpath = "//input[@id='confirmPassword']")
    private WebElement confirmPassword;
    @FindBy (xpath = "//input[@name='phone']")
    private WebElement phoneNumber;
    @FindBy (xpath = "//select[@name='countryOfOrigin']")
    private WebElement countryOfOrigin;
    @FindBy (xpath = "//input[@name='agreedToPrivacyPolicy']")
    private WebElement privacyPolicy;
    @FindBy (xpath = "//input[@name='allowedToContact']")
    private WebElement allowedToContact;
    @FindBy (xpath = "//textarea[@id='address']")
    private WebElement address;
    @FindBy (xpath = "//button[@id='thirdPartyButton']")
    private WebElement thirdPartyButton;
    @FindBy (xpath = "//input[@id='dateOfBirth']")
    private WebElement dateOfBirth;
    @FindBy (xpath = "//button[@id='formSubmit']")
    private WebElement submitButton;


    public void fillUsername(String text){
        sendKeys(username, text);
    }

    public void fillEmail(String text){
        sendKeys(email, text);
    }

    public void acceptThirdPartyAgreement(){
        click(thirdPartyButton);
        getDriver().switchTo().alert().accept();
    }

    public void declineThirdPartyAgreement(){
        click(thirdPartyButton);
        getDriver().switchTo().alert().dismiss();
    }

    public void fillName(String firstName, String middleName, String lastName){
        click(name);
        SampleFormNameDialog dialog = new SampleFormNameDialog();
        dialog.fillFirstName(firstName);
        dialog.fillMiddleName(middleName);
        dialog.fillLastName(lastName);
        dialog.clickSaveButton();
    }

    public void fillPhoneNumber(String text){
        sendKeys(phoneNumber, text);
    }

    public void fillPassword(String text) {
        sendKeys(password, text);
    }

    public void fillConfirmPassword(String text) {
        sendKeys(confirmPassword, text);
    }

    public void selectOriginOfCountry(String value){

        new Select(countryOfOrigin).selectByValue(value);
    }

    public void pickGender(String value){
        click(getByXpath("//input[@value='" + value +"']"));
    }

    public void clickAllovedToContact(){
        click(allowedToContact);
    }

    public  void fillAddress(String address){
        sendKeys(this.address, address);
    }

    public void clickPrivacyPolicy(){
        click(privacyPolicy);
    }

    public void clickSubmitButton(){
       click(submitButton);
    }

}
