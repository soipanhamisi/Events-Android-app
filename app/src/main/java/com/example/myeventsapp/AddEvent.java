package com.example.myeventsapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class AddEvent extends BaseActivity {
    private EditText etEventName;
    private EditText etEventDescription;
    private EditText etVenue;
    private EditText etDate;
    private EditText etTime;
    private Button btnSubmit;

    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_event);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        etEventName = findViewById(R.id.etEventName);
        etEventDescription = findViewById(R.id.etEventDescription);
        etVenue = findViewById(R.id.etVenue);
        etDate = findViewById(R.id.etDate);
        etTime = findViewById(R.id.etTime);
        btnSubmit = findViewById(R.id.btnSubmit);
        databaseReference = FirebaseDatabase.getInstance().getReference("Events");

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendDataToFirebase();
            }
        });
    }

    public void sendDataToFirebase(){
        String eventName = etEventName.getText().toString();
        String eventDescription = etEventDescription.getText().toString();
        String venue = etVenue.getText().toString();
        String date = etDate.getText().toString();
        String time = etTime.getText().toString();


        if (eventName.isEmpty() || eventDescription.isEmpty() || venue.isEmpty() || date.isEmpty() || time.isEmpty()){
            Toast.makeText(this, "Ensure all fields are filled", Toast.LENGTH_SHORT).show();
            return;
        }

        HashMap<String, Object> Event =new HashMap<>();
        Event.put("eventName", eventName);
        Event.put("eventDescription", eventDescription);
        Event.put("venue", venue);
        Event.put("date", date);
        Event.put("time", time);

        databaseReference.push().setValue(Event).addOnCompleteListener(task -> {
            if (task.isSuccessful()){
                Toast.makeText(this, "event published sucessfully", Toast.LENGTH_SHORT).show();
            }else {
                Toast.makeText(this, "Error in publishing event", Toast.LENGTH_SHORT).show();
            }
        });

    }
}