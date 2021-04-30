package lib.ui;

import io.qameta.allure.Step;
import lib.Platform;
import org.openqa.selenium.remote.RemoteWebDriver;

abstract public class SearchPageObject extends MainPageObject {

    protected static String
        SEARCH_INIT_ELEMENT,
        SEARCH_INPUT,
        SEARCH_CANCEL_BUTTON,
        SEARCH_RESULT_BY_SUBSTRING_TPL,
        SEARCH_RESULT_TITLE_AND_DESCRIPTION_TPL,
        SEARCH_RESULT_ELEMENT,
        SEARCH_RESULT_ELEMENT_SAVED_TPL,
        SEARCH_EMPTY_RESULT_ELEMENT,
        SEARCH_CLEAR_BUTTON;


    public SearchPageObject(RemoteWebDriver driver) {
        super(driver);
    }

    /* TEMPLATES METHODS */
    private static String getResultSearchElement(String substring){
        return SEARCH_RESULT_BY_SUBSTRING_TPL.replace("{SUBSTRING}",substring);
    }
    private static String getResultSearchElementSaved(String substring){
        return SEARCH_RESULT_ELEMENT_SAVED_TPL.replace("{TITLE}",substring);
    }

    private static String getResultSearchElementByByTitleAndDescription(String title, String description){
        return SEARCH_RESULT_TITLE_AND_DESCRIPTION_TPL.replace("{TITLE}",title).replace("{DESCRIPTION}", description);
    }
    /* TEMPLATES METHODS */

    @Step("Initializing the search filed")
    public void initSearchInput(){
        try {
            Thread.sleep(3000);
        } catch (Exception ignored){}

        this.waitForElementAndClick(
                SEARCH_INIT_ELEMENT,
                "Cannot find and click search init element",
                5);
        this.waitForElementPresent(
                SEARCH_INPUT,
                "Cannot search input after clicking search init element");
    }

    @Step("Waiting for button to cancel search result")
    public void waitForCancelButtonToAppear(){
        this.waitForElementPresent(SEARCH_CANCEL_BUTTON,
                "Cannot search cancel button!",
                5);
    }

    @Step("Waiting for search cancel button to disappear")
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

    @Step("Clicking button to cancel search result")
    public void clickCancelSearch(){
        this.waitForElementAndClick(SEARCH_CANCEL_BUTTON,
                "Cannot find and click search cancel button!",
                5);
    }

    public void clickClearSearch(){
        this.waitForElementAndClick(SEARCH_CLEAR_BUTTON,
                "Cannot find and click search clear button!",
                5);
    }

    @Step("Typing '{searchLine}' to the search line")
    public void typeSearchLine(String searchLine){
        this.waitForElementAndSendKeys(
                SEARCH_INPUT,
                searchLine,
                "Cannot find and type into search input",
                5);
    }

    @Step("Waiting for search result")
    public void waitForSearchResult(String substring){
        this.waitForElementPresent(
                getResultSearchElement(substring),
                "Cannot find search result with substring " + substring);
    }

    public void waitForSearchResultSaved(String substring){
        this.waitForElementPresent(
                getResultSearchElementSaved(substring),
                "Cannot find search Saved result with title " + substring);
    }

    public void waitForElementByTitleAndDescription(String title, String description){
        this.waitForElementPresent(
                getResultSearchElementByByTitleAndDescription(title, description),
                "Cannot find search result with Title '" + title + "' and Description '" + description + "'!");
    }

    @Step("Waiting for search result and select an article by substring in article title")
    public void clickByArticleWithSubstring(String substring){
        this.waitForElementAndClick(
                getResultSearchElement(substring),
                "Cannot find and click search result with substring " + substring,
                15);
    }

    @Step("Getting amount of found articles")
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

    @Step("Waiting for empty result label")
    public void waitForEmptyResultLabel(){
        this.waitForElementPresent(
                SEARCH_EMPTY_RESULT_ELEMENT,
                "Cannot find empty result element",
                15);
    }

    @Step("Make sure there are no results for search")
    public void assertThereIsResultOfSearch(){
        this.assertElementNotPresent(
                SEARCH_RESULT_ELEMENT,
                "We supposed not to find any results");
    }

    public void isSearchElementSaved(String title){
        if (Platform.getInstance().isMw()) {
            this.initSearchInput();
            this.typeSearchLine(title);
            this.waitForSearchResultSaved(title);
        } else {
            System.out.println("Method isSearchElementSaved() does nothing for platform"
                    + Platform.getInstance().getPlatformVar());
        }
    }
}
