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

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Created by nirbhay on 17/01/19.
 */
public class Login extends AppCompatActivity {


    LinearLayout phnetab,mailtab;
    EditText mobmail;
    TextView labledetails,mobilelable,maillable,txt,txt13,txt14;
    LinearLayout rectTab;
    Typeface ambleRegular;
    EditText mob,ps;
    SharedPreferences loginRememebr;
    SharedPreferences.Editor rem;
    LinearLayout nextbtnlogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        sharepref();
        ambleRegular = Typeface.createFromAsset(getAssets(),"Raleway_Regular.ttf");
        nextbtnlogin=(LinearLayout)findViewById(R.id.nextbtnlogin);
        nextbtnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new LoginAsy().execute();
            }
        });
        txt=(TextView)findViewById(R.id.txt);
        txt14=(TextView)findViewById(R.id.txt14);
        txt13=(TextView)findViewById(R.id.txt13);

        mob=(EditText)findViewById(R.id.mob);
        ps=(EditText)findViewById(R.id.ps);

        txt.setTypeface(ambleRegular);
        mob.setTypeface(ambleRegular);
        ps.setTypeface(ambleRegular);
        txt14.setTypeface(ambleRegular);
        txt13.setTypeface(ambleRegular);
    }

    public void sharepref()
    {
        loginRememebr=getSharedPreferences(
                String.format("%s_preferences", getPackageName()),
                Context.MODE_PRIVATE);
        rem=loginRememebr.edit();
    }
    class LoginAsy extends AsyncTask<String,String,String>
    {
        String res;
        ProgressDialog pdgetbal;
        String tbName;


        @Override
        protected void onPreExecute() {
            pdgetbal=new ProgressDialog(Login.this);
            pdgetbal.setMessage("Please wait...");
            pdgetbal.setCancelable(false);
            pdgetbal.show();
        }

        @Override
        protected String doInBackground(String... strings) {

            String url= AppController.baseURL+AppController.login+"mobile="+mob.getText().toString()+"&pas="+ps.getText().toString()+"";
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
                    rem.putString("flg","1");
                    rem.commit();
new GetDetails(mob.getText().toString()).execute();
                }
                else {
                    Toast.makeText(Login.this,""+message,Toast.LENGTH_LONG).show();
                }




            }catch (Exception e)
            {
                e.printStackTrace();
            }

        }
    }

    class GetDetails extends AsyncTask<String,String,String>
    {
        String res;
        ProgressDialog pdgetbal;
        String tbName;
        String mobNo;
        GetDetails(String mb)
        {
          mobNo= mb;
        }

        @Override
        protected void onPreExecute() {
            pdgetbal=new ProgressDialog(Login.this);
            pdgetbal.setMessage("Please wait...");
            pdgetbal.setCancelable(false);
            pdgetbal.show();
        }

        @Override
        protected String doInBackground(String... strings) {

            String url= AppController.baseURL+AppController.getDetails+"mobileno="+mobNo+"";
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
                    JSONArray arrObj=jsonObjectSub.getJSONArray("data");

                        JSONObject indexObj=arrObj.getJSONObject(0);
                            rem.putString("USER_ID",indexObj.getString("USER_ID"));
                            rem.putString("MOBILE_NO",indexObj.getString("MOBILE_NO"));
                            rem.putString("NAME",indexObj.getString("NAME"));
                            rem.putString("ADDRESS",indexObj.getString("ADDRESS"));
                            rem.putString("PASSWORD",indexObj.getString("PASSWORD"));
                            rem.putString("PROFILE_PIC",indexObj.getString("PROFILE_PIC"));

                            rem.putString("EMAIL",indexObj.getString("EMAIL"));
                            rem.commit();



                    startActivity(new Intent(Login.this,MainActivity.class));
                    finish();
                }
                else {
                    Toast.makeText(Login.this,""+message,Toast.LENGTH_LONG).show();
                }

            }catch (Exception e)
            {
                e.printStackTrace();
            }

        }
    }
}