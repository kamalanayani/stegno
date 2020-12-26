package com.example.stegno;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class encrypt extends AppCompatActivity {

    Button choose,encode_button;
    ImageView original_image;
    EditText dataField,message,secret_key;
    DatabaseReference databaseReference;
    FirebaseDatabase db= FirebaseDatabase.getInstance();

    private static final int SELECT_PICTURE = 250;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_encrypt);

        choose = findViewById(R.id.choose);
        original_image = findViewById(R.id.img);
       // message = findViewById(R.id.message);
        dataField=findViewById(R.id.message);
        encode_button = findViewById(R.id.encode_button);
        secret_key = findViewById(R.id.key);
        //db = FirebaseDatabase.getInstance().getReference().child("data");

        //FirebaseDatabase db= FirebaseDatabase.getInstance();
        databaseReference = db.getReference("Data");

        checkAndRequestPermission();



        choose.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                ImageChooser();
            }

            private void ImageChooser() {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), SELECT_PICTURE);
            }
        });

        encode_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendData();
            }

            public void sendData() {
                String msg = message.getText().toString();
                String sk = secret_key.getText().toString();
                String id_m=databaseReference.push().getKey();
                String id_s=databaseReference.push().getKey();
                if(!TextUtils.isEmpty(msg)){
                    Data data_m = new Data(id_m,msg);
                    //Data data_s = new Data(id_s,sk);
                    databaseReference.child(id_m).setValue(data_m);
                    //databaseReference.child(id_s).setValue(data_s);

                    Toast.makeText(encrypt.this, "data inserted", Toast.LENGTH_LONG).show();

                }
                else{
                    Toast.makeText(encrypt.this, "please enter", Toast.LENGTH_LONG).show();

                }
                if(!TextUtils.isEmpty(sk)){
                    // Data data_m = new Data(id_m,msg);
                    Data data_s = new Data(id_s,sk);
                    //databaseReference.child(id_m).setValue(data_m);
                    databaseReference.child(id_s).setValue(data_s);

                    Toast.makeText(encrypt.this, "key inserted", Toast.LENGTH_LONG).show();

                }
                else{
                    Toast.makeText(encrypt.this, "please enter", Toast.LENGTH_LONG).show();

                }

            }
            /*private void sendData() {
                String msg = message.getText().toString();
                String sk = secret_key.getText().toString();

                if(!TextUtils.isEmpty(msg) && !TextUtils.isEmpty(sk)) {
                    String id=databaseReference.push().getKey();
                    //String id_s=databaseReference.push().getKey();
                    Data data=new Data(id,msg,sk);
                    databaseReference.child(id).setValue(data);
                    message.setText("");
                    secret_key.setText("");
                }
                else {
                    Toast.makeText(encrypt.this,"pls enter data",Toast.LENGTH_LONG).show();
                }
            }*/
        });

    }








    private void checkAndRequestPermission() {
        int permissionWriteStorage = ContextCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE);
        int ReadPermission = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE);
        List<String> listPermissionsNeeded = new ArrayList<>();
        if (ReadPermission != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.READ_EXTERNAL_STORAGE);
        }
        if (permissionWriteStorage != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
        }
        if (!listPermissionsNeeded.isEmpty()) {
            ActivityCompat.requestPermissions(this, listPermissionsNeeded.toArray(new String[0]), 1);
        }

    }
}











