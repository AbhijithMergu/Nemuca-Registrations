package com.example.abhijith.eventsapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;

/**
 * Created by pavan on 2/27/2018.
 */

public class PaymentSuccessfulActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstance ){
        super.onCreate(savedInstance);
       // requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_payment_success);
    }
}
