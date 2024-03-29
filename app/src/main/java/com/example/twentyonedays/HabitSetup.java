package com.example.twentyonedays;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.icu.text.DateFormat;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.twentyonedays.ui.home.HomeFragment;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.Calendar;

public class HabitSetup extends AppCompatActivity {

    EditText habitName, habitDescription;
    ImageView uploadImage;
    Button makeHabitBtn;
    String Type, Frequency;
    String uid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_habit_setup);

        // loading in references to ui elements
        uid = getIntent().getStringExtra("uid");
        habitName = findViewById(R.id.habit_Name);
        habitDescription = findViewById(R.id.habit_description);
        makeHabitBtn = findViewById(R.id.create_habit_button);

        //region spinners
        Spinner typeSpinner = findViewById(R.id.habit_type_spinner);
        Spinner frequencySpinner = findViewById(R.id.habit_frequency_spinner);

        // Create an ArrayAdapter using the string array and a default spinner layout.
        ArrayAdapter<CharSequence> typeAdapter = ArrayAdapter.createFromResource(
                this,
                R.array.habit_types,
                android.R.layout.simple_spinner_item
        );

        // Specify the layout to use when the list of choices appears.
        typeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Apply the adapter to the spinner.
        typeSpinner.setAdapter(typeAdapter);

        ArrayAdapter<CharSequence> freqAdapter = ArrayAdapter.createFromResource(
                this,
                R.array.habit_frequency,
                android.R.layout.simple_spinner_item
        );

        freqAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        frequencySpinner.setAdapter(freqAdapter);
        //endregion

        makeHabitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveData();
            }
        });
    }

    public void saveData() {
        //collecting the data to be saved about a habit
        String type = ((Spinner) findViewById(R.id.habit_type_spinner)).getSelectedItem().toString();
        String frequency = ((Spinner) findViewById(R.id.habit_frequency_spinner)).getSelectedItem().toString();
        String name = habitName.getText().toString();
        String desc = habitDescription.getText().toString();
        String timestamp = DateFormat.getDateTimeInstance().format(Calendar.getInstance().getTime());
        HabitModel HabitData = new HabitModel(name, type, frequency,desc,timestamp);

        FirebaseDatabase.getInstance().getReference("users").child(uid).child("habits").push()
                .setValue(HabitData).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(HabitSetup.this, "Saved", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(HabitSetup.this, HomeFragment.class);
                            intent.putExtra("uid", uid);
                            startActivity(intent);

                            finish();
                            finish();
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(HabitSetup.this, e.getMessage().toString(), Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
