package com.example.myexpensecalculator;

import android.content.Context;

import androidx.annotation.ColorRes;
import androidx.annotation.StringRes;

public interface BaseView {
    void init();

    void showLoading();

    void hideLoading();

    void showSnackBar(@StringRes int resId);

    void showSnackBar(String message, @ColorRes int color);

    void showSnackBar(String message);

    void showError(@StringRes int resId);

    void showError(String message);

    boolean isNetworkConnected();

    void hideKeyboard();

    Context getActivityContext();
}
