package lib.ui;

import io.appium.java_client.AppiumDriver;
import lib.Platform;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

abstract public class ArticlePageObject extends MainPageObject {

    protected static String
        TITLE,
        FOOTER_ELEMENT,
        OPTIONS_BUTTON,
        OPTIONS_ADD_TO_MY_LIST_BUTTON,
        OPTIONS_REMOVE_FROM_MY_LIST_BUTTON,
        ADD_TO_MY_LIST_OVERLAY,
        MY_LIST_NAME_INPUT,
        MY_LIST_OK_BUTTON,
        CLOSE_ARTICLE_BUTTON,
        FOLDER_NAME_ELEMENT_TPL,
        PLACES_AUTH_CLOSE;


    public ArticlePageObject(RemoteWebDriver driver) {
        super(driver);
    }

    private static String getFolderNameElement(String folder_name){
        return FOLDER_NAME_ELEMENT_TPL.replace("{NAMEFOLDER}",folder_name);
    }

    private static String getTitleElement(String title){
        return TITLE.replace("{TITLE}", title);
    }

    public WebElement waitForTitleElement(){
        return this.waitForElementPresent(
                TITLE,
                "Cannot find article title on page!",
                5);
    }

    public WebElement waitForTitleElement(String title){
        return this.waitForElementPresent(
                getTitleElement(title),
                "Cannot find article title on page!",
                5);
    }

    public String getArticleTitle(){
        WebElement titleElement = waitForTitleElement();
        if (Platform.getInstance().isAndroid()){
            return titleElement.getText();
        } else if (Platform.getInstance().isIOS()){
            return titleElement.getAttribute("name");
        } else {
            return titleElement.getText();
        }
    }

    public void swipeToFooter(){
        if (Platform.getInstance().isAndroid()){
            this.swipeUpToFindElement(
                    FOOTER_ELEMENT,
                    "Cannot find the end of the article",
                    40);
        } else if (Platform.getInstance().isIOS()) {
            this.swipeUpTillElementAppear(FOOTER_ELEMENT,
                    "Cannot find the end of the article",
                    40);
        } else {
            this.scrollWebPageUpTillElementNotVisible(FOOTER_ELEMENT,
                    "Cannot find the end of the article",
                    40);
        }

    }

    public void addArticleToMyList(String name_of_folder){
        this.waitForElementAndClick(
                OPTIONS_BUTTON,
                "Cannot find button to open article options",
                5);
        this.waitForElementAndClick(
                OPTIONS_ADD_TO_MY_LIST_BUTTON,
                "Cannot find option 'Add to reading list'",
                5);
        this.waitForElementAndClick(
                ADD_TO_MY_LIST_OVERLAY,
                "Cannot find 'Got it' tip overlay",
                5);
        this.waitForElementAndClear(
                MY_LIST_NAME_INPUT,
                "Cannot find input to set name of article folder",
                5
        );
        this.waitForElementAndSendKeys(
                MY_LIST_NAME_INPUT,
                name_of_folder,
                "Cannot put text into article folder input",
                5);
        this.waitForElementAndClick(
                MY_LIST_OK_BUTTON,
                "Cannot press OK button",
                5);
    }

    public void addAdditionalArticleToMyList(String name_of_folder){
        this.waitForElementAndClick(
                OPTIONS_BUTTON,
                "Cannot find button to open article options",
                5);
        this.waitForElementAndClick(
                OPTIONS_ADD_TO_MY_LIST_BUTTON,
                "Cannot find option 'Add to reading list'",
                5);
        this.changeListByFolderName(name_of_folder);

    }

    public void changeListByFolderName(String name_of_folder){
        this.waitForElementAndClick(
                getFolderNameElement(name_of_folder),
                "Cannot find created folder in reading list",
                5);
    }

    public void addArticleToMySaved(){
        if (Platform.getInstance().isMw()){
            this.removeArticleFromSavedIfItAdded();
        }
        this.waitForElementAndClick(OPTIONS_ADD_TO_MY_LIST_BUTTON,
                "Cannot find option to add article to reading list",
                5);
    }

    public void closeAuthModalForm(){
        this.waitForElementAndClick(PLACES_AUTH_CLOSE,
                "Cannot find places auth close",
                20);
    }

    public void closeArticle(){
         if (Platform.getInstance().isMw()){
             System.out.println("Method closeArticle() does nothing for platform"
                     + Platform.getInstance().getPlatformVar());
         } else {
             this.waitForElementAndClick(
                     CLOSE_ARTICLE_BUTTON,
                     "Cannot close article, cannot find X link.",
                     5);
         }
     }

     public void removeArticleFromSavedIfItAdded(){
         try {Thread.sleep(3000);} catch (Exception ignored) {}
        if (this.isElementPresent(OPTIONS_REMOVE_FROM_MY_LIST_BUTTON)){
            this.waitForElementAndClick(OPTIONS_REMOVE_FROM_MY_LIST_BUTTON,
                    "Cannot click button to remove an article from saved",
                    5);
            this.waitForElementPresent(OPTIONS_ADD_TO_MY_LIST_BUTTON,
                    "Cannot find button to add an article to saved list after removing it from list before",
                    10);
        }

     }

    public void checkTitlePresent(){
        assertElementPresent(
                TITLE,
                "Article title not exist"
        );
    }
}
