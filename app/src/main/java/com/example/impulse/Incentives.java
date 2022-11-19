package com.example.impulse;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Incentives extends AppCompatActivity {

    Button addIncentive;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_incentives);

        addIncentive = findViewById(R.id.addIncentive);
        addIncentive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(Incentives.this, AddIncentive.class);
                Incentives.this.startActivity(myIntent);
            }
        });
    }
}