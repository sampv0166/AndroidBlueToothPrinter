package com.demo.ess;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;


public class Home extends AppCompatActivity {
    Button mButtonnewOrder;
     Button mButtonvieworders ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        mButtonnewOrder = (Button)findViewById(R.id.BTNneworders);
        mButtonnewOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent LoginIntent = new Intent(Home.this,AddData.class);
                startActivity(LoginIntent);
            }
        });

        mButtonnewOrder = (Button)findViewById(R.id.BTNvieworders);
        mButtonnewOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent LoginIntent = new Intent(Home.this,MainActivity.class);
                startActivity(LoginIntent);
            }
        });
    }
}
