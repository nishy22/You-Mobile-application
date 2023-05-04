package com.example.you;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.unity3d.player.UnityPlayerActivity;

public class UnityHolderActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unity_holder);

        Intent intent = new Intent(UnityHolderActivity.this, UnityPlayerActivity.class);
        startActivity(intent);
    }
}