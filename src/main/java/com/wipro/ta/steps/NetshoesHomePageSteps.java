package com.wipro.ta.steps;

import org.apache.log4j.Logger;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Named;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.jbehave.web.selenium.WebDriverProvider;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


@Component
public class NetshoesHomePageSteps extends AbstractSteps {

    @Value("${home.url}")
    private String NETSHOES_HOMEPAGE_URL;

    protected Logger LOG = Logger.getLogger(this.getClass());

    @Autowired
    protected WebDriverProvider webDriverProvider;

    @Given("the customer access the NetShoes home page")
    public void givenCustomerAccessHomePage() {
        LOG.info("Navigating user to page: " + NETSHOES_HOMEPAGE_URL);
        webDriverProvider.get().get(NETSHOES_HOMEPAGE_URL);
    }

    @When("I go to login")
    public void goToLogin() {    	
    	WebElement goLogin = webDriverProvider.get().findElement(By.xpath("//ul[@class='right']/li/a[text"));
    	goLogin.click();
    }
    
    @Then("I should see the search bar")
    public void thenProductListIsDisplayed() {
        WebElement contentDiv = webDriverProvider.get().findElement(By.id("search-input"));
        Assert.assertTrue("The search bar was expected to be displayed, but it was not."
                , contentDiv.isDisplayed());
    }
    
    @When ("I search for product $product")
    public void searchProduct(@Named("product") String product) {
    	WebElement searchBar = webDriverProvider.get().findElement(By.id("search-input"));
    	searchBar.sendKeys(product);
    }
    
    @When ("I click on search button")
    public void clickSearch() {
    	webDriverProvider.get().findElement(By.xpath("//button[@title='Buscar']")).click();
    }
    @When ("I click the $product product")
    public void clickProduct (@Named("product") String product) {
    	webDriverProvider.get().findElement(By.xpath("(//*[@class='item-card__description__product-name']) ["+product+"]")).click();
    }
    
    @When ("I click buy")
    public void clickBuy() {
    	WebElement buyBtn = webDriverProvider.get().findElement(By.className("buy-button-now"));
    	buyBtn.click();
    }
    
    @When ("I search for zip code $zip")
    public void insertZip(@Named("zip") String zip) {
    	WebDriverWait wait = new WebDriverWait(webDriverProvider.get(),30);
    	wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("float-input__input")));
    	
//    	// explicit wait - to wait for the compose button to be click-able
//    	WebDriverWait wait = new WebDriverWait(driver,30);
//    	wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(),'COMPOSE')]")));
    	
    	WebElement searchZip = webDriverProvider.get().findElement(By.className("float-input__input"));
    	searchZip.sendKeys(zip);
    }
    
    @When ("I click search zip")
    public void searchZip(){
    	webDriverProvider.get().findElement(By.className("freight-form__button")).click();
    }
}