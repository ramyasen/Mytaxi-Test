package com.mytaxi.android_demo;


import android.support.test.espresso.Espresso;
import android.support.test.espresso.IdlingResource;
import android.support.test.rule.ActivityTestRule;
import android.support.test.rule.GrantPermissionRule;
import android.support.test.runner.AndroidJUnit4;

import com.mytaxi.android_demo.activities.MainActivity;
import com.mytaxi.android_demo.activities.commons.TimeIdlingResource;
import com.mytaxi.android_demo.activities.pages.AuthenticationPage;
import com.mytaxi.android_demo.activities.pages.DriverProfilePage;
import com.mytaxi.android_demo.activities.pages.MainPage;


import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

import android.text.InputType;
import android.util.Log;

import androidx.test.espresso.matcher.ViewMatchers;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static androidx.test.espresso.matcher.ViewMatchers.isClickable;
import static androidx.test.espresso.matcher.ViewMatchers.isFocusable;
import static androidx.test.espresso.matcher.ViewMatchers.withEffectiveVisibility;
import static androidx.test.espresso.matcher.ViewMatchers.withInputType;
import static org.hamcrest.Matchers.allOf;


/**
 * Contains tests for login, search and call.
 * @author ramya
 */
@RunWith(AndroidJUnit4.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class MainTest {


    @Rule
    public ActivityTestRule<MainActivity> activityRule =
            new ActivityTestRule<>(MainActivity.class);

    @Rule
    public GrantPermissionRule mGrantPermissionRule =
            GrantPermissionRule.grant(
                    "android.permission.ACCESS_FINE_LOCATION");



    @Before
    public void setUp() throws Exception {
        //Intents.init();
        //idlingResource = TimeIdlingResource.timeout(4000);
    }

    @After
    public void tearDown() throws Exception{
        //Espresso.unregisterIdlingResources(idlingResource);
    }

    //@Test
    public void testEmptyInputFields() throws Exception {
        onView(withId(R.id.edt_username)).check(matches(allOf(
                withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE),
                isFocusable(),
                isClickable(),
                withText("")
        )));

        onView(withId(R.id.edt_password)).check(matches(allOf(
                withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE),
                isFocusable(),
                isClickable(),
                withInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD),
                withText("")
        )));
    }

    //@Test
    public void testLogInButtonShown() throws Exception {
        onView(withId(R.id.btn_login)).check(matches(allOf(
                withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE),
                isClickable(),
                withText(R.string.button_login)
        )));
    }


    @Test
    public void verifyLoginAndMakeCall() throws InterruptedException {
//        IdlingPolicies.setMasterPolicyTimeout(10, TimeUnit.SECONDS);
//        IdlingPolicies.setIdlingResourceTimeout(10, TimeUnit.SECONDS);
        onView(withId(R.id.edt_username)).perform(typeText("crazydog335"));
        closeSoftKeyboard();
        onView(withId(R.id.edt_password)).perform(typeText("venture"));
        closeSoftKeyboard();
        onView(withId(R.id.btn_login)).perform(click());

        IdlingResource idlingResource = TimeIdlingResource.timeout(4000);
        onView(withText("mytaxi demo")).check(matches(isDisplayed()));
        Espresso.unregisterIdlingResources(idlingResource);

        onView(withId(R.id.toolbar)).perform(click());
    }

}