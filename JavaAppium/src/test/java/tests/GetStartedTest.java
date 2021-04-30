package tests;

import io.qameta.allure.*;
import io.qameta.allure.junit4.DisplayName;
import lib.CoreTestCase;
import lib.Platform;
import lib.ui.WelcomePageObject;
import org.junit.Test;

@Epic("Test for iOS. Pass through Welcome")
public class GetStartedTest extends CoreTestCase {

    @Test
    @Features(value = {@Feature(value="Welcome")})
    @DisplayName("Pass through Welcome")
    @Description("We pass through Welcome is iOS")
    @Step("Starting test testPassThroughWelcome")
    @Severity(value = SeverityLevel.CRITICAL)
    public void testPassThroughWelcome(){
        if (Platform.getInstance().isAndroid() || Platform.getInstance().isMw()){
            return;
        }
        WelcomePageObject welcomePageObject = new WelcomePageObject(driver);

        welcomePageObject.waitForLearnMoreLink();
        welcomePageObject.clickNextButton();

        welcomePageObject.waitForNewWayToExploreText();
        welcomePageObject.clickNextButton();

        welcomePageObject.waitForAddOrEditPreferredLangText();
        welcomePageObject.clickNextButton();

        welcomePageObject.waitForLearnMoreAboutDataCollectedText();
        welcomePageObject.clickGetStartedButton();
    }
}

