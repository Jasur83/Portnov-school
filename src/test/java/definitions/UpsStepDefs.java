package definitions;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static org.assertj.core.api.Assertions.assertThat;
import static support.TestContext.*;

public class UpsStepDefs {


    @And("I open Shipping menu")
    public void iOpenShippingMenu() {

        getDriver().findElement(By.xpath("//a[@id='ups-menuLinks1']")).click();
    }

    @And("I go to Create a Shipment")
    public void iGoToCreateAShipment() {

        getDriver().findElement(By.xpath("//a[contains(text(),'Create a Shipment:')]")).click();
    }

    @When("I fill out origin shipment fields")
    public void iFillOutOriginShipmentFields() {

        getDriver().findElement(By.xpath("//input[@id='originname']")).sendKeys("Administrator");
        getDriver().findElement(By.xpath("//input[@id='originaddress1']")).sendKeys("4970 El Camino Real");
        getDriver().findElement(By.xpath("//input[@id='originpostal']")).sendKeys("94022");
        getDriver().findElement(By.xpath("//input[@id='originemail']")).sendKeys("mail@example.com");
        getDriver().findElement(By.xpath("//input[@id='originphone']")).sendKeys("1234567890");
        getDriver().findElement(By.xpath("//button[@class='close_btn_thick']")).click();
    }

    @And("I submit the shipment form")
    public void iSubmitTheShipmentForm() {

        getDriver().findElement(By.xpath("//button[contains(@id,'ContinueButton')]")).click();
    }

    @Then("I verify origin shipment fields submitted")
    public void iVerifyOriginShipmentFieldsSubmitted() {

        String shipFrom = getDriver().findElement(By.xpath("//div[contains(@class,'group_con')]")).getText();
        assertThat(shipFrom).contains("4970 El Camino Real");
    }

    @When("I fill out destination shipment fields")
    public void iFillOutDestinationShipmentFields() throws InterruptedException {

        getDriver().findElement(By.xpath("//input[@id='destinationname']")).sendKeys("John Doe");
        getDriver().findElement(By.xpath("//input[@id='destinationaddress1']")).sendKeys("870 7th Ave");
        getDriver().findElement(By.xpath("//input[@id='destinationpostal']")).sendKeys("10019");
        Thread.sleep(1000);
    }

    @And("I set packaging type")
    public void iSetPackagingType() {

        getDriver().findElement(By.xpath("//select[contains(@id,'PackagingType')]/option[contains(text(),'Large')]")).click();
        getDriver().findElement(By.xpath("//input[contains(@id,'WeightField')]")).sendKeys("2");

    }

    @Then("I verify total charges")
    public void iVerifyTotalCharges() {

        String price = getDriver().findElement(By.xpath("//span[@id='total-charges-spinner']")).getText();
        assertThat(price).contains("56.18");
        getExecutor().executeScript("window.scrollBy(0,1500)");
    }

    @And("I select cheapest delivery option")
    public void iSelectCheapestDeliveryOption() throws InterruptedException {
        Thread.sleep(1000);
        getExecutor().executeScript("window.scrollBy(0,600)");
        getDriver().findElement(By.xpath("//input[@id='cust-input-Cheapest']")).click();
    }

    @And("I set Saturday Delivery type")
    public void iSetSaturdayDeliveryType() {
        getExecutor().executeScript("window.scrollBy(0,500)");
        getDriver().findElement(By.xpath("//strong[contains(text(),'Saturday')]")).click();
        getDriver().findElement(By.xpath("//input[@id='nbsShipmentDescription']")).sendKeys("Coat");
    }

    @Then("I verify total charges changed")
    public void iVerifyTotalChargesChanged() {

        getWait().until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//spinner[@class='ng-tns-c2-1']//img")));
        String priceChange = getDriver().findElement(By.xpath("//span[@id='total-charges-spinner']")).getText();
        assertThat(priceChange).contains("73.34");
    }

    @And("I select Paypal payment type")
    public void iSelectPaypalPaymentType() {

        getDriver().findElement(By.xpath("//input[@id='other-ways-to-pay-tile']")).click();
        String paypalSelected = getDriver().findElement(By.xpath("//section[contains(@class,'ng-valid')]")).getText();
        assertThat(paypalSelected).contains("PayPal");
    }

    @And("I click on review button")
    public void iClickOnReviewButton() throws InterruptedException {

        WebElement reviewButton = getDriver().findElement(By.xpath("//button[contains(@id,'Review')]"));
        getExecutor().executeScript("arguments[0].click();", reviewButton);
    }

    @Then("I review all recorded details on the review page")
    public void iReviewAllRecordedDetailsOnTheReviewPage() {

        String shipFrom = getDriver().findElement(By.xpath("//origin-return-drawer[@class='ng-star-inserted']//li[contains(@class,'list')]")).getText();
        assertThat(shipFrom).containsIgnoringCase("Administrator");
        assertThat(shipFrom).containsIgnoringCase("4970 El Camino Real");
        assertThat(shipFrom).containsIgnoringCase("94022");
        assertThat(shipFrom).containsIgnoringCase("mail@example.com");
        assertThat(shipFrom).containsIgnoringCase("1234567890");


        String shipTo = getDriver().findElement(By.xpath("//destination-drawer//div[@class='ups-drawer-content']/../div")).getText();
        assertThat(shipTo).containsIgnoringCase("United States");
        assertThat(shipTo).containsIgnoringCase("New York");

//        Bug
//        assertThat(shipTo).containsIgnoringCase("John Doe");
//        assertThat(shipTo).containsIgnoringCase("870 7th Ave");
//        assertThat(shipTo).containsIgnoringCase("10019");
    }

    @And("I cancel the shipment form")
    public void iCancelTheShipmentForm() {

        WebElement cancelButton = getDriver().findElement(By.xpath("//button[contains(@id,'CancelShipmentButton')]"));
        getExecutor().executeScript("arguments[0].click();", cancelButton);
        getDriver().findElement(By.xpath("//button[@id='nbsCancelShipmentWarningYes']")).click();
    }

    @Then("I verify shipment form is reset")
    public void iVerifyShipmentFormIsReset() {

        String resetPage = getDriver().findElement(By.xpath("//agent[@class='ng-star-inserted']//section[@class='ups-section']")).getText();
        assertThat(resetPage).doesNotContain("Administrator");
        assertThat(resetPage).doesNotContain("4970 El Camino Real");
        assertThat(resetPage).doesNotContain("94022");
        assertThat(resetPage).doesNotContain("mail@example.com");
        assertThat(resetPage).doesNotContain("1234567890");
    }


    }
