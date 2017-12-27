package com.gokhanaliccii.flavorhunter;

import android.content.Context;
import android.support.test.InstrumentationRegistry;

import org.mockito.MockitoAnnotations;

/**
 * Created by gokhan on 27/12/17.
 */

public class MockitoInitializer {

    private MockitoInitializer() {
    }

    public static void initMock(Object target) {
        new MockitoInitializer()
                .init().initMocks(target);
    }

    private MockitoInitializer init() {
        Context context = InstrumentationRegistry.getTargetContext();
        System.setProperty("dexmaker.dexcache", context.getCacheDir().getPath());
        return this;
    }

    private void initMocks(Object testClass) {
        MockitoAnnotations.initMocks(testClass);
    }
}
