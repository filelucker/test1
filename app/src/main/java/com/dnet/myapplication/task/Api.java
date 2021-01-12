package com.dnet.myapplication.task;

import com.dnet.myapplication.interfaces.ApiCall;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Api {
    private static Retrofit retrofit = null;

    public static ApiCall getClient() {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        // change your base URL
        if (retrofit==null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl("http://dev-kalaazar.dnet.org.bd/")
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();
        }
        //Creating object for our interface
        ApiCall api = retrofit.create(ApiCall.class);
        return api; // return the APIInterface object
    }
}
