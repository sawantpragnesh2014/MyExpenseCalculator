package com.example.myexpensecalculator

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.google.android.material.snackbar.Snackbar

open class BaseActivityK : AppCompatActivity(), BaseView, View.OnClickListener {

    override fun init() {
        
    }

    override fun showLoading() {
        
    }

    override fun hideLoading() {
        
    }

    override fun showSnackBar(resId: Int) {
        showSnackBar(getString(resId))
    }

    override fun showSnackBar(message: String?, color: Int) {
        val snackbar: Snackbar = Snackbar.make(findViewById(android.R.id.content), message as String, Snackbar.LENGTH_SHORT)
         val sbView : View = snackbar.view;
         val textView: TextView =  sbView
                .findViewById(R.id.snackbar_text)
        textView.setTextColor(ContextCompat.getColor(this, color))
        snackbar.show();
    }

    override fun showSnackBar(message: String?) {
        showSnackBar(message, R.color.colorWhite)
    }

    override fun showError(resId: Int) {
        showError(getString(resId))
    }

    override fun showError(message: String?) {
        showSnackBar(message?: "Unknown", R.color.colorErrorRed)
    }

    override fun isNetworkConnected(): Boolean {
        return false
    }

    override fun hideKeyboard() {
        val view = this.currentFocus
         val imm : InputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view?.windowToken, 0)
    }

    override fun getActivityContext(): Context {
        return applicationContext
    }

    override fun onClick(v: View?) {
        
    }
}