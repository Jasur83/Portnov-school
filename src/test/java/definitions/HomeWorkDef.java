package definitions;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.text.ParseException;

import static org.assertj.core.api.Assertions.assertThat;
import static support.TestContext.getDriver;

public class HomeWorkDef {
    private String nums;
    private String nums2;
    private String word;



    @When("I go to {string} tab")
    public void iGoToTab(String tab) throws InterruptedException {

        WebElement postalStore = getDriver().findElement(By.xpath("//*[text()='" + tab + "']"));
        if (tab.equals("Help")) {
            postalStore.click();
            Thread.sleep(5000);
        } else {
            new Actions(getDriver()).moveToElement(postalStore).perform();
        }

    }

    @And("I enter {string} into store search")
    public void iEnterIntoStoreSearch(String nums) {
        this.nums = nums;

        getDriver().findElement(By.id("global-header--search-track-store")).sendKeys(nums);
    }


    @Then("I search and validate no products found")
    public void iSearchAndValidateNoProductsFound() {

        getDriver().findElement(By.xpath("//li[4]//input[2]")).click();
        WebElement noProductResult = getDriver().findElement(By.xpath("//div[@class='page-wrapper']//p[1]"));
        String noResult = noProductResult.getText();
        System.out.println(noResult);
        assertThat(noResult).contains(nums);

    }

    @And("I type {string} into store search")
    public void iTypeIntoStoreSearch(String word) {
        this.word = word;
        getDriver().findElement(By.id("global-header--search-track-store")).sendKeys(word);

    }

    @Then("I search and validate products found")
    public void iSearchAndValidateProductsFound() {

        getDriver().findElement(By.xpath("//li[4]//input[2]")).click();
        WebElement productResult = getDriver().findElement(By.xpath("//div[@class='result-products-holder']"));
        String result = productResult.getText();
        assertThat(result).contains(word);

    }

    @When("I go to {string} section")
    public void iGoToSection(String arg0) {

        if (arg0.equals("Informed Delivery")) {
            getDriver().findElement(By.xpath("//a[@class='button--link'][text()='Informed Delivery']")).click();

        } else if (arg0.equals("Stamps and Supplies")) {
            getDriver().findElement(By.xpath("//*[@class='d-none d-lg-block d-xl-block']")).click();
        }

    }

    @And("I open Stamps")
    public void iOpenStamps() {

        getDriver().findElement(By.xpath("//li[contains(@class,'stamps-navigation')]")).click();
    }

    @And("choose category Priority Mail")
    public void chooseCategoryPriorityMail() {

        JavascriptExecutor down = (JavascriptExecutor) getDriver();
        down.executeScript("window.scrollBy(0,3000)");

        getDriver().findElement(By.xpath("//*[text()='Priority Mail (1)']")).click();
    }

    @Then("I verify {string} item found in result")
    public void iVerifyItemFoundInResult(String arg0) {

        WebElement element = getDriver().findElement(By.xpath("//*[@class='results-numerical']"));
        String result = element.getText();
        System.out.println(result);
        assertThat(result).contains(arg0);
    }

    @When("I unselect Stamps checkbox")
    public void iUnselectStampsCheckbox() {

        getDriver().findElement(By.xpath("//*[contains(text(),'Stamps (91)')]")).click();
    }

    @And("select size {string}")
    public void selectSize(String arg0) {

        JavascriptExecutor size = (JavascriptExecutor) getDriver();
        size.executeScript("window.scrollBy(0,1500)");
        getDriver().findElement(By.xpath("//*[contains(text(),'Large (11)')]")).click();
    }

    @And("I click {string} color")
    public void iClickColor(String arg0) {

        getDriver().findElement(By.xpath("//*[@style='background-color:#033366;']")).click();
    }

    @Then("I verify {string} and {string} filters")
    public void iVerifyAndFilters(String color, String size) {

        WebElement element = getDriver().findElement(By.xpath("//*[@class='breadcrumb-cartridge']"));
        String result = element.getText();
        assertThat(result).contains(color);
        assertThat(result).contains(size);
    }

    @Then("I verify {string} present in results")
    public void iVerifyPresentInResults(String string) {

        WebElement product = getDriver().findElement(By.xpath("//*[@class='result-products-holder']"));
        String mailProduct = product.getText();
        assertThat(mailProduct).contains(string);
        System.out.println(string);
    }

