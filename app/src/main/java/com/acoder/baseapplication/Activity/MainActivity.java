package com.acoder.baseapplication.Activity;

import android.content.Context;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.acoder.baseapplication.ModelClass.ModelResponseGET;
import com.acoder.baseapplication.R;
import com.acoder.baseapplication.Utility.ApiClient;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;

        getNearbyPlaces();
    }


    public void getNearbyPlaces() {


        Call<ModelResponseGET<Object>> call = ApiClient.getAPIInterface(context)
                .getReferral();
        call.enqueue(new Callback<ModelResponseGET<Object>>() {
            @Override
            public void onResponse(Call<ModelResponseGET<Object>> call,
                                   Response<ModelResponseGET<Object>> response) {


                if (response.code() > 199 && response.code() < 300) {

                } else {

                    try {
//                        Snackbar.make(b.mRecyclerView, "Sorry Something went wrong", 3000)
//                                .setAction("Try Again", new View.OnClickListener() {
//                                    @Override
//                                    public void onClick(View v) {
//                                        getNearbyPlaces();
//                                    }
//                                }).show();
                    } catch (Exception e) {
                        Toast.makeText(context, "Sorry Something went wrong", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<ModelResponseGET<Object>> call, Throwable t) {

                if (t.getMessage().contains("Unable to resolve host")) {
//                    Constants.showNoInternetSnackbar(context, b.searching);
                    return;
                }

                if (t.getMessage().contains("timed out")) {
                    getNearbyPlaces();
                } else {

                    if (t instanceof IOException) {
                        Toast.makeText(context, "Please check your internet connection", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(context, "Something went wrong, try again", Toast.LENGTH_SHORT).show();
                    }

                }


            }


        });

    }


}
