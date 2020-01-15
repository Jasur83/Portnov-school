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
    @FindBy (xpath = "//b[@id='currentDate']")
    private WebElement currentDate;
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
    @FindBy (xpath = "//label[@id='password-error']")
    private WebElement passwordError;
    @FindBy (xpath = "//label[@id='email-error']")
    private WebElement emailError;
    @FindBy (xpath = "//input[@id='contactPersonName']")
    private WebElement contactPersonName;
    @FindBy (xpath = "//input[@id='contactPersonPhone']")
    private WebElement contactPersonPhone;
    @FindBy (xpath = "//iframe[@name='additionalInfo']")
    private WebElement additionalInfoIframe;
    @FindBy (xpath = "//button[@id='formSubmit']")
    private WebElement submitButton;


    public void fillUsername(String text){
        sendKeys(username, text);
    }

    public void fillContactPersonName(String name){
        getDriver().switchTo().frame(additionalInfoIframe);
        sendKeys(contactPersonName, name);
        getDriver().switchTo().defaultContent();
    }

    public void fillContactPersonPhone(String phone){
        getDriver().switchTo().frame(additionalInfoIframe);
        sendKeys(contactPersonPhone, phone);
        getDriver().switchTo().defaultContent();
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

    public boolean isConfirmPasswordDisabled(){
        return !confirmPassword.isEnabled();
    }

    public boolean isConfirmPasswordEnabled(){
        return confirmPassword.isEnabled();
    }

    public boolean isPasswordErrorDisplayed(){
        return passwordError.isDisplayed();
    }

    public boolean isEmailErrorDisplayed(){
        return emailError.isDisplayed();
    }

    public String getPasswordType(){

        return password.getAttribute("type");
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

    public String getCurrentDate(){
        return currentDate.getText();
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
