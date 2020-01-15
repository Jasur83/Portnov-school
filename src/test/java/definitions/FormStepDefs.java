package definitions;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import pages.*;

import java.text.SimpleDateFormat;
import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;
import static support.TestContext.getData;

public class FormStepDefs {
    @Given("I open sample page")
    public void iOpenSamplePage() {

        new SampleForm().open();

    }

    @When("I fill out all page object fields")
    public void iFillOutAllPageObjectFields() {

        SampleForm form = new SampleForm();
        form.fillUsername(getData("username"));
        form.fillEmail(getData("email"));
        form.fillPassword(getData("password"));
        form.fillConfirmPassword(getData("confirmPassword"));
        form.fillPhoneNumber(getData("phoneNumber"));
        form.selectOriginOfCountry(getData("countryOfOrigin"));
        form.fillName(getData("firstName"), "", getData("lastName"));
        form.pickGender(getData("gender"));
        form.fillAddress(getData("address"));
        form.clickAllovedToContact();
        form.clickPrivacyPolicy();
        form.acceptThirdPartyAgreement();
        form.fillContactPersonName(getData("contactPersonName"));
        form.fillContactPersonPhone(getData("contactPersonPhone"));

    }

    @And("I submit page object form")
    public void iSubmitPageObjectForm() {

        new SampleForm().clickSubmitButton();
    }

    @Then("I verify all page object fields")
    public void iVerifyAllPageObjectFields() {

         SampleVerify verifyPage = new SampleVerify();
        String result = verifyPage.getResult();
        assertThat(result).contains(getData("username"));
        assertThat(result).contains(getData("email"));
        assertThat(result).contains(getData("firstName"));
        assertThat(result).contains(getData("lastName"));
        assertThat(result).contains(getData("savedPassword"));
        assertThat(result).contains(getData("address"));
        assertThat(result).contains(getData("contactPersonName"));
        assertThat(result).contains(getData("contactPersonPhone"));

        assertThat(verifyPage.getContact()).contains("true");
        assertThat(verifyPage.getPolicy()).contains("true");
        assertThat(verifyPage.getPassword()).contains(getData("savedPassword"));
    }

    @Given("I go to usps page object")
    public void iGoToUspsPageObject() {

        new UspsHeader().open();
    }

    @When("I go to Lookup ZIP page object by address")
    public void iGoToLookupZIPPageObjectByAddress() {

        UspsHeader clickZip = new UspsHeader();
        clickZip.mouseOverMail();
        clickZip.clickLookUpZipCode();
        new LookupByZip().clickFindAddress();
    }

    @And("I fill out {string} street, {string} city, {string} state page object fields")
    public void iFillOutStreetCityStatePageObjectFields(String street, String city, String state) {
        ByAddressForm page = new ByAddressForm();
        page.fillStreetAddress(street);
        page.fillCity(city);
        page.fillState(state);
        page.clickFindButton();
    }

    @Then("I validate {string} zip code exists in the result page object")
    public void iValidateZipCodeExistsInTheResultPageObject(String zip) throws InterruptedException {

        UspsZipVerify zipCode = new UspsZipVerify();
        assertThat(zipCode.getZip()).contains(zip);
    }

    @When("I go to Calculate Price page object")
    public void iGoToCalculatePricePageObject() {

        new UspsHeader().clickCalculatePrice();
    }

    @And("I select {string} with {string} shape and I define {string} quantity page object")
    public void iSelectWithShapeAndIDefineQuantityPageObject(String selectText, String shape, String quantity) {

        UspsPriceCalculate uspsPriceCalculate = new UspsPriceCalculate();
        uspsPriceCalculate.selectCountry(getData(selectText));
        uspsPriceCalculate.choosePostcard(shape);
        new UspsPostcardCalculate().fillQuantity(quantity);
    }

    @Then("I calculate the price and validate cost is {string} page object")
    public void iCalculateThePriceAndValidateCostIsPageObject(String cost) {

        UspsPostcardCalculate calculate = new UspsPostcardCalculate();
        calculate.clickCalculate();
        assertThat(calculate.getCost()).isEqualTo(cost);
    }

    @Then("I verify confirm password field is disabled")
    public void iVerifyConfirmPasswordFieldIsDisabled() {

        assertThat(new SampleForm().isConfirmPasswordDisabled()).isTrue();
    }

    @And("I verify password field requires {int} characters")
    public void iVerifyPasswordFieldRequiresCharacters(int minSize){
        SampleForm form = new SampleForm();
//       instead form.fillPassword("1234");
        for(int i = 0; i < minSize - 1; i++){
            form.fillPassword("A");
        }
        form.clickSubmitButton();
        assertThat(new SampleForm().isPasswordErrorDisplayed()).isTrue();

        form.fillPassword("A");
        assertThat(new SampleForm().isPasswordErrorDisplayed()).isFalse();
    }


    @And("I verify that password field is masked")
    public void iVerifyThatPasswordFieldIsMasked() {

        assertThat(new SampleForm().getPasswordType()).isEqualTo("password");
    }

    @Then("I verify confirm password field is enabled when password field")
    public void iVerifyConfirmPasswordFieldIsEnabledWhenPasswordField() {
        SampleForm form = new SampleForm();
        form.fillPassword("12345");
        assertThat(form.isConfirmPasswordEnabled()).isTrue();

    }

    @And("I verify email does not accept invalid email format")
    public void iVerifyEmailDoesNotAcceptInvalidEmailFormat() {

        SampleForm email = new SampleForm();
        email.fillEmail("wrongFormat");
        email.clickSubmitButton();
        assertThat(email.isEmailErrorDisplayed()).isTrue();
    }

    @Then("I verify application date is today's date")
    public void iVerifyApplicationDateIsTodaySDate() {
        String applicationDate = new SampleForm().getCurrentDate();
        Date today = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        String todayString = dateFormat.format(today);
         assertThat(applicationDate).isEqualTo(todayString);
    }

    @And("I verify email does not accept {string} format")
    public void iVerifyEmailDoesNotAcceptFormat(String invalidEmail) {

        SampleForm email = new SampleForm();
        email.fillEmail(invalidEmail);
        email.clickSubmitButton();
        assertThat(email.isEmailErrorDisplayed()).isTrue();
    }
}
