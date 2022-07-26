package com.example.registration;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {
    TextView titleTv, contentTv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        if(getSupportActionBar()!=null){
            getSupportActionBar().hide();
        }
        Intent intent = getIntent();
        String courseName = intent.getStringExtra("courseName");
        String courseDetails = intent.getStringExtra("content");
        String plainText = Html.fromHtml(courseDetails).toString();

        titleTv = findViewById(R.id.tagline_name);
        contentTv = findViewById(R.id.detail_tv);

        if(!courseName.isEmpty())
            Log.d("Mera wala text", courseName);
        titleTv.setText(courseName);
        contentTv.setText(plainText);
    }

}