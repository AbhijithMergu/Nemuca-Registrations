package com.example.abhijith.eventsapp;


import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import at.markushi.ui.CircleButton;

public class MainActivity extends AppCompatActivity implements AsyncResponse {

    SendPostRequest asyncTask;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button b = (Button) findViewById(R.id.scan_button);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this,QRCodeScanActivity.class);
                startActivity(i);
            }
        });

        CircleButton goButton = (CircleButton) findViewById(R.id.go_button);
        goButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText qrCode = (EditText) findViewById(R.id.qrcode);
                String qr = qrCode.getText().toString();
//                Intent i = new Intent(MainActivity.this,RegistrationActivity.class);
//                i.putExtra("Result",qr);
                JSONObject url = new JSONObject();
                JSONObject values = new JSONObject();

                try {
                    url.put("url","http://www.acumenit.in/andy/register/fetch");
                    values.put("qId", qr);
                }catch (JSONException e)
                {

                }

                asyncTask =new SendPostRequest(MainActivity.this);
                asyncTask.delegate = MainActivity.this;
                asyncTask.execute(url,values);
//                i.putExtra("PAID","AWD,AER,ALP");
//                i.putExtra("REGISTERED","DXT,KOT,TTX");
//                startActivity(i);
            }
        });

    }
    @Override
    public void processFinish(String result){
        try{
            JSONArray arr = new JSONArray(result);
            Intent i = new Intent(MainActivity.this,RegistrationActivity.class);
            i.putExtra("Result",result);
            startActivity(i);
        }catch (JSONException e) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Error!");
            builder.setMessage(result);
            builder.setCancelable(false);
            builder.setNeutralButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    Intent intent = getIntent();
                    finish();
                    startActivity(intent);
                }
            });
            AlertDialog alertDialog = builder.create();
            alertDialog.show();
        }
    }
}
