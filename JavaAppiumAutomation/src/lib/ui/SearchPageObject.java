package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class SearchPageObject extends MainPageObject {

    private static final String
        SEARCH_INIT_ELEMENT = "xpath://*[contains(@text,'Search Wikipedia')]",
        SEARCH_INPUT = "xpath://*[contains(@text,'Search…')]",
        SEARCH_CANCEL_BUTTON = "xpath://*[@resource-id='org.wikipedia:id/search_close_btn']",
        SEARCH_RESULT_BY_SUBSTRING_TPL = "xpath://*[@resource-id='org.wikipedia:id/page_list_item_container']//*[contains(@text,'{SUBSTRING}')]",
        SEARCH_RESULT_TITLE_AND_DESCRIPTION_TPL = "xpath://*[@resource-id='org.wikipedia:id/page_list_item_title' and @text='{TITLE}']/../*[@resource-id='org.wikipedia:id/page_list_item_description' and @text='{DESCRIPTION}']",
        SEARCH_RESULT_ELEMENT = "xpath://*[@resource-id='org.wikipedia:id/page_list_item_container']",
        SEARCH_EMPTY_RESULT_ELEMENT = "xpath://*[@text='No results found']";


    public SearchPageObject(AppiumDriver driver) {
        super(driver);
    }

    /* TEMPLATES METHODS */
    private static String getResultSearchElement(String substring){
        return SEARCH_RESULT_BY_SUBSTRING_TPL.replace("{SUBSTRING}",substring);
    }

    private static String getResultSearchElementByByTitleAndDescription(String title, String description){
        return SEARCH_RESULT_TITLE_AND_DESCRIPTION_TPL.replace("{TITLE}",title).replace("{DESCRIPTION}", description);
    }
    /* TEMPLATES METHODS */

    public void initSearchInput(){
        this.waitForElementAndClick(
                SEARCH_INIT_ELEMENT,
                "Cannot find and click search init element",
                5);
        this.waitForElementPresent(
                SEARCH_INPUT,
                "Cannot search input after clicking search init element");
    }

    public void waitForCancelButtonToAppear(){
        this.waitForElementPresent(SEARCH_CANCEL_BUTTON,
                "Cannot search cancel button!",
                5);
    }

    public void waitForCancelButtonToDisappear(){
        this.waitForElementNotPresent(SEARCH_CANCEL_BUTTON,
                "Search cancel button is still present!",
                5);
    }

    public void waitForResultToDisappear(){
        this.waitForElementNotPresent(SEARCH_RESULT_ELEMENT,
                "Result list is still present!",
                5);
    }

    public void waitForResultToAppear(){
        this.waitForElementPresent(SEARCH_RESULT_ELEMENT,
                "Result list is not present!",
                5);
    }

    public void clickCancelSearch(){
        this.waitForElementAndClick(SEARCH_CANCEL_BUTTON,
                "Cannot find and click search cancel button!",
                5);
    }

    public void typeSearchLine(String searchLine){
        this.waitForElementAndSendKeys(
                SEARCH_INPUT,
                searchLine,
                "Cannot find and type into search input",
                5);
    }

    public void waitForSearchResult(String substring){
        this.waitForElementPresent(
                getResultSearchElement(substring),
                "Cannot find search result with substring " + substring);
    }

    public void waitForElementByTitleAndDescription(String title, String description){
        this.waitForElementPresent(
                getResultSearchElementByByTitleAndDescription(title, description),
                "Cannot find search result with Title '" + title + "' and Description '" + description + "'!");
    }

    public void clickByArticleWithSubstring(String substring){
        this.waitForElementAndClick(
                getResultSearchElement(substring),
                "Cannot find and click search result with substring " + substring,
                5);
    }

    public int getAmountOfFoundArticles(){
        this.waitForElementPresent(
                SEARCH_RESULT_ELEMENT,
                "Cannot find empty result label by the request",
                15
        );
        return this.getAmountOfElements(
                SEARCH_RESULT_ELEMENT
        );
    }

    public void waitForEmptyResultLabel(){
        this.waitForElementPresent(
                SEARCH_EMPTY_RESULT_ELEMENT,
                "Cannot find empty result element",
                15);
    }

    public void assertThereIsResultOfSearch(){
        this.assertElementNotPresent(
                SEARCH_RESULT_ELEMENT,
                "We supposed not to find any results");
    }
}
