package com.hongwei.kotlintest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.Toast
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Response

class MainActivity : BaseActivity() {
    lateinit var etxemail : EditText;
    lateinit var etxpassword: EditText;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        etxemail = findViewById(R.id.ext_email);
        etxpassword = findViewById(R.id.etx_password);
    }

    fun isValidated() : Boolean{
        if(etxemail.text.trim().length == 0){
            Toast.makeText(applicationContext,getString(R.string.enteremailaddress),Toast.LENGTH_SHORT).show()
            return false;
        }else if(etxpassword.text.toString().length == 0){
            Toast.makeText(applicationContext,getString(R.string.enterpassword),Toast.LENGTH_SHORT).show()
            return false;
        }
        return true;
    }

    fun trylogin(view: View) {
        if(isValidated()){
            callloginapi();
        }
    }

    private fun callloginapi() {
        showProgress()
        val apiInterface = ApiInterface.create().login(hashMapOf(
            "email" to getString(etxemail),
            "password" to getString(etxpassword)
        ))

        showProgress()
        apiInterface.enqueue(object  : retrofit2.Callback<ResponseBody> {
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                closeProgress()
                if(response.body() == null){
                    Toast.makeText(applicationContext,getString(R.string.somethingwentwrong),Toast.LENGTH_SHORT).show()
                    return;
                }
                val jsonRes = JSONObject(response.body()?.string())
                Log.d("responsebody==", jsonRes.toString())

            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                closeProgress()
                Log.d("error ===> " , "Login API Error")            }

        })

    }
}