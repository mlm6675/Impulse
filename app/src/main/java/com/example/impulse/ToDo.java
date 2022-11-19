package com.example.impulse;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class ToDo extends AppCompatActivity {

    Button newTask;
    RecyclerView taskList;
    ArrayList<Task> tasks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to_do);

        newTask = findViewById(R.id.addToDo);
        taskList = findViewById(R.id.taskRecycler);


        tasks = new ArrayList<>();
        tasks.add(new Task("Finish French homework"));
        tasks.add(new Task("Clean up kitchen"));
        tasks.add(new Task("Call the eye doctor"));
        tasks.add(new Task("Schedule meeting with potential employer"));

        TaskAdapter adapter = new TaskAdapter(tasks);

        taskList.setAdapter(adapter);

        taskList.setLayoutManager(new LinearLayoutManager(this));

       /* newTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(ToDo.this, AddTask.class);
                ToDo.this.startActivity(myIntent);
            }
        });*/
    }
}