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
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.nirbhay.autoplayvideosample.R;
import com.squareup.picasso.Picasso;

import org.json.JSONObject;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by nirbhay on 17/01/19.
 */
public class Profile extends AppCompatActivity {



    Typeface ambleRegular;

    Button editProBtn;
    TextView nameTxt;
    CircleImageView profile_image;
    SharedPreferences loginRememebr;
    SharedPreferences.Editor rem;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile);
        editProBtn=(Button)findViewById(R.id.editProBtn);
        nameTxt=(TextView)findViewById(R.id.nameTxt);
        profile_image=(CircleImageView)findViewById(R.id.profile_image);
        sharepref();
        ambleRegular = Typeface.createFromAsset(getAssets(),"Raleway_Regular.ttf");

        editProBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Profile.this,ProfileEditable.class));
                finish();
            }
        });
        Picasso.with(Profile.this)
                .load(loginRememebr.getString("proPick","https://t4.ftcdn.net/jpg/02/15/84/43/240_F_215844325_ttX9YiIIyeaR7Ne6EaLLjMAmy4GvPC69.jpg"))
                .placeholder(R.drawable.defaultimg) //this is optional the image to display while the url image is downloading
                .error(R.drawable.defaultimg)         //this is also optional if some error has occurred in downloading the image this image would be displayed
                .into(profile_image);

    }

    public void sharepref()
    {
        loginRememebr=getSharedPreferences(
                String.format("%s_preferences", getPackageName()),
                Context.MODE_PRIVATE);
        rem=loginRememebr.edit();
        nameTxt.setText(loginRememebr.getString("NAME",""));
    }
    class GEtWalletBal extends AsyncTask<String,String,String>
    {
        String res;
        ProgressDialog pdgetbal;
        String tbName;


        @Override
        protected void onPreExecute() {
            pdgetbal=new ProgressDialog(Profile.this);
            pdgetbal.setMessage("Please wait...");
            pdgetbal.setCancelable(false);
            pdgetbal.show();
        }

        @Override
        protected String doInBackground(String... strings) {

           // String url= AppController.baseURL+AppController.login+"mobile="+mob.getText().toString()+"&pas="+ps.getText().toString()+"";
          //  res= CustomHttpClient.urlincoding(url);
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
                    startActivity(new Intent(Profile.this,MainActivity.class));
                    finish();
                }
                else {
                    Toast.makeText(Profile.this,""+message,Toast.LENGTH_LONG).show();
                }




            }catch (Exception e)
            {
                e.printStackTrace();
            }

        }
    }
}