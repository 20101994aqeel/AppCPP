package com.qboxus.appcpp;

import android.app.Activity;

public class SdkInitNew {

    Activity context;
    public SdkInitNew (Activity context){
        this.context=context;
    }

    public void initialize() {
        setStoreBoolValues(context,false);

    }


    public native void setStoreBoolValues(Activity activity, boolean flag);
}
