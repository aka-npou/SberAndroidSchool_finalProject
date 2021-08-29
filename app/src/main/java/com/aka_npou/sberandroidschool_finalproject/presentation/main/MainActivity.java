package com.aka_npou.sberandroidschool_finalproject.presentation.main;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.aka_npou.sberandroidschool_finalproject.R;
import com.aka_npou.sberandroidschool_finalproject.presentation.common.IFragmentNavigation;
import com.aka_npou.sberandroidschool_finalproject.presentation.common.ISchedulersProvider;
import com.aka_npou.sberandroidschool_finalproject.presentation.common.SchedulersProvider;
import com.aka_npou.sberandroidschool_finalproject.presentation.selectTypeGame.SelectTypeGameFragment;

public class MainActivity extends AppCompatActivity {

    private ISchedulersProvider schedulersProvider;

    private IFragmentNavigation mFragmentNavigation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        schedulersProvider = new SchedulersProvider();

        mFragmentNavigation = this::replaceFragment;

        showStartFragment();
    }

    private void replaceFragment(Fragment fragment, String tag) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, fragment, tag)
                .commit();
    }

    private void showStartFragment() {
        getSupportFragmentManager().beginTransaction()
                .add(R.id.container, SelectTypeGameFragment.newInstance(mFragmentNavigation, schedulersProvider), SelectTypeGameFragment.TAG)
                .commit();
    }
}