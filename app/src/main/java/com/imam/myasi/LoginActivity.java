package com.imam.myasi;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    private EditText email,password;
    private Button masuk;
    private String hasil;
    private TextView daftar;
    SharedPreferences session;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        masuk = findViewById(R.id.masuk);
        daftar = findViewById(R.id.daftar);

        session = getSharedPreferences("sessionku", Context.MODE_PRIVATE);
        Log.d(TAG, "onCreate: id "+session.getString("_id", ""));
        DbHelper dbHelper =new DbHelper(this);
        masuk.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("Range")
            @Override
            public void onClick(View view) {
                UserModel usr = new UserModel(null, null,
                        null, email.getText().toString(), password.getText().toString());
                Cursor hasil = dbHelper.findUser(usr);

                if(hasil.getCount() <= 0){
                    Toast.makeText(getApplicationContext(),"Login Anda Gagal!",Toast.LENGTH_LONG).show();
                }else{
                    SharedPreferences.Editor editor = session.edit();
                    if (hasil.moveToNext()) {
                        editor.putString("email", hasil.getString(hasil.getColumnIndex("email")));
                        editor.putString("hp",hasil.getString(hasil.getColumnIndex("hp")));
                        editor.putString("nama",hasil.getString(hasil.getColumnIndex("nama")));
                        editor.putString("ktp",hasil.getString(hasil.getColumnIndex("ktp")));
                        editor.putString("_id",hasil.getString(hasil.getColumnIndex("_id")));
                        editor.commit();

                    }
                    hasil.close();

                    Intent in = new Intent(getApplicationContext(),DiagnosaActivity.class);
                    startActivity(in);
                    finish();
                }



            }
        });

        daftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(getApplicationContext(),RegisterActivity.class);
                startActivity(in);
            }
        });
    }
}