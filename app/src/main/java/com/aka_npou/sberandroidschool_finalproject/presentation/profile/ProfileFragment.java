package com.aka_npou.sberandroidschool_finalproject.presentation.profile;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.aka_npou.sberandroidschool_finalproject.R;
import com.aka_npou.sberandroidschool_finalproject.domain.interactor.IProfileInteractor;
import com.aka_npou.sberandroidschool_finalproject.domain.interactor.IStatisticInteractor;
import com.aka_npou.sberandroidschool_finalproject.domain.model.Profile;
import com.aka_npou.sberandroidschool_finalproject.presentation.common.IFragmentNavigation;
import com.aka_npou.sberandroidschool_finalproject.presentation.common.ISchedulersProvider;
import com.aka_npou.sberandroidschool_finalproject.presentation.selectTypeGame.SelectTypeGameFragment;
import com.aka_npou.sberandroidschool_finalproject.presentation.statistic.StatisticFragment;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;

import java.io.FileNotFoundException;
import java.io.InputStream;

public class ProfileFragment extends Fragment {
    public final static String TAG = ProfileFragment.class.getSimpleName();

    private final IFragmentNavigation mFragmentNavigation;
    private final ISchedulersProvider mSchedulersProvider;
    private final IProfileInteractor profileInteractor;
    private final IStatisticInteractor statisticInteractor;

    private ProfileViewModel mViewModel;

    ActivityResultLauncher<Intent> activityResultLauncher;

    private ImageView profileImage;
    private ImageView editProfileImage;
    private TextView profileName;
    private ImageView editProfileName;
    private TextInputEditText inputProfileName;
    private ImageView saveProfileName;
    private ImageView cancelSaveProfileName;
    private View mRootView;

    private Profile profile;

    public static Fragment newInstance(IFragmentNavigation fragmentNavigation,
                                       ISchedulersProvider schedulersProvider,
                                       IProfileInteractor profileInteractor,
                                       IStatisticInteractor statisticInteractor) {
        return new ProfileFragment(fragmentNavigation, schedulersProvider, profileInteractor, statisticInteractor);
    }

    public ProfileFragment(IFragmentNavigation fragmentNavigation,
                           ISchedulersProvider schedulersProvider,
                           IProfileInteractor profileInteractor,
                           IStatisticInteractor statisticInteractor) {
        mFragmentNavigation = fragmentNavigation;
        mSchedulersProvider = schedulersProvider;
        this.profileInteractor = profileInteractor;
        this.statisticInteractor = statisticInteractor;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        mRootView = view.getRootView();
        profileImage = view.findViewById(R.id.profile_image);
        profileName = view.findViewById(R.id.profile_name);
        editProfileImage = view.findViewById(R.id.edit_profile_image);
        editProfileImage.setOnClickListener((v) -> startEditProfileImageActivity());
        editProfileName = view.findViewById(R.id.edit_profile_name);
        editProfileName.setOnClickListener(v -> showEditProfileName());
        saveProfileName = view.findViewById(R.id.save_profile_name);
        saveProfileName.setOnClickListener(v -> saveProfileName());
        saveProfileName.setVisibility(View.GONE);
        cancelSaveProfileName = view.findViewById(R.id.cancer_edit_profile_name);
        cancelSaveProfileName.setOnClickListener(v -> cancelSaveProfileName());
        cancelSaveProfileName.setVisibility(View.GONE);
        inputProfileName = view.findViewById(R.id.input_profile_name);
        inputProfileName.setVisibility(View.GONE);

        getChildFragmentManager().beginTransaction()
                .add(R.id.statistic_container,
                        StatisticFragment.newInstance(mFragmentNavigation,
                                mSchedulersProvider,
                                statisticInteractor),
                        SelectTypeGameFragment.TAG)
                .commit();

        activityResultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == Activity.RESULT_OK) {
                            Intent data = result.getData();
                            if (data != null) {
                                Uri uri = data.getData();
                                setImageBitmap(uri.toString());

                                mViewModel.saveProfile(new Profile(profile.getId(), profile.getName(), uri.toString()));
                            }
                        }
                    }
                });

        createViewModel();
        observeLiveData();

        if (savedInstanceState == null) {
            mViewModel.getProfileData();
        }
    }

    private void createViewModel() {
        mViewModel = new ViewModelProvider(this, new ViewModelProvider.Factory() {
            @NonNull
            @Override
            public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
                return (T) new ProfileViewModel(profileInteractor, mSchedulersProvider);
            }
        }).get(ProfileViewModel.class);
    }

    private void observeLiveData() {
        mViewModel.getProfileLiveData().observe(getViewLifecycleOwner(), this::showData);
        mViewModel.getErrorLiveData().observe(getViewLifecycleOwner(), this::showError);
        mViewModel.getProfileEditLiveData().observe(getViewLifecycleOwner(), this::editProfileResult);
    }

    private void showData(@NonNull Profile profile) {
        this.profile = profile;

        setImageBitmap(profile.getImageFilePath());
        profileName.setText(profile.getName());
    }

    private void setImageBitmap(String imageFilePath) {
        Bitmap bitmap = getImage(imageFilePath);
        if (bitmap == null) {
            profileImage.setImageResource(R.drawable.ic_person_24);
        } else {
            profileImage.setImageBitmap(bitmap);
        }
    }

    private Bitmap getImage(String imageFilePath) {
        Uri uri = Uri.parse(imageFilePath);
        try {
            InputStream inputStream = getContext().getApplicationContext().getContentResolver().openInputStream(uri);
            return BitmapFactory.decodeStream(inputStream);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void showError(@NonNull Throwable throwable) {
        Log.e(TAG, "showError: ", throwable);
        Snackbar.make(mRootView, throwable.toString(), BaseTransientBottomBar.LENGTH_LONG).show();
    }

    private void editProfileResult(boolean result) {
        if (result) {
            Snackbar.make(mRootView, "profile saved", BaseTransientBottomBar.LENGTH_LONG).show();
        }
    }

    private void startEditProfileImageActivity() {
        Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        activityResultLauncher.launch(intent);
    }

    private void showEditProfileName() {
        inputProfileName.setText(profileName.getText());
        editProfileName(true);
    }

    private void saveProfileName() {
        if (inputProfileName.getText() != null && !inputProfileName.getText().equals("")) {
            profileName.setText(inputProfileName.getText());
            mViewModel.saveProfile(new Profile(profile.getId(), String.valueOf(profileName.getText()), profile.getImageFilePath()));
        }
        editProfileName(false);
    }

    private void cancelSaveProfileName() {
        editProfileName(false);
    }

    private void editProfileName(boolean edit) {
        if (edit) {
            inputProfileName.setVisibility(View.VISIBLE);
            saveProfileName.setVisibility(View.VISIBLE);
            cancelSaveProfileName.setVisibility(View.VISIBLE);

            profileName.setVisibility(View.GONE);
            editProfileName.setVisibility(View.GONE);
        } else {
            inputProfileName.setVisibility(View.GONE);
            saveProfileName.setVisibility(View.GONE);
            cancelSaveProfileName.setVisibility(View.GONE);

            profileName.setVisibility(View.VISIBLE);
            editProfileName.setVisibility(View.VISIBLE);
        }
    }
}
