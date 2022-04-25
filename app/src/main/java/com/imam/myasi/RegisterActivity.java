package com.imam.myasi;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.content.ContentValues;
import com.imam.myasi.QuizContract.*;
import android.content.Context;
import android.widget.TextView;

public class RegisterActivity extends AppCompatActivity {

    private EditText editNama, editKtp, editHp, editEmail, editPassword;
    private Button btnDaftar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        editNama = findViewById(R.id.nama);
        editKtp = findViewById(R.id.ktp);
        editHp =  findViewById(R.id.hp);
        editEmail = findViewById(R.id.email);
        editPassword = findViewById(R.id.password);
        btnDaftar = findViewById(R.id.daftar);

        DbHelper dbHelper = new DbHelper(this);

        btnDaftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UserModel usr = new UserModel(editNama.getText().toString(), editKtp.getText().toString(),
                        editHp.getText().toString(), editEmail.getText().toString(), editPassword.getText().toString());
                dbHelper.addUser(usr);
            }
        });
    }
}