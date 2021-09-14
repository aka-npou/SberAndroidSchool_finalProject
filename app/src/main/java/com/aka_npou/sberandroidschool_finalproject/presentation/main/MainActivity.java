package com.aka_npou.sberandroidschool_finalproject.presentation.main;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import com.aka_npou.sberandroidschool_finalproject.QuizApplication;
import com.aka_npou.sberandroidschool_finalproject.R;
import com.aka_npou.sberandroidschool_finalproject.di.activity.ActivityComponent;
import com.aka_npou.sberandroidschool_finalproject.presentation.common.IFragmentNavigation;
import com.aka_npou.sberandroidschool_finalproject.presentation.profile.ProfileFragment;
import com.aka_npou.sberandroidschool_finalproject.presentation.question.QuestionFragment;
import com.aka_npou.sberandroidschool_finalproject.presentation.selectTypeGame.SelectTypeGameFragment;
import com.aka_npou.sberandroidschool_finalproject.presentation.statistic.StatisticFragment;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

/**
 * Главное Activity, являющееся хостом для всех фрагментов
 *
 * @author Мулярчук Александр
 */
public class MainActivity extends AppCompatActivity {
    public static final String TAG = MainActivity.class.getSimpleName();

    private IFragmentNavigation fragmentNavigation;

    private MainActivityViewModel viewModel;

    private View progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragmentNavigation = this::replaceFragment;

        progressBar = findViewById(R.id.progress_main_activity);

        createViewModel();
        observeLiveData();

        viewModel.initDB();
    }

    private void replaceFragment(String tagFragment, boolean addToBackStack) {
        Fragment fragment = getFragment(tagFragment);
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.container, fragment, tagFragment);

        if (addToBackStack) {
            fragmentTransaction.addToBackStack(null);
        }

        fragmentTransaction.commit();
    }

    private Fragment getFragment(String tagFragment) {
        switch (tagFragment) {
            case "QuestionFragment": {
                return QuestionFragment.newInstance(fragmentNavigation);
            }
            case "SelectTypeGameFragment": {
                return SelectTypeGameFragment.newInstance(fragmentNavigation);
            }
            case "ProfileFragment": {
                return ProfileFragment.newInstance();
            }
            case "StatisticFragment": {
                return StatisticFragment.newInstance();
            }
            default: {
                throw new IllegalArgumentException("not support fragment " + tagFragment);
            }
        }
    }

    private void showStartFragment() {
        replaceFragment(SelectTypeGameFragment.TAG, false);
    }

    private void createViewModel() {
        ActivityComponent activityComponent = QuizApplication.getAppComponent(this).getActivityComponent();
        viewModel = new ViewModelProvider(this, activityComponent.getViewModelFactory())
                .get(MainActivityViewModel.class);
    }

    private void observeLiveData() {
        viewModel.getProgressLiveData().observe(this, this::showProgress);
        viewModel.getInitDBLiveData().observe(this, this::showApp);
        viewModel.getErrorLiveData().observe(this, this::showError);
    }

    private void showProgress(boolean isVisible) {
        progressBar.setVisibility(isVisible ? View.VISIBLE : View.GONE);
    }

    private void showApp(boolean result) {
        showStartFragment();
    }

    private void showError(@NonNull Throwable throwable) {
        Log.e(TAG, "showError: ", throwable);
        Snackbar.make(findViewById(R.id.container), throwable.toString(), BaseTransientBottomBar.LENGTH_LONG).show();
    }
}