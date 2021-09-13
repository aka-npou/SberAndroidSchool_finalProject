package com.aka_npou.sberandroidschool_finalproject.presentation.profile;

import static org.mockito.Mockito.when;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.Observer;

import com.aka_npou.sberandroidschool_finalproject.domain.interactor.IProfileInteractor;
import com.aka_npou.sberandroidschool_finalproject.domain.model.Profile;
import com.aka_npou.sberandroidschool_finalproject.presentation.common.ISchedulersProvider;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import io.reactivex.Completable;
import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;

@RunWith(MockitoJUnitRunner.class)
public class ProfileViewModelTest {

    @Rule
    public InstantTaskExecutorRule rule = new InstantTaskExecutorRule();

    @Mock
    IProfileInteractor profileInteractor;
    @Mock
    ISchedulersProvider schedulersProvider;

    @Mock
    private Observer<Boolean> progressLiveDataObserver;
    @Mock
    private Observer<Throwable> errorLiveDataObserver;
    @Mock
    private Observer<Profile> profileLiveDataObserver;
    @Mock
    private Observer<Boolean> profileEditLiveDataObserver;

    private ProfileViewModel viewModel;

    @Before
    public void setUp() {
        when(schedulersProvider.io()).thenReturn(Schedulers.trampoline());
        when(schedulersProvider.ui()).thenReturn(Schedulers.trampoline());

        viewModel = new ProfileViewModel(profileInteractor, schedulersProvider);

        viewModel.getProgressLiveData().observeForever(progressLiveDataObserver);
        viewModel.getErrorLiveData().observeForever(errorLiveDataObserver);
        viewModel.getProfileLiveData().observeForever(profileLiveDataObserver);
        viewModel.getProfileEditLiveData().observeForever(profileEditLiveDataObserver);
    }

    @Test
    public void getProfileDataTest() {
        Profile profile = new Profile("test", "test_path");
        when(profileInteractor.getProfile()).thenReturn(Single.just(profile));

        viewModel.getProfileData();

        InOrder inOrder = Mockito.inOrder(progressLiveDataObserver, profileLiveDataObserver);

        //Проверка, что презентер действительно вызывает методы представления, причем в порядке вызова этих методов.
        inOrder.verify(progressLiveDataObserver).onChanged(true);
        inOrder.verify(profileLiveDataObserver).onChanged(profile);
        inOrder.verify(progressLiveDataObserver).onChanged(false);

        //Проверка, что никакой метод не будет вызван.
        inOrder.verifyNoMoreInteractions();
    }

    @Test
    public void getProfileDataTestError() {
        Exception exception = new Exception("Test");
        when(profileInteractor.getProfile()).thenReturn(Single.error(exception));

        viewModel.getProfileData();

        InOrder inOrder = Mockito.inOrder(progressLiveDataObserver, errorLiveDataObserver);

        //Проверка, что презентер действительно вызывает методы представления, причем в порядке вызова этих методов.
        inOrder.verify(progressLiveDataObserver).onChanged(true);
        inOrder.verify(errorLiveDataObserver).onChanged(exception);
        inOrder.verify(progressLiveDataObserver).onChanged(false);

        //Проверка, что никакой метод не будет вызван.
        inOrder.verifyNoMoreInteractions();
    }

    @Test
    public void saveProfileTest() {
        Profile profile = new Profile("test", "test_path");
        when(profileInteractor.editProfile(profile)).thenReturn(Completable.complete());

        viewModel.saveProfile(profile);

        InOrder inOrder = Mockito.inOrder(progressLiveDataObserver, profileEditLiveDataObserver);

        //Проверка, что презентер действительно вызывает методы представления, причем в порядке вызова этих методов.
        inOrder.verify(progressLiveDataObserver).onChanged(true);
        inOrder.verify(profileEditLiveDataObserver).onChanged(true);
        inOrder.verify(progressLiveDataObserver).onChanged(false);

        //Проверка, что никакой метод не будет вызван.
        inOrder.verifyNoMoreInteractions();
    }

    @Test
    public void saveProfileTestError() {
        Exception exception = new Exception("Test");
        Profile profile = new Profile("test", "test_path");
        when(profileInteractor.editProfile(profile)).thenReturn(Completable.error(exception));

        viewModel.saveProfile(profile);

        InOrder inOrder = Mockito.inOrder(progressLiveDataObserver, errorLiveDataObserver);

        //Проверка, что презентер действительно вызывает методы представления, причем в порядке вызова этих методов.
        inOrder.verify(progressLiveDataObserver).onChanged(true);
        inOrder.verify(errorLiveDataObserver).onChanged(exception);
        inOrder.verify(progressLiveDataObserver).onChanged(false);

        //Проверка, что никакой метод не будет вызван.
        inOrder.verifyNoMoreInteractions();
    }
}