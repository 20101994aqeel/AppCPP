package com.qboxus.appcpp;

import android.app.Activity;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.qboxus.appcpp.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    // Used to load the 'appcpp' library on application startup.
    static {
        System.loadLibrary("appcpp");
    }

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Example of a call to a native method
        TextView tv = binding.statustxt;
        tv.setText(stringFromJNI());

        Button  login=binding.loginbtn;
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SharedPreferences sharedPreferences=getSharedPreferences("Myshared",MODE_PRIVATE);
                sharedPreferences.edit().putBoolean("login",true).commit();

                tv.setText("Login status "+sharedPreferences.getBoolean("login",false));

            }
        });


        Button  logoutbtn=binding.logoutbtn;
        logoutbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             //   setStoreBoolValues(MainActivity.this,false);

                SharedPreferences sharedPreferences=getSharedPreferences("Myshared",MODE_PRIVATE);
                tv.setText("Login status"+sharedPreferences.getBoolean("login",false));

            }
        });
    }

    /**
     * A native method that is implemented by the 'appcpp' native library,
     * which is packaged with this application.
     */
    public native String stringFromJNI();

   // public native void setStoreBoolValues(Activity activity, boolean flag);
}