package com.aka_npou.sberandroidschool_finalproject.presentation.question;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.aka_npou.sberandroidschool_finalproject.data.model.Question;
import com.aka_npou.sberandroidschool_finalproject.domain.interactor.IQuestionInteractor;
import com.aka_npou.sberandroidschool_finalproject.presentation.common.ISchedulersProvider;

import io.reactivex.disposables.Disposable;

public class QuestionViewModel extends ViewModel {
    private final IQuestionInteractor mInteractor;
    private final ISchedulersProvider mSchedulersProvider;

    private final MutableLiveData<Question> mQuestionLiveData = new MutableLiveData<>();

    private Disposable mDisposable;

    public QuestionViewModel(IQuestionInteractor interactor, ISchedulersProvider schedulersProvider) {
        this.mInteractor = interactor;
        this.mSchedulersProvider = schedulersProvider;
    }

    public void getQuestion() {
        mDisposable = mInteractor.getQuestion()
                .subscribeOn(mSchedulersProvider.io())
                .observeOn(mSchedulersProvider.ui())
                .subscribe(mQuestionLiveData::setValue);
    }

    @Override
    protected void onCleared() {
        super.onCleared();

        if (mDisposable != null && !mDisposable.isDisposed()) {
            mDisposable.dispose();
            mDisposable = null;
        }
    }

    public LiveData<Question> getMyLiveData() {
        return mQuestionLiveData;
    }
}
