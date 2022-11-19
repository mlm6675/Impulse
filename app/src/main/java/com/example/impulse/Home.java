package com.example.impulse;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Home extends AppCompatActivity {

    private Button toDo;
    private Button dailyTasks;
    private Button incentives;
    private Button trackProductivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        toDo = findViewById(R.id.todo);
        dailyTasks = findViewById(R.id.tasks);
        incentives = findViewById(R.id.incentives);
        trackProductivity = findViewById(R.id.productivity);

        toDo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(Home.this, ToDo.class);
                Home.this.startActivity(myIntent);
            }
        });

        dailyTasks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(Home.this, DailyTasks.class);
                Home.this.startActivity(myIntent);
            }
        });
        incentives.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(Home.this, Incentives.class);
                Home.this.startActivity(myIntent);
            }
        });
        trackProductivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
}