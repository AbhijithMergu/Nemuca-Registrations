package com.example.abhijith.eventsapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.HashMap;

import at.markushi.ui.CircleButton;

/**
 * Created by pavan on 2/27/2018.
 */

public class RegistrationActivity extends AppCompatActivity implements AsyncResponse{

    HashMap<String,Integer> eventValues;
    int checkValues[]; // 1 - Paid, 2 - Registered, 0 - Default
    GridLayout mainGrid;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events_list);
        eventValues = new HashMap<>();
        setEventValues();
        checkValues = new int[11];
        Bundle bundle = getIntent().getExtras();
        String paidList = bundle.getString("PAID");
        String registeredList = bundle.getString("REGISTERED");
        setCheckValues(paidList,registeredList);
        calculateBill();
        mainGrid = (GridLayout) findViewById(R.id.mainGrid);

        setBehaviours(mainGrid);
        setCardListeners(mainGrid);

        CircleButton payButton = (CircleButton) findViewById(R.id.pay_button);
        payButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(RegistrationActivity.this,PaymentSuccessfulActivity.class);
                startActivity(i);
            }
        });

    }
    private void setEventValues(){
        eventValues.put("CHL",0);
        eventValues.put("BYC",1);
        eventValues.put("ANW",2);
        eventValues.put("ALP",3);
        eventValues.put("TTX",4);
        eventValues.put("AER",5);
        eventValues.put("CRP",6);
        eventValues.put("KOT",7);
        eventValues.put("CRC",8);
        eventValues.put("AWD",9);
        eventValues.put("DXT",10);
    }
    private void setCheckValues(String paid, String registered){
        String paidArr[] = paid.split(",");
        String registeredArr[] = registered.split(",");

        for(String str : paidArr)
            checkValues[eventValues.get(str)]=1;
        for(String str : registeredArr)
            checkValues[eventValues.get(str)]=2;
    }

    private void setBehaviours(GridLayout mainGrid){
        for(int i=0;i<mainGrid.getChildCount();i++){

            CardView cardView = (CardView) mainGrid.getChildAt(i);
            if(checkValues[i]==1){
                cardView.setCardBackgroundColor(Color.parseColor("#696969"));
                cardView.setEnabled(false);
            }

            else if(checkValues[i]==2)
                cardView.setCardBackgroundColor(Color.parseColor("#008000"));
        }
    }
    private void setCardListeners(GridLayout mainGrid){
        for (int i = 0; i < mainGrid.getChildCount(); i++) {
            //You can see , all child item is CardView , so we just cast object to CardView
            final CardView cardView = (CardView) mainGrid.getChildAt(i);
            final int j = i;
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if(cardView.isEnabled()==true)
                        if (cardView.getCardBackgroundColor().getDefaultColor() == -1) {
                            //Change background color
                            //cardView.setEnabled(false);
                            cardView.setCardBackgroundColor(Color.parseColor("#008000"));
                            checkValues[j] = 2;
                           // Toast.makeText(RegistrationActivity.this, "State : True", Toast.LENGTH_SHORT).show();
                            calculateBill();
                        } else {
                            //Change background color
                            cardView.setCardBackgroundColor(Color.parseColor("#FFFFFF"));
                            checkValues[j] = 0;
                            calculateBill();
                           // Toast.makeText(RegistrationActivity.this, "State : False", Toast.LENGTH_SHORT).show();
                        }
                }
            });
        }
    }

    public void calculateBill(){
        int count =0;
        for(int i=0;i<11;i++)
            if(checkValues[i]==2)
                count++;

        TextView totalBill = (TextView) findViewById(R.id.total_bill);
        if(count==0)
            totalBill.setText("Bill : Rs. 0");
        else if(count<3)
        {
            String bill = String.valueOf(count*40);
            totalBill.setText("Bill : Rs. "+bill);
        }
        else if(count==3)
        {
            totalBill.setText("Bill : Rs. 100");
        }
        else if(count == 4)
        {
            totalBill.setText("Bill : Rs. 160");
        }
        else
        {
            totalBill.setText("Bill : Rs. 200");
        }
    }
    @Override
    public void processFinish(JSONObject result){

    }
    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Payment Not Processed!");
        builder.setMessage("\nAre you sure you want to exit?");
        builder.setCancelable(false);
        builder.setPositiveButton("Yes",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // String name = _listDataHeader.get(gPosition);
                        //_listDataHeader.set(gPosition,"Submitted");
                        RegistrationActivity.super.onBackPressed();
                    }
                });
        builder.setNegativeButton("No",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
        // super.onBackPressed();
    }


}