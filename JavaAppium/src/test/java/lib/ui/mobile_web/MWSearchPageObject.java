package lib.ui.mobile_web;

import lib.ui.SearchPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class MWSearchPageObject extends SearchPageObject {

    static {
        SEARCH_INIT_ELEMENT ="id:searchIcon";
        SEARCH_INPUT ="xpath://form/input[@name='search']";
        SEARCH_CANCEL_BUTTON ="xpath://*[text()='Close']";
        SEARCH_RESULT_BY_SUBSTRING_TPL ="xpath://div[contains(@class,'wikidata-description') and contains(text(),'{SUBSTRING}')]";
        SEARCH_RESULT_TITLE_AND_DESCRIPTION_TPL ="xpath://div[contains(@class,'results-list-container')]/ul/li[contains(@title,'{TITLE}')]//div[contains(@class,'wikidata-description') and contains(text(),'{DESCRIPTION}')]";
        SEARCH_RESULT_ELEMENT ="xpath://div[contains(@class,'results-list-container')]/ul/li";
        SEARCH_EMPTY_RESULT_ELEMENT ="xpath://*[text()='No page with this title.']";
        SEARCH_CLEAR_BUTTON = "xpath://*[text()='Clear']";
        SEARCH_RESULT_ELEMENT_SAVED_TPL = "xpath://div[contains(@class,'results-list-container')]/ul/li[contains(@title,'{TITLE}')]/../..//a[contains(@href,'unwatch')]";
    }

    public MWSearchPageObject(RemoteWebDriver driver) {
        super(driver);
    }
}