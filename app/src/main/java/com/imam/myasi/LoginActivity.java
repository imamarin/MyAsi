package com.imam.myasi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    private EditText email,password;
    private Button masuk;
    private String hasil;
    SharedPreferences session;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        masuk = findViewById(R.id.masuk);

        session = getSharedPreferences("sessionku", Context.MODE_PRIVATE);
        DbHelper dbHelper =new DbHelper(this);
        masuk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UserModel usr = new UserModel(null, null,
                        null, email.getText().toString(), password.getText().toString());
                hasil = dbHelper.findUser(usr);

                if(hasil=="0"){
                    Toast.makeText(getApplicationContext(),hasil,Toast.LENGTH_LONG).show();
                }else{
                    SharedPreferences.Editor editor = session.edit();
                    editor.putString("email", hasil);
                    editor.commit();
                    Intent in = new Intent(getApplicationContext(),MainActivity.class);
                    startActivity(in);
                }



            }
        });
    }
}