package com.example.parkngo;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

/**
 * Created by Ghanendra on 13-May-16.
 */
public class FindOwnerAct extends Activity {
    private Button btnShow,text;
    private EditText platenum;
    private TextView tv;
    private Firebase ref;
    private String str;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.findowner);
        Firebase.setAndroidContext(this);
        ref = new Firebase("https://parkngoapp.firebaseio.com/RegistrationParking/");

        btnShow=(Button) findViewById(R.id.btnShowOwnerDetails);
        text=(Button) findViewById(R.id.btnMsgowner);
        platenum=(EditText) findViewById(R.id.editPlateNum);
        tv=(TextView) findViewById(R.id.ownerDetails);

        btnShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                str=platenum.getText().toString();

                System.out.println("btn click string val="+str);
                ref.child(str).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot snapshot) {
                        if(snapshot.getValue()==null){
                            Toast.makeText(getApplicationContext(), "No matches found", Toast.LENGTH_SHORT).show();
                        }
                        else{
                            OwnerDets own = snapshot.getValue(OwnerDets.class);
                            tv.setText("first name"+own.getFirstname()+"\nlast name "+own.getLastname()+"\ncity"+own.getCity()+" "+
                                    own.getVehiclenum()+"\n mobile num"+own.getMobnum());
                            final String str=own.getMobnum().toString();
                            text.setVisibility(View.VISIBLE);
                            text.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Intent sendIntent = new Intent(Intent.ACTION_VIEW);
                                    sendIntent.setData(Uri.parse("sms:"+str));
                                    startActivity(sendIntent);
                                }
                            });
                        }

                    }
                    @Override
                    public void onCancelled(FirebaseError error) {
                    }
                });
            }
        });
    }
}
