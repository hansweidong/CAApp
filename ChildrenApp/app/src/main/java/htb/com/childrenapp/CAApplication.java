package htb.com.childrenapp;

import android.app.Application;

import java.util.LinkedList;
import java.util.List;

import htb.com.childrenapp.Base.BaseFragment;
import htb.com.childrenapp.Core.User.UserType;
import htb.com.childrenapp.Framework.http.CAHttpManager;

/**
 * Created by weidong_wu on 15/11/10.
 * 邮箱:wwdhao163@163.com
 */
public class CAApplication extends Application {
    public List<BaseFragment> mList = new LinkedList<BaseFragment>();
    public static String tokenId = "";
    public static UserType.UserTypeEnum m_currentRoleType = UserType.UserTypeEnum.DIRECTOR;
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

    // add Activity
    public void addActivity(BaseFragment activity) {
        mList.add(activity);
    }

    public void exit() {
        try {
            for (BaseFragment activity : mList) {
                if (activity != null)
                    activity.finish();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.exit(0);
        }
    }


    @Override
    public void onLowMemory() {
        super.onLowMemory();
        System.gc();
    }
}
