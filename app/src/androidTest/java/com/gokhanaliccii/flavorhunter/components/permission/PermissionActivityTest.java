package com.gokhanaliccii.flavorhunter.components.permission;

import android.Manifest;
import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.uiautomator.UiObject;
import android.support.test.uiautomator.UiObjectNotFoundException;

import com.gokhanaliccii.flavorhunter.MockitoInitializer;
import com.gokhanaliccii.flavorhunter.TestActivity;
import com.gokhanaliccii.flavorhunter.UIAutomatorUtil;

import org.hamcrest.core.IsEqual;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;

import static com.gokhanaliccii.flavorhunter.components.permission.PermissionActivity.requestForPermission;
import static org.mockito.Mockito.only;
import static org.mockito.Mockito.verify;

/**
 * Created by gokhan on 27/12/17.
 */
public class PermissionActivityTest {

    /**
     * Due to cant revoke permission at runtime we should manually enable/disable permissions
     **/

    @Rule
    public ActivityTestRule<TestActivity> activityTestRule = new ActivityTestRule<>(TestActivity.class);

    @Mock
    public PermissionResponseListener permissionResponseListener;

    @Before
    public void setUp() {
        MockitoInitializer.initMock(this);
    }

    @Test
    public void should_GeneratePermissionIdCorrectly() {
        final int expectedPermissionId = PermissionActivity.sRequesterId.get() + 1;
        final String[] intendedPermission = new String[]{Manifest.permission.ACCESS_FINE_LOCATION};

        requestForPermission(targetContext(), permissionResponseListener, intendedPermission);
        int actualPermissionId = PermissionActivity.sRequesterId.get();

        Assert.assertThat(actualPermissionId, IsEqual.equalTo(expectedPermissionId));
    }

    @Test
    public void should_TriggerPermissionGrantedCallbackWhenPermissionRequestAllowed() throws UiObjectNotFoundException, InterruptedException {
        final String targetPermission = Manifest.permission.ACCESS_FINE_LOCATION;

        final String[] intendedPermission = new String[]{targetPermission};

        requestForPermission(targetContext(), permissionResponseListener, intendedPermission);

        UiObject allowButton = UIAutomatorUtil.findUIObjectByName("ALLOW");
        if (allowButton.exists()) {
            allowButton.click();
        }

        sleep(300);

        verify(permissionResponseListener, only()).onPermissionGranted();
    }

    @Test
    public void should_TriggerPermissionRejectedCallbackWhenPermissionRequestDenied() throws UiObjectNotFoundException, InterruptedException {
        final String targetPermission = Manifest.permission.ACCESS_FINE_LOCATION;

        final String[] intendedPermission = new String[]{targetPermission};

        requestForPermission(targetContext(), permissionResponseListener, intendedPermission);

        UiObject denyButton = UIAutomatorUtil.findUIObjectByName("DENY");
        if (denyButton.exists()) {
            denyButton.click();
        }

        sleep(300);

        verify(permissionResponseListener, only()).onPermissionRejected();
    }

    private void sleep(long duration) throws InterruptedException {
        Thread.sleep(duration);
    }


    private Context targetContext() {
        return InstrumentationRegistry.getTargetContext();
    }

}