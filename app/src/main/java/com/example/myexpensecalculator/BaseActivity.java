package com.example.myexpensecalculator;

import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;

import androidx.annotation.ColorRes;
import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.amitshekhar.utils.NetworkUtils;
import com.google.android.material.snackbar.Snackbar;

public class BaseActivity extends AppCompatActivity implements BaseView,View.OnClickListener {

    @Override
    public void showError(String message) {
        if (message != null) {
            showSnackBar(message, R.color.colorErrorRed);
        } else {
            showSnackBar(("Unknown"), R.color.colorErrorRed);
        }
    }

    @Override
    public void showError(@StringRes int resId) {
        showError(getString(resId));
    }

    @Override
    public void showSnackBar(String message) {
        showSnackBar(message, R.color.colorWhite);
    }

    @Override
    public void showSnackBar(String message, @ColorRes int textColor) {
        Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content),
                message, Snackbar.LENGTH_SHORT);
        View sbView = snackbar.getView();
        TextView textView = (TextView) sbView
                .findViewById(R.id.snackbar_text);
        textView.setTextColor(ContextCompat.getColor(this, textColor));
        snackbar.show();
    }

    @Override
    public void init() {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showSnackBar(@StringRes int resId) {
        showSnackBar(getString(resId));
    }

    @Override
    public boolean isNetworkConnected() {
        return false;
    }

    public void hideKeyboard() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager)
                    getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    @Override
    public Context getActivityContext() {
        return getApplicationContext();
    }

    @Override
    public void onClick(View v) {

    }
}
