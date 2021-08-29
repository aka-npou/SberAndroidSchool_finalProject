package com.aka_npou.sberandroidschool_finalproject.presentation.selectTypeGame;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.aka_npou.sberandroidschool_finalproject.R;
import com.aka_npou.sberandroidschool_finalproject.presentation.common.IFragmentNavigation;
import com.aka_npou.sberandroidschool_finalproject.presentation.common.ISchedulersProvider;
import com.aka_npou.sberandroidschool_finalproject.presentation.question.QuestionFragment;

public class SelectTypeGameFragment extends Fragment {
    public final static String TAG = SelectTypeGameFragment.class.getName();

    private final IFragmentNavigation mFragmentNavigation;
    private final ISchedulersProvider mSchedulersProvider;

    public static Fragment newInstance(IFragmentNavigation fragmentNavigation, ISchedulersProvider schedulersProvider) {
        return new SelectTypeGameFragment(fragmentNavigation, schedulersProvider);
    }

    public SelectTypeGameFragment(IFragmentNavigation fragmentNavigation, ISchedulersProvider schedulersProvider) {
        mFragmentNavigation = fragmentNavigation;
        mSchedulersProvider = schedulersProvider;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_select_type_game, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        Button buttonStartSimpleGame = view.findViewById(R.id.start_simple_game_button);

        buttonStartSimpleGame.setOnClickListener(viewButton ->
                mFragmentNavigation.replace(QuestionFragment.newInstance(mFragmentNavigation, mSchedulersProvider), QuestionFragment.TAG));
    }
}
