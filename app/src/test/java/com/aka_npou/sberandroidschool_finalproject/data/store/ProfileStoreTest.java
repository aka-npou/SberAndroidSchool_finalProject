package com.aka_npou.sberandroidschool_finalproject.data.store;

import static org.mockito.Mockito.when;

import android.content.SharedPreferences;

import com.aka_npou.sberandroidschool_finalproject.domain.model.Profile;
import com.aka_npou.sberandroidschool_finalproject.domain.store.IProfileStore;
import com.google.common.truth.Truth;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ProfileStoreTest {

    @Mock
    SharedPreferences sharedPreferences;

    private IProfileStore profileStore;

    @Before
    public void setup() {
        profileStore = new ProfileStore(sharedPreferences);
    }

    @Test
    public void editProfileTest() {
        //Arrange
        //Act
        //Assert
        //Truth.assertThat(actualResult).isEqualTo(expectedResult);
    }

    @Test
    public void getProfileTest() {
        //Arrange
        Profile expectedResult = new Profile("test", "test_path");

        String NAME_KEY = "NAME_KEY";
        String IMAGE_FILE_PATH_KEY = "IMAGE_FILE_PATH_KEY";

        when(sharedPreferences.getString(NAME_KEY, "")).thenReturn("test");
        when(sharedPreferences.getString(IMAGE_FILE_PATH_KEY, "")).thenReturn("test_path");
        //Act
        Profile actualResult = profileStore.getProfile();
        //Assert
        Truth.assertThat(actualResult).isEqualTo(expectedResult);
    }
}