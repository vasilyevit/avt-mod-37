package lib.ui.ios;

import io.appium.java_client.AppiumDriver;
import lib.ui.MyListsPageObject;

public class IOSMyListsPageObject extends MyListsPageObject {

    static {
        ARTICLE_BY_TITLE_TPL = "xpath://XCUIElementTypeStaticText[contains(@name,'{TITLE}')]";
        SEARCH_FIELD = "id:Search";
        RESULT_SEARCH_LIST = "xpath://XCUIElementTypeCollectionView/XCUIElementTypeCell/XCUIElementTypeOther[2]";
    }

    public IOSMyListsPageObject(AppiumDriver driver) {
        super(driver);
    }
}
