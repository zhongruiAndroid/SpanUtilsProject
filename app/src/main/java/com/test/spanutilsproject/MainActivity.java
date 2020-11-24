package com.test.spanutilsproject;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private View btApi;
    private View btTest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btApi = findViewById(R.id.btApi);
        btApi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startAct(ApiActivity.class);
            }
        });

        btTest = findViewById(R.id.btTest);
        btTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startAct(TestActivity.class);
            }
        });
    }
    public void startAct(Class clazz){
        startActivity(new Intent(this,clazz));
    }
}
