package com.shazam.david.shazamexercise.views.utils;

import java.lang.ref.WeakReference;

public abstract class BasePresenter<T> {

    private WeakReference<T> mView;

    public void bindView(T view) {
        mView = new WeakReference<T>(view);
        updateView();
    }

    abstract protected void updateView();

    public void unbindView() {
        mView = null;
    }

    public T getView() {
        if (mView != null) {
            return mView.get();
        } else {
            return null;
        }
    }

}
