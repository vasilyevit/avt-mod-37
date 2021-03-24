package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class SearchPageObject extends MainPageObject {

    private static final String
        SEARCH_INIT_ELEMENT = "//*[contains(@text,'Search Wikipedia')]",
        SEARCH_INPUT = "//*[contains(@text,'Search…')]",
        SEARCH_CANCEL_BUTTON = "//*[@resource-id='org.wikipedia:id/search_close_btn']",
        SEARCH_RESULT_BY_SUBSTRING_TPL = "//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[contains(@text,'{SUBSTRING}')]",
        SEARCH_RESULT_ELEMENT = "//*[@resource-id='org.wikipedia:id/page_results_list']//*[@resource-id='org.wikipedia:id/page_list_item_container']",
        SEARCH_EMPTY_RESULT_ELEMENT = "//*[@text='No results found']";


    public SearchPageObject(AppiumDriver driver) {
        super(driver);
    }

    /* TEMPLATES METHODS */
    private static String getResultSearchElement(String substring){
        return SEARCH_RESULT_BY_SUBSTRING_TPL.replace("{SUBSTRING}",substring);
    }
    /* TEMPLATES METHODS */

    public void initSearchInput(){
        this.waitForElementAndClick(
                By.xpath(SEARCH_INIT_ELEMENT),
                "Cannot find and click search init element",
                5);
        this.waitForElementPresent(
                By.xpath(SEARCH_INPUT),
                "Cannot search input after clicking search init element");
    }

    public void waitForCancelButtonToAppear(){
        this.waitForElementPresent(By.xpath(SEARCH_CANCEL_BUTTON),
                "Cannot search cancel button!",
                5);
    }

    public void waitForCancelButtonToDisappear(){
        this.waitForElementNotPresent(By.xpath(SEARCH_CANCEL_BUTTON),
                "Search cancel button is still present!",
                5);
    }

    public void clickCancelSearch(){
        this.waitForElementAndClick(By.xpath(SEARCH_CANCEL_BUTTON),
                "Cannot find and click search cancel button!",
                5);
    }

    public void typeSearchLine(String searchLine){
        this.waitForElementAndSendKeys(
                By.xpath(SEARCH_INPUT),
                searchLine,
                "Cannot find and type into search input",
                5);
    }

    public void waitForSearchResult(String substring){
        this.waitForElementPresent(
                By.xpath(getResultSearchElement(substring)),
                "Cannot find search result with substring " + substring);
    }

    public void clickByArticleWithSubstring(String substring){
        this.waitForElementAndClick(
                By.xpath(getResultSearchElement(substring)),
                "Cannot find and click search result with substring " + substring,
                5);
    }

    public int getAmountOfFoundArticles(){
        this.waitForElementPresent(
                By.xpath(SEARCH_RESULT_ELEMENT),
                "Cannot find empty result label by the request",
                15
        );
        return this.getAmountOfElements(
                By.xpath(SEARCH_RESULT_ELEMENT)
        );
    }

    public void waitForEmptyResultLabel(){
        this.waitForElementPresent(
                By.xpath(SEARCH_EMPTY_RESULT_ELEMENT),
                "Cannot find empty result element",
                15);
    }

    public void assertThereIsResultOfSearch(){
        this.assertElementNotPresent(
                By.xpath(SEARCH_RESULT_ELEMENT),
                "We supposed not to find any results");
    }
}
