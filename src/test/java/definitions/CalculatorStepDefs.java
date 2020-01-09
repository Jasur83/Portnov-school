package definitions;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static support.TestContext.getDriver;

public class CalculatorStepDefs {
    @When("I navigate to {string}")
    public void iNavigateTo(String calMenu) {

        getDriver().findElement(By.xpath("//a[contains(text(),'Auto Loan')]")).click();
    }

    @And("I clear all calculator fields")
    public void iClearAllCalculatorFields() throws InterruptedException {

        List<WebElement> elements = getDriver().findElements(By.xpath("//input[@id]"));

        for(WebElement element : elements) {
            if(element.isDisplayed()){
                element.clear();
            }
           
        }
    }

    @And("I calculate")
    public void iCalculate() {
        getDriver().findElement(By.xpath("//input[@type='image']")).click();
    }

    @Then("I verify {string} calculator error")
    public void iVerifyCalculatorError(String typeOfError) {

        String verifyError =  getDriver().findElement(By.xpath("//font[@color='red']/../..")).getText();
        assertThat(verifyError).containsIgnoringCase(typeOfError);
    }

    @And("I enter {string} price, {string} months, {string} interest, {string} downpayment, {string} trade-in, {string} state, {string} percent tax, {string} fees")
    public void iEnterPriceMonthsInterestDownpaymentTradeInStatePercentTaxFees(String price, String months, String interest, String downpayment, String tradeIn, String state, String percentTax, String fees) {

        getDriver().findElement(By.xpath("//input[@id='cloanamount']")).sendKeys(price);
        getDriver().findElement(By.xpath("//input[@id='cloanterm']")).sendKeys(months);
        getDriver().findElement(By.xpath("//input[@id='cinterestrate']")).sendKeys(interest);
        getDriver().findElement(By.xpath("//input[@id='cdownpayment']")).sendKeys(downpayment);
        getDriver().findElement(By.xpath("//input[@id='ctradeinvalue']")).sendKeys(tradeIn);
        getDriver().findElement(By.xpath("//select[@name='cstate']//option[contains(text(),'" + state +"')]")).click();
        getDriver().findElement(By.xpath("//input[@id='csaletax']")).sendKeys(percentTax);
        getDriver().findElement(By.xpath("//input[@id='ctitlereg']")).sendKeys(fees);
    }

    @Then("I verify monthly pay is {string}")
    public void iVerifyMonthlyPayIs(String expectedPrice) {


        String actualPrice = getDriver().findElement(By.xpath("//h2[contains(text(),'" + expectedPrice + "')]")).getText();
        assertThat(actualPrice).contains(expectedPrice);
    }
}
