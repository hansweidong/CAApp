package htb.com.childrenapp;

import android.app.Application;

import htb.com.childrenapp.Framework.http.CAHttpManager;

/**
 * Created by weidong_wu on 15/11/10.
 * 邮箱:wwdhao163@163.com
 */
public class CAApplication extends Application {

    public static String tokenId = "";

    public CAApplication(){}

    private static CAApplication caApp;

    public synchronized static CAApplication instance(){
        if (caApp==null){
            caApp = new CAApplication();
        }
        return caApp;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        caApp = this;
        CAHttpManager.instance().init(this);
    }


}
