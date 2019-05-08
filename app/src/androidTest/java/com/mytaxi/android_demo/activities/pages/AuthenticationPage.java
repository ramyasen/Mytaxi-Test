package com.mytaxi.android_demo.activities.pages;

import android.support.test.espresso.ViewInteraction;
import android.support.test.espresso.matcher.ViewMatchers;
import android.util.Log;
import android.view.View;

import com.mytaxi.android_demo.R;

import org.hamcrest.Matcher;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isClickable;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.isFocusable;
import static android.support.test.espresso.matcher.ViewMatchers.withEffectiveVisibility;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

/**
 * Page class for Authentication page.
 *
 * @author ramya
 */
public class AuthenticationPage extends BasePage {

    private Matcher<View> matcherTextFieldUserName = withId(R.id.edt_username);
    private Matcher<View> matcherTextFieldPassword = withId(R.id.edt_password);
    private Matcher<View> matcherButtonLogin = withId(R.id.btn_login);
    private static final String PAGE_NAME = "Authentication page";

    /**
     * Types user name.
     *
     * @param userName name to be set in user name field
     */
    public final void typeUserName(String userName) {
        Log.d(PAGE_NAME, "Typing user name");
        ViewInteraction viewTextFieldUserName = onView(matcherTextFieldUserName);
        viewTextFieldUserName.perform(replaceText(userName), closeSoftKeyboard());
    }

    /**
     * Verifies user name.
     *
     * @param name user name
     */
    public final void verifyUserName(String name) {
        Log.d(PAGE_NAME, "Verifying user name");
        onView(matcherTextFieldUserName).check(matches(withText(name)));
    }

    /**
     * Checks whether user name field is empty or not.
     */
    public final void checkEmptyUserNameField() {
        Log.d(PAGE_NAME, "Checking whether user name is empty");
        onView(matcherTextFieldUserName).check(matches(allOf(
                withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE),
                isFocusable(),
                isClickable(),
                withText("")
        )));
    }

    /**
     * Types password
     *
     * @param password password to be set in password field
     */
    public final void typePassword(String password) {
        Log.d(PAGE_NAME, "Typing password");
        ViewInteraction viewTextFieldPassword = onView(matcherTextFieldPassword);
        viewTextFieldPassword.perform(replaceText(password), closeSoftKeyboard());
    }

    /**
     * Checks whether password field is empty or not.
     */
    public final void checkEmptyPasswordField() {
        Log.d(PAGE_NAME, "Checking whether password is empty");
        onView(matcherTextFieldPassword).check(matches(allOf(
                withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE),
                isFocusable(),
                isClickable(),
                withText("")
        )));
    }

    /**
     * Clicks Login.
     */
    public final void clickLogin() {
        Log.d(PAGE_NAME, "Clicking Login");
        ViewInteraction viewButtonLogin = onView(matcherButtonLogin);
        viewButtonLogin.perform(click());
    }

    /**
     * Checks login failed msg.
     */
    public final void checkLoginFailure() throws InterruptedException {
        Log.d(PAGE_NAME, "Checking login failure");
        //need to be replace with idling resource
        Thread.sleep(1000);
        onView(withText("Login failed")).check(matches(isDisplayed()));
    }
}
