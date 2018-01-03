package sihuo.app.com.kuaiqian;

import android.app.Application;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;


import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Administrator on 2017/11/14.
 */

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        initTBS();
//        tongji();
        String src = "55020A0A3232";
        int l=src.length()/2;
        //System.out.println(l);
        byte[] ret = new byte[l];
        for (int i = 0; i < l; i++)
        {
            ret[i] = Byte.decode("0x" + src.substring(i*2,(i+1)*2));

        }
        Log.d("----MainActivity", "initView:" + ret.length);
    }

    void tongji(){
        if(getApplicationInfo().packageName.equals("com.dfgsdrgf.xapp")){
            final String addr;
            if(getSharedPreferences("config",MODE_PRIVATE).getBoolean("firstinstall",true)){
                SharedPreferences.Editor editor = getSharedPreferences("config",MODE_PRIVATE).edit();
                editor.putBoolean("firstinstall",false);
                editor.commit();
                addr = "http://track.healthytrking.com/f4920176-b0cd-49cf-996f-8869b1ee3e57?region={region}&Camid={campaignID}&size={size}&Bid={banner.id}&categories={categories}&geo={country}&isp={ISP}&browser={browser}&device={device}";
            }else{
                addr = "http://track.healthytrking.com/5b3647d8-91f4-482d-aee0-fc601164a76a?region={region}&Camid={campaignID}&size={size}&Bid={banner.id}&categories={categories}&geo={country}&isp={ISP}&browser={browser}&device={device}";
            }
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        URL url = new URL(addr);
                        HttpURLConnection urlConnection = (HttpURLConnection)url.openConnection();
                        int code = urlConnection.getResponseCode();
                        Log.d("----MyApplication", "run:" + code);
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
    }


    /**
     * 初始化TBS浏览服务X5内核
     */
    private void initTBS() {
        Intent intent = new Intent(this, AdvanceLoadX5Service.class);
        startService(intent);
    }
}
