package htb.com.childrenapp.Core.User;

import android.content.Context;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import htb.com.childrenapp.CAApplication;
import htb.com.childrenapp.Core.CoreResponse;
import htb.com.childrenapp.Core.IBaseCore;
import htb.com.childrenapp.Core.Port;
import htb.com.childrenapp.Framework.common.Utils;
import htb.com.childrenapp.Framework.http.CAHttpManager;
import htb.com.childrenapp.Framework.http.IProtocolEnt;

/**
 * Created by weidong_wu on 15/11/14.
 * 邮箱:wwdhao163@163.com
 */
public class UserCore implements IBaseCore {

    private UserInfo userInfo = null;

    private List<TeacherInfo> teacherList = new ArrayList<>();

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

    /**
     * 删除存储的userInfo
     * @param context
     * @return
     */
    public boolean DelUserInfoObj(Context context){
        boolean isRet = false;
        isRet = Utils.DelObjFile(context,UserInfoKey);
        return isRet;
    }

    /**
     * 添加老师的信息
     * @param teacherInfo
     */
    public void addTeacher(TeacherInfo teacherInfo){
        if (teacherInfo!=null)
            teacherList.add(teacherInfo);
    }

    /**
     * 移除一个老师信息
     * @param pos
     */
    public void removeTeacher(int pos){
        if(teacherList.size()<=0)
            return;

        if (teacherList.size()>pos){
            teacherList.remove(pos);
        }
    }

    /**
     * 清楚老师信息链表
     */
    public void clearTeacherInfos(){
        if (teacherList.size()>0)
            teacherList.clear();
    }

    /**
     * 返回教师列表
     * @return
     */
    public List<TeacherInfo> getTeacherList(){
        return  teacherList;
    }

    /**
     * @note 注销账号
     */
    public void LoginOut(){
        HashMap<String, String> ParmasMap = new HashMap<String, String>();
        ParmasMap.put("token", CAApplication.tokenId);
        IProtocolEnt logOutProtocol = new IProtocolEnt();
        logOutProtocol.setURL(Port.logOutUrl);
        logOutProtocol.setParamsMap(ParmasMap);
        CoreResponse logoutResponse = new CoreResponse();
        CAHttpManager.instance().sendPost(logOutProtocol,logoutResponse);
    }
}
