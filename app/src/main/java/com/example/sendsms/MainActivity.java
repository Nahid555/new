package com.example.sendsms;

import android.Manifest;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    EditText editText1,editText2;
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText1=findViewById(R.id.editText1);
        editText2=findViewById(R.id.editText2);
        button=findViewById(R.id.button);

        //depandance add and write this code for any permission
        Dexter.withActivity(this)
                .withPermissions(
                       // Manifest.permission.CAMERA,
                        Manifest.permission.READ_CONTACTS,
                      //  Manifest.permission.RECORD_AUDIO,
                        Manifest.permission.SEND_SMS//if you need permisson then .permision name
                ).withListener(new MultiplePermissionsListener() {
            @Override public void onPermissionsChecked(MultiplePermissionsReport report) {/* ... */}
            @Override public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions, PermissionToken token) {/* ... */}
        }).check();


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phn=editText1.getText().toString();
                String text=editText2.getText().toString();

              try {
                  SmsManager smgr = SmsManager.getDefault();
                  smgr.sendTextMessage(phn,null,text,null,null);
                  Toast.makeText(getApplicationContext(),"Sent",Toast.LENGTH_SHORT).show();
              }catch (Exception e){
                  Toast.makeText(getApplicationContext(),"Faild",Toast.LENGTH_SHORT).show();
              }
            }
        });
    }


}
