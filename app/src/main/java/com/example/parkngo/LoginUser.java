package com.example.parkngo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.client.AuthData;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;


public class LoginUser extends Activity {
    private Button btnLoginUsr;
    private EditText loginid,loginpass;
    private String id,pass;
    private Firebase ref;
    SOSPreferences sh;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loginlayout);
        Firebase.setAndroidContext(this);
        sh = new SOSPreferences(getBaseContext());
        loginid=(EditText) findViewById(R.id.editUsrLoginEmail);
        loginpass=(EditText) findViewById(R.id.editUsrLoginPass);
        btnLoginUsr=(Button) findViewById(R.id.btnLogIN);
        ref = new Firebase("https://parkngoapp.firebaseio.com/");
        btnLoginUsr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ref.authWithPassword(loginid.getText().toString(), loginpass.getText().toString(), new Firebase.AuthResultHandler() {
                    @Override
                    public void onAuthenticated(AuthData authData) {
                        Intent openProfile = new Intent(LoginUser.this, DashBoard.class);
                        System.out.println("New App ID: " + authData.getUid() + ", Provider: " + authData.getProvider());
                        openProfile.putExtra("user_uid", authData.getUid());
                        sh.setUsrUid(authData.getUid());
                        startActivity(openProfile);
                    }

                    @Override
                    public void onAuthenticationError(FirebaseError firebaseError) {
                        Toast.makeText(getApplicationContext(), "Failed to login,, please check email and password, try again later.", Toast.LENGTH_LONG).show();
                    }
                });
            }
        });

    }
}
