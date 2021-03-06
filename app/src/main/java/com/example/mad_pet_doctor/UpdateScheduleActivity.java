package com.example.mad_pet_doctor;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.model.BookingModal;
import com.example.model.ScheduleModal;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class UpdateScheduleActivity extends AppCompatActivity {

    private ImageView hoslogo , schedulepic;
    private TextView schedHeading, Docname, Date, Time,Id;
    private EditText DocNameEdt, DateEdt, TimeEdt,IDEdt;
    private Button UpdateBtn, DeleteBtn;
    private FirebaseDatabase firebaseDatabase ;
    private DatabaseReference databaseReference;
    private String scheduleId;
    private ScheduleModal scheduleModal;
    private ArrayList<ScheduleModal> scheduleModalArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.updateschedule);
        hoslogo = findViewById(R.id.hos_logo1);
        schedulepic = findViewById(R.id.sched_pic);
        schedHeading = findViewById(R.id.sched_name);
        Id = findViewById(R.id.sched_label111);
        Docname = findViewById(R.id.sched_label2);
        Date = findViewById(R.id.sched_label3);
        Time = findViewById(R.id.sched_label4);
        IDEdt=findViewById(R.id.editTextTextPersonName4);
        DocNameEdt = findViewById(R.id.editTextTextPersonName6);
        DateEdt = findViewById(R.id.Date);
        TimeEdt = findViewById(R.id.Time);
        UpdateBtn = findViewById(R.id.bton7);
        firebaseDatabase = FirebaseDatabase.getInstance();
        scheduleModal = getIntent().getParcelableExtra("Schedule");
        if(scheduleModalArrayList!=null){
            IDEdt.setText(scheduleModal.getId());
            DocNameEdt.setText(scheduleModal.getDoctorName());
            DateEdt.setText(scheduleModal.getDate());
            TimeEdt.setText(scheduleModal.getTime());
            scheduleId = scheduleModal.getScheduleId();

        }

        databaseReference = FirebaseDatabase.getInstance().getReference("Schedules");
        UpdateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Id =IDEdt.getText().toString();
                String Docname =DocNameEdt.getText().toString();
                String date =DateEdt.getText().toString();
                String time =TimeEdt.getText().toString();



                Map<String , Object > map = new HashMap<>();
                    map.put("Id", Id);
                    map.put("DoctorName", Docname);
                    map.put("Date", date);
                    map.put("Time", time);

                databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull  DataSnapshot snapshot) {
                            databaseReference.updateChildren(map);
                        Toast.makeText(UpdateScheduleActivity.this, "Updated Successfully", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(UpdateScheduleActivity.this, ScheduleApprovalsActivity.class));
                    }

                    @Override
                    public void onCancelled(@NonNull  DatabaseError error) {
                        Toast.makeText(UpdateScheduleActivity.this, "Fail to update", Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });



    }


    }

