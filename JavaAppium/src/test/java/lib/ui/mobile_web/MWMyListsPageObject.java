package lib.ui.mobile_web;

import lib.ui.MyListsPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class MWMyListsPageObject extends MyListsPageObject {

    static {
        ARTICLE_BY_TITLE_TPL = "xpath://li[contains(@class,'with-watchstar') and contains(@title,'{TITLE}')]";
        REMOVE_FROM_SAVED_BUTTON_TPL = "xpath://li[contains(@class,'with-watchstar') and contains(@title,'{TITLE}')]/a[contains(@class,'mw-ui-icon-wikimedia-unStar-progressive')]";
        SEARCH_FIELD = "id:Search";
        RESULT_SEARCH_LIST = "xpath://li[contains(@class,'with-watchstar')]";
    }

    public MWMyListsPageObject(RemoteWebDriver driver) {
        super(driver);
    }
}