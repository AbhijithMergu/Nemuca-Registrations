package com.example.abhijith.eventsapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

/**
 * Created by pavan on 2/27/2018.
 */

public class RegistrationActivity extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_registration);
        Bundle bundle = getIntent().getExtras();

        TextView studentId = (TextView) findViewById(R.id.student_qr_id);

        studentId.setText(bundle.get("Result").toString());
        Button event1 = (Button) findViewById(R.id.event_1_button);
        Button event2 = (Button) findViewById(R.id.event_2_button);
        Button event3 = (Button) findViewById(R.id.event_3_button);
        Button event4 = (Button) findViewById(R.id.event_4_button);
        Button event5 = (Button) findViewById(R.id.event_5_button);
        Button event6 =(Button) findViewById(R.id.event_6_button);
        event1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

    }
    public void onClick(View v)
    {

        Intent i = new Intent(RegistrationActivity.this,PaymentSuccessfulActivity.class);
        startActivity(i);
    }

}