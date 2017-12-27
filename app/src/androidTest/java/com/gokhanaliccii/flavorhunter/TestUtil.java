package com.gokhanaliccii.flavorhunter;

import android.app.Instrumentation;
import android.content.Context;
import android.support.test.InstrumentationRegistry;

/**
 * Created by gokhan on 27/12/17.
 */

public class TestUtil {

    public static void grantPermission(String permission) {
        Instrumentation instrumentation = InstrumentationRegistry.getInstrumentation();
        Context context = instrumentation.getTargetContext();

        String command = "adb grant " + context.getPackageName() + " " + permission;
        System.out.println(command);

        instrumentation.getUiAutomation().executeShellCommand(command);
    }

    public static void revokePermission(String permission) {
        Instrumentation instrumentation = InstrumentationRegistry.getInstrumentation();
        Context context = instrumentation.getTargetContext();

        String command = "adb revoke " + context.getPackageName() + " " + permission;
        instrumentation.getUiAutomation().executeShellCommand(command);
    }
}
