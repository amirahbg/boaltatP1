package com.example.boaltatp1.view;

import android.content.Intent;
import android.os.Bundle;


import com.example.boaltatp1.R;
import com.example.boaltatp1.view.MainActivity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class AuthActivity extends AppCompatActivity
        implements OnStartMainActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);
    }

    @Override
    public void startMainActivity() {
        Intent intent = new Intent(AuthActivity.this,
                MainActivity.class);

        startActivity(intent);
        finish();
    }
}
