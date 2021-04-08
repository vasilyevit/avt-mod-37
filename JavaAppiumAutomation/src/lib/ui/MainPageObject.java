package lib.ui;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import lib.Platform;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class MainPageObject {

    protected AppiumDriver driver;

    public MainPageObject(AppiumDriver driver){
        this.driver=driver;
    }

    public WebElement waitForElementPresent(String locator, String error_message, long timeoutInSeconds){
        By by = this.getLocatorByString(locator);
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_message + "\n");
        return wait.until(ExpectedConditions.presenceOfElementLocated(by));
    }

    public WebElement waitForElementPresent(String locator, String error_message){
        return waitForElementPresent(locator, error_message, 5);
    }

    public WebElement waitForElementAndClick(String locator, String error_message, long timeoutInSeconds){
        WebElement element = waitForElementPresent(locator, error_message, timeoutInSeconds);
        element.click();
        return element;
    }

    public WebElement waitForElementAndSendKeys(String locator, String value, String error_message, long timeoutInSeconds){
         WebElement element = waitForElementPresent(locator, error_message, timeoutInSeconds);
         new Actions(driver).moveToElement(element).pause(2000).click().pause(2000).sendKeys(value).release().perform();
        return element;
    }

    public WebElement waitForElementAndClear(String locator, String error_message, long timeoutInSeconds){
        WebElement element = waitForElementPresent(locator, error_message, timeoutInSeconds);
        element.clear();
        return element;
    }

    public boolean waitForElementNotPresent(String locator, String error_message, long timeoutInSeconds){
        By by = this.getLocatorByString(locator);
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_message + "\n");
        return wait.until(ExpectedConditions.invisibilityOfElementLocated(by));
    }

    public void assertElementHasText(String locator, String expected, String error_message){
        WebElement text_element = waitForElementPresent(
                locator,
                "Cannot find element"
        );
        String text_value = text_element.getText();
        assertEquals(
                error_message,
                expected,
                text_value);
    }

    public List<WebElement> getElementList(String locator){
        By by = this.getLocatorByString(locator);
        return driver.findElements(by);
    }

    public void assertListContainsText(String locator, String expected, String error_message){
        List<String> titles = new ArrayList<>();
        for (WebElement we: getElementList(locator)){
            titles.add(we.getText().toLowerCase());
        }
        assertTrue(
                error_message,
                titles.contains(expected.toLowerCase()));
    }

    public void swipeUp(int timeOfSwipe){
        TouchAction action = new TouchAction(driver);
        Dimension size = driver.manage().window().getSize();
        int x = size.width / 2;
        int start_y = (int) (size.height * 0.8);
        int end_y = (int) (size.height * 0.2);

        action
                .press(PointOption.point(x, start_y))
                .waitAction(WaitOptions.waitOptions(Duration.ofSeconds(timeOfSwipe)))
                .moveTo(PointOption.point(x, end_y))
                .release()
                .perform();
    }

    public void swipeUpQuick(){
        swipeUp(200);
    }

    public void swipeUpToFindElement(String locator, String error_message, int max_swipes){
        By by = this.getLocatorByString(locator);
        int already_swipe = 0;
        if (max_swipes < already_swipe) {
            waitForElementPresent(locator, "Cannot find element by swiping up. \n" + error_message, 0);
            return;
        }
        while (driver.findElements(by).size() == 0){
            swipeUpQuick();
            ++already_swipe;
        }
    }

    public void swipeUpTillElementAppear(String locator, String error_message, int max_swipes){
        int already_swipe = 0;
        while (!this.isElementLocatedOnTheScreen(locator)){
            if (max_swipes < already_swipe) {
                Assert.assertTrue(error_message, this.isElementLocatedOnTheScreen(locator));
            }
            swipeUpQuick();
            ++already_swipe;
        }
    }

    public boolean isElementLocatedOnTheScreen(String locator){
        int element_location_by_y = this.waitForElementPresent(locator,
                "Cannot find element by locator", 1).getLocation().getY();
        int screen_size_by_y = driver.manage().window().getSize().getHeight();
        return element_location_by_y < screen_size_by_y;
    }

    public void clickElementToTheRightUpperCornet(String locator, String error_message){
        WebElement element = this.waitForElementPresent(locator + "/..", error_message);
        int right_x = element.getLocation().getX();
        int upper_y = element.getLocation().getY();
        int lower_y = upper_y + element.getSize().getHeight();
        int middle_y = (upper_y + lower_y) / 2;
        int width = element.getSize().getWidth();
        int point_to_click_x = (right_x + width) - 3;
        int point_to_click_y = middle_y;
        TouchAction action = new TouchAction(driver);
        action.tap(PointOption.point(point_to_click_x, point_to_click_y)).
                moveTo(PointOption.point(point_to_click_x, point_to_click_y)).
                perform();
    }

    public void swipeElementToLeft(String locator, String error_message) {
        WebElement element = waitForElementPresent(
                locator,
                error_message,
                10);
        int left_x = element.getLocation().getX();
        int right_x = left_x + element.getSize().getWidth();
        int upper_y = element.getLocation().getY();
        int lower_y = upper_y + element.getSize().getHeight();
        int middle_y = (upper_y + lower_y) / 2;

        TouchAction action = new TouchAction(driver);
        action.press(PointOption.point(right_x, middle_y));
        action.waitAction(WaitOptions.waitOptions(Duration.ofMillis(300)));
        if (Platform.getInstance().isAndroid()){
            action.moveTo(PointOption.point(left_x, middle_y));
        } else {
            int offset_x = (-1 * element.getSize().getWidth());
            action.moveTo(PointOption.point(offset_x, 0));
        }
        action.release();
        action.perform();
    }

    public int getAmountOfElements(String locator){
        By by = this.getLocatorByString(locator);
        List elements = driver.findElements(by);
        return elements.size();
    }

    public void assertElementNotPresent(String locator, String error_message) {
        int amount_of_elements = getAmountOfElements(locator);
        if (amount_of_elements > 0) {
            String default_message = "An element '" + locator + "' supposed to bu not present";
            throw new AssertionError(default_message + " " + error_message);
        }
    }

    public String waitForElementAndGetAttribute(String locator, String attribute, String error_message, long timeoutInSeconds){
        WebElement element = waitForElementPresent(locator,error_message,timeoutInSeconds);
        return element.getAttribute(attribute);
    }

    public String waitForElementAndGetText(String locator, String error_message, long timeoutInSeconds){
        WebElement element = waitForElementPresent(locator,error_message,timeoutInSeconds);
        return element.getText();
    }

    public void assertElementPresent(String locator, String error_message){
        By by = this.getLocatorByString(locator);
        List elements = driver.findElements(by);
        assertTrue(
                error_message,
                elements.size() > 0);
    }

    private By getLocatorByString(String locator_with_type){
        String[] exploded_locator = locator_with_type.split(Pattern.quote(":"),2);
        String by_type = exploded_locator[0];
        String locator = exploded_locator[1];

        if (by_type.equals("xpath")){
            return By.xpath(locator);
        } else if (by_type.equals("id")){
            return  By.id(locator);
        } else {
            throw new IllegalArgumentException("Cannot get type of locator. Locator: " +locator_with_type);
        }
    }
}
