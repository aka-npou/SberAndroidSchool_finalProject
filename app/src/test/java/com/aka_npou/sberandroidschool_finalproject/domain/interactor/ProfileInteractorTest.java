package com.aka_npou.sberandroidschool_finalproject.domain.interactor;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.aka_npou.sberandroidschool_finalproject.domain.store.IProfileStore;
import com.aka_npou.sberandroidschool_finalproject.domain.model.Profile;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import io.reactivex.Single;

@RunWith(MockitoJUnitRunner.class)
public class ProfileInteractorTest {

    @Mock
    IProfileStore profileStore;

    IProfileInteractor profileInteractor;

    @Before
    public void setup() {
        profileInteractor = new ProfileInteractor(profileStore);
    }

    @Test
    public void editProfileTest() {
        //Arrange
        Profile profile = new Profile("test_name", "test_path");
        //Act
        profileInteractor.editProfile(profile)
                .test()
                .assertComplete();
        //Assert
        verify(profileStore).editProfile(profile);
    }

    @Test
    public void getProfileTest() {
        //Arrange
        Profile expectedResult = new Profile("test_name", "test_path");
        when(profileStore.getProfile()).thenReturn(expectedResult);
        //Act
        Single<Profile> actualResult = profileInteractor.getProfile();
        //Assert
        actualResult.test().assertResult(expectedResult);
    }

}