package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class ArticlePageObject extends MainPageObject {

    private static final String
        TITLE = "//*[@resource-id='org.wikipedia:id/view_page_title_text']",
        FOOTER_ELEMENT = "//*[@text='View page in browser']",
        OPTIONS_BUTTON = "//android.widget.ImageView[@content-desc='More options']",
        OPTIONS_ADD_TO_MY_LIST_BUTTON = "//*[@text='Add to reading list']",
        ADD_TO_MY_LIST_OVERLAY = "//*[@resource-id='org.wikipedia:id/onboarding_button']",
        MY_LIST_NAME_INPUT = "//*[@resource-id='org.wikipedia:id/text_input']",
        MY_LIST_OK_BUTTON = "//*[@text='OK']",
        CLOSE_ARTICLE_BUTTON = "//android.widget.ImageButton[@content-desc='Navigate up']",
        FOLDER_NAME_ELEMENT_TPL = "//*[@resource-id='org.wikipedia:id/item_title' and @text='{NAMEFOLDER}']";


    public ArticlePageObject(AppiumDriver driver) {
        super(driver);
    }

    private static String getFolderNameElement(String folder_name){
        return FOLDER_NAME_ELEMENT_TPL.replace("{NAMEFOLDER}",folder_name);
    }

    public WebElement waitForTitleElement(){
        return this.waitForElementPresent(
                By.xpath(TITLE),
                "Cannot find article title on page!",
                5);
    }

    public String getArticleTitle(){
        WebElement titleElement = waitForTitleElement();
        return titleElement.getText();
    }

    public void swipeToFooter(){
        this.swipeUpToFindElement(
                By.xpath(FOOTER_ELEMENT),
                "Cannot find the end of the article",
                20);
    }

    public void addArticleToMyList(String name_of_folder){
        this.waitForElementAndClick(
                By.xpath(OPTIONS_BUTTON),
                "Cannot find button to open article options",
                5);
        this.waitForElementAndClick(
                By.xpath(OPTIONS_ADD_TO_MY_LIST_BUTTON),
                "Cannot find option 'Add to reading list'",
                5);
        this.waitForElementAndClick(
                By.xpath(ADD_TO_MY_LIST_OVERLAY),
                "Cannot find 'Got it' tip overlay",
                5);
        this.waitForElementAndClear(
                By.xpath(MY_LIST_NAME_INPUT),
                "Cannot find input to set name of article folder",
                5
        );
        this.waitForElementAndSendKeys(
                By.xpath(MY_LIST_NAME_INPUT),
                name_of_folder,
                "Cannot put text into article folder input",
                5);
        this.waitForElementAndClick(
                By.xpath(MY_LIST_OK_BUTTON),
                "Cannot press OK button",
                5);
    }

    public void addAdditionalArticleToMyList(String name_of_folder){
        this.waitForElementAndClick(
                By.xpath(OPTIONS_BUTTON),
                "Cannot find button to open article options",
                5);
        this.waitForElementAndClick(
                By.xpath(OPTIONS_ADD_TO_MY_LIST_BUTTON),
                "Cannot find option 'Add to reading list'",
                5);
        this.changeListByFolderName(name_of_folder);

    }

    public void changeListByFolderName(String name_of_folder){
        this.waitForElementAndClick(
                By.xpath(getFolderNameElement(name_of_folder)),
                "Cannot find created folder in reading list",
                5);
    }

    public void closeArticle(){
        this.waitForElementAndClick(
                By.xpath(CLOSE_ARTICLE_BUTTON),
                "Cannot close article, cannot find X link.",
                5);
    }

    public void checkTitlePresent(){
        assertElementPresent(
                By.xpath(TITLE),
                "Article title not exist"
        );
    }
}
