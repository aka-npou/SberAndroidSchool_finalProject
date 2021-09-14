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
import com.aka_npou.sberandroidschool_finalproject.presentation.profile.ProfileFragment;
import com.aka_npou.sberandroidschool_finalproject.presentation.question.QuestionFragment;
import com.aka_npou.sberandroidschool_finalproject.presentation.selectTypeQuestions.SelectTypeQuestionsFragment;

/**
 * Фрагмент отображающий выбор игры
 *
 * @author Мулярчук Александр
 */
public class SelectTypeGameFragment extends Fragment {
    public final static String TAG = SelectTypeGameFragment.class.getSimpleName();

    private final IFragmentNavigation fragmentNavigation;

    /**
     * Получение фрагмента выбора игры
     * @param fragmentNavigation обработчик для перехода между фрагментами
     * @return {@link SelectTypeGameFragment} фрагмент отображающий выбор игры
     */
    public static Fragment newInstance(IFragmentNavigation fragmentNavigation) {
        return new SelectTypeGameFragment(fragmentNavigation);
    }

    /**
     * Конструктор
     * @param fragmentNavigation обработчик для перехода между фрагментами
     */
    public SelectTypeGameFragment(IFragmentNavigation fragmentNavigation) {
        this.fragmentNavigation = fragmentNavigation;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_select_type_game, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        Button buttonStartSimpleGame = view.findViewById(R.id.start_simple_game_button);
        buttonStartSimpleGame.setOnClickListener(viewButton ->
                fragmentNavigation.replace(SelectTypeQuestionsFragment.TAG, true));

        Button buttonOpenProfile = view.findViewById(R.id.open_profile_button);
        buttonOpenProfile.setOnClickListener(viewButton ->
                fragmentNavigation.replace(ProfileFragment.TAG, true));
    }
}
