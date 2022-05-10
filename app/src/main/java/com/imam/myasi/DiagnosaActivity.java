package com.imam.myasi;

import static android.content.ContentValues.TAG;
import static android.os.ParcelFileDescriptor.MODE_APPEND;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.google.android.material.navigation.NavigationBarView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.MenuItem;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.imam.myasi.databinding.ActivityDiagnosaBinding;

public class DiagnosaActivity extends AppCompatActivity{

    private AppBarConfiguration appBarConfiguration;
    private ActivityDiagnosaBinding binding;
    private SharedPreferences sh;

    NavController navController;
    @SuppressLint("WrongConstant")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//
//        BottomNavigationView bottomMenu = (BottomNavigationView) findViewById(R.id.bnv);



        sh = getSharedPreferences("sessionku", MODE_PRIVATE);
        Log.d(TAG, "onCreate: id "+sh.getString("_id", ""));
        if(sh.getString("_id", "") == "" ){
            Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
            startActivity(intent);
            finish();
        }else{
            binding = ActivityDiagnosaBinding.inflate(getLayoutInflater());
            setContentView(binding.getRoot());

            if(getIntent().hasExtra("listdiagnosa")){
//                Log.d(TAG, "onCreate: intent: "+getIntent().getExtras().getString("listdiagnosa"));

                getSupportFragmentManager().beginTransaction().add(R.id.nav_host_fragment_content_diagnosa, new ListDiagnosa() ).commit();

            }
            binding.bnv.setSelectedItemId(R.id.diagnosamenu);
            binding.bnv.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    switch (item.getItemId()) {
                        case R.id.profil:

                            getSupportFragmentManager().beginTransaction().add(R.id.nav_host_fragment_content_diagnosa, new SecondFragment() ).commit();
                            break;

                        case R.id.diagnosamenu:
                            getSupportFragmentManager().beginTransaction().add(R.id.nav_host_fragment_content_diagnosa, new FirstFragment()).commit();
                            break;

                        case R.id.artikel:
                            getSupportFragmentManager().beginTransaction().add(R.id.nav_host_fragment_content_diagnosa, new ThirdFragment()).commit();
                            break;
                    }

                    return true;
                }
            });
        }




    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_diagnosa);
        return NavigationUI.navigateUp(navController, appBarConfiguration)
                || super.onSupportNavigateUp();
    }


}