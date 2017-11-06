package com.mindnotix.admin.dynamicview;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

import static android.R.attr.id;

public class MainActivity extends AppCompatActivity {

    LinearLayout linearLayout;
    EditText name;
    int count = 1;
    EditText ed;
    Spinner spinner;

    public static String[] strings;
    Button btnClick, btnSub;
    List<EditText> allEds = new ArrayList<EditText>();
    List<Spinner> allSpnr = new ArrayList<Spinner>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        linearLayout = (LinearLayout) findViewById(R.id.linear);
  //      name = (EditText) findViewById(R.id.edtName);
     //   name.setVisibility(View.INVISIBLE);
        btnSub = (Button) findViewById(R.id.btnSub);
        btnClick = (Button) findViewById(R.id.btnCount);

        // int a=1;

        btnClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                callMethod(count);
                count++;
            }
        });

        btnSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getValue();
            }
        });
    }



    public void callMethod(int count) {
        ed = new EditText(this);
        allEds.add(ed);
        ed.setId(count);
        ed.setLayoutParams(new ActionBar.LayoutParams(ActionBar.LayoutParams.FILL_PARENT,
                ActionBar.LayoutParams.WRAP_CONTENT));
        ed.setText("Dynamic EditText!");
        ed.setMinLines(1);
        ed.setMaxLines(3);
        linearLayout.addView(ed);
        System.out.println("Edittext view ID=" + ed.getId());


        spinner = new Spinner(this);

        // Spinner Drop down elements
        List<String> categories = new ArrayList<String>();
        categories.add("Automobile");
        categories.add("Business Services");
        categories.add("Computers");
        categories.add("Education");
        categories.add("Personal");
        categories.add("Travel");

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinner.setAdapter(dataAdapter);
        allSpnr.add(spinner);

        //spinner.setId(count);
        spinner.setLayoutParams(new ActionBar.LayoutParams(ActionBar.LayoutParams.FILL_PARENT,
                ActionBar.LayoutParams.WRAP_CONTENT));
        linearLayout.addView(spinner);

    }

    public void getValue() {
        Intent intent = new Intent(MainActivity.this, NextActivity.class);
         strings = new String[allEds.size()];

        for(int i=0; i < allEds.size(); i++){
            strings[i] = allEds.get(i).getText().toString();
            System.out.println(""+strings[i]);
         //   intent.putExtra("add_"+i,strings[i]);
        }



        intent.putExtra("string-array", strings);
        startActivity(intent);
    }
}
