package com.gokhanaliccii.flavorhunter;

import android.support.test.InstrumentationRegistry;
import android.support.test.uiautomator.UiDevice;
import android.support.test.uiautomator.UiObject;
import android.support.test.uiautomator.UiSelector;

/**
 * Created by gokhan on 27/12/17.
 */

public class UIAutomatorUtil {

    public static UiObject findUIObjectByName(String name) {
        UiDevice uiDevice = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());
        return uiDevice.findObject(new UiSelector().text(name));
    }
}
