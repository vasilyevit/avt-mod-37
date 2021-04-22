package lib.ui.ios;

import io.appium.java_client.AppiumDriver;
import lib.ui.SearchPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class IOSSearchPageObject extends SearchPageObject {

    static {
        SEARCH_INIT_ELEMENT ="xpath://XCUIElementTypeSearchField[@name='Search Wikipedia']";
        SEARCH_INPUT ="xpath://XCUIElementTypeSearchField";
        SEARCH_CANCEL_BUTTON ="xpath://*[@id='Close']";
        SEARCH_RESULT_BY_SUBSTRING_TPL ="xpath://XCUIElementTypeStaticText[contains(@name,'{SUBSTRING}')]";
        SEARCH_RESULT_TITLE_AND_DESCRIPTION_TPL ="xpath://XCUIElementTypeStaticText[contains(@name,'{TITLE}')]/../XCUIElementTypeStaticText[contains(@name,'{DESCRIPTION}')]";
        SEARCH_RESULT_ELEMENT ="xpath://XCUIElementTypeCollectionView/XCUIElementTypeCell";
        SEARCH_EMPTY_RESULT_ELEMENT ="xpath://XCUIElementTypeStaticText[@name='No results found']";
        SEARCH_CLEAR_BUTTON = "xpath://XCUIElementTypeButton[@name='clear mini']";
    }

    public IOSSearchPageObject(RemoteWebDriver driver) {
        super(driver);
    }
}
