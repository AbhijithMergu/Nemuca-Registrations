package com.example.abhijith.eventsapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import at.markushi.ui.CircleButton;

public class PaymentConfirmationActivity extends AppCompatActivity implements AsyncResponse{

    List<String> events;
    List<String> eventsList;
    HashMap<Integer,String> eventValues;
    int QId;
    SendPostRequest asyncTask =new SendPostRequest(PaymentConfirmationActivity.this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_confirmation);
        ListView eventList = (ListView) findViewById(R.id.list_view);
        eventList.setDivider(null);
        populateEventValues();
        populateEventsList();
        asyncTask.delegate = this;
        Bundle bundle = getIntent().getExtras();
        final int[] checkValues = bundle.getIntArray("checkValues");
        int cost = bundle.getInt("cost");
        this.QId = bundle.getInt("QId");
        TextView confirmBill = (TextView) findViewById(R.id.confirm_bill);
        String bill = "Confirm ( Rs. "+String.valueOf(cost)+" )";
        confirmBill.setText(bill);
        populateEvents(checkValues);
//        events.add("CHALLENGICA");
//        events.add("TECHTRIX");
//        events.add("KNOCK OFF TOURNAMENT");
//        events.add("ALPATCHINO");
//        events.add("CRYPTOTHON");
//        events.add("CHALLENGICA");
//        events.add("TECHTRIX");
//        events.add("KNOCK OFF TOURNAMENT");
//        events.add("ALPATCHINO");
//        events.add("CRYPTOTHON"); events.add("CHALLENGICA");
//        events.add("TECHTRIX");
//        events.add("KNOCK OFF TOURNAMENT");
//        events.add("ALPATCHINO");
//        events.add("CRYPTOTHON"); events.add("CHALLENGICA");
//        events.add("TECHTRIX");
//        events.add("KNOCK OFF TOURNAMENT");
//        events.add("ALPATCHINO");
//        events.add("CRYPTOTHON");
        CustomListAdapter adapter = new CustomListAdapter(this,R.layout.list_confirm_item,events);
        eventList.setAdapter(adapter);
        CircleButton editButton = (CircleButton) findViewById(R.id.confirm_edit);
        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PaymentConfirmationActivity.super.onBackPressed();
            }
        });

        CircleButton confirmButton = (CircleButton) findViewById(R.id.confirm_confirm);
        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String paid = prepareString(checkValues);
                JSONObject url = new JSONObject();
                JSONObject values = new JSONObject();
                try{
                    url.put("url","http:/www.acumenit.in/andy/register/push");
                    values.put("paid",paid);
                    values.put("registered",paid);
                    values.put("QId",QId);
                }catch (JSONException e){

                }
                asyncTask.execute(url,values);
            }
        });
    }

    private void populateEventValues()
    {
        eventValues = new HashMap<>();
        eventValues.put(0,"CHL");
        eventValues.put(1,"BYC");
        eventValues.put(2,"ANW");
        eventValues.put(3,"ALP");
        eventValues.put(4,"TTX");
        eventValues.put(5,"AER");
        eventValues.put(6,"CRP");
        eventValues.put(7,"KOT");
        eventValues.put(8,"CRC");
        eventValues.put(9,"AWD");
        eventValues.put(10,"DXT");
    }
    private void populateEventsList(){
        eventsList = new ArrayList<>();
        eventsList.add("CHALLENGICA");
        eventsList.add("BEYCODE");
        eventsList.add("ANWESHA");
        eventsList.add("AL'PATCH'INO");
        eventsList.add("TECHTRIX");
        eventsList.add("AEROPLANE CHESS");
        eventsList.add("CRYPTOTHON");
        eventsList.add("KNOCK OFF TOURNAMENT");
        eventsList.add("CRIMINAL CASE");
        eventsList.add("A WALK IN THE DARK");
        eventsList.add("DEXTRA");
    }
    private void populateEvents(int[] checkValues){
        events = new ArrayList<>();
        for(int i=0;i<checkValues.length;i++)
        {
            if(checkValues[i]==2)
                events.add(eventsList.get(i));
        }
    }

    private String prepareString(int[] checkValues){
        String result = "";
        boolean flag = true;
        for(int i=0;i<checkValues.length;i++)
            if(checkValues[i]!=0)
                if(flag)
                {
                    result+=eventValues.get(i);
                    flag=false;
                }
                else
                    result+=","+eventValues.get(i);
        return result;
    }
    @Override
    public void processFinish(String result){
        if(result.equals("Success"))
        {
            Intent i = new Intent(PaymentConfirmationActivity.this,PaymentSuccessfulActivity.class);
            startActivity(i);
        }
    }

}
