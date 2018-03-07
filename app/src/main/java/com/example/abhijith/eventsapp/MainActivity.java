package com.example.abhijith.eventsapp;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONObject;

import at.markushi.ui.CircleButton;

public class MainActivity extends AppCompatActivity implements AsyncResponse {

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
                TextView qrCode = (TextView) findViewById(R.id.qrcode);
                String qr = qrCode.getText().toString();
                Intent i = new Intent(MainActivity.this,RegistrationActivity.class);
                i.putExtra("Result",qr);
                i.putExtra("PAID","AWD,AER,ALP");
                i.putExtra("REGISTERED","DXT,KOT,TTX");
                startActivity(i);
            }
        });

    }
    @Override
    public void processFinish(JSONObject result){

    }
}
