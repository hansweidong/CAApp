package htb.com.childrenapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;



import java.util.HashMap;

import htb.com.childrenapp.Core.CoreManager;
import htb.com.childrenapp.Core.Login.LoginResponse;
import htb.com.childrenapp.Core.Port;
import htb.com.childrenapp.Core.User.UserCore;
import htb.com.childrenapp.Core.User.UserInfo;
import htb.com.childrenapp.Framework.http.CAHttpManager;
import htb.com.childrenapp.Framework.http.IProtocolEnt;
import htb.com.childrenapp.UI.Login.ui_login;
import htb.com.childrenapp.UI.Main.ui_home;


public class splash extends Activity {

    private UserCore userCore;
    private UserInfo userInfo;
    private LoginResponse loginResponse = null;
    private boolean  isExitUser = false;

    private Context m_context;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Intent intent = new Intent();
            intent.setClass(m_context, ui_home.class);
            startActivity(intent);
            finish();
        }
    };

    private RunableHandler runableHandler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ui_splash);
        userCore = (UserCore) CoreManager.instance().getCore(UserCore.class);
        userInfo = (UserInfo)userCore.getUserInfoObj(this);
        if (userInfo == null)
            isExitUser = false;
        else
            isExitUser = true;
        m_context = this;

        runableHandler = new RunableHandler();

        handler.postDelayed(runableHandler,2000);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeCallbacks(runableHandler);
    }

    private class RunableHandler implements Runnable{
        @Override
        public void run() {
            if (!isExitUser){
                Intent intent = new Intent();
                intent.setClass(m_context,ui_login.class);
                startActivity(intent);
                finish();
            }else {
                sendLoginRequest(userInfo.getPhone(),userInfo.getPassword());
            }
        }
    }

    /**
     * 发送登陆请求
     * @param userName
     * @param password
     */
    private void sendLoginRequest(String userName, String password) {
        HashMap<String, String> ParmasMap = new HashMap<String, String>();
        ParmasMap.put("phone", userName);
        ParmasMap.put("password", password);
        IProtocolEnt loginProtocol = new IProtocolEnt();
        loginProtocol.setURL(Port.loginUrl);
        loginProtocol.setParamsMap(ParmasMap);
        loginResponse = new LoginResponse();
        loginResponse.setHandler(handler);
        CAHttpManager.instance().sendPost(loginProtocol, loginResponse);
    }

}
