package tests;

import lib.CoreTestCase;
import lib.Platform;
import lib.ui.*;
import lib.ui.factories.ArticlePageObjectFactory;
import lib.ui.factories.MyListsPageObjectFactory;
import lib.ui.factories.NavigationUIFactory;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Test;

public class ArticleTests extends CoreTestCase {

    @Test
    public void testCompareArticleTitle(){
        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);

        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Java");
        searchPageObject.clickByArticleWithSubstring("bject-oriented programming language");

        ArticlePageObject articlePageObject = ArticlePageObjectFactory.get(driver);
        if (Platform.getInstance().isAndroid()) {
            String article_title = articlePageObject.getArticleTitle();
            assertEquals(
                    "We see unexpected title",
                    "Java (programming language)",
                    article_title);
        } else {
            articlePageObject.waitForTitleElement("Java (programming language)");
        }
    }

    @Test
    public void testSwipeArticle(){
        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);

        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Java");
        searchPageObject.clickByArticleWithSubstring("bject-oriented programming language");

        ArticlePageObject articlePageObject = ArticlePageObjectFactory.get(driver);
        if (Platform.getInstance().isAndroid()) {
            articlePageObject.waitForTitleElement();
        }
        else {
            articlePageObject.waitForTitleElement("Java (programming language)");
        }
        articlePageObject.swipeToFooter();
    }

    @Test
    public void testSaveTwoArticlesEx5(){
        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);

        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Java");
        searchPageObject.clickByArticleWithSubstring("bject-oriented programming language");

        ArticlePageObject articlePageObject = ArticlePageObjectFactory.get(driver);
        if (Platform.getInstance().isAndroid()){
            articlePageObject.waitForTitleElement();
        } else {
            articlePageObject.waitForTitleElement("Java (programming language)");
        }

        String name_of_folder = "Ex5";

        if (Platform.getInstance().isAndroid()){
            articlePageObject.addArticleToMyList(name_of_folder);
        } else {
            articlePageObject.addArticleToMySaved();
            if (Platform.getInstance().isIOS()) {
                articlePageObject.closeAuthModalForm();
            }
        }
        if (Platform.getInstance().isMw()) {
            AuthorizationPageObject auth = new AuthorizationPageObject(driver);
            auth.clickAuthButton();
            auth.enterLoginData("Vasilyevit","C00851097");
            auth.submitForm();
            articlePageObject.waitForTitleElement();

            assertEquals("We are not on the same page after login",
                    "Java (programming language)",
                    articlePageObject.getArticleTitle());
            articlePageObject.addArticleToMySaved();
        }
        articlePageObject.closeArticle();

        searchPageObject.initSearchInput();
        if (Platform.getInstance().isIOS()){
            searchPageObject.clickClearSearch();
        }
        searchPageObject.typeSearchLine("PHP");
        searchPageObject.clickByArticleWithSubstring("Server-side scripting language created in 1994");

        if (Platform.getInstance().isAndroid()){
            articlePageObject.waitForTitleElement();
        } else {
            articlePageObject.waitForTitleElement("PHP");
        }
        if (Platform.getInstance().isAndroid()){
            articlePageObject.addAdditionalArticleToMyList(name_of_folder);
        } else {
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
        myListsPageObject.swipeByArticleToDelete("Java (programming language)");
        if (Platform.getInstance().isAndroid()) {
            myListsPageObject.waitForArticleToAppearByTitle("PHP");
        } else if (Platform.getInstance().isIOS()){
            myListsPageObject.typeSearchLine("PHP");
            assertEquals("As a result, only one saved reading link should be found",
                    1,
                    myListsPageObject.getSearchResultCount());
        } else {
            searchPageObject.isSearchElementSaved("PHP");
        }
    }

    @Test
    public void testAssertTitleEx6(){
        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Java");
        searchPageObject.clickByArticleWithSubstring("bject-oriented programming language");

        ArticlePageObject articlePageObject = ArticlePageObjectFactory.get(driver);
        if (Platform.getInstance().isAndroid()) {
            articlePageObject.checkTitlePresent();
        } else {
            articlePageObject.waitForTitleElement("Java (programming language)");
        }
    }
}
