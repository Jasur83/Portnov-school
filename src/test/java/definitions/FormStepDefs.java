package definitions;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import pages.*;

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
}
