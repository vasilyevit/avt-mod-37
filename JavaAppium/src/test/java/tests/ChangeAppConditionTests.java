package tests;

import io.qameta.allure.*;
import io.qameta.allure.junit4.DisplayName;
import lib.CoreTestCase;
import lib.Platform;
import lib.ui.ArticlePageObject;
import lib.ui.SearchPageObject;
import lib.ui.factories.ArticlePageObjectFactory;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Test;

import java.time.Duration;

import static org.junit.Assert.assertEquals;

@Epic("Test for app conditions")
public class ChangeAppConditionTests extends CoreTestCase {

    @Test
    @Features(value = {@Feature(value="Search"),@Feature(value="Article"),@Feature(value="Orientation")})
    @DisplayName("Change screen orientation on search results")
    @Description("We check the title of the article before and after the coup")
    @Step("Starting test testChangeScreenOrientationOnSearchResults")
    @Severity(value = SeverityLevel.CRITICAL)
    public void testChangeScreenOrientationOnSearchResults(){
        if (Platform.getInstance().isMw()){
            return;
        }
        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);

        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("java");
        searchPageObject.clickByArticleWithSubstring("Object-oriented programming language");

        ArticlePageObject articlePageObject = ArticlePageObjectFactory.get(driver);
        String title_before_rotation = articlePageObject.getArticleTitle();

        this.rotateScreenLandscape();
        String title_after_rotation = articlePageObject.getArticleTitle();

        assertEquals(
                "Article title have been changed after screen rotation",
                title_before_rotation,
                title_after_rotation);
        this.rotateScreenPortrait();
        String title_after_second_rotation = articlePageObject.getArticleTitle();

        assertEquals(
                "Article title have been changed after screen rotation",
                title_before_rotation,
                title_after_second_rotation);
    }

    @Test
    @Features(value = {@Feature(value="Search"),@Feature(value="Article"),@Feature(value="Background")})
    @DisplayName("Check search article in background")
    @Description("We find the article collapse and expand the application")
    @Step("Starting test testCheckSearchArticleInBackground")
    @Severity(value = SeverityLevel.CRITICAL)
    public void testCheckSearchArticleInBackground(){
        if (Platform.getInstance().isMw()){
            return;
        }
        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);

        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("java");
        searchPageObject.waitForSearchResult("Object-oriented programming language");
        this.backgroundApp(Duration.ofSeconds(2));
        searchPageObject.waitForSearchResult("Object-oriented programming language");
    }
}
