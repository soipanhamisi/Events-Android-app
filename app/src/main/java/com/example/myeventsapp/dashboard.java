package com.example.myeventsapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class dashboard extends AppCompatActivity {

    RecyclerView eventsRecyclerView;
    ArrayList<events> list;
    DatabaseReference databaseReference;
    EventsAdapter adapter;

    TextView userNameText;
    Button addEventButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_dashboard);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        userNameText = findViewById(R.id.userNameText);
        addEventButton  = findViewById(R.id.addEventButton);

        eventsRecyclerView = findViewById(R.id.events_recycler_view);
        databaseReference = FirebaseDatabase.getInstance().getReference("Events");
        list = new ArrayList<>();
        eventsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new EventsAdapter(this, list);
        eventsRecyclerView.setAdapter(adapter);
        addEventButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent moveToAddEvent = new Intent(dashboard.this, AddEvent.class);
                startActivity(moveToAddEvent);
            }
        });
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    events event = dataSnapshot.getValue(events.class);
                    list.add(event);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(dashboard.this, "error...", Toast.LENGTH_SHORT).show();
            }
        });

    }
}