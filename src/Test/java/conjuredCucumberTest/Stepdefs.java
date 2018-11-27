package conjuredCucumberTest;


import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import edu.insightr.gildedrose.Inventory;
import edu.insightr.gildedrose.Item;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

//import static  org.junit.Assert.assertThat;
//import static org.hamcrest.CoreMatchers.is;


public class Stepdefs {
    private Inventory inventory;
    private Item[] items;
    private Item vest;
    private Item conjured;

    @Given("^I have a new inventory$")
    public void iHaveANewInventory() throws Throwable {
        inventory = new Inventory();
        items = inventory.getItems();
        conjured = items[5];
    }

    @Then("^the quality of the conjured is (\\d+)$")
    public void theQualityOfTheConjuredIs(int conjuredQuality) throws Throwable {
        assertThat(conjured.getQuality(), is(conjuredQuality));
    }

    @When("^I update the inventory$")
    public void iUpdateTheInventory() throws Throwable {
        inventory.updateQuality();
    }

    @Then("^the quality of the conjured item is (\\d+)$")
    public void theQualityOfTheConjuredItemIs(int conjuredQuality) throws Throwable {
        assertThat(conjured.getQuality(), is(conjuredQuality));
    }
}

