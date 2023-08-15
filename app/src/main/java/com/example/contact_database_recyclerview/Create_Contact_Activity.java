package com.example.contact_database_recyclerview;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

public class Create_Contact_Activity extends AppCompatActivity {

    ImageView back,confirm,c_image;
    AppCompatEditText c_name,c_subname,c_number;
    Uri imguri;
    int id;
    String name,subname,number;
    MyDatabase myDatabase = new MyDatabase(Create_Contact_Activity.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_contact);
        back = findViewById(R.id.back);
        confirm = findViewById(R.id.confirm);
        c_image = findViewById(R.id.c_image);
        c_name = findViewById(R.id.c_name);
        c_subname = findViewById(R.id.c_subname);
        c_number = findViewById(R.id.c_number);

        id = getIntent().getIntExtra("id",0);
        name = getIntent().getStringExtra("name");
        subname = getIntent().getStringExtra("subname");
        number = getIntent().getStringExtra("number");
        //imguri = Uri.parse(getIntent().getStringExtra("imageuri"));
        Log.d("AAA", "onCreate: uri  "+imguri);

        c_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK,
                        MediaStore.Images.Media.INTERNAL_CONTENT_URI);
                startActivityForResult(intent,100);
            }
        });
        if (getIntent().getExtras() != null)
        {
            c_name.setText(""+name);
            c_subname.setText(""+subname);
            c_number.setText(""+number);
        }

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Create_Contact_Activity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (getIntent().getExtras() != null)
                {
                    String n1 =c_name.getText().toString();
                    String n2 = c_subname.getText().toString();
                    String n3 = c_number.getText().toString();
                    myDatabase.UpdateContact(id,n1,n2,n3,imguri);
                }
                else
                {
                    String n1 = c_name.getText().toString();
                    String n2 = c_subname.getText().toString();
                    String n3 = c_number.getText().toString();
                    myDatabase.AddContacts(""+n1,""+n2,""+n3,imguri);
                }
                Intent intent = new Intent(Create_Contact_Activity.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK)
        {
            if (requestCode == 100 && data != null) {
                imguri = data.getData();
                Log.d("TTT", "onActivityResult: ImgUri="+imguri);
                c_image.setImageURI(imguri);
            }
        }
    }
}