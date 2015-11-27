package htb.com.childrenapp.Core.Login;

import android.os.Handler;

import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;
import htb.com.childrenapp.Core.CoreManager;
import htb.com.childrenapp.Core.Port;
import htb.com.childrenapp.Core.User.UserCore;
import htb.com.childrenapp.Core.User.UserInfo;
import htb.com.childrenapp.Framework.Json.JsonParser;

/**
 * Created by weidong_wu on 15/11/14.
 * 邮箱:wwdhao163@163.com
 */
public class LoginResponse extends AsyncHttpResponseHandler {

    private Handler handler;

    public LoginResponse() {

    }

    public void setHandler(Handler handler) {
        this.handler = handler;
    }

    @Override
    public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
        System.out.println("onSuccess:" + statusCode);
        String msg = new String(responseBody);
        JSONObject jsonObject = null;
        if (statusCode == Port.ResponseSuccessCode) {
            System.out.println("info:" + msg);
            try {
                jsonObject = JsonParser.instance().ParserString(msg);
                if (jsonObject == null) {
                    handler.sendEmptyMessage(-1);
                    return;
                }
                int code = jsonObject.getInt("code");
                if (code == Port.ResponseJsonCode_0) {
                    String data = jsonObject.getString("data");
                    JSONObject dataObj = JsonParser.instance().ParserString(data);
                    UserInfo userInfo = new UserInfo();
                    String phone = dataObj.getString("phone");
                    if (phone != null || phone.compareTo("") != 0)
                        userInfo.setPhone(phone);
                    String userName = dataObj.getString("username");
                    if (userName != null || userName.compareTo("") != 0)
                        userInfo.setUsername(userName);
                    int role = dataObj.getInt("role");
                    userInfo.setRole(role);
                    String avatar = dataObj.getString("avatar");
                    if (avatar != null || avatar.compareTo("") == 0)
                        userInfo.setAvatar(avatar);
                    String id = dataObj.getString("showId");
                    if (id != null || id.compareTo("") == 0)
                        userInfo.setId(id);
                    String token = dataObj.getString("token");
                    if (token != null || token.compareTo("") == 0)
                        userInfo.setToken(token);
                    UserCore userCore = (UserCore) CoreManager.instance().getCore(UserCore.class);
                    if (userCore == null) {
                        handler.sendEmptyMessage(-1);
                        return;
                    }
                    userCore.setUserInfo(userInfo);
                }
                handler.sendEmptyMessage(code);
            } catch (JSONException e) {
                System.out.println(e.toString());
            }
        }
    }

    @Override
    public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
        System.out.println("onFailure:" + statusCode);
        handler.sendEmptyMessage(404);
    }
}
