package com.example.plantsmart;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.example.plantsmart.Databases.DatabaseInit;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity{

    DatabaseInit db = new DatabaseInit();
    GetDate getDate = new GetDate();

    private TextView tvValueHumi, tvTanggal, tvSelamat;
    private TextView tvValueHumiLink, tvValueTempLink;
    private ToggleButton tglSiram;
    private Button btnRincian;
    private ImageView btnSetting;

    private int hourNow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvValueHumi = findViewById(R.id.tv_value_humi);
        tvTanggal = findViewById(R.id.tv_tanggal);
        tvSelamat = findViewById(R.id.tv_selamat);

        tvValueHumiLink = findViewById(R.id.tv_value_humi_lingkungan);
        tvValueTempLink = findViewById(R.id.tv_value_temp_lingkungan);

        tglSiram = findViewById(R.id.tgl_siram);

        btnRincian = findViewById(R.id.btn_rincian);

        btnSetting = findViewById(R.id.btn_setting);

        tvTanggal.setText(getDate.getDateNow("E, dd MMMM yyyy"));
        hourNow = getDate.getTimeNow("HH");

        Log.d("Hour", String.valueOf(hourNow));

        if (hourNow >= 3 && hourNow <= 10) {
            tvSelamat.setText("Selamat Pagi");
        }
        if (hourNow >= 11 && hourNow <= 16) {
            tvSelamat.setText("Selamat Siang");
        }
        if (hourNow >= 17 && hourNow <= 3) {
            tvSelamat.setText("Selamat Malam");
        }

        db.siram.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                int value = dataSnapshot.getValue(int.class);
                if (value == 1) {
                    tglSiram.setChecked(true);
                } else {
                    tglSiram.setChecked(false);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        db.valueHumi.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Float value = dataSnapshot.getValue(Float.class);
                tvValueHumi.setText(String.valueOf(value) + "%");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        db.valueHumiLingkungan.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Float value = dataSnapshot.getValue(Float.class);
                tvValueHumiLink.setText(String.valueOf(value) + "%");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        db.valueTempLingkungan.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Float value = dataSnapshot.getValue(Float.class);
                tvValueTempLink.setText(String.valueOf(value) + "° C");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        db.valueHumi.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Float value = dataSnapshot.getValue(Float.class);
                tvValueHumi.setText(String.valueOf(value) + "%");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        tglSiram.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    db.siram.setValue(1);
                } else {
                    db.siram.setValue(0);
                }
            }
        });

        btnSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), SettingActivity.class);
                startActivity(intent);
            }
        });

        btnRincian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), RincianActivity.class);
                startActivity(intent);
            }
        });

    }

}