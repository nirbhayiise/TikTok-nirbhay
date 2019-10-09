package com.nirbhay.autoplayvideosample.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PagerSnapHelper;
import android.support.v7.widget.SnapHelper;
import android.view.View;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.nirbhay.autoplayvideosample.activity.httpreq.CustomHttpClient;
import com.nirbhay.autoplayvideosample.adapter.AutoPlayVideoAdapter;
import com.nirbhay.autoplayvideo.CustomRecyclerView;
import com.nirbhay.autoplayvideosample.model.VideoModel;
import com.nirbhay.autoplayvideosample.R;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
/**
 * Created by robert on 17/08/03.
 */
public class MainActivity extends AppCompatActivity {


    @BindView(R.id.rv_home)


    CustomRecyclerView recyclerView;
    Animation animMoveToTop;
    LinearLayout protabbottom;
    SharedPreferences loginRememebr;
    SharedPreferences.Editor rem;
    ImageView uploads;
    AutoPlayVideoAdapter mAdapter;
    private final List<VideoModel> modelList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sharepref();
new VideoGet().execute();
        protabbottom=(LinearLayout)findViewById(R.id.protabbottom);
        protabbottom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String flg=loginRememebr.getString("flg","0");
                if(flg.equals("1"))
                {
                    startActivity(new Intent(MainActivity.this,Profile.class));
                }
                else {
                    startActivity(new Intent(MainActivity.this, HalfVisibleActivity.class));
                }
            }
        });
        uploads=(ImageView)findViewById(R.id.uploads);
        uploads.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,UploadRecordingActivity.class));
            }
        });
        ButterKnife.bind(this);

        Picasso p = Picasso.with(this);

       /* modelList.add(new VideoModel("http://video.pp.cn/fs08/2017/02/23/10/aa74cfad-fca1-4aa4-9969-4a22d0d2b45b.mp4", "http://android-imgs.25pp.com/fs08/2017/02/23/2/48da103a3a21d8a1dea01570bc35de8e.jpg", "Video1"));
        modelList.add(new VideoModel("https://firebasestorage.googleapis.com/v0/b/flickering-heat-5334.appspot.com/o/demo1.mp4?alt=media&token=f6d82bb0-f61f-45bc-ab13-16970c7432c4", "http://res.cloudinary.com/krupen/video/upload/w_300,h_150,c_crop,q_70,so_0/v1481795681/2_rp0zyy.jpg", "Video1"));
        modelList.add(new VideoModel("http://res.cloudinary.com/krupen/video/upload/w_300,h_150,c_crop,q_70/v1491561340/hello_cuwgcb.mp4", "http://res.cloudinary.com/krupen/video/upload/w_300,h_150,c_crop,q_70,so_0/v1491561340/hello_cuwgcb.jpg", "Video2"));
        modelList.add(new VideoModel("http://res.cloudinary.com/krupen/image/upload/q_70/v1481795690/3_lfndfq.jpg", "Image3"));
        modelList.add(new VideoModel("http://res.cloudinary.com/krupen/video/upload/w_300,h_150,c_crop,q_70/v1481795675/3_yqeudi.mp4", "http://res.cloudinary.com/krupen/video/upload/w_300,h_150,c_crop,q_70,so_0/v1481795675/3_yqeudi.jpg", "Video4"));
        modelList.add(new VideoModel("http://res.cloudinary.com/krupen/video/upload/w_300,h_150,c_crop,q_70/v1481795675/1_pyn1fm.mp4", "http://res.cloudinary.com/krupen/video/upload/w_300,h_150,c_crop,q_70,so_0/v1481795675/1_pyn1fm.jpg", "Video5"));
        modelList.add(new VideoModel("http://res.cloudinary.com/krupen/video/upload/w_300,h_150,c_crop,q_70/v1491561340/hello_cuwgcb.mp4", "http://res.cloudinary.com/krupen/video/upload/w_300,h_150,c_crop,q_70,so_0/v1491561340/hello_cuwgcb.jpg", "Video6"));
        modelList.add(new VideoModel("http://res.cloudinary.com/krupen/image/upload/q_70/v1481795690/2_qwpgis.jpg", "Image7"));
        modelList.add(new VideoModel("http://res.cloudinary.com/krupen/image/upload/q_70/v1481795690/1_ybonak.jpg", "Image8"));
        modelList.add(new VideoModel("https://firebasestorage.googleapis.com/v0/b/flickering-heat-5334.appspot.com/o/demo1.mp4?alt=media&token=f6d82bb0-f61f-45bc-ab13-16970c7432c4", "http://res.cloudinary.com/krupen/video/upload/w_300,h_150,c_crop,q_70,so_0/v1481795681/2_rp0zyy.jpg", "Video9"));
        modelList.add(new VideoModel("http://res.cloudinary.com/krupen/image/upload/q_70/v1481795690/3_lfndfq.jpg", "Name10"));
        modelList.add(new VideoModel("http://res.cloudinary.com/krupen/video/upload/w_300,h_150,c_crop,q_70/v1481795676/4_nvnzry.mp4", "http://res.cloudinary.com/krupen/video/upload/w_300,h_150,c_crop,q_70,so_0/v1481795676/4_nvnzry.jpg", "Video11"));
        modelList.add(new VideoModel("https://firebasestorage.googleapis.com/v0/b/flickering-heat-5334.appspot.com/o/demo1.mp4?alt=media&token=f6d82bb0-f61f-45bc-ab13-16970c7432c4", "http://res.cloudinary.com/krupen/video/upload/w_300,h_150,c_crop,q_70,so_0/v1481795681/2_rp0zyy.jpg", "Video12"));
        modelList.add(new VideoModel("http://res.cloudinary.com/krupen/image/upload/q_70/v1481795690/3_lfndfq.jpg", "Image13"));
        modelList.add(new VideoModel("http://res.cloudinary.com/krupen/image/upload/q_70/v1481795690/2_qwpgis.jpg", "Image14"));
        modelList.add(new VideoModel("http://res.cloudinary.com/krupen/video/upload/w_300,h_150,c_crop,q_70/v1481795675/3_yqeudi.mp4", "http://res.cloudinary.com/krupen/video/upload/w_300,h_150,c_crop,q_70,so_0/v1481795675/3_yqeudi.jpg", "Video16"));
        modelList.add(new VideoModel("http://res.cloudinary.com/krupen/image/upload/q_70/v1481795690/1_ybonak.jpg", "Image15"));
        modelList.add(new VideoModel("http://res.cloudinary.com/krupen/video/upload/w_300,h_150,c_crop,q_70/v1481795675/1_pyn1fm.mp4", "http://res.cloudinary.com/krupen/video/upload/w_300,h_150,c_crop,q_70,so_0/v1481795675/1_pyn1fm.jpg", "Video17"));
        modelList.add(new VideoModel("https://firebasestorage.googleapis.com/v0/b/flickering-heat-5334.appspot.com/o/demo1.mp4?alt=media&token=f6d82bb0-f61f-45bc-ab13-16970c7432c4", "http://res.cloudinary.com/krupen/video/upload/w_300,h_150,c_crop,q_70,so_0/v1481795681/2_rp0zyy.jpg", "Video18"));
        modelList.add(new VideoModel("http://res.cloudinary.com/krupen/image/upload/q_70/v1481795690/2_qwpgis.jpg", "Image19"));
        modelList.add(new VideoModel("http://res.cloudinary.com/krupen/image/upload/q_70/v1481795690/3_lfndfq.jpg", "Image20"));
        modelList.add(new VideoModel("http://res.cloudinary.com/krupen/image/upload/q_70/v1481795690/1_ybonak.jpg", "Image21"));*/

