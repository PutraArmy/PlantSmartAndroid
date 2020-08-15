package com.example.plantsmart.Databases;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class RincianDatabase {
    public DatabaseInit db = new DatabaseInit();
    public List<String> keys = new ArrayList<>();

    public interface DataStatus {
        void DataIsLoaded(List<String> keys);
    }

    public void getData(final RincianDatabase.DataStatus dataStatus) {
        db.rincian.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                keys.clear();
                for (final DataSnapshot ds : dataSnapshot.getChildren()) {
                    Log.d("TAG", String.valueOf(ds));
                    keys.add(ds.getKey());
                    dataStatus.DataIsLoaded(keys);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
