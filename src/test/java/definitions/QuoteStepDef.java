package definitions;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static support.TestContext.getDriver;

public class QuoteStepDef {
    @Given("I go to {string} page")
    public void iGoToPage(String page) {

        switch(page.toLowerCase()) {

            case "google":
                getDriver().get("https://www.google.com/");
                break;
            case "yahoo":
                getDriver().get("https://www.yahoo.com/");
                break;
            case "usps":
            getDriver().get("https://www.usps.com/");
                break;
            case "calculator":
                getDriver().get("https://www.calculator.net/");
                break;
            case "converter":
                getDriver().get("https://www.unitconverters.net/");
                break;
            case "ups":
                getDriver().get("https://www.ups.com/us/en/Home.page?");
                break;
            case "quote":
                getDriver().get("https://skryabin.com/market/quote.html");
                break;
            default:
                System.out.println("Unsupported page: " + page);
        }
    }

    @And("I print page details")
    public void iPrintPageDetails() {
        System.out.println(getDriver().getTitle());
        System.out.println(getDriver().getCurrentUrl());
    }

    @And("I go back and forward, then refresh the page")
    public void iGoBackAndForwardThenRefreshThePage() {
        getDriver().navigate().back();
        getDriver().navigate().forward();
        getDriver().navigate().refresh();
    }

    @And("I change resolution to {string}")
    public void iChangeResolutionTo(String layout) {

        Dimension size;

        switch (layout) {

            case "phone":
                size = new Dimension(400, 1024);
                break;
            case "desktop":
                size = new Dimension(1280, 1024);
                break;
            default:
                throw new RuntimeException("Not supported layout: " + layout);
        }

    getDriver().manage().window().setSize(size);

    }

    @When("I fill out required fields")
    public void iFillOutRequiredFields() {
        getDriver().findElement(By.name("username")).sendKeys("Jasur");
        getDriver().findElement(By.name("email")).sendKeys("jas@mail.ru");

    }


    @And("I submit the form")
    public void iSubmitTheForm() {
        getDriver().findElement(By.xpath("//button[@id='formSubmit']")).click();
        
    }

//    @Then("I verify required fields")
//    public void iVerifyRequiredFields() {
//
//        Map<String, String> customer = getData("customer");
//        String actualResult = getDriver().findElement(By.xpath("//div[@id='quotePageResult']")).getText();
//        System.out.println(actualResult);
//
//        assertThat(actualResult).contains(customer.get("username"));
//        assertThat(actualResult).contains(customer.get("email"));
//        assertThat(actualResult).contains(customer.get("firstName") + " " + customer.get("lastName"));
//        assertThat(actualResult).doesNotContain(customer.get("password"));
//
//        String privacyResult = getDriver().findElement(By.xpath("//b[@name='agreedToPrivacyPolicy']")).getText();
//        assertThat(privacyResult).contains("true");

//        int windowCount = getDriver().getWindowHandles().size();
//        getDriver().findElement(By.xpath("link")).click();
//        int newWindowCount = getDriver().getWindowHandles().size();
//        assertThat(newWindowCount).isEqualTo(windowCount + 1);
//    }

    @And("I print logs to the console")
    public void iPrintLogsToTheConsole() throws InterruptedException {
        Thread.sleep(1000);
        LogEntries logs = getDriver().manage().logs().get("browser");
        for(LogEntry entry : logs) {
            System.out.println(entry);
        }

    }

    @When("I {string} {int}rd party agreement")
    public void iRdPartyAgreement(String action, int arg1) {
        getDriver().findElement(By.xpath("//button[@id='thirdPartyButton']")).click();
        if (action.equals("accept")) {
            getDriver().switchTo().alert().accept();
        } else if (action.equals("dismiss")) {
            getDriver().switchTo().alert().dismiss();
        } else {
            throw new RuntimeException("Unrecognized action: " + action);
        }
    }

    @And("I fill out {string} and {string} in additional info")
    public void iFillOutAndInAdditionalInfo(String name, String phone) throws InterruptedException {

        getDriver().switchTo().frame("additionalInfo");

        getDriver().findElement(By.xpath("//input[@id='contactPersonName']")).sendKeys(name);
        getDriver().findElement(By.xpath("//input[@id='contactPersonPhone']")).sendKeys(phone);

        getDriver().switchTo().defaultContent();
    }

    @And("I verify related documents {string} is present")
    public void iVerifyRelatedDocumentsIsPresent(String document) {
//        Set<String> newWindows = getDriver().getWindowHandles();
//        for (String window : newWindows) {
//            System.out.println(window);
//        }

        String originalWindow = getDriver().getWindowHandle();
        getDriver().findElement(By.xpath("//button[contains(@onclick,'new')]")).click();

        Set<String> allWindows = getDriver().getWindowHandles();
        // switch to last window
        for (String window : allWindows) {
            getDriver().switchTo().window(window);
        }

        String actualDocuments = getDriver().findElement(By.xpath("//ul")).getText();
        assertThat(actualDocuments).contains(document);

        // switch to original window
        getDriver().switchTo().window(originalWindow);
    }

}



