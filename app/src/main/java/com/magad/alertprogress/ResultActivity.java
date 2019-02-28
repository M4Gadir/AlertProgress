package com.magad.alertprogress;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {

    TextView tvnama, tvadress, tvage, tvqutoe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        tvnama = findViewById(R.id.tvname);
        tvage = findViewById(R.id.tvage);
        tvadress = findViewById(R.id.tvadress);
        tvqutoe = findViewById(R.id.tvquote);

        tvnama.setText(getIntent().getStringExtra("name"));
        tvadress.setText(getIntent().getStringExtra("adress"));
        tvqutoe.setText(getIntent().getStringExtra("quote"));
        tvage.setText(getIntent().getStringExtra("age"));
    }
}
