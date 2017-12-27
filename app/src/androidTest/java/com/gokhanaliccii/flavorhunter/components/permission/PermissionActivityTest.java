package com.gokhanaliccii.flavorhunter.components.permission;

import android.Manifest;
import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;

import com.gokhanaliccii.flavorhunter.TestActivity;

import org.hamcrest.core.IsEqual;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static com.gokhanaliccii.flavorhunter.components.permission.PermissionActivity.requestForPermission;

/**
 * Created by gokhan on 27/12/17.
 */
public class PermissionActivityTest {

    // TODO: 27/12/17 add tests

    @Rule
    public ActivityTestRule<TestActivity> activityTestRule = new ActivityTestRule<>(TestActivity.class);

    @Mock
    public PermissionResponseListener permissionResponseListener;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void should_GeneratePermissionIdCorrectly() {
        final int expectedPermissionId = PermissionActivity.sRequesterId.get() + 1;
        final String[] intendedPermission = new String[]{Manifest.permission.READ_CONTACTS};

        requestForPermission(targetContext(), permissionResponseListener, intendedPermission);
        int actualPermissionId = PermissionActivity.sRequesterId.get();

        Assert.assertThat(actualPermissionId, IsEqual.equalTo(expectedPermissionId));
    }



    private Context targetContext() {
        return InstrumentationRegistry.getTargetContext();
    }

}