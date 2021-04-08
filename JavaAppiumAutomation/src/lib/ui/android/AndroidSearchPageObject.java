package lib.ui.android;

import io.appium.java_client.AppiumDriver;
import lib.ui.SearchPageObject;

public class AndroidSearchPageObject extends SearchPageObject {

    static {
        SEARCH_INIT_ELEMENT ="xpath://*[contains(@text,'Search Wikipedia')]";
        SEARCH_INPUT ="xpath://*[contains(@text,'Search…')]";
        SEARCH_CANCEL_BUTTON ="xpath://*[@resource-id='org.wikipedia:id/search_close_btn']";
        SEARCH_RESULT_BY_SUBSTRING_TPL ="xpath://*[@resource-id='org.wikipedia:id/page_list_item_container']//*[contains(@text,'{SUBSTRING}')]";
        SEARCH_RESULT_TITLE_AND_DESCRIPTION_TPL ="xpath://*[@resource-id='org.wikipedia:id/page_list_item_title' and @text='{TITLE}']/../*[@resource-id='org.wikipedia:id/page_list_item_description' and @text='{DESCRIPTION}']";
        SEARCH_RESULT_ELEMENT ="xpath://*[@resource-id='org.wikipedia:id/page_list_item_container']";
        SEARCH_EMPTY_RESULT_ELEMENT ="xpath://*[@text='No results found']";
    }

    public AndroidSearchPageObject(AppiumDriver driver) {
        super(driver);
    }
}