package tests;

import io.qameta.allure.*;
import io.qameta.allure.junit4.DisplayName;
import lib.CoreTestCase;
import lib.Platform;
import lib.ui.*;
import lib.ui.factories.ArticlePageObjectFactory;
import lib.ui.factories.MyListsPageObjectFactory;
import lib.ui.factories.NavigationUIFactory;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

@Epic("Test for article list")
public class MyListsTests extends CoreTestCase {

    private static final String name_of_folder = "Learning programming";

    @Test
    @Features(value = {@Feature(value="Search"),@Feature(value="Article"),@Feature(value="Article list"),@Feature(value="Saved")})
    @DisplayName("Save article to list")
    @Description("We keep the article in the list")
    @Step("Starting test testSaveFirstArticleToMyList")
    @Severity(value = SeverityLevel.NORMAL)
    public void testSaveFirstArticleToMyList(){
        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);

        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Java");
        searchPageObject.clickByArticleWithSubstring("bject-oriented programming language");

        ArticlePageObject articlePageObject = ArticlePageObjectFactory.get(driver);
        articlePageObject.waitForTitleElement();
        String article_title = articlePageObject.getArticleTitle();

        if (Platform.getInstance().isAndroid()){
            articlePageObject.addArticleToMyList(name_of_folder);
        } else {
            articlePageObject.addArticleToMySaved();
        }
        if (Platform.getInstance().isMw()) {
            AuthorizationPageObject auth = new AuthorizationPageObject(driver);
            auth.clickAuthButton();
            auth.enterLoginData("Vasilyevit","C00851097");
            auth.submitForm();
            articlePageObject.waitForTitleElement();

            assertEquals("We are not on the same page after login",
                    article_title,
                    articlePageObject.getArticleTitle());
            articlePageObject.addArticleToMySaved();
        }
        articlePageObject.closeArticle();


        NavigationUI navigationUI = NavigationUIFactory.get(driver);
        navigationUI.openNavigation();
        navigationUI.clickMyLists();

        MyListsPageObject myListsPageObject = MyListsPageObjectFactory.get(driver);
        if (Platform.getInstance().isAndroid()){
            myListsPageObject.openFolderByName(name_of_folder);
        }
        myListsPageObject.swipeByArticleToDelete(article_title);
    }
}
