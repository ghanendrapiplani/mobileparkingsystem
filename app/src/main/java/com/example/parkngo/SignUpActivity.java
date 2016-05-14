package com.example.parkngo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

import java.util.Map;

public class SignUpActivity extends Activity {
    private EditText editMob, editCity, editUser, editPass;
    private Button regBtn;
    private Firebase ref;
    private String em,pass,mob,city;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_pg);
        Firebase.setAndroidContext(this);

        editMob=(EditText) findViewById(R.id.editNum);
        editCity=(EditText) findViewById(R.id.editCity);
        editUser=(EditText) findViewById(R.id.editUsr);
        editPass=(EditText) findViewById(R.id.editPass);

        regBtn=(Button) findViewById(R.id.btnSignUP);
        ref = new Firebase("https://parkngoapp.firebaseio.com/");

        regBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                em=editUser.getText().toString();
                pass=editPass.getText().toString();
                city=editCity.getText().toString();
                mob=editMob.getText().toString();

                String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

                if (em.matches(emailPattern)&&pass.length()>=5)
                {
                    ref.createUser(em, pass, new Firebase.ValueResultHandler<Map<String, Object>>() {
                        @Override
                        public void onSuccess(Map<String, Object> result) {
                            System.out.println(result.get("uid").toString());
                            ref.child("user").child(mob).child("city").setValue(city);
                            ref.child("user").child(mob).child("password").setValue(pass);
                            ref.child("user").child(mob).child("mobile").setValue(mob);
                            System.out.println("Successfully created user account with uid: " + result.get("uid"));
                            Toast.makeText(SignUpActivity.this, "Successfully created account, now you can Log-in.", Toast.LENGTH_LONG).show();
                            startActivity(new Intent(SignUpActivity.this, LoginUser.class));
                            editPass.setText(" ");
                            editUser.setText(" ");
                            editCity.setText(" ");
                        }

                        @Override
                        public void onError(FirebaseError firebaseError) {
                            Toast.makeText(SignUpActivity.this, "Error creating user, please check the information filled above and try again.", Toast.LENGTH_LONG).show();
                            System.out.println("Error: " + firebaseError.toString());
                        }
                    });
                }
                else
                {
                    if(pass.length()<5)
                        Toast.makeText(getApplicationContext(), "Password too small, atleast 5 characters", Toast.LENGTH_SHORT).show();
                    else
                        Toast.makeText(getApplicationContext(), "Invalid Email Address", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}