    @When("I perform {string} search")
    public void iPerformSearch(String arg0) {

        WebElement popup = getDriver().findElement(By.xpath("//li[@class='nav-search menuheader']"));
        WebElement string = getDriver().findElement(By.id("global-header--search-track-search"));
        WebElement click = getDriver().findElement(By.xpath("//*[@class='nav-search menuheader']//*[@value='Search']"));
        new Actions(getDriver()).moveToElement(popup).sendKeys(string, arg0).click(click).perform();
    }

    @And("I select {string} in results")
    public void iSelectInResults(String arg0) {
        if (arg0.contains("Priority Mail")) {
            getDriver().findElement(By.xpath("//*[text()='Priority Mail | USPS']")).click();
        } else {
            getDriver().findElement(By.xpath("//*[contains(text(),'Passport Application')]")).click();
        }
    }

    @And("I click {string} button")
    public void iClickButton(String find) {

        if (find.contains("Ship")) {
            getDriver().findElement(By.xpath("//a[@class='button--primary']")).click();
            for (String window : getDriver().getWindowHandles()) {
                getDriver().switchTo().window(window);
            }
        } else {
            getDriver().findElement(By.xpath("//a[@class='button--primary'][contains(text(),'F')]")).click();
        }
    }

    @And("I select {string} zip code within {string} and search")
    public void iSelectZipCodeWithinAndSearch(String arg0, String arg1) throws InterruptedException {

        getDriver().findElement(By.id("city-state-input")).sendKeys(arg0);
        getDriver().findElement(By.id("within-select")).click();

//      Cannot resolve this part
//     getDriver().findElement(By.xpath("//a[contains(text(),' " + arg1 + " ')]")).click();
//        or
//        WebElement select = getDriver().findElement(By.id("'within-select"));
//        new Select(select).selectByValue(arg1);

        getDriver().findElement(By.xpath("//a[contains(text(),'10 Miles')]")).click();
        getDriver().findElement(By.id("searchLocations")).click();
    }

    @Then("I verify that {string} present in search results")
    public void iVerifyThatPresentInSearchResults(String address) throws InterruptedException {

        JavascriptExecutor check = (JavascriptExecutor) getDriver();
        check.executeScript("window.scrollBy(0,1500)");
        Thread.sleep(2000);

        WebElement element = getDriver().findElement(By.id("resultBox"));
        String result = element.getText();
        assertThat(result).contains(address);
    }

    @And("I verify that {string} in {string}")
    public void iVerifyThatIn(String str, String str2) {

        assertThat(getDriver().findElement(By.id("1440608")).getText()).contains(str);
    }

    @When("I expand {string}")
    public void iExpand(String arg0) {

        getDriver().findElement(By.id("1440608")).click();
    }

    @Then("I verify that {string} phone in {string}")
    public void iVerifyThatPhoneIn(String arg0, String arg1) {

        assertThat(getDriver().findElement(By.id("po-location-detail")).getText()).contains(arg0);
    }

    @When("I collapse office details")
    public void iCollapseOfficeDetails() {

        getDriver().findElement(By.xpath("//span[@class='overlay-icon']")).click();
    }

    @Then("I verify I'm back on search results")
    public void iVerifyIMBackOnSearchResults() {

        WebElement element = getDriver().findElement(By.xpath("//div[@class='po-location']"));
        String results = element.getText();
        assertThat(results).contains("MOUNTAIN VIEW");
    }

    @When("I go to {string} under {string}")
    public void iGoToUnder(String text, String tab) {

        WebElement element = getDriver().findElement(By.xpath("//a[text()='" + tab + "']"));
        if (text.contains("Door")) {
            WebElement click = getDriver().findElement(By.xpath("//a[text()='Every Door Direct Mail']"));
            new Actions(getDriver()).moveToElement(element).click(click).perform();
        } else if (text.contains("PO Boxes")) {
            WebElement click = getDriver().findElement(By.xpath("//li//a[(text()='PO Boxes')]"));
            new Actions(getDriver()).moveToElement(element).click(click).perform();
        }


    }

    @And("I reserve new PO box for {string}")
    public void iReserveNewPOBoxFor(String arg0) throws InterruptedException {

        JavascriptExecutor down = (JavascriptExecutor) getDriver();
        down.executeScript("window.scrollBy(0,300)");
        getDriver().findElement(By.id("searchInput")).sendKeys(arg0);
        getDriver().findElement(By.xpath("//span[@class='icon-search']")).click();
    }

