package com.android.medipro.ui.activity.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.android.medipro.R;

public class TermsAndConditionsActivity extends AppCompatActivity {
    ImageView ivBack;
    WebView webViewTerms;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tems_and_conditions);

        webViewTerms = (WebView)findViewById(R.id.wv_terms);
        webViewTerms.loadUrl("file:///android_asset/terms.html");

      ivBack=(ImageView)findViewById(R.id.iv_back);
      ivBack.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              Intent i = new Intent(TermsAndConditionsActivity.this,MainActivity.class);
              startActivity(i);
          }
      });

    }
}