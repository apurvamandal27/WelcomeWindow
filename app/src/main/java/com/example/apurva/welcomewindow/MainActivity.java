package com.example.apurva.welcomewindow;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    EditText name,email;
    Button submit;
    Dialog custom_dialog;
    SharedPreferences pref;
    TextView info;
    SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        info=findViewById(R.id.tv_info);

        pref=getSharedPreferences("myfile", Context.MODE_PRIVATE);

        if (pref.getString("name_key",null)!=null && pref.getString("email_key",null)!=null){
            String a=pref.getString("name_key",null);
            String b=pref.getString("email_key",null);
            info.setText("Name: "+a+"\nEmail: "+b);
        }
        else {
            custom_dialog=new Dialog(this);
            custom_dialog.setCancelable(false);
            custom_dialog.setContentView(R.layout.custom_dialog);
            custom_dialog.show();
            name=custom_dialog.findViewById(R.id.et_name);
            email=custom_dialog.findViewById(R.id.et_email);
            submit=custom_dialog.findViewById(R.id.btn_submit);
            submit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (name.getText().toString().isEmpty() || email.getText().toString().isEmpty()){
                        AlertDialog.Builder ad=new AlertDialog.Builder(MainActivity.this);
                        ad.setMessage("Please  enter name and email first");
                        ad.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                        ad.show();
                    }
                    else {
                        editor=pref.edit();

                        String n=name.getText().toString();
                        String eml=email.getText().toString();

                        editor.putString("name_key",n);
                        editor.putString("email_key",eml);
                        editor.commit();

                        custom_dialog.dismiss();

                        info.setText("Name: "+n+"\nEmail: "+eml);

                    }
                }
            });
        }






    }
}
