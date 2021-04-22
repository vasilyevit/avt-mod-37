package lib.ui.mobile_web;

import lib.ui.ArticlePageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class MWArticlePageObject extends ArticlePageObject {

    static {
        TITLE = "xpath://h1[@id='section_0']";
        FOOTER_ELEMENT = "id:footer-places";
        OPTIONS_ADD_TO_MY_LIST_BUTTON = "xpath://a[contains(@href,'action=watch')]";
        OPTIONS_REMOVE_FROM_MY_LIST_BUTTON = "xpath://a[contains(@href,'action=unwatch')]";
    }

    public MWArticlePageObject(RemoteWebDriver driver) {
        super(driver);
    }
}
