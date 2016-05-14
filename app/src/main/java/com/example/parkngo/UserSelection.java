package com.example.parkngo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by Ghanendra on 13-May-16.
 */
public class UserSelection extends Activity {
    private Button btnExist,btnNew;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.userselection);

        btnExist=(Button) findViewById(R.id.btnGotoLogin);
        btnNew=(Button) findViewById(R.id.btnGotoSignup);

        btnNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("signup clicked");
                startActivity(new Intent(UserSelection.this, SignUpActivity.class));
            }
        });

        btnExist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("login clicked");
                startActivity(new Intent(UserSelection.this, LoginUser.class));
            }
        });
    }
}
