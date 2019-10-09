package com.nirbhay.autoplayvideosample.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.nirbhay.autoplayvideosample.R;
import com.nirbhay.autoplayvideosample.activity.httpreq.CustomHttpClient;

import org.json.JSONObject;

/**
 * Created by nirbhay on 17/01/19.
 */
public class Password extends AppCompatActivity {


    LinearLayout nextpasbtn;
    Typeface ambleRegular;
    SharedPreferences loginRememebr;
    SharedPreferences.Editor rem;
    AppController obj;
    EditText pas;
    TextView pastxt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.password);
        obj=(AppController)getApplicationContext();
        sharepref();
        pastxt=(TextView)findViewById(R.id.pastxt);
        pas=(EditText)findViewById(R.id.pas);
        ambleRegular = Typeface.createFromAsset(getAssets(),"Raleway_Regular.ttf");
        nextpasbtn=(LinearLayout)findViewById(R.id.nextpasbtn);
        pastxt.setTypeface(ambleRegular);
        pas.setTypeface(ambleRegular);
        nextpasbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                new GEtWalletBal().execute();
//                startActivity(new Intent(Password.this,MainActivity.class));
//                finish();
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

    class GEtWalletBal extends AsyncTask<String,String,String>
    {
        String res;
        ProgressDialog pdgetbal;
        String tbName;


        @Override
        protected void onPreExecute() {
            pdgetbal=new ProgressDialog(Password.this);
            pdgetbal.setMessage("Please wait...");
            pdgetbal.setCancelable(false);
            pdgetbal.show();
        }

        @Override
        protected String doInBackground(String... strings) {

            String url= AppController.baseURL+AppController.register+"mobile="+obj.getMobileStr()+"&pas="+pas.getText().toString()+"";
            res= CustomHttpClient.urlincoding(url);
            return res;
        }

        @Override
        protected void onPostExecute(String s) {
            pdgetbal.dismiss();
            try{
                // String key = BuildConfig.API_KEY;
                // Toast.makeText(StaupActivity.this,""+key,Toast.LENGTH_LONG).show();

                JSONObject jObjetc = new JSONObject(s);
                JSONObject jsonObjectSub=jObjetc.getJSONObject("response");
                String flg=jsonObjectSub.getString("status");
                String message=jsonObjectSub.getString("message");
               if(flg.equals("1"))
               {
                   Toast.makeText(Password.this,""+message,Toast.LENGTH_LONG).show();
                   startActivity(new Intent(Password.this,MainActivity.class));
                   finish();
               }
               else {
                   Toast.makeText(Password.this,""+message,Toast.LENGTH_LONG).show();
               }




            }catch (Exception e)
            {
                e.printStackTrace();
            }

        }
    }
}