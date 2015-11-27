package htb.com.childrenapp.Core.Login;

import android.os.Handler;
import android.os.Message;

import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;
import htb.com.childrenapp.Core.Port;
import htb.com.childrenapp.Framework.Json.JsonParser;

/**
 * Created by weidong_wu on 15/11/15.
 * 邮箱:wwdhao163@163.com
 */
public class RegisterResponse extends AsyncHttpResponseHandler {

    private Handler handler;
    private String userName;
    private String password;

    public RegisterResponse() {

    }

    public void setHandler(Handler handler) {
        this.handler = handler;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
        if (statusCode == Port.ResponseSuccessCode) {
            String msg = new String(responseBody);
            JSONObject jsonObject ;
            System.out.println("info:" + msg);
            try {
                jsonObject = JsonParser.instance().ParserString(msg);
                if (jsonObject == null) {
                    handler.sendEmptyMessage(-1);
                    return;
                }
                int code = jsonObject.getInt("code");
                if (code == Port.ResponseJsonCode_0) {
                    Message msgObj = new Message();
                    msgObj.what =Port.ResponseRegisterSuccessCode;
                    msgObj.obj = this;
                    handler.sendMessage(msgObj);
                }else
                    handler.sendEmptyMessage(code);

            } catch (JSONException e) {
                System.out.println(e.toString());
            }
        }
    }

    @Override
    public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
        handler.sendEmptyMessage(404);
    }
}
