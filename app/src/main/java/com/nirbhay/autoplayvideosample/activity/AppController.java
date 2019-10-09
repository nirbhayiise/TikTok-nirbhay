package com.nirbhay.autoplayvideosample.activity;

import android.app.Application;


public class AppController extends Application {

  public static  String imgUrl="http://demo.lannettechnology.net/test/chatappdemo/tiktokimg/";
   public static String baseURL="http://13.59.173.64/school/tiktok/tiktokapis/";
   public static String register="register.php?";
   public static String login="login.php?";
   public static String getDetails="getDetails.php?";
   String mobileStr;
   String otp;
   String pass;

    public String getMobileStr() {
        return mobileStr;
    }

    public void setMobileStr(String mobileStr) {
        this.mobileStr = mobileStr;
    }

    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
}