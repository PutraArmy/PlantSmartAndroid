package com.example.plantsmart;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.plantsmart.Adapters.RincianAdapter;
import com.example.plantsmart.Databases.DatabaseInit;
import com.example.plantsmart.Databases.RincianDatabase;
import com.example.plantsmart.Models.RincianModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class RincianActivity extends AppCompatActivity {

    public DatabaseInit db = new DatabaseInit();
    private Spinner spinner;

    public ArrayAdapter<String> adapter;
    public List<RincianModel> rincianModels = new ArrayList<>();

    private RecyclerView rvGrid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rincian);

        spinner = findViewById(R.id.spinner);
        rvGrid = findViewById(R.id.rincian_grid_recycler);

        new RincianDatabase().getData(new RincianDatabase.DataStatus() {
            @Override
            public void DataIsLoaded(List<String> keys) {
                adapter = new ArrayAdapter<String>(RincianActivity.this,
                        android.R.layout.simple_spinner_item, keys);

                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                spinner.setAdapter(adapter);
                spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        String date = String.valueOf(spinner.getSelectedItem());
                        db.rincian.child(date).addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                rincianModels.clear();
                                if (dataSnapshot.exists()){
                                    for (final DataSnapshot ds : dataSnapshot.getChildren()) {
                                        final RincianModel rincianModel = ds.getValue(RincianModel.class);
                                        rincianModels.add(rincianModel);
                                        Log.d("lihat", String.valueOf(rincianModel.getHumidityTanah()));
                                    }

                                    new RincianAdapter().setConfig(rvGrid, RincianActivity.this, rincianModels);
                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });
            }
        });
    }
}