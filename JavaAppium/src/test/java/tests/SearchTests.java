package tests;

import io.qameta.allure.*;
import io.qameta.allure.junit4.DisplayName;
import lib.CoreTestCase;
import lib.Platform;
import lib.ui.SearchPageObject;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

@Epic("Tests for search")
public class SearchTests extends CoreTestCase {

    @Test
    @Features(value = {@Feature(value="Search")})
    @DisplayName("Search article")
    @Description("We search article")
    @Step("Starting test testSearch")
    @Severity(value = SeverityLevel.CRITICAL)
    public void testSearch(){
        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);

        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Java");
        searchPageObject.waitForSearchResult("bject-oriented programming language");
    }

    @Test
    @Features(value = {@Feature(value="Search")})
    @DisplayName("Cancel search")
    @Description("We search and clear it")
    @Step("Starting test testCancelSearch")
    @Severity(value = SeverityLevel.CRITICAL)
    public void testCancelSearch(){
        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);

        searchPageObject.initSearchInput();
        searchPageObject.waitForCancelButtonToAppear();
        searchPageObject.clickCancelSearch();
        searchPageObject.waitForCancelButtonToDisappear();
    }

    @Test
    @Features(value = {@Feature(value="Search")})
    @DisplayName("Amount of not empty search")
    @Description("We check not empty search result")
    @Step("Starting test testAmountOfNotEmptySearch")
    @Severity(value = SeverityLevel.NORMAL)
    public void testAmountOfNotEmptySearch(){
        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);

        searchPageObject.initSearchInput();
        String search_line = "Linkin Park Diskography";
        searchPageObject.typeSearchLine(search_line);
        int amount_of_search_results = searchPageObject.getAmountOfFoundArticles();
        assertTrue(
                "We found too few results",
                amount_of_search_results > 0
        );
    }

    @Test
    @Features(value = {@Feature(value="Search")})
    @DisplayName("Amount of empty search")
    @Description("We check empty search result")
    @Step("Starting test testAmountOfEmptySearch")
    @Severity(value = SeverityLevel.NORMAL)
    public void testAmountOfEmptySearch(){
        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);

        searchPageObject.initSearchInput();
        String search_line = "sdfsgsdzxca";
        searchPageObject.typeSearchLine(search_line);
        searchPageObject.waitForEmptyResultLabel();
        searchPageObject.assertThereIsResultOfSearch();
    }

    @Test
    @Features(value = {@Feature(value="Search")})
    @DisplayName("Check result after clear search input")
    @Description("We perform a search, clear the search string and check that the list of results is empty.")
    @Step("Starting test testCancelSearchEx3")
    @Severity(value = SeverityLevel.NORMAL)
    public void testCancelSearchEx3(){
        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);

        searchPageObject.initSearchInput();
        String search_line = "Java";
        searchPageObject.typeSearchLine(search_line);
        int amount_of_search_results = searchPageObject.getAmountOfFoundArticles();
        assertTrue(
                "There should be several articles",
                amount_of_search_results > 1);
        searchPageObject.clickCancelSearch();
        searchPageObject.waitForResultToDisappear();
    }

    @Test
    @Features(value = {@Feature(value="Search")})
    @DisplayName("Search by title and description")
    @Description("We search for a Java string and check that there are three required strings in the result")
    @Step("Starting test testSearchByTitleAndDescriptionEx9")
    @Severity(value = SeverityLevel.NORMAL)
    public void testSearchByTitleAndDescriptionEx9(){
        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);

        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Java");
        if (Platform.getInstance().isMw()){
            searchPageObject.waitForElementByTitleAndDescription("Java", "Indonesian island");
            searchPageObject.waitForElementByTitleAndDescription("JavaScript", "High-level programming language");
        } else {
            searchPageObject.waitForElementByTitleAndDescription("Java", "Island of Indonesia");
            searchPageObject.waitForElementByTitleAndDescription("JavaScript", "Programming language");
        }
        searchPageObject.waitForElementByTitleAndDescription("Java (programming language)", "Object-oriented programming language");

    }
}
