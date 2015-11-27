package htb.com.childrenapp.Core.User;

import android.content.Context;

import htb.com.childrenapp.Core.IBaseCore;
import htb.com.childrenapp.Framework.common.Utils;

/**
 * Created by weidong_wu on 15/11/14.
 * 邮箱:wwdhao163@163.com
 */
public class UserCore implements IBaseCore {
    private UserInfo userInfo = null;

    public UserCore() {
    }

    public static final String UserInfoKey = "ObjectUser.bat";

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    /**
     * 存储userInfo对象信息
     *
     * @param context
     */
    public void saveUserInfo(Context context) {
        if (userInfo != null)
            Utils.saveObject(context, UserInfoKey, userInfo);
    }

    /**
     * 获得存储的userInfo
     * @param context
     * @return
     */
    public Object getUserInfoObj(Context context){
        Object object = Utils.getSaveObject(context,UserInfoKey);
        if (object == null)
            return  null;
        return object;
    }
}