    @Then("I verify that {string} present")
    public void iVerifyThatPresent(String arg0) {

        assertThat(getDriver().findElement(By.xpath("//*[@id='1370964']//*[@class='row']")).getText()).contains(arg0);
        System.out.println(arg0);
    }

    @And("I verify that {string} PO Box is available in {string}")
    public void iVerifyThatPOBoxIsAvailableIn(String arg0, String arg1) throws InterruptedException {

        getDriver().findElement(By.xpath("//*[@id='1370964']//*[@class='row']")).click();

        assertThat(getDriver().findElement(By.xpath("//div[@class='row poLocationResults']//div[5]//label[1]")).getText()).contains(arg0);

    }

    @And("I enter {string} zip for informed delivery")
    public void iEnterZipForInformedDelivery(String arg0) {
        getDriver().findElement(By.xpath("//input[@id='zipcodeForm']")).sendKeys(arg0);
        getDriver().findElement(By.xpath("//a[@class='search']//img")).click();

    }

    @Then("I verify that informed delivery is {string}")
    public void iVerifyThatInformedDeliveryIs(String arg0) {

    }

    @And("I set {string} in filters")
    public void iSetInFilters(String arg0) {

        getDriver().findElement(By.xpath("//a[@class='dn-attr-a'][text()='Mail & Ship']")).click();
    }

    @Then("I verify that {string} results found")
    public void iVerifyThatResultsFound(String arg0) throws InterruptedException {
        Thread.sleep(2000);
        String result = getDriver().findElement(By.xpath("//span[@id='searchResultsHeading']")).getText();
        assertThat(result).contains(arg0);

    }

    @Then("I validate that Sign In is required")
    public void iValidateThatSignInIsRequired() throws InterruptedException {
        Thread.sleep(5000);

        getDriver().findElement(By.xpath("//button[@id='btn-submit']")).click();
        String password = getDriver().findElement(By.xpath("//span[contains(text(),'A password')]")).getText();
        assertThat(password).contains("required");

    }

    @And("I perform {string} help search")
    public void iPerformHelpSearch(String arg0) throws InterruptedException {
        getDriver().findElement(By.xpath("//input[@id='131:0']")).sendKeys(arg0);
        getDriver().findElement(By.xpath("//*[contains(@class,'search-button')]")).click();
    }

    @Then("I verify that no results of {string} available in help search")
    public void iVerifyThatNoResultsOfAvailableInHelpSearch(String arg0) {

        String result = getDriver().findElement(By.xpath("//ul[@class='slds-has-dividers--bottom']")).getText();
        assertThat(result).doesNotContain("Quadcopters");
    }

    @And("I search for {string}")
    public void iSearchFor(String address) {

//        getDriver().manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
//        getDriver().findElement(By.xpath("//input[@id='address']")).sendKeys(address);
//        getDriver().findElement(By.xpath("//*[@class='search-form-field-icon search-form-field-icon-search']")).click();

    }

    @And("I click {string} on the map")
    public void iClickOnTheMap(String arg0) {

//        getDriver().findElement(By.xpath("//a[@class='route-table-toggle']")).click();
    }

    @When("I click {string} on the table")
    public void iClickOnTheTable(String arg0) {

//        getDriver().findElement(By.xpath("//a[@class='totalsArea']")).click();
    }

    @And("I close modal window")
    public void iCloseModalWindow() {

//        getDriver().findElement(By.xpath("//div[@id='modal-box-closeModal']")).click();
    }

    @Then("I verify that summary of all rows of Cost column is equal Approximate Cost in Order Summary")
    public void iVerifyThatSummaryOfAllRowsOfCostColumnIsEqualApproximateCostInOrderSummary() throws ParseException {

       
//        List<WebElement> rowsAndColumns = getDriver().findElements(By.xpath("//div[@class='dojoxGridContent']//tr[1]//td[8]"));
//
//        List<String> all_elements = new ArrayList<>();
//        for (int i = 0; i < rowsAndColumns.size(); i++) {
//            all_elements.add(rowsAndColumns.get(i).getText());
//            System.out.println(rowsAndColumns.get(i).getText());
//            Object obj = Collections.max(all_elements);
//            System.out.println(obj);
//
//            String sum = getDriver().findElement(By.xpath("//div[@class='dojoxGridContent']//tr['" + (i++) + "']//td[8]")).getText();

//        }

        }

    }