//        modelList.add(new VideoModel("https://firebasestorage.googleapis.com/v0/b/flickering-heat-5334.appspot.com/o/demo1.mp4?alt=media&token=f6d82bb0-f61f-45bc-ab13-16970c7432c4", "http://res.cloudinary.com/krupen/video/upload/w_300,h_150,c_crop,q_70,so_0/v1481795681/2_rp0zyy.jpg", "video1"));
//        modelList.add(new VideoModel("http://res.cloudinary.com/krupen/video/upload/w_300,h_150,c_crop,q_70/v1491561340/hello_cuwgcb.mp4", "http://res.cloudinary.com/krupen/video/upload/w_300,h_150,c_crop,q_70,so_0/v1491561340/hello_cuwgcb.jpg", "video1"));
//        modelList.add(new VideoModel("http://demo.lannettechnology.net/test/chatappdemo/VID_20190125_005655917.mp4", "http://res.cloudinary.com/krupen/video/upload/w_300,h_150,c_crop,q_70,so_0/v1481795676/4_nvnzry.jpg", "video11"));
//        modelList.add(new VideoModel("https://firebasestorage.googleapis.com/v0/b/flickering-heat-5334.appspot.com/o/demo1.mp4?alt=media&token=f6d82bb0-f61f-45bc-ab13-16970c7432c4", "http://res.cloudinary.com/krupen/video/upload/w_300,h_150,c_crop,q_70,so_0/v1481795681/2_rp0zyy.jpg", "video1"));
//
//        modelList.add(new VideoModel("http://demo.lannettechnology.net/test/chatappdemo/VID_20190125_005655917.mp4", "http://res.cloudinary.com/krupen/video/upload/w_300,h_150,c_crop,q_70,so_0/v1481795676/4_nvnzry.jpg", "video11"));
//        modelList.add(new VideoModel("https://firebasestorage.googleapis.com/v0/b/flickering-heat-5334.appspot.com/o/demo1.mp4?alt=media&token=f6d82bb0-f61f-45bc-ab13-16970c7432c4", "http://res.cloudinary.com/krupen/video/upload/w_300,h_150,c_crop,q_70,so_0/v1481795681/2_rp0zyy.jpg", "video1"));
//        modelList.add(new VideoModel("http://res.cloudinary.com/krupen/video/upload/w_300,h_150,c_crop,q_70/v1491561340/hello_cuwgcb.mp4", "http://res.cloudinary.com/krupen/video/upload/w_300,h_150,c_crop,q_70,so_0/v1491561340/hello_cuwgcb.jpg", "video1"));
//        modelList.add(new VideoModel("http://res.cloudinary.com/krupen/video/upload/w_300,h_150,c_crop,q_70/v1481795676/4_nvnzry.mp4", "http://res.cloudinary.com/krupen/video/upload/w_300,h_150,c_crop,q_70,so_0/v1481795676/4_nvnzry.jpg", "video11"));
//        modelList.add(new VideoModel("https://firebasestorage.googleapis.com/v0/b/flickering-heat-5334.appspot.com/o/demo1.mp4?alt=media&token=f6d82bb0-f61f-45bc-ab13-16970c7432c4", "http://res.cloudinary.com/krupen/video/upload/w_300,h_150,c_crop,q_70,so_0/v1481795681/2_rp0zyy.jpg", "video1"));


        //you can pass local file uri, but make sure it exists
