package com.hongwei.kotlintest

import android.app.ProgressDialog
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.Toast


open class BaseActivity : AppCompatActivity(), View.OnClickListener {

    var progressBar : ProgressDialog? = null;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        progressBar = ProgressDialog(this, R.style.MyTheme)
        progressBar!!.setCancelable(false)
    }

    //** keybaord hide when touch empty eara in screen
    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {

        if (currentFocus != null){
            var imm : InputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager;
            imm.hideSoftInputFromWindow(currentFocus!!.windowToken, 0);
        }

        return super.dispatchTouchEvent(ev)
    }


    fun  showProgress() {
        if (progressBar!!.isShowing) return;
        progressBar!!.show();
    }

    fun closeProgress() {
        if (progressBar!!.isShowing){
            progressBar!!.dismiss()
        }
    }


    fun getString(edtText : EditText) : String {
        return  edtText.text.toString().trim()
    }

    override fun onClick(p0: View?) {
        TODO("Not yet implemented")
    }


}