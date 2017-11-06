package com.mindnotix.admin.dynamicview;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.mindnotix.admin.dynamicview.network.ApiClient;
import com.mindnotix.admin.dynamicview.network.ConstractAPI;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Admin on 12/23/2016.
 */

public class NextActivity extends AppCompatActivity implements View.OnClickListener{

    String [] stringArray;
    EditText email, name;
    Button sub;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.next_activity);

        email =(EditText)findViewById(R.id.editText);
        name =(EditText)findViewById(R.id.editText2);
        sub =(Button)findViewById(R.id.button);

        sub.setOnClickListener(this);
        Intent intent = getIntent();
       stringArray = intent.getStringArrayExtra("string-array");

    }

    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.button){

            Toast.makeText(getApplicationContext(),"hello",Toast.LENGTH_SHORT).show();
            String emailId = email.getText().toString();
            String names = name.getText().toString();
/*
            for (int i=1;i< =stringArray.length; i++) {
            }*/

            System.out.println("legnth ="+stringArray.length);
            int index = 0;

            Map<String, String> data = new HashMap<>();


            for (String aa:stringArray) {

                System.out.println(""+aa);
                System.out.println("Current index is: " + (index++));
                data.put("uname_"+index, aa);
            }
            data.put("emailID", emailId);
            data.put("mobile", names);

            sendData(data);

        }
    }

    public void sendData(Map<String,String> aa){


        for (Map.Entry<String, String> entry : aa.entrySet()) {
            String key = entry.getKey().toString();
            String value = entry.getValue();
            System.out.println("key, " + key + " value " + value);
        }

        ConstractAPI apiService =
                ApiClient.getClient().create(ConstractAPI.class);


        Call<DataModel> call = apiService.groupList(aa);


        call.enqueue(new Callback<DataModel>() {
            @Override
            public void onResponse(Call<DataModel> call, Response<DataModel> response) {
                String msg  =response.body().getMessage();
                Toast.makeText(getApplicationContext(),msg,Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<DataModel> call, Throwable t) {

                System.out.println(t.getMessage());
                Toast.makeText(getApplicationContext(),t.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });

    }
}
