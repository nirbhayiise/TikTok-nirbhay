package com.nirbhay.autoplayvideosample.activity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nirbhay.autoplayvideosample.R;


/**
 * Created by nirbhay on 17/01/19.
 */
public class HalfVisibleActivity extends AppCompatActivity implements Animation.AnimationListener {


    Animation animMoveToTop;
    LinearLayout phnetab,mailtab;
    EditText mobmail;
    TextView labledetails,mobilelable,maillable;
    LinearLayout rectTab;
    Typeface ambleRegular;
    LinearLayout lay1;
    TextView singUpfrom,txt11,txt1,txtt111,txtt222;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.halfscreen);
        ambleRegular = Typeface.createFromAsset(getAssets(),"Raleway_Regular.ttf");
        animMoveToTop = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.move);
        animMoveToTop.setAnimationListener(this);
        lay1=(LinearLayout)findViewById(R.id.lay1);
        lay1.startAnimation(animMoveToTop);
        singUpfrom=(TextView)findViewById(R.id.singUpfrom);
        txt11=(TextView)findViewById(R.id.txt11);
        txtt222=(TextView)findViewById(R.id.txtt222);
        txtt111=(TextView)findViewById(R.id.txtt111);
        txt1=(TextView)findViewById(R.id.txt1);
        singUpfrom.setTypeface(ambleRegular);
        txt11.setTypeface(ambleRegular);
        txt1.setTypeface(ambleRegular);
        txtt222.setTypeface(ambleRegular);
        txtt111.setTypeface(ambleRegular);


        singUpfrom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HalfVisibleActivity.this,Signup.class));
                finish();
            }
        });

        txtt222.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HalfVisibleActivity.this,Login.class));
                finish();
            }
        });

    }

    @Override
    public void onAnimationStart(Animation animation) {

    }

    @Override
    public void onAnimationEnd(Animation animation) {

    }

    @Override
    public void onAnimationRepeat(Animation animation) {

    }
}