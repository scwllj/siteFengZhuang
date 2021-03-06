package sihuo.app.com.kuaiqian;

import android.app.Application;
import android.app.Notification;

import cn.jpush.android.api.BasicPushNotificationBuilder;
import cn.jpush.android.api.JPushInterface;


/**
 * Created by Administrator on 2017/11/14.
 */

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        BasicPushNotificationBuilder builder = new BasicPushNotificationBuilder(this);
        builder.statusBarDrawable = R.drawable.iconx;
        builder.notificationFlags = Notification.FLAG_AUTO_CANCEL;  //设置为自动消失
        builder.notificationDefaults = Notification.DEFAULT_SOUND | Notification.DEFAULT_VIBRATE | Notification.DEFAULT_LIGHTS;  // 设置为铃声与震动都要
        JPushInterface.setPushNotificationBuilder(1, builder);
        JPushInterface.setDebugMode(true);
        JPushInterface.init(this.getApplicationContext());
    }

}
