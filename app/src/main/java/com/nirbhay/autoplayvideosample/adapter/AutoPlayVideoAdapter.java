package com.nirbhay.autoplayvideosample.adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.support.v7.widget.AppCompatImageView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nirbhay.autoplayvideosample.model.VideoModel;
import com.nirbhay.autoplayvideosample.R;
import com.nirbhay.autoplayvideo.CustomViewHolder;
import com.nirbhay.autoplayvideo.VideosAdapter;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by robert on 17/08/03.
 */
public class AutoPlayVideoAdapter extends VideosAdapter {
    private String TAG = "AutoPlayVideoAdapter";
    SharedPreferences loginRememebr;
    SharedPreferences.Editor rem;
    Context cnt;
    private final List<VideoModel> list;
    private final Picasso picasso;

    public class MyViewHolder extends CustomViewHolder {
        final TextView tv, userName;
        final ImageView img_vol, img_playback;
        final AppCompatImageView userIcon;
       // CircleImageView profile_image1;
        //to mute/un-mute video (optional)
        boolean isMuted;
        public MyViewHolder(View x) {
            super(x);
            tv = ButterKnife.findById(x, R.id.tv);
            img_vol = ButterKnife.findById(x, R.id.img_vol);
            img_playback = ButterKnife.findById(x, R.id.img_playback);
            userIcon = ButterKnife.findById(x, R.id.fb_user_icon);
           // profile_image1 = ButterKnife.findById(x, R.id.profile_image1);
            userName = ButterKnife.findById(x, R.id.fb_user_name);
        }

        //override this method to get callback when video starts to play
        @Override
        public void videoStarted() {
            super.videoStarted();
            img_playback.setImageResource(R.drawable.ic_pause);
            if (isMuted) {
                muteVideo();
                img_vol.setImageResource(R.drawable.ic_mute);
            } else {
                unmuteVideo();
                img_vol.setImageResource(R.drawable.ic_unmute);
            }
        }
        @Override
        public void pauseVideo() {
            super.pauseVideo();
            img_playback.setImageResource(R.drawable.ic_play);
        }
    }
    public AutoPlayVideoAdapter(List<VideoModel> list_urls, Picasso p,Context ct) {
        this.list = list_urls;
        this.picasso = p;
        cnt=ct;
        loginRememebr=cnt.getSharedPreferences(
                String.format("%s_preferences", cnt.getPackageName()),
                Context.MODE_PRIVATE);
        rem=loginRememebr.edit();
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_card, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final CustomViewHolder holder, int position) {
        ((MyViewHolder) holder).userName.setText(list.get(position).getName());
        ((MyViewHolder) holder).tv.setText(String.format("%s・%s", (position + 5) + " minutes from now",list.get(position).getName().toUpperCase()));
        picasso.load(R.mipmap.ic_launcher).config(Bitmap.Config.RGB_565).into(((MyViewHolder) holder).userIcon);
       // profile_image1
        //todo
        holder.setImageUrl(list.get(position).getImage_url());
        holder.setVideoUrl(list.get(position).getVideo_url());
       /* Picasso.with(cnt)
                .load(loginRememebr.getString("proPick","https://t4.ftcdn.net/jpg/02/15/84/43/240_F_215844325_ttX9YiIIyeaR7Ne6EaLLjMAmy4GvPC69.jpg"))
               .placeholder(R.drawable.defaultimg) //this is optional the image to display while the url image is downloading
                .error(R.drawable.defaultimg)         //this is also optional if some error has occurred in downloading the image this image would be displayed
                .into(((MyViewHolder) holder).profile_image1);*/

        //load image into imageview
        if (list.get(position).getImage_url() != null && !list.get(position).getImage_url().isEmpty()) {
            picasso.load(holder.getImageUrl()).config(Bitmap.Config.RGB_565).into(holder.getImageView());

            Log.e(TAG, "--->ImageUrl=" + holder.getImageUrl());
        }

        holder.setLooping(true); //optional - true by default

        //to play pause videos manually (optional)
        ((MyViewHolder) holder).img_playback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (holder.isPlaying()) {
                    holder.pauseVideo();
                    holder.setPaused(true);
                } else {
                    holder.playVideo();
                    holder.setPaused(false);
                }
            }
        });

        //to mute/un-mute video (optional)
        ((MyViewHolder) holder).img_vol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (((MyViewHolder) holder).isMuted) {
                    holder.unmuteVideo();
                    ((MyViewHolder) holder).img_vol.setImageResource(R.drawable.ic_unmute);
                } else {
                    holder.muteVideo();
                    ((MyViewHolder) holder).img_vol.setImageResource(R.drawable.ic_mute);
                }
                ((MyViewHolder) holder).isMuted = !((MyViewHolder) holder).isMuted;
            }
        });

        if (list.get(position).getVideo_url() == null) {
            ((MyViewHolder) holder).img_vol.setVisibility(View.GONE);
            ((MyViewHolder) holder).img_playback.setVisibility(View.GONE);
        } else {
            ((MyViewHolder) holder).img_vol.setVisibility(View.VISIBLE);
            ((MyViewHolder) holder).img_playback.setVisibility(View.VISIBLE);
        }
    }


    @Override
    public int getItemCount() {
        return list.size();
    }


    @Override
    public int getItemViewType(int position) {
        return 0;
    }


}