//        modelList.add(new VideoModel("/storage/emulated/0/VideoPlay/myvideo.mp4","http://res.cloudinary.com/krupen/video/upload/w_300,h_150,c_crop,q_70,so_0/v1481795681/2_rp0zyy.jpg","video18"));

        mAdapter= new AutoPlayVideoAdapter(modelList, p,MainActivity.this);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);

        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());


        SnapHelper snapHelper = new PagerSnapHelper();
        recyclerView.setLayoutManager(mLayoutManager);
        snapHelper.attachToRecyclerView(recyclerView);

        //todo before setAdapter
        recyclerView.setActivity(this);

        //optional - to play only first visible video
        recyclerView.setPlayOnlyFirstVideo(true); // false by default

        //optional - by default we check if url ends with ".mp4". If your urls do not end with mp4, you can set this param to false and implement your own check to see if video points to url
        recyclerView.setCheckForMp4(false); //true by default

        //optional - download videos to local storage (requires "android.permission.WRITE_EXTERNAL_STORAGE" in manifest or ask in runtime)
        recyclerView.setDownloadPath(Environment.getExternalStorageDirectory() + "/MyVideo"); // (Environment.getExternalStorageDirectory() + "/Video") by default

        recyclerView.setDownloadVideos(true); // false by default

        //extra - start downloading all videos in background before loading RecyclerView
        List<String> urls = new ArrayList<>();
        for (VideoModel object : modelList) {
            if (object.getVideo_url() != null && object.getVideo_url().contains("http"))
                urls.add(object.getVideo_url());
        }
        recyclerView.preDownload(urls);


        //call this function when u want to start autoplay on loading async lists (eg firebase)
//        recyclerView.playAvailableVideos(0);

    }

    @Override
    protected void onStop() {
        super.onStop();
        //add this code to pause videos (when app is minimised or paused)
        recyclerView.stopVideos();
    }

    @Override
    protected void onResume() {
        super.onResume();
        recyclerView.playAvailableVideos(0);
    }

    public void sharepref()
    {
        loginRememebr=getSharedPreferences(
                String.format("%s_preferences", getPackageName()),
                Context.MODE_PRIVATE);
        rem=loginRememebr.edit();
    }

    class VideoGet extends AsyncTask<String, String, String> {


        String mail, pass;
        // String ostype="ANDROID";
        // String token;
        ProgressDialog pd;
        String respo;

        @Override
        protected void onPreExecute() {
            pd = new ProgressDialog(MainActivity.this);
            pd.setMessage("Please wait...");
            pd.show();
        }

        @Override
        protected String doInBackground(String... arg0) {
            //String url=AppController.baseURL+AppController.loginUserURL+"user_email=" + userName + "&user_password=" + password + "&token="+regiToken+"";
            String url = "http://13.59.173.64/school/tiktok/tiktokapis/fetchAllVideo.php";
            try {
                respo = CustomHttpClient.urlincoding(url);
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            return respo;
        }

        @Override
        protected void onPostExecute(String result) {
            try {
                pd.dismiss();
                JSONObject jObje = new JSONObject(result);
                JSONObject namObj = jObje.getJSONObject("response");
                String st = namObj.getString("status");
                String st1 = namObj.getString("message");
                if (st.equals("1")) {
                    JSONArray jArr = namObj.getJSONArray("data");
                    for (int i = 0; i < jArr.length(); i++) {
                        JSONObject jIndexObj = jArr.getJSONObject(i);



                        String vdo = "http://demo.lannettechnology.net/test/chatappdemo/"+jIndexObj.getString("v_name");
                        modelList.add(new VideoModel(vdo, "http://demo.lannettechnology.net/test/chatappdemo/progressbg.png", "video1"));
                    }
                    recyclerView.setAdapter(mAdapter);
                }


                else{


                }

            }


            catch (JSONException e1) {


            }

        }
    }
}
