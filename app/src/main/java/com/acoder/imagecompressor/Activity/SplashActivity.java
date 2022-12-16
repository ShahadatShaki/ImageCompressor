package com.acoder.imagecompressor.Activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.acoder.imagecompressor.Base.BaseActivity;
import com.acoder.imagecompressor.R;
import com.acoder.imagecompressor.databinding.ActivitySplashBinding;

/**
 * Created by Shaki
 * 01685558803
 * shahadat.shaki03@gmail.com
 */

public class SplashActivity extends BaseActivity {

    ActivitySplashBinding binding;
    Context context;



    @Override
    protected int getLayoutResourceFile() {
        return R.layout.activity_splash;
    }

    @Override
    protected void initComponent() {
        context = this;

        binding =  getBinding();

        startActivity(new Intent(context, HomePage.class));
        finish();

    }


}
