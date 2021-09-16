package com.aka_npou.sberandroidschool_finalproject.presentation.common;

import androidx.lifecycle.ViewModel;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public abstract class CommonViewModel extends ViewModel {
    private CompositeDisposable compositeDisposable;

    public CommonViewModel() {
        compositeDisposable = new CompositeDisposable();
    }

    public void addDisposable(Disposable disposable) {
        compositeDisposable.add(disposable);
    }

    @Override
    protected void onCleared() {
        super.onCleared();

        if (compositeDisposable != null && !compositeDisposable.isDisposed()) {
            compositeDisposable.dispose();
            compositeDisposable = null;
        }
    }
}
