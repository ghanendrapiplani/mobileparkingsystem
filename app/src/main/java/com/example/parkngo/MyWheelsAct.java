package com.example.parkngo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextPaint;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.Firebase;

/**
 * Created by Ghanendra on 13-May-16.
 */
public class MyWheelsAct extends Activity{
    private Button btnSaveLoc, btnShareLoc;
    private SOSPreferences sh;
    private TextView tvLat,tvLong,shareloc;
    private Firebase ref;
    private EditText edit;
    private String locn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mywheels);
        Firebase.setAndroidContext(this);
        ref = new Firebase("https://parkngoapp.firebaseio.com/");
        sh = new SOSPreferences(getBaseContext());
        System.out.println(sh.Locatn());
        tvLat=(TextView) findViewById(R.id.setlat);
        tvLong=(TextView) findViewById(R.id.setlong);
        shareloc=(TextView) findViewById(R.id.latlong);
        tvLat.setText(sh.LocatnLat());
        tvLong.setText(sh.LocatnLong());
        shareloc.setText("maps.google.com/maps?q=loc:"+sh.LocatnLat()+","+sh.LocatnLong());
        edit=(EditText) findViewById(R.id.locname);
        btnSaveLoc=(Button) findViewById(R.id.saveloc);
        btnShareLoc=(Button) findViewById(R.id.shareloc);

        btnSaveLoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                locn=edit.getText().toString();
                if(locn.matches("")){
                    Toast.makeText(getApplicationContext(), "Please enter a location name", Toast.LENGTH_SHORT).show();
                }
                else{
                    ref.child("SavedLocation").child(locn).child("Latitude").setValue(sh.LocatnLat());
                    ref.child("SavedLocation").child(locn).child("Longitude").setValue(sh.LocatnLong());
                    Toast.makeText(getApplicationContext(), "Location Saved Successfully!", Toast.LENGTH_SHORT).show();
                }

            }
        });

        btnShareLoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, "maps.google.com/maps?q=loc:"+sh.LocatnLat()+","+sh.LocatnLong());
                sendIntent.setType("text/plain");
                startActivity(sendIntent);
            }
        });
    }
}
