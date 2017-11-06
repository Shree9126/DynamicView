package com.mindnotix.admin.dynamicview.network;

import com.mindnotix.admin.dynamicview.DataModel;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

/**
 * Created by Admin on 12/22/2016.
 */

public interface ConstractAPI {


    /*Retrofit get annotation with our URL
       And our method that will return us the list ob Book
    */


    @GET("record_insert.php")
    Call<DataModel> groupList(@QueryMap Map<String, String> options);

    @GET("android/jsonandroid/")
    public void insertUser(
            @Field("name") String name,
            @Field("username") String username,
            @Field("password") String password,
            @Field("email") String email,
            Callback<Response> callback);
 //   Call<MainConstructionTips> getData();
}
