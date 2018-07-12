package com.android.medipro.ui.activity.login;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.medipro.R;
import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    EditText etPhoneNumber;
    Button btnContinue;
    TextView tvTerms;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getView();
        etPhoneNumber.addTextChangedListener(PhoneNumberTextWatcher);

    onClick();
    }

        TextWatcher PhoneNumberTextWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String PhoneNumber = etPhoneNumber.getText().toString().trim();
                Log.e("length", "phone" + PhoneNumber.length());
                if (PhoneNumber.length() == 10) {
                    btnContinue.setEnabled(true);
                    btnContinue.setBackgroundColor(getResources().getColor(R.color.button_color_main));
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }

        };




    private void getView() {
        etPhoneNumber = (EditText) findViewById(R.id.et_phone_number);
        btnContinue=(Button)findViewById(R.id.btn_continue_main);
        tvTerms = (TextView)findViewById(R.id.tv_terms);
      }
private void onClick(){
    btnContinue.setOnClickListener(this);
    tvTerms.setOnClickListener(this);
}
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_continue_main:
               // generateOtp();
                Intent i = new Intent(MainActivity.this,MobileSignUpActivity.class);
                String phone = etPhoneNumber.getText().toString();
                Log.e("phone","number"+phone);
                i.putExtra("PhoneNumber",phone);
                startActivity(i);
                break;
            case R.id.tv_terms:
                Intent intent = new Intent (MainActivity.this,TermsAndConditionsActivity.class);
                startActivity(intent);
        }
    }


    // code for OTP generation
//    private void generateOtp() {
//        RequestQueue queue = Volley.newRequestQueue(this);
//        String url = "http://13.232.102.36/api/v1/user/otp/generate";
//
//        StringRequest sr = new StringRequest(Request.Method.POST, url,
//
//                new com.android.volley.Response.Listener<String>() {
//                    @Override
//                    public void onResponse(String s) {
//
//                        Log.e("response", s);
//
//                        try {
//
//                            JSONObject object = new JSONObject(s);
//                            Boolean success = object.getBoolean("success");
//                            String message = object.getString("message");
//
//                            if (success) {
//                                Log.e("success", String.valueOf(success));
//                            }
//
//                        } catch (Exception e) {
//                            e.printStackTrace();
//                            Log.e("Messages Frag", "" + e.toString());
//                        }
//                    }
//                },
//                new com.android.volley.Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//
//                        Log.e("callApplyCoupon", "error: " + error.toString());
//                    }
//                }) {
//            @SuppressLint("LongLogTag")
//            @Override
//            protected Map<String, String> getParams() {
//                Map<String, String> params = new HashMap<String, String>();
//                params.put("phone", etPhoneNumber.getText().toString());
//                return params;
//            }
//
//        };
//        queue.add(sr);

  //  }
    }




