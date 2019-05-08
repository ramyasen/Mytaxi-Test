package com.mytaxi.android_demo;


import android.support.test.rule.ActivityTestRule;
import android.support.test.rule.GrantPermissionRule;
import android.support.test.runner.AndroidJUnit4;

import com.mytaxi.android_demo.activities.MainActivity;
import com.mytaxi.android_demo.activities.pages.AuthenticationPage;
import com.mytaxi.android_demo.activities.pages.DriverProfilePage;
import com.mytaxi.android_demo.activities.pages.MainPage;


import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

import android.util.Log;


/**
 * Contains tests for login, search and call.
 * @author ramya
 */
@RunWith(AndroidJUnit4.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class MainTest {

    @Rule
    public GrantPermissionRule mGrantPermissionRule =
            GrantPermissionRule.grant(
                    "android.permission.ACCESS_FINE_LOCATION");
    @Rule
    public ActivityTestRule<MainActivity> activityRule =
            new ActivityTestRule<>(MainActivity.class, true, false);

    private static final String TEST_GROUP = "Main Test";
    private static final String USER_NAME = "crazydog335";
    private static final String PASSWORD = "venture";
    private static final String MAIN_PAGE_TITLE = "mytaxi demo";
    private static final String SEARCH_TEXT = "sa";
    private static final String DRIVER_NAME = "Sarah Scott";
    private static final String DRIVER_LOCATION = "6834 charles st";

    @Before
    public void setUp() throws Exception {
        Log.d("AuthenticationActivity", "Lauching activity");
        activityRule.launchActivity(null);
    }

    @Test
    public void testEmptyLoginFlow() throws Exception {
        Log.d(TEST_GROUP, "Test for empty login form");
        AuthenticationPage loginPage = new AuthenticationPage();
        loginPage.checkEmptyUserNameField();
        loginPage.checkEmptyPasswordField();
        Log.d(TEST_GROUP, "Login with empty form");
        loginPage.clickLogin();
        Log.d(TEST_GROUP, "Expecting login failure");
        loginPage.checkLoginFailure();
    }

    @Test
    public void testLoginAndLogout() throws InterruptedException {
        Log.d(TEST_GROUP, "Test for valid login and logout");
        //checking login and logout feature
        AuthenticationPage loginPage = loginUser(USER_NAME, PASSWORD);

        Log.d(TEST_GROUP, "Landing on Main page with successful login");
        MainPage mainPage = new MainPage(activityRule.getActivity());
        mainPage.verifyPageTitle(MAIN_PAGE_TITLE);

        Log.d(TEST_GROUP, "Expecting successful logout");
        mainPage.logout();
    }

    @Test
    public void testSearchAndMakeCall() {
        Log.d(TEST_GROUP, "Test for valid login with search and call making");
        AuthenticationPage loginPage = loginUser(USER_NAME, PASSWORD);

        MainPage mainPage = new MainPage(activityRule.getActivity());
        mainPage.verifyPageTitle(MAIN_PAGE_TITLE);
        mainPage.search(SEARCH_TEXT);
        mainPage.verifySearchText(SEARCH_TEXT);
        mainPage.verifyNameOccurrence(DRIVER_NAME);
        mainPage.selectSearchItem(DRIVER_NAME);

        DriverProfilePage driverProfilePage = new DriverProfilePage();
        driverProfilePage.verifyAvatar();
        driverProfilePage.verifyName(DRIVER_NAME);
        driverProfilePage.verifyLocation(DRIVER_LOCATION);
        driverProfilePage.makeCall();
    }

    private AuthenticationPage loginUser(String userName, String pwd) {
        Log.d(TEST_GROUP, "Performing user login");
        AuthenticationPage loginPage = new AuthenticationPage();
        loginPage.typeUserName(userName);
        loginPage.typePassword(pwd);
        loginPage.verifyUserName(userName);
        loginPage.clickLogin();
        return loginPage;
    }
}