package com.example.parkngo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AboutUsAct extends Activity{
    private Button btnAb, btnHow;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aboutus);

        btnAb=(Button) findViewById(R.id.btnAboutUs2);
        btnHow=(Button) findViewById(R.id.btnHowWorks);

        btnAb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AboutUsAct.this, AboutUsDescription.class));
            }
        });

        btnHow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AboutUsAct.this, HowitWorks.class));
            }
        });
    }
}
