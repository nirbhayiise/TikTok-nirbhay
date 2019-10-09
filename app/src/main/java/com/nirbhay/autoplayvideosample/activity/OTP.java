package com.nirbhay.autoplayvideosample.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nirbhay.autoplayvideosample.R;

/**
 * Created by nirbhay on 17/01/19.
 */
public class OTP extends AppCompatActivity {



    LinearLayout nextbtnotp;
    Typeface ambleRegular;
    AppController obj;
    TextView txtl1,txtl2,txtl3;
    EditText otptxt;
    SharedPreferences loginRememebr;
    SharedPreferences.Editor rem;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.otp_confrm);
        sharepref();
        obj=(AppController)getApplicationContext();
        txtl1=(TextView)findViewById(R.id.txtl1);
        txtl2=(TextView)findViewById(R.id.txtl2);
        txtl3=(TextView)findViewById(R.id.txtl3);
        otptxt=(EditText)findViewById(R.id.otptxt);
        ambleRegular = Typeface.createFromAsset(getAssets(),"Raleway_Regular.ttf");
        nextbtnotp=(LinearLayout)findViewById(R.id.nextbtnotp);
        txtl1.setTypeface(ambleRegular);
        txtl2.setTypeface(ambleRegular);
        txtl3.setTypeface(ambleRegular);
        otptxt.setTypeface(ambleRegular);
        nextbtnotp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
obj.setOtp(otptxt.getText().toString());
                startActivity(new Intent(OTP.this,Password.class));
            }
        });
    }
    public void sharepref()
    {
        loginRememebr=getSharedPreferences(
                String.format("%s_preferences", getPackageName()),
                Context.MODE_PRIVATE);
        rem=loginRememebr.edit();
    }

}