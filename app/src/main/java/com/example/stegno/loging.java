package com.example.stegno;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class loging extends AppCompatActivity {

    Button login;
    TextView username, password;
    //EditText et_username, et_password, et_cpassword;
    //Button btn_register, btn_login;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loging);

        login=findViewById(R.id.logi);
        username=findViewById(R.id.username);
        password=findViewById(R.id.password);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(login.this,"logged in",Toast.LENGTH_LONG).show();
                Intent intent = new Intent(loging.this, e_d.class);
                startActivity(intent);
            }
        });


    }
}