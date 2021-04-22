package lib.ui;

import io.appium.java_client.AppiumDriver;
import lib.Platform;
import org.openqa.selenium.remote.RemoteWebDriver;

abstract public class MyListsPageObject extends MainPageObject {
    protected static String
            FOLDER_BY_NAME_TPL,
            ARTICLE_BY_TITLE_TPL,
            REMOVE_FROM_SAVED_BUTTON_TPL,
            SEARCH_FIELD,
            RESULT_SEARCH_LIST;

    private static String getFolderXpathByName(String name_of_folder){
       return FOLDER_BY_NAME_TPL.replace("{FOLDER_NAME}", name_of_folder);
    }

    private static String getSavedArticleXpathByTitle(String article_title){
        return ARTICLE_BY_TITLE_TPL.replace("{TITLE}", article_title);
    }

    private static String getRemoveButtonByTitle(String article_title){
        return REMOVE_FROM_SAVED_BUTTON_TPL.replace("{TITLE}", article_title);
    }

    public MyListsPageObject(RemoteWebDriver driver) {
        super(driver);
    }

    public void openFolderByName(String name_of_folder){
        this.waitForElementAndClick(
                getFolderXpathByName(name_of_folder),
                "Cannot find folder by name " + name_of_folder,
                5);
    }

    public void waitForArticleToAppearByTitle(String article_title){
        this.waitForElementPresent(
                getSavedArticleXpathByTitle(article_title),
                "Cannot find saved article by title " + article_title,
                15);
    }

    public void waitForArticleToDisappearByTitle(String article_title){
        this.waitForElementNotPresent(
                getSavedArticleXpathByTitle(article_title),
                "Saved article still present with title " + article_title,
                15);
    }

    public void typeSearchLine(String searchLine){
        this.waitForElementAndSendKeys(
                SEARCH_FIELD,
                searchLine,
                "Cannot find and type into search field",
                5);
    }

    public int getSearchResultCount(){
        return getAmountOfElements(RESULT_SEARCH_LIST);
    }

    public void swipeByArticleToDelete(String article_title){
        this.waitForArticleToAppearByTitle(article_title);
        String article_xpath = getSavedArticleXpathByTitle(article_title);

        if (Platform.getInstance().isIOS() || Platform.getInstance().isAndroid()){
            this.swipeElementToLeft(
                    article_xpath,
                    "Cannot find saved article"
            );
        } else {
            String remove_locator = getRemoveButtonByTitle(article_title);
            this.waitForElementAndClick(
                    remove_locator,
                    "Cannot click button to remove article from saved.",
                    10
            );
        }
        if (Platform.getInstance().isIOS()){
            this.clickElementToTheRightUpperCornet(article_xpath, "Cannot find saved article");
        }
        if (Platform.getInstance().isMw()){
            driver.navigate().refresh();
        }
        this.waitForArticleToDisappearByTitle(article_title);
    }
}
