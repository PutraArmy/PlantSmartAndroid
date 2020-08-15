package com.example.plantsmart.Databases;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DatabaseInit {
    public FirebaseAuth mAuth;
    public FirebaseAuth.AuthStateListener mAuthListener;

    public FirebaseUser user;

    public FirebaseDatabase database;

    public DatabaseReference valueHumi;
    public DatabaseReference valueHumiLingkungan;
    public DatabaseReference valueTempLingkungan;
    public DatabaseReference siram;
    public DatabaseReference rincian;

    public DatabaseInit() {
        mAuth       = FirebaseAuth.getInstance();
        user        = mAuth.getCurrentUser();

        database    = FirebaseDatabase.getInstance();

        valueHumi   = database.getReference("98:F4:AB:DC:CF:F5/monitoring/tanah/humidity");

        valueHumiLingkungan = database.getReference("98:F4:AB:DC:CF:F5/monitoring/lingkungan/humidity");
        valueTempLingkungan = database.getReference("98:F4:AB:DC:CF:F5/monitoring/lingkungan/suhu");

        siram       = database.getReference("98:F4:AB:DC:CF:F5/controlling/pump");

        rincian     = database.getReference("98:F4:AB:DC:CF:F5/record");

    }
}
