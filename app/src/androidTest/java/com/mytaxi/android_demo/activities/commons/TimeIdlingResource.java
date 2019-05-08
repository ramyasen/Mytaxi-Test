package com.mytaxi.android_demo.activities.commons;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.test.espresso.Espresso;
import android.support.test.espresso.IdlingResource;

/**
 * Handle for idling resource.
 *
 * @author ramya
 */
public class TimeIdlingResource implements IdlingResource {

    private final long waitTimeMs;
    private final long startTime;

    @Nullable
    private volatile ResourceCallback mCallback;

    private TimeIdlingResource(long waitTimeMs) {
        this.waitTimeMs = waitTimeMs;
        this.startTime = System.currentTimeMillis();
    }

    @NonNull
    public static IdlingResource timeout(long waitTimeMs) {
        IdlingResource idlingResource = new TimeIdlingResource(waitTimeMs);
        //need to use replacement for deprecation
        Espresso.registerIdlingResources(idlingResource);
        return idlingResource;
    }

    @Override
    public String getName() {
        return this.getClass().getName();
    }

    @Override
    public boolean isIdleNow() {
        boolean isIdle = System.currentTimeMillis() - startTime >= waitTimeMs;
        if (isIdle && mCallback != null) {
            mCallback.onTransitionToIdle();
        }
        return isIdle;
    }

    @Override
    public void registerIdleTransitionCallback(ResourceCallback callback) {
        mCallback = callback;
    }
}