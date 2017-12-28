package com.gokhanaliccii.flavorhunter.util;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

/**
 * Created by gokhan on 28/12/17.
 */

@RunWith(AndroidJUnit4.class)
public class AssetReaderTest {


    @Test
    public void should_ReadUserFileAndMapCorrectly() {
        final String expected = "test";
        final String filePath = "test/test.txt";

        final String readFileResult = AssetReader
                .newReader(context())
                .path(filePath)
                .read();

        assertThat(readFileResult, equalTo(expected));
    }

    @Test
    public void should_ReadAndMapIntendedClassCorrectly() {
        final User expectedUser = new User("gokhan");
        final String filePath = "test/user.json";

        final User user = (User) AssetReader.newReader(context(), User.class)
                .path(filePath)
                .map();

        assertThat(user, equalTo(expectedUser));
    }

    private Context context() {
        return InstrumentationRegistry.getTargetContext();
    }

    private class User {

        private String username;

        public User(String username) {
            this.username = username;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o)
                return true;

            if (o instanceof User) {
                return username.equals(((User) o).getUsername());
            }

            return super.equals(o);
        }

        @Override
        public String toString() {
            return "user:" + username;
        }
    }
}