package definitions;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.By;

import static support.TestContext.getDriver;

public class converterStepDefs {
    @When("I click on {string}")
    public void iClickOn(String menu) {

        getDriver().findElement(By.xpath("//div[@id='menu']//a[contains(text(),'" + menu + "')]")).click();
    }

    @And("I set {string} to {string}")
    public void iSetTo(String fromValue, String toValue) {

        getDriver().findElement(By.xpath("//select[@id='calFrom']//option[contains(text(),'" + fromValue + "')]")).click();
        getDriver().findElement(By.xpath("//select[@id='calTo']//option[contains(text(),'" + toValue + "')]")).click();

    }

    @Then("I enter into From field {string} and verify {string} result")
    public void iEnterIntoFromFieldAndVerifyResult(String fromField, String toField) {

        getDriver().findElement(By.xpath("//input[@name='fromVal']")).sendKeys(fromField);
        getDriver().findElement(By.xpath("//input[@name='toVal']")).sendKeys(toField);

    }
}
