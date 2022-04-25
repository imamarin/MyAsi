package com.imam.myasi;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

import com.imam.myasi.databinding.ActivityDiagnosaBinding;

public class TestActivity extends AppCompatActivity {
    private ImageButton backTombol;
    private ActivityDiagnosaBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test2);

        backTombol = findViewById(R.id.backDiagnosa);

        if(getIntent().getStringExtra("tes").equals("bayi")){
            Log.d(TAG, "onCreate: bayi");
            getSupportFragmentManager().beginTransaction().add(R.id.fg_test_diagnosa, new BayiFragment()).commit();
        }else if(getIntent().getStringExtra("tes").equals("ibu")){
            Log.d(TAG, "onCreate: ibu");
            getSupportFragmentManager().beginTransaction().add(R.id.fg_test_diagnosa, new IbuFragment()).commit();
        }else if(getIntent().getStringExtra("tes").equals("cemas")){
            Log.d(TAG, "onCreate: cemas");
            getSupportFragmentManager().beginTransaction().add(R.id.fg_test_diagnosa, new CemasFragment()).commit();
        }



        backTombol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }
}