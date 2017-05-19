package se.remit.ui.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import se.remit.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.all).setOnClickListener(d ->{});
    }
}
