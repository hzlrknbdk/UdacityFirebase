package com.hzlrknbdk.udacityfirebase;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class WritingLayout extends AppCompatActivity {

    Button write;
    Button read;
    TextView textView;
    FirebaseDatabase database;
    DatabaseReference myRef;


    public void writeNewUser(String userId, String name, String email) {

        User kullanici = new User(name, email);
        myRef.child("user").child(userId).setValue(kullanici);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_writing_layout);

        write = findViewById(R.id.write);
        read = findViewById(R.id.read);
        textView = findViewById(R.id.textView);
        database = FirebaseDatabase.getInstance();
        final DatabaseReference oku = FirebaseDatabase.getInstance().getReference().child("user").child("45236");
        myRef = database.getReference();

        write.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                writeNewUser("45236", "Hazal Ruken Budak", "hazalrukenbudak@gmail.com");
            }
        });

        read.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ValueEventListener listener = new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        User k = dataSnapshot.getValue(User.class);
                        textView.setText(k.username + " " + k.email);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                };

                oku.addValueEventListener(listener);
            }
        });
    }


}
