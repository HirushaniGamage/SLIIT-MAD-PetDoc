package com.example.mad_pet_doctor;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.model.BookingModal;
import com.google.android.material.chip.Chip;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;



public class AppointmentDetailsActivity extends AppCompatActivity {

    private ImageView Hoslogo ;
    private TextView input1 , input2,  input3, input4 , input5, input6 , input7 , AnimalName, OwnerName , AppDate , AppTime , PayDetails , AppFee , ServiceCharge , TotalFee ;
    private Button confirmBtn;
    private Chip HomeChip1,HomeChip2;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private String BookingId;
    private BookingModal bookingModal;
    private ValueEventListener valueEventListener;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.appointmentdetails);
        Hoslogo = findViewById(R.id.hos_logo1);
        HomeChip1 = findViewById(R.id.chip5);
        HomeChip2 = findViewById(R.id.chip9);
        AnimalName = findViewById(R.id.app_label1);
        OwnerName = findViewById(R.id.app_label2);
        AppDate = findViewById(R.id.app_label3);
        AppTime = findViewById(R.id.app_label4);
        PayDetails = findViewById(R.id.app_label5);
        AppFee = findViewById(R.id.app_label6);
        ServiceCharge = findViewById(R.id.app_label7);
        TotalFee = findViewById(R.id.app_label8);
        input1 = findViewById(R.id.input_label1);
        input2 = findViewById(R.id.input_label2);
        input3 = findViewById(R.id.input_label3);
        input4 = findViewById(R.id.input_label5);
        input5 = findViewById(R.id.input_label6);
        input6 = findViewById(R.id.input_label7);
        input7 = findViewById(R.id.input_label);
        confirmBtn = findViewById(R.id.bton17);
        firebaseDatabase = FirebaseDatabase.getInstance();

        HomeChip1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AppointmentDetailsActivity.this, ActivityMainSideBar.class));
            }
        });
        HomeChip2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AppointmentDetailsActivity.this, ActivityMainSideBar.class));
            }
        });
        //retrieve
        bookingModal = getIntent().getParcelableExtra("Bookings");

        databaseReference = FirebaseDatabase.getInstance().getReference("Boookings");


            databaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {

                    for (DataSnapshot datasnapshot : snapshot.getChildren()) {

                        BookingModal bookingModal;
                        bookingModal = datasnapshot.getValue(BookingModal.class);

                        String petname = bookingModal.getAnimalName();
                        String ownername = bookingModal.getOwnerName();
                        String appdate = bookingModal.getAppointmentDate();
                        String apptime = bookingModal.getAppointmentTime();

                        input1.setText(petname);
                        input2.setText(ownername);
                        input3.setText(appdate);
                        input4.setText(apptime);
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Toast.makeText(AppointmentDetailsActivity.this, "Error occurs to booking" + error.toString(), Toast.LENGTH_SHORT).show();
                }
            });

        confirmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AppointmentDetailsActivity.this, CardPayActivity.class));

            }
        });

    }}

