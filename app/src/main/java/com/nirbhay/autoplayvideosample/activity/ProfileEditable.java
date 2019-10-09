package com.nirbhay.autoplayvideosample.activity;

import android.Manifest;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.nirbhay.autoplayvideosample.R;
import com.nirbhay.autoplayvideosample.activity.httpreq.CustomHttpClient;
import com.nirbhay.autoplayvideosample.interfaces.UploadImageInterface;
import com.nirbhay.autoplayvideosample.interfaces.UploadObject;
import com.squareup.picasso.Picasso;

import org.json.JSONObject;

import java.io.File;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import pub.devrel.easypermissions.EasyPermissions;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by nirbhay on 17/01/19.
 */
public class ProfileEditable extends AppCompatActivity implements EasyPermissions.PermissionCallbacks{

    // base url for img

    private static final String TAG = MainActivity.class.getSimpleName();
    private static final int REQUEST_GALLERY_CODE = 200;
    private static final int READ_REQUEST_CODE = 300;
    private static final String SERVER_PATH = "http://demo.lannettechnology.net";
    private Uri uri;
    TextView changebtn;

    Typeface ambleRegular;

    EditText mob,name,addre;
    SharedPreferences loginRememebr;
    SharedPreferences.Editor rem;
    LinearLayout nextbtnlogin;
    String fileNameStr="";
    CircleImageView profile_image;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.editable_profile);
        profile_image=(CircleImageView)findViewById(R.id.profile_image);
        ambleRegular = Typeface.createFromAsset(getAssets(),"Raleway_Regular.ttf");
        mob=(EditText)findViewById(R.id.mob);
        name=(EditText)findViewById(R.id.name);
        addre=(EditText)findViewById(R.id.addres);
        changebtn=(TextView)findViewById(R.id.changebtn);
        nextbtnlogin=(LinearLayout)findViewById(R.id.nextbtnlogin);
        name.setTypeface(ambleRegular);
        mob.setTypeface(ambleRegular);
        addre.setTypeface(ambleRegular);
        sharepref();
        changebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent openGalleryIntent = new Intent(Intent.ACTION_PICK);
                openGalleryIntent.setType("image/*");
                startActivityForResult(openGalleryIntent, REQUEST_GALLERY_CODE);
            }
        });
        nextbtnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new UpdatePro().execute();
            }
        });
        Picasso.with(ProfileEditable.this)
                .load(loginRememebr.getString("proPick",""))
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
        getDetaildFill();
    }
    public void getDetaildFill()
    {
        addre.setText(loginRememebr.getString("ADDRESS",""));
        name.setText(loginRememebr.getString("NAME",""));
        mob.setText(loginRememebr.getString("MOBILE_NO",""));
    }
    class UpdatePro extends AsyncTask<String,String,String>
    {
        String res;
        ProgressDialog pdgetbal;
        String tbName;


        @Override
        protected void onPreExecute() {
            pdgetbal=new ProgressDialog(ProfileEditable.this);
            pdgetbal.setMessage("Please wait...");
            pdgetbal.setCancelable(false);
            pdgetbal.show();
        }

        @Override
        protected String doInBackground(String... strings) {
//            mob=(EditText)findViewById(R.id.mob);
//            name=(EditText)findViewById(R.id.name);
//            addre=(EditText)findViewById(R.id.addres);
//            name.setTypeface(ambleRegular);
            String url= "http://13.59.173.64/school/tiktok/tiktokapis/updateProfile.php?name="+name.getText().toString()+"&address="+addre.getText().toString()+"&mobile="+mob.getText().toString()+"";
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
                    rem.putString("NAME",name.getText().toString());
                    rem.putString("ADDRESS",addre.getText().toString());
                    rem.commit();
                    startActivity(new Intent(ProfileEditable.this,MainActivity.class));
                    finish();
                }
                else {
                    Toast.makeText(ProfileEditable.this,""+message,Toast.LENGTH_LONG).show();
                }




            }catch (Exception e)
            {
                e.printStackTrace();
            }

        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, ProfileEditable.this);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == REQUEST_GALLERY_CODE && resultCode == Activity.RESULT_OK){
            uri = data.getData();
            if(EasyPermissions.hasPermissions(this, Manifest.permission.READ_EXTERNAL_STORAGE)) {
                String filePath = getRealPathFromURIPath(uri, ProfileEditable.this);
                File file = new File(filePath);
                Log.d(TAG, "Filename " + file.getName());
                fileNameStr=file.getName();
                //RequestBody mFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);
                RequestBody mFile = RequestBody.create(MediaType.parse("image/*"), file);
                MultipartBody.Part fileToUpload = MultipartBody.Part.createFormData("video", file.getName(), mFile);
                RequestBody filename = RequestBody.create(MediaType.parse("text/plain"), file.getName());
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(SERVER_PATH)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                UploadImageInterface uploadImage = retrofit.create(UploadImageInterface.class);
                Call<UploadObject> fileUpload = uploadImage.uploadFile(fileToUpload, filename);
                fileUpload.enqueue(new Callback<UploadObject>() {
                    @Override
                    public void onResponse(Call<UploadObject> call, Response<UploadObject> response) {
                        Toast.makeText(ProfileEditable.this, "Response " + response.raw().message(), Toast.LENGTH_LONG).show();
                        Toast.makeText(ProfileEditable.this, "Success " + response.body().getSuccess(), Toast.LENGTH_LONG).show();
                        new EntryImgName().execute();
                    }
                    @Override
                    public void onFailure(Call<UploadObject> call, Throwable t) {
                        Log.d(TAG, "Error " + t.getMessage());
                    }
                });
            }else{
                EasyPermissions.requestPermissions(this, getString(R.string.read_file), READ_REQUEST_CODE, Manifest.permission.READ_EXTERNAL_STORAGE);
            }
        }
    }
    private String getRealPathFromURIPath(Uri contentURI, Activity activity) {
        Cursor cursor = activity.getContentResolver().query(contentURI, null, null, null, null);
        if (cursor == null) {
            return contentURI.getPath();
        } else {
            cursor.moveToFirst();
            int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
            return cursor.getString(idx);
        }
    }
    @Override
    public void onPermissionsGranted(int requestCode, List<String> perms) {
        if(uri != null){
            String filePath = getRealPathFromURIPath(uri, ProfileEditable.this);
            File file = new File(filePath);
            RequestBody mFile = RequestBody.create(MediaType.parse("image/*"), file);
            MultipartBody.Part fileToUpload = MultipartBody.Part.createFormData("file", file.getName(), mFile);
            RequestBody filename = RequestBody.create(MediaType.parse("text/plain"), file.getName());
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(SERVER_PATH)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            UploadImageInterface uploadImage = retrofit.create(UploadImageInterface.class);
            Call<UploadObject> fileUpload = uploadImage.uploadFile(fileToUpload, filename);
            fileUpload.enqueue(new Callback<UploadObject>() {
                @Override
                public void onResponse(Call<UploadObject> call, Response<UploadObject> response) {
                    Toast.makeText(ProfileEditable.this, "Success " + response.message(), Toast.LENGTH_LONG).show();
                    Toast.makeText(ProfileEditable.this, "Success " + response.body().toString(), Toast.LENGTH_LONG).show();
                   new EntryImgName().execute();
                }
                @Override
                public void onFailure(Call<UploadObject> call, Throwable t) {
                    Log.d(TAG, "Error " + t.getMessage());
                }
            });
        }
    }
    @Override
    public void onPermissionsDenied(int requestCode, List<String> perms) {
        Log.d(TAG, "Permission has been denied");
    }


    class EntryImgName extends AsyncTask<String,String,String>
    {
        String res;
        ProgressDialog pdgetbal;
        String tbName;


        @Override
        protected void onPreExecute() {
            pdgetbal=new ProgressDialog(ProfileEditable.this);
            pdgetbal.setMessage("Please wait...");
            pdgetbal.setCancelable(false);
            pdgetbal.show();
        }

        @Override
        protected String doInBackground(String... strings) {
//            mob=(EditText)findViewById(R.id.mob);
//            name=(EditText)findViewById(R.id.name);
//            addre=(EditText)findViewById(R.id.addres);
//            name.setTypeface(ambleRegular);
            String mob=loginRememebr.getString("MOBILE_NO","0");
            String url= "http://13.59.173.64/school/tiktok/tiktokapis/fileuploadnameimg.php?fname="+fileNameStr+"&mobile="+mob+"";
            res= CustomHttpClient.urlincoding(url);
            return res;
        }

        @Override
        protected void onPostExecute(String s) {
            pdgetbal.dismiss();
            rem.putString("proPick",AppController.imgUrl+fileNameStr);
            rem.commit();
            Picasso.with(ProfileEditable.this)
                    .load(AppController.imgUrl+fileNameStr)
               /* .placeholder(R.drawable.swmlogo) //this is optional the image to display while the url image is downloading
                .error(R.drawable.swmlogo) */        //this is also optional if some error has occurred in downloading the image this image would be displayed
                    .into(profile_image);
                    startActivity(new Intent(ProfileEditable.this,MainActivity.class));
                    finish();

        }
    }
}