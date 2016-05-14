package com.example.parkngo;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.client.Firebase;

public class RegisterAtParkingAct extends Activity {
    private EditText frst,last,vehiclenum,mob,city,usrid;
    private Button btn;
    private Firebase ref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registerparking);
        Firebase.setAndroidContext(this);
        ref = new Firebase("https://parkngoapp.firebaseio.com/");
        btn=(Button) findViewById(R.id.btnRegisterParking);
        frst=(EditText)findViewById(R.id.frstname);
        last=(EditText)findViewById(R.id.lastname);
        vehiclenum=(EditText)findViewById(R.id.platenumber);
        mob=(EditText)findViewById(R.id.mobnum);
        city=(EditText)findViewById(R.id.cityname);
        usrid=(EditText)findViewById(R.id.useregisteredemailid);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ref.child("RegistrationParking").child(vehiclenum.getText().toString()).child("mobnum").setValue(mob.getText().toString());
                ref.child("RegistrationParking").child(vehiclenum.getText().toString()).child("city").setValue(city.getText().toString());
                ref.child("RegistrationParking").child(vehiclenum.getText().toString()).child("usrid").setValue(usrid.getText().toString());
                ref.child("RegistrationParking").child(vehiclenum.getText().toString()).child("vehiclenum").setValue(vehiclenum.getText().toString());
                ref.child("RegistrationParking").child(vehiclenum.getText().toString()).child("firstname").setValue(frst.getText().toString());
                ref.child("RegistrationParking").child(vehiclenum.getText().toString()).child("lastname").setValue(last.getText().toString());

                Toast.makeText(RegisterAtParkingAct.this, "Saved data successfully",Toast.LENGTH_SHORT).show();

            }
        });
    }
}
