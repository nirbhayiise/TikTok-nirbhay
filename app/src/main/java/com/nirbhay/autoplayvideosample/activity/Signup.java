package com.nirbhay.autoplayvideosample.activity;

import android.content.Intent;
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
public class Signup extends AppCompatActivity {


    LinearLayout phnetab,mailtab;
    EditText mobmail;
    TextView labledetails,mobilelable,maillable;
    LinearLayout rectTab;
    Typeface ambleRegular;
    LinearLayout nextbtn;
    AppController obj;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);
        obj=(AppController)getApplicationContext();
        ambleRegular = Typeface.createFromAsset(getAssets(),"Raleway_Regular.ttf");
        labledetails=(TextView)findViewById(R.id.labledetails);
        mobilelable=(TextView)findViewById(R.id.mobilelable);
        maillable=(TextView)findViewById(R.id.maillable);
        nextbtn=(LinearLayout)findViewById(R.id.nextbtn);
        mobmail=(EditText)findViewById(R.id.mobmail);
        phnetab=(LinearLayout)findViewById(R.id.phnetab);
        rectTab=(LinearLayout)findViewById(R.id.rectTab);
        mailtab=(LinearLayout)findViewById(R.id.mailtab);
        mobmail.setHint("Phone number");
        phnetab.setBackgroundResource(R.drawable.singupradius);
        mobmail.setTypeface(ambleRegular);
        maillable.setTypeface(ambleRegular);
        mobmail.setTypeface(ambleRegular);
        mobilelable.setTypeface(ambleRegular);
        labledetails.setTypeface(ambleRegular);

        nextbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(Signup.this,OTP.class));
                obj.setMobileStr(mobmail.getText().toString());

            }
        });
        phnetab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mobmail.setHint("Phone number");
                rectTab.setBackgroundResource(R.drawable.rect);
                phnetab.setBackgroundResource(R.drawable.singupradius);
                rectTab.setBackgroundResource(R.drawable.rect);
                mailtab.setBackgroundResource(R.drawable.transparent_rect);
            }
        });
        mailtab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mobmail.setHint("Mail Id");
                rectTab.setBackgroundResource(R.drawable.rect);
                phnetab.setBackgroundResource(R.drawable.transparent_rect);

                rectTab.setBackgroundResource(R.drawable.rect);
                mailtab.setBackgroundResource(R.drawable.singupradius1);
            }
        });
    }
}