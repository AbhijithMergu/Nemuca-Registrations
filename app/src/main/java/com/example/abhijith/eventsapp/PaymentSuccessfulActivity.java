package com.example.abhijith.eventsapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.Button;

/**
 * Created by pavan on 2/27/2018.
 */

public class PaymentSuccessfulActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstance ){
        super.onCreate(savedInstance);
       // requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_payment_success);

        Button done = (Button) findViewById(R.id.end_button);
        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(PaymentSuccessfulActivity.this,MainActivity.class);
                startActivity(i);
            }
        });
    }
}
