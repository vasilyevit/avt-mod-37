package lib.ui.ios;

import lib.ui.ArticlePageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class IOSArticlePageObject extends ArticlePageObject {

    static {
        TITLE = "id:{TITLE}";
        FOOTER_ELEMENT = "id:View article in browser";
        OPTIONS_ADD_TO_MY_LIST_BUTTON = "id:Save for later";
        CLOSE_ARTICLE_BUTTON = "id:Back";
        PLACES_AUTH_CLOSE = "xpath://XCUIElementTypeButton[@name='places auth close']";
    }

    public IOSArticlePageObject(RemoteWebDriver driver) {
        super(driver);
    }
}
